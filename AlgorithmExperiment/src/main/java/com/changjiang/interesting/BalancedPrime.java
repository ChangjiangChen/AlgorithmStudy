package com.changjiang.interesting;

import java.util.ArrayList;
import java.util.List;

/**
 * @author changjiang.chen
 * @description 找出N内的所有质数且对称的数字，包含个位数。注意:0和1既不是质数也不是合数。
 * @timestamp 2021-09-15 11:04:19
 */
public class BalancedPrime {

    /**
     * 1.如果大于10，那么结果集包含2,3,5,7
     * 2.对于大于10的数字（两位以上的数字，含两位）的处理：
     * a.分析数字的位数，并判断这个位数的奇偶性。--构建所有可能的对称数
     * a1.打头的数字不能为0,2,4,5,6,8
     * a2.如果输入的是n位数，那么构建的n位数第一个数字不能比输入数字大
     *
     * @param inputMaxNumber 输入的最大值,0和1既不是质数也不是合数，要求输入必须大于1
     */

    public static final List<Integer> INTEGER_LIST = new ArrayList<Integer>();

    static {
        INTEGER_LIST.add(2);
        INTEGER_LIST.add(3);
        INTEGER_LIST.add(5);
        INTEGER_LIST.add(7);
    }

    private static void processNumberLtTen(int source) {
        ArrayList<Integer> integerArrayList = new ArrayList<Integer>();
        integerArrayList.add(2);
        if (source >= 3) {
            integerArrayList.add(3);
        }
        if (source >= 5) {
            integerArrayList.add(5);
        }
        if (source >= 7) {
            integerArrayList.add(7);
        }
        System.out.println("integerArrayList = " + integerArrayList.toString());
    }

    public static void find(int source) {
        if (source <= 1) {
            throw new RuntimeException("out of range");
        }
        if (source < 10) {
            processNumberLtTen(source);
            return;
        }
        //后续都是大于等于10的数字，可以用（1.构建对称数，2.判断质数）的逻辑处理
        int compare = 0;
        for (int i = 1; compare <= source; i++) {
            compare = 6 * i + 5;
            compareAndProcess(compare);
            compare = 6 * i + 7;
            compareAndProcess(compare);
        }
        System.out.println("INTEGER_LIST = " + INTEGER_LIST.toString());
    }

    private static void compareAndProcess(int compare) {
        if (isBalanced(compare) && isPrime(compare)) {
            INTEGER_LIST.add(compare);
        }
    }

    private static boolean isBalanced(int source) {
        if (source <= 9) {
            throw new RuntimeException("out of range");
        }
        int original = source;
        //构造新数字，以原数字的个位为最高位，十位为次,...依次到原数字的最高位成为本数字的个位
        int buildNew = 0;
        while (source != 0) {
            buildNew = buildNew * 10 + source % 10;
            source /= 10;
        }
        if (buildNew == original) {
            return true;
        }
        return false;
    }

    private static boolean isPrime(int inputMaxNumber) {
        if (inputMaxNumber <= 1) {
            throw new RuntimeException("out of range");
        }
        if (inputMaxNumber == 2 || inputMaxNumber == 3) {
            return false;
        }
        if (inputMaxNumber % 6 != 1 && inputMaxNumber % 6 != 5) {
            return false;
        }
        int sqrt = (int) Math.sqrt(inputMaxNumber);
        for (int i = 5; i <= sqrt; i += 6) {
            if (inputMaxNumber % i == 0 || inputMaxNumber % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        find(1000000000);
        System.out.println("time eclipsed = " + (System.currentTimeMillis() - l));
    }
}
