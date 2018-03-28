package interview;

/**
 * 求出给定字符串的最长回文子串长度<BR>
 * 回文的含义：该字符串左右对称
 * 
 * 
 * @author libo09@mails.tsinghua.edu.cn
 *
 */
public class MaxEchoSubString {

    /**
     * 求出给定字符串的最长回文子串长度
     * 
     * @author libo09@mails.tsinghua.edu.cn
     * 
     * @param str
     * @return
     */
    public static int findMaxEchoSubStringLength(String str) {
        if (null == str || str.isEmpty()) {
            return 0;
        }

        for (int size = str.length(); size > 1; size--) {
            for (int i = 0; i + size <= str.length(); i++) {
                String subString = str.substring(i, i + size);
                if (isEcho(subString)) {
                    return size;
                }
            }
        }

        return 1;
    }

    /**
     * 判断给定字符串是否是回文
     * 
     * 
     * @author libo09@mails.tsinghua.edu.cn
     * 
     * @param subString
     * @return
     */
    private static boolean isEcho(String subString) {
        char[] strArr = subString.toCharArray();
        int i = 0, j = strArr.length - 1;
        while (i < j) {
            if (strArr[i] == strArr[j]) {
                i++;
                j--;
            } else {
                return false;
            }
        }

        return true;
    }

    /**
     * For Test
     * 
     * @param args
     */
    public static void main(String[] args) {
        String testStr = "XXOOBOBOOXXxxoo";
        System.out.println("======= testStr: " + testStr + ", MAX echo subString length is: "
                + findMaxEchoSubStringLength(testStr));
    }

}
