package jp.co.nohana.albums;

import android.content.Context;
import android.database.MatrixCursor;

/**
 * @author keishin.yokomaku
 * @since 2014/04/08
 */
public class SimpleAlbumLoader extends AbstractAlbumLoader {
    public SimpleAlbumLoader(Context context, String[] projection) {
        super(context, projection);
    }

    @Override
    protected void onPrependDummy(MatrixCursor cursor) {
        // nothing to do
    }

    @Override
    protected void onAppendDummy(MatrixCursor cursor) {
        // nothing to do
    }
}
