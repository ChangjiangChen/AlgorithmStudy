package com.changjiang.sort.insert;

import java.util.Arrays;

/**
 * 使用二分查找找到合适的位置
 * 虽然找的速度快，但最坏情况下，每一轮还是需要发生n-1次的交换
 */
public class BinarySearchInsertionSort {
    public static void sort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }

        int start = 0;
        int end = 0;
        int insert = 0;
        for (int i = 0; i < array.length - 1; i++) {
            //1.找到已排好的序列，找到需要进行插入的元素
            //待处理的元素如果已经超过范围，说明排序已经完成，可以退出循环
            int process = end + 1;
            if (process > array.length - 1) {
                break;
            }

            //比较待二分处理的序列，如果序列长度超过2，才进行二分处理
            boolean findLocation = false;
            while (end - start >= 2) {
                //进行二分处理，找到插入位置
                int middle = start + ((end - start) / 2);
                if (array[process] == array[middle]) {
                    insert = middle + 1;
                    findLocation = true;
                    break;
                } else if (array[process] > array[middle]) {
                    start = middle + 1;
                } else {
                    end = middle - 1;
                }
            }
            if (!findLocation) {
                //如果到这里，说明本身序列长度不超过2个；或者，在while中没有找到相等的middle位置
                insert = findInsertLocationForLessEqualTwo(array, start, end, insert, process);
            }

            //2.找到了插入位置，这里进行元素的后移
            int readyInsertValue = array[process];
            for (int j = process; j > insert; j--) {
                array[j] = array[j - 1];
            }
            array[insert] = readyInsertValue;

            //3.确定下一个元素二分查找的start与end位置
            start = 0;
            end = i + 1;
        }
    }

    private static int findInsertLocationForLessEqualTwo(int[] array, int start, int end, int insert, int process) {
        if (start == end) {
            if (array[process] <= array[start]) {
                insert = start;
            } else {
                insert = start + 1;
            }
        } else if (start + 1 == end) {
            if (array[process] <= array[start]) {
                insert = start;
            } else if (array[process] >= array[end]) {
                insert = end + 1;
            } else {
                insert = start + 1;
            }
        } else {
            throw new RuntimeException("logic error");
        }
        return insert;
    }

    public static void main(String[] args) {
        int[] array = {2, 3, 1, 4, 2, 44, 32, 13, 12, 56, 32, 12, 33, 21, 15, 16, 76, 45, 32, 33, 121, 123, 123, 122};
        sort(array);
        System.out.println("Arrays.toString(array) = " + Arrays.toString(array));
    }
}
