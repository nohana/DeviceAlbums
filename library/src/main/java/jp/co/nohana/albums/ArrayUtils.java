package jp.co.nohana.albums;

import java.lang.reflect.Array;

/**
 * @author keishin.yokomaku
 * @since 2014/04/08
 */
final class ArrayUtils {
    private ArrayUtils() {
        throw new AssertionError();
    }

    public static String[] join(String[] left, String[] right) {
        if (left == null) {
            return clone(right);
        } else if (right == null) {
            return clone(left);
        }
        String[] joined = (String[]) Array.newInstance(String.class, left.length + right.length);
        System.arraycopy(left, 0, joined, 0, left.length);
        System.arraycopy(right, 0, joined, left.length, right.length);
        return joined;
    }

    public static String[] clone(String[] array) {
        if (array == null) {
            return null;
        }
        return array.clone();
    }
}
