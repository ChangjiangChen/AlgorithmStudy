package com.changjiang.sort.quick;

import com.changjiang.sort.util.Common;

import java.util.Arrays;

public class DualPivotQuickSort {

    public static void dualQuicksort(int[] array, int start, int end) {
        if (array == null || array.length < 2) {
            return;
        }
        if (end < start) {
            //如果放入的初始位置有错误，此时抛异常提示；
            //如果是循环过程中造成的初始位置比结尾位置大，那么要在程序中避免
            throw new IllegalArgumentException("param error!");
        }

        //默认从小到大排序
        //保证左边节点比右边节点要小
        if (array[start] > array[end]) {
            Common.swampValue(array, start, end);
        }

        int left = start;
        int right = end;
        int k = start + 1;
        int pivot1 = array[start];
        int pivot2 = array[end];

        while (k < right) {
            if (array[k] <= pivot1) {
                if (left + 1 == k) {
                    //避免left就在k前面时进行元素的自我比较交换
                    ++left;
                } else {
                    Common.swampValue(array, ++left, k);
                }
                k++;
            } else if (array[k] >= pivot2) {
                //由于放入了一个新的元素进来，此时的k位置要继续与左侧比较
                Common.swampValue(array, --right, k);
            } else {
                k++;
            }
        }

        if (left != start) {
            //相等说明这边没有元素发生变化，所有元素都比它大，不用交换位置
            Common.swampValue(array, start, left);
        }
        if (right != end) {
            Common.swampValue(array, end, right);
        }

        if (start < left - 1) {
            dualQuicksort(array, start, left - 1);
        }
        if (left + 1 < right - 1) {
            dualQuicksort(array, left + 1, right - 1);
        }
        if (right + 1 < end) {
            dualQuicksort(array, right + 1, end);
        }
    }

    public static void main(String[] args) {
        int[] range = {12, 13, 22, 31, 1, 3, 4, 5, 6, 7, 8, 323, 423};
        dualQuicksort(range, 0, range.length - 1);
        System.out.println("Arrays.toString(range) = " + Arrays.toString(range));
    }
}
