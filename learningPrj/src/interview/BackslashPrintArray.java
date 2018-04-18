package interview;

/**
 * 对n*n的数组，按反斜杠顺序打印出来
 * 
 * 
 * @author libo09@mails.tsinghua.edu.cn
 *
 */
public class BackslashPrintArray {

    public static int[][] constructResultArray(int[][] inputArray) {
        int[][] resultArray = new int[2 * inputArray.length - 1][inputArray.length];
        for (int row = 0; row < resultArray.length; row++) {
            resultArray[row] = new int[inputArray.length];
            for (int col = 0; col < inputArray.length; col++) {
                resultArray[row][col] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < inputArray.length; i++) {
            for (int j = 0; j < inputArray.length; j++) {
                resultArray[i + j][i] = inputArray[i][j];
            }
        }

        for (int i = 0; i < resultArray.length; i++) {
            for (int j = 0; j < resultArray[i].length; j++) {
                if (resultArray[i][j] != Integer.MAX_VALUE) {
                    System.out.print(resultArray[i][j] + " ");
                }
            }
            System.out.println();
        }

        return inputArray;
    }

    public static void main(String[] args) {
        constructResultArray(new int[][] { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } });
    }

}
