package jp.co.nohana.albums.sample;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import jp.co.nohana.albums.BucketId;
import jp.co.nohana.albums.FixedBucketIdFactory;

/**
 * @author keishin.yokomaku
 * @since 2014/04/08
 */
public class AlbumAdapter extends CursorAdapter {
    public AlbumAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(R.layout.list_item_album, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView albumName = (TextView) view.findViewById(R.id.label_album_name);
        BucketId bucketId = BucketId.from(cursor);
        if (bucketId.equals(FixedBucketIdFactory.CAMERA.getBucketId())) {
            albumName.setText(FixedBucketIdFactory.CAMERA.getLocalizedDisplayName(context));
        } else if (bucketId.equals(FixedBucketIdFactory.DOWNLOAD.getBucketId())) {
            albumName.setText(FixedBucketIdFactory.DOWNLOAD.getLocalizedDisplayName(context));
        } else if (bucketId.equals(FixedBucketIdFactory.EDITED_ONLINE_PHOTO.getBucketId())) {
            albumName.setText(FixedBucketIdFactory.EDITED_ONLINE_PHOTO.getLocalizedDisplayName(context));
        } else if (bucketId.equals(FixedBucketIdFactory.IMPORTED_PHOTO.getBucketId())) {
            albumName.setText(FixedBucketIdFactory.IMPORTED_PHOTO.getLocalizedDisplayName(context));
        } else if (bucketId.equals(FixedBucketIdFactory.SNAP_SHOT.getBucketId())) {
            albumName.setText(FixedBucketIdFactory.SNAP_SHOT.getLocalizedDisplayName(context));
        } else {
            String name = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME));
            albumName.setText(name);
        }
    }
}
