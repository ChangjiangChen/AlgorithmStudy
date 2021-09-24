package com.changjiang.sort.insert;

import com.changjiang.sort.util.Common;

import java.util.Arrays;

public class InsertionSort {

    /**
     * 从第一个元素开始，与前面的元素进行比较
     * 如果当前元素大于上一个元素，那么比较停止，进入下一轮
     * 如果当前元素小于上一个元素，那么交换该元素与上一个元素，继续与之前的比较，一直到array[0]
     *
     * @param array
     */
    public static void sort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }

        for (int i = 1; i < array.length; i++) {
            for (int j = i; j >= 1; j--) {
                if (array[j] >= array[j - 1]) {
                    break;
                }
                Common.swampValue(array, j, j - 1);
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {2, 3, 1, 4, 2, 44, 32, 13, 12, 56, 32, 12, 33, 21, 15, 16, 76, 45, 32, 33, 121, 123, 123, 122};
        sort(array);
        System.out.println("Arrays.toString(array) = " + Arrays.toString(array));
    }
}
