package jp.co.nohana.albums;

/**
 * Facade object factory of {@link jp.co.nohana.albums.BucketId}.
 * @author KeithYokoma
 * @since 1.0.0
 * @version 1.0.0
 */
@SuppressWarnings("unused") // public API
public enum FixedBucketIdFactory {
    CAMERA(BucketId.valueOf("DCIM/Camera")),
    DOWNLOAD(BucketId.valueOf("download")),
    EDITED_ONLINE_PHOTO(BucketId.valueOf("EditedOnlinePhotos")),
    IMPORTED_PHOTO(BucketId.valueOf("Imported")),
    SNAP_SHOT(BucketId.valueOf("Pictures/Screenshots"));

    private final BucketId mBucketId;

    private FixedBucketIdFactory(BucketId bucketId) {
        mBucketId = bucketId;
    }

    public BucketId getBucketId() {
        return mBucketId;
    }
}