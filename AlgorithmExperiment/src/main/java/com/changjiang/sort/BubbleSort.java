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
        int count = 0;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < current; j++) {
                count++;
                if (array[j] > array[j + 1]) {
                    array[j] ^= array[j + 1];
                    array[j + 1] ^= array[j];
                    array[j] ^= array[j + 1];
                    flag = false;
                    position = j;
                }
            }
            if (flag) {
                break;
            } else {
                flag = true;
                current = position;
            }
        }
        System.out.println("count = " + count);
    }

    public static void sortWithFlag(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }

        boolean flag = true;
        int count = 0;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                count++;
                if (array[j] > array[j + 1]) {
                    array[j] ^= array[j + 1];
                    array[j + 1] ^= array[j];
                    array[j] ^= array[j + 1];
                    flag = false;
                }
            }
            if (flag) {
                break;
            } else {
                flag = true;
            }
        }
        System.out.println("count = " + count);
    }

    public static void main(String[] args) {
        int[] array = {2, 3, 1, 4, 2, 44, 32, 13, 12, 56, 32, 12, 33, 21, 15, 16, 76, 45, 32, 33, 121, 123, 123, 122};
        sortWithFlag(array);
//        sortWithInternalPosition(array);
        System.out.println("Arrays.toString(array) = " + Arrays.toString(array));
    }
}
