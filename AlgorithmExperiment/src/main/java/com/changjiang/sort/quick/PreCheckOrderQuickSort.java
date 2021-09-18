package com.changjiang.sort.quick;

import com.sun.deploy.util.ArrayUtil;

import java.util.Arrays;
import java.util.Random;

public class PreCheckOrderQuickSort {

    public static void sort(int[] array, int start, int end) {
        if (array == null || array.length < 2 || start >= end || start < 0) {
            return;
        }


        boolean alwaysAsc = true;
        boolean alwaysDes = true;
        boolean equal = true;

        for (int i = start; i < end; i++) {
            if (array[i] < array[i + 1]) {
                alwaysDes = false;
                equal = false;
            } else if (array[i] > array[i + 1]) {
                alwaysAsc = false;
                equal = false;
            }
        }

        if (equal) {
            System.out.println("equal = " + equal);
            return;
        }
        if (alwaysAsc) {
            System.out.println("alwaysAsc = " + alwaysAsc);
            return;
        }
        if (alwaysDes) {
            System.out.println("alwaysDes = " + alwaysDes);
            reverseArray(array, start, end);
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

    private static void reverseArray(int[] array, int start, int end) {
        for (int i = 0; i < (end - start) / 2; i++) {
            array[start + i] ^= array[end - i];
            array[end - i] ^= array[start + i];
            array[start + i] ^= array[end - i];
        }
    }

    /**
     * 选定了随机的pivot后，将该位置的元素与array[left]进行交换
     *
     * @param array
     * @param left
     * @param position
     */
    private static void swapValue(int[] array, int left, int position) {
        array[position] ^= array[left];
        array[left] ^= array[position];
        array[position] ^= array[left];
    }

    public static void main(String[] args) {
        int[] range = {1, 3, 4, 5, 6, 7, 8, 12, 13, 22, 31, 323, 423};
//        reverseArray(range, 0, range.length - 1);
        sort(range, 0, range.length - 1);
        System.out.println("range = " + Arrays.toString(range));
    }

}
