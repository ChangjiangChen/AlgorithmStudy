package com.changjiang.sort.util;

public class Common {
    /**
     * 交换两个元素的值
     *
     * @param left
     * @param right
     */
    public static void swampValue(int[] array, int left, int right) {
        if (array == null || array.length < 2) {
            return;
        }

        if (left == right) {
            return;
        }

        if (array[left] == array[right]) {
            return;
        }

        array[left] ^= array[right];
        array[right] ^= array[left];
        array[left] ^= array[right];
    }
}
