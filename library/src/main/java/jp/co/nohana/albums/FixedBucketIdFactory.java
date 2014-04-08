package jp.co.nohana.albums;

import android.content.Context;

/**
 * Facade object factory of {@link jp.co.nohana.albums.BucketId}.
 * @author KeithYokoma
 * @since 1.0.0
 * @version 1.0.0
 */
@SuppressWarnings("unused") // public API
public enum FixedBucketIdFactory {
    CAMERA(BucketId.valueOf("DCIM/Camera"), R.string.folder_camera),
    DOWNLOAD(BucketId.valueOf("download"), R.string.folder_download),
    EDITED_ONLINE_PHOTO(BucketId.valueOf("EditedOnlinePhotos"), R.string.folder_edited_online_photos),
    IMPORTED_PHOTO(BucketId.valueOf("Imported"), R.string.folder_imported),
    SNAP_SHOT(BucketId.valueOf("Pictures/Screenshots"), R.string.folder_screenshot);

    private final BucketId mBucketId;
    private final int mDisplayNameId;

    private FixedBucketIdFactory(BucketId bucketId, int displayNameId) {
        mBucketId = bucketId;
        mDisplayNameId = displayNameId;
    }

    public BucketId getBucketId() {
        return mBucketId;
    }

    public String getLocalizedDisplayName(Context context) {
        return context.getString(mDisplayNameId);
    }
}