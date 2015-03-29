package interview;

/**
 * @author libo E-mail:libo09@mails.tsinghua.edu.cn
 * @date 创建时间：2015年3月29日 下午7:09:15
 * @version 1.0
 */

/**
 * 有一个n元数组，需要将其中的元素左移i个位置，如果额外存储空间非常有限，请使用尽可能少的存储空间完成这项工作，写出伪代码并给出空间复杂度和时间复杂度
 * 示例：
 * [abcdefg] 左移3位，结果为[defgabc]
 * 
 */
//---------- 下面给出的是空间复杂度为O(1),时间复杂度为O(n)的实现 ----------------

public class ArrayShift
{
	//Algorithm method
	public static char[] leftShiftArray(int i, char[] sourceArray)
	{
		char tmp;
		for (int j = i; j > 0; j--)
		{
			tmp = sourceArray[0];
			for (int m = 0; m < sourceArray.length - 1; m++)
			{
				sourceArray[m] = sourceArray[m + 1];
			}
			sourceArray[sourceArray.length - 1] = tmp;
		}

		return sourceArray;
	}

	//Test case
	public static void main(String[] args)
	{
		String testStr = "abcdefghijklmnopq";
		char[] testCharArray = testStr.toCharArray();
		System.out.println("Before shift, the source char array:" + testStr);
		System.out.println("After left shift 4, the result char array:" + new String(leftShiftArray(4, testCharArray)));
	}
}
