package com.changjiang.sort.quick;

import java.util.Arrays;
import java.util.Random;

/**
 * 选取pivot时，并不总是使用第一个元素，而是随机位置的元素
 */
public class RandomPivotQuickSort {
    public static void sort(int[] array, int start, int end) {
        if (array == null || array.length < 2 || start >= end || start < 0) {
            return;
        }

        int left = start;
        int right = end;
        int position = start + new Random().nextInt(end - start + 1);
        int pivot = array[position];

        swapValue(array, left, position);

        while (left < right) {
            while (array[right] >= pivot && left < right) {
                right--;
            }
            if (array[right] < pivot) {
                array[left] = array[right];
            }

            while (array[left] <= pivot && left < right) {
                left++;
            }
            if (array[left] > pivot) {
                array[right] = array[left];
            }

            if (left == right) {
                array[left] = pivot;
            }
        }
        if (start < left - 1) {
            sort(array, start, left - 1);
        }
        if (left + 1 < end) {
            sort(array, left + 1, end);
        }
    }

    private static void swapValue(int[] array, int left, int position) {
        array[position] ^= array[left];
        array[left] ^= array[position];
        array[position] ^= array[left];
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int[] range = {1, 3, 4, 2, 55, 32, 21, 12, 32, 22, 11, 323, 123, 1, 3, 2, 4, 7};
            sort(range, 0, range.length - 1);
            System.out.println("range = " + Arrays.toString(range));
        }

        int[] range1 = {1, 3, 4, 2, 55, 32, 21, 12, 32, 22, 11, 323, 123, 1, 3, 2, 4, 7};
        swapValue(range1, 1, 0);
        System.out.println("Arrays.toString(range1) = " + Arrays.toString(range1));
    }
}
