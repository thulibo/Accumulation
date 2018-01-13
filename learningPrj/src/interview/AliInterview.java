package interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 题目：5个整数，写码输出前4个数用加法能得到第5个数的所有可能组合<BR>
 * 
 * 
 * 
 * @author libo09@mails.tsinghua.edu.cn
 * 
 * @since 2018-01-13
 *
 */
// TODO 需要JDK版本需要>=1.8（支持Stream和Lambda）
public class AliInterview {

    /**
     * main方法for test
     * 
     * @author libo09@mails.tsinghua.edu.cn
     * 
     * @param args
     */
    // TODO 注意使用IDE跑测试时填入测试输入
    public static void main(String[] args) {
        // 输入串转为整型list
        List<Integer> inputIntList = new ArrayList<Integer>();
        Arrays.asList(args).forEach(eachArg -> {
            try {
                inputIntList.add(Integer.valueOf(eachArg));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        });

        // 测试方案一
        printDestinationCombinationsRoughly(inputIntList.toArray(new Integer[0]));
    }

    /**
     * 方案一：<BR>
     * 采用暴力枚举各种可能的排列组合来遍历查找符合条件的组合并打印输出<BR>
     * 
     * 时间复杂度稳定的为所有可能组合的和，即：6 + 4 + 1 = O(11)
     * 
     * @author libo09@mails.tsinghua.edu.cn
     * 
     * 
     * @param inputArray
     */
    public static void printDestinationCombinationsRoughly(Integer[] inputArray) {
        // Validate
        if (null == inputArray || inputArray.length != 5) {
            System.err.println("input int array is illegal!");
            return;
        }

        int findCounter = 0;
        for (int i = 0; i < inputArray.length; i++) {
            for (int j = i + 1; j < inputArray.length; j++) {
                if (inputArray[i] + inputArray[j] == inputArray[inputArray.length - 1]) {
                    findCounter++;
                    System.out.println("Found combination " + findCounter + ": " + inputArray[i] + " + " + inputArray[j]
                            + " == " + inputArray[inputArray.length - 1]);
                }
                for (int k = j + 1; k < inputArray.length; k++) {
                    if (inputArray[i] + inputArray[j] + inputArray[k] == inputArray[inputArray.length - 1]) {
                        findCounter++;
                        System.out.println("Found combination " + findCounter + ": " + inputArray[i] + " + "
                                + inputArray[j] + " + " + inputArray[k] + " == " + inputArray[inputArray.length - 1]);
                    }
                    for (int m = k + 1; m < inputArray.length; m++) {
                        if (inputArray[i] + inputArray[j] + inputArray[k]
                                + inputArray[m] == inputArray[inputArray.length - 1]) {
                            findCounter++;
                            System.out.println("Found combination " + findCounter + ": " + inputArray[i] + " + "
                                    + inputArray[j] + " + " + inputArray[k] + " + " + inputArray[m] + " == "
                                    + inputArray[inputArray.length - 1]);
                        }
                    }
                }
            }
        }
        System.out.println("Found total combinations: " + findCounter);
    }

    /**
     * 方案二：<BR>
     * 第一步，类似快速排序的第一步分区，进行一次分区，将前4个数中小于第5个数的放在数组一边，大于第5个数的放另一边<BR>
     * 第二步，根据4个数中小于第5个数的个数，再进行遍历所有可能组合；<BR>
     * 比如：如果小于第5个数的数只有0个或者1个，那么必然没有满足条件的组合；如果有2个，看看是否满足相加后等于第5个数，满足则输出；以此类推<BR>
     * 
     * 时间复杂度不定，最好的情况如上述（小于第五个数的数只有<=2时），为：分区遍历 O(4)；最坏的情况是前4个数都小于第5个数，为：分区遍历O(4) + 所有可能排列组合遍历O(11) = O(15)
     * 
     * 
     * @author libo09@mails.tsinghua.edu.cn
     * 
     * 
     * @param inputArray
     */
    // TODO 时间有限，就不抽取子方法了 ：）
    public static void printDestinationCombinationsOptimized(Integer[] inputArray) {
        // Validate
        if (null == inputArray || inputArray.length != 5) {
            System.err.println("input int array is illegal!");
            return;
        }

        // 进行一次分区：将前4个数中小于第5个数的放一边，大于第5个数的放另一边
        int i = 0, j = 3;
        while (i < j) {
            if (inputArray[i] <= inputArray[inputArray.length]) {
                i++;
                continue;
            }
            if (inputArray[j] > inputArray[inputArray.length]) {
                j--;
                continue;
            }
            // 交换
            int tmp = inputArray[i];
            inputArray[i] = inputArray[j];
            inputArray[j] = tmp;
            i++;
            j--;
        }

        int middleIndex = 0;
        if (inputArray[i] <= inputArray[inputArray.length]) {
            middleIndex = i + 1;
        } else {
            middleIndex = i - 1;
        }

        int findCounter = 0;

        // 这时middleIndex就表示前4个数满足小于第5个数的个数
        if (middleIndex < 2) {
            System.out.println("Found total combinations: " + findCounter);
            return;
        }

        if (middleIndex == 2) {
            if (inputArray[0] + inputArray[1] == inputArray[inputArray.length]) {
                findCounter++;
                System.out.println("Found combination " + findCounter + ": " + inputArray[0] + " + " + inputArray[1]
                        + " == " + inputArray[inputArray.length - 1]);
            }
            System.out.println("Found total combinations: " + findCounter);
            return;
        }

        if (middleIndex == 3) {
            for (i = 0; i < 4; i++) {
                for (j = i + 1; j < 4; j++) {
                    if (inputArray[i] + inputArray[j] == inputArray[inputArray.length - 1]) {
                        findCounter++;
                        System.out.println("Found combination " + findCounter + ": " + inputArray[i] + " + "
                                + inputArray[j] + " == " + inputArray[inputArray.length - 1]);
                    }
                    for (int k = j + 1; k < 4; k++) {
                        if (inputArray[i] + inputArray[j] + inputArray[k] == inputArray[inputArray.length - 1]) {
                            findCounter++;
                            System.out.println(
                                    "Found combination " + findCounter + ": " + inputArray[i] + " + " + inputArray[j]
                                            + " + " + inputArray[k] + " == " + inputArray[inputArray.length - 1]);
                        }
                    }
                }
            }
            System.out.println("Found total combinations: " + findCounter);
            return;
        }

        if (middleIndex == 4) {
            for (i = 0; i < inputArray.length; i++) {
                for (j = i + 1; j < inputArray.length; j++) {
                    if (inputArray[i] + inputArray[j] == inputArray[inputArray.length - 1]) {
                        findCounter++;
                        System.out.println("Found combination " + findCounter + ": " + inputArray[i] + " + "
                                + inputArray[j] + " == " + inputArray[inputArray.length - 1]);
                    }
                    for (int k = j + 1; k < inputArray.length; k++) {
                        if (inputArray[i] + inputArray[j] + inputArray[k] == inputArray[inputArray.length - 1]) {
                            findCounter++;
                            System.out.println(
                                    "Found combination " + findCounter + ": " + inputArray[i] + " + " + inputArray[j]
                                            + " + " + inputArray[k] + " == " + inputArray[inputArray.length - 1]);
                        }
                        for (int m = k + 1; m < inputArray.length; m++) {
                            if (inputArray[i] + inputArray[j] + inputArray[k]
                                    + inputArray[m] == inputArray[inputArray.length - 1]) {
                                findCounter++;
                                System.out.println("Found combination " + findCounter + ": " + inputArray[i] + " + "
                                        + inputArray[j] + " + " + inputArray[k] + " + " + inputArray[m] + " == "
                                        + inputArray[inputArray.length - 1]);
                            }
                        }
                    }
                }
            }
            System.out.println("Found total combinations: " + findCounter);
            return;
        }
    }

}
