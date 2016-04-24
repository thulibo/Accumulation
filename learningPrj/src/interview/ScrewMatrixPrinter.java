package interview;

/**
 * 顺时针外螺旋矩阵的构造和打印
 * 
 * @author libo <br>
 * E-mail:libo09@mails.tsinghua.edu.cn
 * @date 创建时间：2016年4月24日 下午5:07:36
 * @version 1.0
 */
public class ScrewMatrixPrinter
{

	/**
	 * 生成row*column的顺时针螺旋矩阵<BR>
	 * 采用按螺旋方向依次填充对应的矩阵（2维数组）的方法来构造
	 * 
	 * @param row
	 * @param column
	 */
	public static int[][] genScrewMatrix(int row, int column)
	{
		int[][] matrix = new int[row][column];

		//螺旋一圈的左起点
		int left = 0;
		//螺旋一圈的右终点
		int right = column - 1;
		//螺旋一圈的上起点
		int top = 0;
		//螺旋一圈的下终点
		int bottom = row - 1;

		//矩阵元素从1开始递增，row*column为最后一个元素
		int elementValue = 1;
		while (elementValue <= row * column)
		{
			// step1.螺旋开始，从左到右
			for (int j = left; j <= right; j++)
			{
				matrix[top][j] = elementValue++;
			}
			top++;

			// step2.从上到下
			for (int i = top; i <= bottom; i++)
			{
				matrix[i][right] = elementValue++;
			}
			right--;

			// step3.从右到左
			for (int j = right; j >= left; j--)
			{
				matrix[bottom][j] = elementValue++;
			}
			bottom--;

			// step4.从下到上
			for (int i = bottom; i >= top; i--)
			{
				matrix[i][left] = elementValue++;
			}
			left++;
		}

		return matrix;
	}

	/**
	 * 打印输出矩阵matrix
	 * 
	 * @param matrix
	 */
	public static void printMatrix(int[][] matrix)
	{
		if (null == matrix)
		{
			System.out.println("input matrix is null!");
			throw new IllegalArgumentException("input matrix is null!");
		}

		for (int i = 0; i < matrix.length; i++)
		{
			for (int j = 0; j < matrix[i].length; j++)
			{
				System.out.print(matrix[i][j]);

				if (j < matrix[i].length - 1)
				{
					System.out.print("\t");
				}
			}

			System.out.println();
		}
	}

	/**
	 * main test
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		printMatrix(genScrewMatrix(5, 5));
	}

}
