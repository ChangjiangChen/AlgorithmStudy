package com.changjiang.sort.util;

public class Common {
    /**
     * 交换两个元素的值
     *
     * @param left
     * @param right
     */
    public static void swampValue(int left, int right) {
        left ^= right;
        right ^= left;
        left ^= right;
    }
}
