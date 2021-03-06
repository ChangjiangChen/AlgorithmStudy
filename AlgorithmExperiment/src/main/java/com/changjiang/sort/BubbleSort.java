package com.changjiang.sort;

import java.util.Arrays;

public class BubbleSort {
    public static void sortWithInternalPosition(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }

        boolean flag = true;
        int current = array.length - 1;
        int position = 0;
        int internalCount = 0;
        int outerCount = 0;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < current; j++) {
                internalCount++;
                if (array[j] > array[j + 1]) {
                    array[j] ^= array[j + 1];
                    array[j + 1] ^= array[j];
                    array[j] ^= array[j + 1];
                    flag = false;
                    position = j;
                }
            }
            outerCount++;
            if (flag) {
                break;
            } else {
                flag = true;
                current = position;
            }

        }
        System.out.println("internalCount = " + internalCount);
        System.out.println("outerCount = " + outerCount);
        System.out.println("array = " + Arrays.toString(array));
    }

    public static void sortWithFlag(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }

        boolean flag = true;
        int internalCount = 0;
        int outerCount = 0;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                internalCount++;
                if (array[j] > array[j + 1]) {
                    array[j] ^= array[j + 1];
                    array[j + 1] ^= array[j];
                    array[j] ^= array[j + 1];
                    flag = false;
                }
            }
            outerCount++;
            if (flag) {
                break;
            } else {
                flag = true;
            }
        }
        System.out.println("internalCount = " + internalCount);
        System.out.println("outerCount = " + outerCount);
        System.out.println("array = " + Arrays.toString(array));
    }

    public static void main(String[] args) {
        int[] array = {2, 3, 1, 4, 2, 44, 32, 13, 12, 56, 32, 12, 33, 21, 15, 16, 76, 45, 32, 33, 121, 123, 123, 122};
        sortWithFlag(Arrays.copyOf(array, array.length));
        sortWithInternalPosition(Arrays.copyOf(array, array.length));
    }
}
