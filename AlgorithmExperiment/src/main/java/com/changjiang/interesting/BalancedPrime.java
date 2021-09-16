package com.changjiang.interesting;

import java.util.ArrayList;
import java.util.List;

/**
 * @author changjiang.chen
 * @description 找出N内的所有质数且对称的数字，包含个位数。注意:0和1既不是质数也不是合数。
 * @timestamp 2021-09-15 11:04:19
 * <p>
 */
public class BalancedPrime {
    public static final List<Integer> INTEGER_LIST = new ArrayList<>();

    static {
        INTEGER_LIST.add(2);
        INTEGER_LIST.add(3);
        INTEGER_LIST.add(5);
        INTEGER_LIST.add(7);
    }

    private static void processNumberLtTen(int source) {
        ArrayList<Integer> integerArrayList = new ArrayList<>();
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
    }

    public static void find(int source) {
        if (source <= 1) {
            throw new RuntimeException("out of range");
        }
        if (source < 10) {
            processNumberLtTen(source);
            return;
        }

        //遇关键数字跳跃数字段，可提升接近50%性能
        int ex = 0;
        int skipCount = 0;
        int limitSkipCount = -1;
        for (int i = 11; i <= source; i += 2) {
            if (skipCount == limitSkipCount || limitSkipCount == -1) {
                int current = i;
                while (current >= 10) {
                    current /= 10;
                    ex++;
                }
                limitSkipCount = ((int) Math.pow(10, ex)) / 2;
                if (current % 2 == 0 || current == 5) {
                    i = calculateCurrent(current, ex);
                } else {
                    limitSkipCount = (((current + 1) * (int) Math.pow(10, ex)) + 1 - i) / 2;
                }
                ex = 0;
                skipCount = 0;
            }
            skipCount++;

            compareAndProcess(i);
        }
    }

    private static int calculateCurrent(int current, int ex) {
        if (current == 2) {
            return (3 * (int) Math.pow(10, ex)) + 1;
        } else if (current == 4 || current == 5 || current == 6) {
            return (7 * (int) Math.pow(10, ex)) + 1;
        } else if (current == 8) {
            return (9 * (int) Math.pow(10, ex)) + 1;
        } else {
            throw new RuntimeException("out of range");
        }
    }

    private static void compareAndProcess(int compare) {
        if (isBalanced(compare) && isPrime(compare)) {
            INTEGER_LIST.add(compare);
        }
    }

    private static boolean isBalanced(int source) {
        if (source <= 9) {
            throw new RuntimeException("out of range" + source);
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

    private static int buildBalanceByPrefix(int number, boolean isEven) {
        int process = number;
        if (!isEven) {
            process /= 10;
        }
        int inverse = 0;
        int ex = 0;
        while (process > 0) {
            int i = process % 10;
            inverse = inverse * 10 + i;
            ex++;
            process /= 10;
        }
        return (number * (int) Math.pow(10, ex) + inverse);
    }

    /**
     * 构建与输入数字长度相同的对称数
     *
     * @param inputMaxNumber
     * @return
     */
    private static List<Integer> buildSameLengthBalance(int inputMaxNumber) {
        if (inputMaxNumber < 10) {
            throw new RuntimeException("out of range" + inputMaxNumber);
        }
        int process = inputMaxNumber;
        //数字长度
        int ex = 0;
        while (process > 0) {
            process /= 10;
            ex++;
        }

        //数字长度是否为偶数
        boolean isEven = ex % 2 == 0;
        int prefixCount = ex / 2;
        if (!isEven) {
            prefixCount += 1;
        }

        List<Integer> integerList = new ArrayList<>();
        boolean firstRounded = true;
        List<Integer> lastRounded = new ArrayList<>();
        for (int i = 0; i < prefixCount; i++) {
            integerList.clear();
            for (int j = 0; j < 10; j++) {
                if (firstRounded) {
                    if (j % 2 == 0 || j == 5) {
                        continue;
                    }
                    integerList.add(j);
                } else {
                    for (int lastNumber : lastRounded) {
                        integerList.add(lastNumber + j);
                    }
                }
            }
            if (firstRounded) {
                firstRounded = false;
            } else {
                lastRounded.clear();
            }
            for (int number : integerList) {
                lastRounded.add(number * 10);
            }
        }

        List<Integer> result = new ArrayList<Integer>();
        for (int k : integerList) {
            //避免溢出
            if ((isEven && k <= 21474) || (!isEven && ex / 2 <= 4)) {
                result.add(buildBalanceByPrefix(k, isEven));
            }
        }

        return result;
    }

    /**
     * 1.如果大于10，那么结果集包含2,3,5,7
     * 2.对于大于10的数字（两位以上的数字，含两位）的处理：
     * a.分析数字的位数，并判断这个位数的奇偶性。--构建所有可能的对称数
     * a1.打头的数字不能为0,2,4,5,6,8
     * a2.如果输入的是n位数，那么构建的n位数第一个数字不能比输入数字大
     *
     * @param inputMaxNumber
     */
    public static void findFromSymmetry(int inputMaxNumber) {
        if (inputMaxNumber <= 1) {
            throw new RuntimeException("out of range");
        }
        if (inputMaxNumber < 10) {
            processNumberLtTen(inputMaxNumber);
            return;
        }

        int process = inputMaxNumber;
        while (process >= 10) {
            List<Integer> integers = buildSameLengthBalance(process);
            for (Integer integer : integers) {
                //构建的比原输入数字大时，跳过
                if (integer > inputMaxNumber) {
                    continue;
                }

                //首位数字为偶数或者5，则不进行比较了
                int current = integer;
                while (current >= 10) {
                    current /= 10;
                }
                if (current % 2 == 0 || current == 5) {
                    continue;
                }

                //比较，并决定是否放入结果集
                compareAndProcess(integer);
            }
            //减少一位，继续循环，直到降低到个位数字
            process /= 10;
        }
    }

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        findFromSymmetry(1000000000);
//        find(1000000000);
        System.out.println("time escaped = " + (System.currentTimeMillis() - l));
    }
}
