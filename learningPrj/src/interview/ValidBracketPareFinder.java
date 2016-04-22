package interview;

/**
 * 给定n对左右括号，输出所有合法的数学括号运算组合
 * 
 * @author libo <br>
 *         E-mail:libo09@mails.tsinghua.edu.cn
 * @date 创建时间：2016年4月22日 下午08:15:32
 * @version 1.0
 */
public class ValidBracketPareFinder {

    /**
     * 递归的从剩余的左、右括号对里取出括号，放入bracketArray括号对数组。<br>
     * 
     * "只要还有左括号，我们就加入输出串，然后递归调用.<br>
     * 当退出递归时，如果剩余的右括号数量比剩余的左括号数量多，我们就将右括号加入输出串。<br>
     * 直到最后剩余的左括号和右括号都为0时，即可打印一个输出串。"<br>
     * 
     * 如果没有剩余的左括号，或者剩余的右括号数小于剩余的左括号数，递归结束。
     * 
     * @param leftL 剩余的左括号数
     * @param leftR 剩余的右括号数
     * @param bracketArray 用于存储合法括号对的String数组
     * @param index bracketArray数组当前的下标
     */
    public static void findValidBracketPare(int leftL, int leftR, String[] bracketArray, int index) {
        if (leftL < 0 || leftR < leftL) {
            return;
        }

        if (leftL == 0 && leftR == 0) {
            for (String eachBracket : bracketArray) {
                System.out.print(eachBracket);
            }

            System.out.println(", ");
        } else {
            // 还剩余有左括号，就放入数组
            if (leftL > 0) {
                bracketArray[index] = "(";
                // TODO 特别注意：这里是递归，leftL、leftR、index不要用++、--运算符！
                findValidBracketPare(leftL - 1, leftR, bracketArray, index + 1);
            }

            // 剩余右括号数多于左括号数
            if (leftR >= leftL) {
                bracketArray[index] = ")";
                findValidBracketPare(leftL, leftR - 1, bracketArray, index + 1);
            }
        }
    }

    public static void main(String[] args) {
        // 括号对数
        int numBracketPare = 4;

        int leftL = numBracketPare;
        int leftR = numBracketPare;
        String[] bracketArray = new String[2 * numBracketPare];
        int index = 0;

        findValidBracketPare(leftL, leftR, bracketArray, index);
    }

}
