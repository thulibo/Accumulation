package dataStructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 各种常见的简单排序类<BR>
 * 包括：插入排序、归并排序、快速排序等
 * 
 * @author libo <br>
 *         E-mail:libo09@mails.tsinghua.edu.cn
 * @date 创建时间：2016年4月13日 下午6:00:21
 * @version 1.0
 */
public class SimpleSort {

    /**
     * 插入排序
     * 
     * @param inputArray
     */
    public static void insertSort(Integer[] inputArray) {
        if (inputArray == null) {
            System.err.println("输入的待排序数组为空！");
            return;
        }

        if (inputArray.length == 1) {
            return;
        }

        for (int i = 1; i < inputArray.length; i++) {
            int temp = inputArray[i];

            int j = i - 1;
            for (; j >= 0; j--) {
                if (inputArray[j] > temp) {
                    inputArray[j + 1] = inputArray[j];
                } else {
                    break;
                }
            }

            inputArray[j + 1] = temp;
        }
    }

    /**
     * 归并排序
     * 
     * @param inputArray
     * @return
     */
    public static int[] mergeSort(int[] inputArray) {
        // check input
        if (null == inputArray || inputArray.length == 0) {
            throw new IllegalArgumentException("The input array is empty!");
        }

        if (inputArray.length == 1) {
            return inputArray;
        }

        subMergeSort(inputArray, 0, inputArray.length - 1);
        return inputArray;
    }

    /**
     * 递归地将一个数组从中点切分为2个数组，然后归并
     * 
     * @param inputArray
     * @param left
     * @param right
     */
    private static void subMergeSort(int[] inputArray, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            subMergeSort(inputArray, left, middle);
            subMergeSort(inputArray, middle + 1, right);
            merge(inputArray, left, middle, right);
        }
    }

    /**
     * 将inputArray数组从left -> right段切分为2个数组：left -> middle和middle+1 -> right <BR>
     * 然后进行归并
     * 
     * @param inputArray
     * @param left
     * @param middle
     * @param right
     */
    private static void merge(int[] inputArray, int left, int middle, int right) {
        int[] tmpArray = new int[1 + right - left];
        int secondGrpIndex = middle + 1;
        int tmpIndex = 0;
        int oriLeft = left;

        // step1： 比较切分出来的2组数组，较小的数据入tmp数组
        while (left <= middle && secondGrpIndex <= right) {
            if (inputArray[left] <= inputArray[secondGrpIndex]) {
                tmpArray[tmpIndex++] = inputArray[left++];
            } else {
                tmpArray[tmpIndex++] = inputArray[secondGrpIndex++];
            }
        }

        // step2：上一步循环比较并将较小数据入tmp数组后，终有一组数据会有剩余
        // 将剩余的数据拷入tmp数组尾部（下面2个while循环是互斥的，只会有一个执行）
        while (left <= middle) {
            tmpArray[tmpIndex++] = inputArray[left++];
        }
        while (secondGrpIndex <= right) {
            tmpArray[tmpIndex++] = inputArray[secondGrpIndex++];
        }

        // step3：将归并好的tmp数据拷回到源数组的对应位置
        for (int i = oriLeft; i <= right; i++) {
            inputArray[i] = tmpArray[i - oriLeft];
        }
    }

    /**
     * 二分查找，非递归
     * 
     * @author libo09@mails.tsinghua.edu.cn
     * 
     * @param target
     * @param inputArray
     * @return 返回查找到与目标数相等的数组下标，-1表示没有找到
     */
    public static int binarySearch(int target, int[] inputArray) {
        if (null == inputArray || inputArray.length == 0) {
            System.err.println("input array is empty!!");
            return -1;
        }

        int left = 0;
        int right = inputArray.length;

        while (left <= right) {
            int middle = (left + right) >> 1;
            if (target == inputArray[middle]) {
                return middle;
            }
            if (target < inputArray[middle]) {
                right = middle - 1;
            } else if (target > inputArray[middle]) {
                left = middle + 1;
            }
        }

        return -1;
    }

    /**
     * 二分查找，递归
     * 
     * @author libo09@mails.tsinghua.edu.cn
     * 
     * @param target
     * @param left
     * @param right
     * @param inputArray
     * @return 返回查找到与目标数相等的数组下标，-1表示没有找到
     */
    public static int binarySearchRecursive(int target, int left, int right, Integer[] inputArray) {
        if (null == inputArray || inputArray.length == 0) {
            return -1;
        }

        if (left > right) {
            return -1;
        }

        int middle = (left + right) >> 1;
        if (target == inputArray[middle]) {
            return middle;
        }
        if (target < inputArray[middle]) {
            return binarySearchRecursive(target, left, middle - 1, inputArray);
        }
        return binarySearchRecursive(target, middle + 1, right, inputArray);
    }

    public static void main(String[] args) {
        System.out.println("haha".hashCode());

        System.out.println("=======测试开始=======");
        System.out.println("请输入一组整数数组：");

        Scanner scanner = new Scanner(System.in);
        List<Integer> inputInts = new ArrayList<Integer>();

        while (scanner.hasNextInt()) {
            Integer eachInputInt = scanner.nextInt();
            inputInts.add(eachInputInt);
            System.out.println(eachInputInt);
        }
        if (scanner.hasNextLine()) {
            Integer[] inputArray = inputInts.toArray(new Integer[0]);

            insertSort(inputArray);

            System.out.println("=========通过插入法排序后========");
            for (int j = 0; j < inputInts.size(); j++) {
                System.out.println(inputArray[j]);
            }

            int[] inputIntArray = new int[inputInts.size()];
            int i = 0;
            for (Integer eachOriInt : inputInts) {
                inputIntArray[i++] = eachOriInt;
            }

            mergeSort(inputIntArray);

            System.out.println("=========通过归并排序后========");
            for (int j = 0; j < inputIntArray.length; j++) {
                System.out.println(inputIntArray[j]);
            }

            System.out.println("=========通过二分查找（非递归）后========");
            System.out.println(binarySearch(10, inputIntArray));
            System.out.println("=========通过二分查找（递归）后========");
            System.out.println(binarySearchRecursive(10, 0, inputArray.length - 1, inputArray));
        }
    }
}
