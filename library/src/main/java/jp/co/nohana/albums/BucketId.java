package jp.co.nohana.albums;

import android.database.Cursor;
import android.os.Environment;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.MediaStore;

/**
 * Wrapper object of the bucket id that represents device album folder as identifiable.
 * @author KeithYokoma
 * @since 1.0.0
 * @version 1.0.0
 */
@SuppressWarnings("unused") // public APIs
public final class BucketId implements Parcelable {
    public static final Creator<BucketId> CREATOR = new Creator<BucketId>() {
        @Override
        public BucketId createFromParcel(Parcel source) {
            return new BucketId(source);
        }

        @Override
        public BucketId[] newArray(int size) {
            return new BucketId[size];
        }
    };
    private final int mHash;

    /* package */ BucketId(int hash) {
        mHash = hash;
    }

    /* package */ BucketId(Parcel source) {
        mHash = source.readInt();
    }

    public static BucketId valueOf(String dirName) {
        String filePath = Environment.getExternalStorageDirectory().toString() + "/" + dirName;
        return new BucketId(AlbumUtils.getBucketIdHash(filePath));
    }

    /**
     * Create a bucket id object from a {@link android.database.Cursor}.
     * This method is not responsible for managing cursor resource.
     * If the {@link android.database.Cursor} does not have a column of {@link android.provider.MediaStore.Images.Media#BUCKET_ID},
     * this will return null.
     * @param cursor to obtain bucket id.
     * @return the bucket id object, null if bucket id column does not exist.
     */
    public static BucketId from(Cursor cursor) {
        int index = cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_ID);
        if (index == -1) {
            return null;
        }
        int hash = cursor.getInt(index);
        return new BucketId(hash);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof BucketId) {
            BucketId e = (BucketId) o;
            return e.mHash == mHash;
        }
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return mHash;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mHash);
    }

    @Override
    public String toString() {
        return String.valueOf(mHash);
    }

    public int toInteger() {
        return mHash;
    }
}
