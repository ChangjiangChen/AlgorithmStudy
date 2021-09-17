package com.changjiang.sort.quick;

import java.util.Arrays;

public class QuickSort {
    public static void sort(int[] array, int start, int end) {
        if (array == null || array.length == 0 || start > end || start < 0) {
            throw new IllegalArgumentException("param error!");
        }
        int left = start;
        int right = end;
        int pivotValue = array[start];
        while (left < right) {
            while (array[right] >= pivotValue && left < right) {
                right--;
            }
            if (array[right] < pivotValue) {
                array[left] = array[right];
            }

            while (array[left] <= pivotValue && left < right) {
                left++;
            }
            if (array[left] > pivotValue) {
                array[right] = array[left];
            }

            if (left == right) {
                array[left] = pivotValue;
            }
        }

        //处理左子序列
        if (start < left - 1) {
            sort(array, start, left - 1);
        }

        //处理右子序列
        if (left + 1 < end) {
            sort(array, left + 1, end);
        }
    }


    public static void main(String[] args) {
        int[] range = {1, 3, 4, 2, 55, 32, 21, 12, 32, 22, 11, 323, 123, 1, 3, 2, 4, 7};
        sort(range, 0, range.length - 1);
        System.out.println("range = " + Arrays.toString(range));
    }
}
