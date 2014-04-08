package jp.co.nohana.albums;

import java.util.Locale;

/**
 * Utility class for dealing with device album.
 * Some method comes from AOSP Gallery app implementation.
 * @author KeithYokoma
 * @since 1.0.0
 * @version 1.0.0
 */
public final class AlbumUtils {
    private AlbumUtils() {
        throw new AssertionError();
    }

    /**
     * Calculate bucket id for the specified path.
     * @param imageFilePath the file path of the local storage.
     * @return an identifier of the album bucket.
     */
    public static int getBucketIdHash(String imageFilePath) {
        return imageFilePath.toLowerCase(Locale.US).hashCode();
    }
}
