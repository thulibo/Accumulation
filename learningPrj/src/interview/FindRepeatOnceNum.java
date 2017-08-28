package interview;

/**
 * 一个大小为N的整数数组，存储着从1到N-1的整数。显然，其中有个数重复了一次。<BR>
 * 实现找出这个重复数的算法，要求空间复杂度为O(1)，时间复杂度为O(N)
 * 
 * @author libo09@mails.tsinghua.edu.cn
 *
 */
public class FindRepeatOnceNum {

    /**
     * 使用位图解法
     * 
     * @author libo09@mails.tsinghua.edu.cn
     * 
     * @param inputArray
     * @return
     */
    public static int findTheRepeatNum(int[] inputArray) {
        int bitMapValue = 0;

        for (int i = 0; i < inputArray.length; i++) {
            int shiftValue = 1 << (inputArray[i] - 1);
            bitMapValue ^= shiftValue;
        }

        int first1Bit = 0;
        int oriBitMapValue = bitMapValue;
        while (bitMapValue > 0) {
            bitMapValue >>= 1;
            first1Bit++;
        }

        int repeatNum = 0;
        int destiValue = ((1 << first1Bit) - 1) ^ oriBitMapValue;
        while (destiValue > 0) {
            destiValue >>= 1;
            repeatNum++;
        }

        return repeatNum;
    }

    public static void main(String[] args) {
        int[] inputArray = new int[] { 4, 2, 5, 1, 7, 6, 4, 3, 8, 9, 10, 11, 5 };
        int result = findTheRepeatNum(inputArray);
        System.out.println("result=" + result);
    }

}
