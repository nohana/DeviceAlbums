package jp.co.nohana.albums;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.MergeCursor;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;

/**
 * Album bucket loader.
 * @author KeithYokoma
 * @since 1.0.0
 * @version 1.0.0
 */
@SuppressWarnings("unused") // public APIs
public abstract class AbstractAlbumLoader extends CursorLoader {
    /*
     * Note: The following hacky SQL injection comes from AOSP Gallery app impl.
     *
     * // We want to order the albums by reverse chronological order. We abuse the
     * // "WHERE" parameter to insert a "GROUP BY" clause into the SQL statement.
     * // The template for "WHERE" parameter is like:
     * //    SELECT ... FROM ... WHERE (%s)
     * // and we make it look like:
     * //    SELECT ... FROM ... WHERE (1) GROUP BY 1,(2)
     * // The "(1)" means true. The "1,(2)" means the first two columns specified after SELECT.
     * // Note that because there is a ")" in the template, we use "(2" to match it. */
    private static final String BUCKET_GROUP_BY = "1) GROUP BY 1,(2";
    private static final String BUCKET_ORDER_BY = "MAX(datetaken) DESC";
    private static final String[] REQUIRED_PROJECTION = {
            MediaStore.Images.Media.BUCKET_ID,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Images.Media._ID
    };

    /**
     *
     * @param context the loader context.
     * @param projection the projection of the result cursor.
     */
    public AbstractAlbumLoader(Context context, String[] projection) {
        super(context, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, ArrayUtils.join(REQUIRED_PROJECTION, projection), BUCKET_GROUP_BY, null, BUCKET_ORDER_BY);
    }

    @Override
    public Cursor loadInBackground() {
        Cursor cursor = super.loadInBackground();
        MatrixCursor prepend = new MatrixCursor(getProjection());
        onPrependDummy(prepend);
        MatrixCursor append = new MatrixCursor(getProjection());
        onAppendDummy(append);
        return new MergeCursor(new Cursor[]{ prepend, cursor, append });
    }

    /**
     * Hook point for adding dummy rows with {@link android.database.MatrixCursor} on the head of the load result.
     * @param cursor to insert dummy rows.
     */
    protected abstract void onPrependDummy(MatrixCursor cursor);

    /**
     * Hook point for adding dummy rows with {@link android.database.MatrixCursor} on the tail of the load result.
     * @param cursor to insert dummy rows.
     */
    protected abstract void onAppendDummy(MatrixCursor cursor);
}
