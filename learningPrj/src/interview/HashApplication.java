package interview;

/**
 * hash算法简版实现及应用<BR>
 * 给定n个1~(2^31-1)的数，检查m个相同范围的数是否在前面给定的n个数中出现过。
 * 
 * 
 * @author libo09@mails.tsinghua.edu.cn
 *
 */
public class HashApplication {

    private static int[][] hashTable;
    private static int n;

    /**
     * 特别注意，这里使用不定列长的二维数组并不是最优；实际就应该使用数组+链表开链的数据结构
     */
    private static int intialSubSize = 10;

    /**
     * 将指定的n个1~（2^31-1）的正整数转换存入内部由不定列长的二维数组构成的HashTable中
     * 
     * @author libo09@mails.tsinghua.edu.cn
     * 
     * @param oriNums
     */
    public static void putOriNumsIntoHashTable(int[] oriNums) {
        if (null == oriNums || oriNums.length == 0) {
            System.err.println("no input num!!");
            return;
        }

        n = oriNums.length;
        hashTable = new int[n][intialSubSize];

        for (int eachNum : oriNums) {
            int mode = eachNum % oriNums.length;

            int cursor = 0;
            for (int eachSubElement : hashTable[mode]) {
                if (eachSubElement == 0) {
                    hashTable[mode][cursor] = eachNum;
                    break;
                }
                cursor++;
            }
        }
    }

    /**
     * 检查输入数据的存在性
     * 
     * @author libo09@mails.tsinghua.edu.cn
     * 
     * @param input
     * @return
     */
    public static boolean isInOriNum(int input) {
        int destiSeg = input % n;
        if (hashTable[destiSeg].length == 0) {
            return false;
        }

        for (int eachSubElement : hashTable[destiSeg]) {
            if (eachSubElement == input) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] inputNums = new int[] { 24, 1, 34, 56, 666, 88738, 6, 785, 10, 26 };
        putOriNumsIntoHashTable(inputNums);
        System.out.println("The test input num is in ori nums: " + isInOriNum(26));
    }

}
