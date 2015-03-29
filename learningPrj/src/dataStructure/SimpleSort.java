package dataStructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SimpleSort
{
	public static void insertSort(Integer[] inputArray)
	{
		if (inputArray == null)
		{
			System.err.println("输入的待排序数组为空！");
			return;
		}

		if (inputArray.length == 1)
		{
			return;
		}

		for (int i = 1; i < inputArray.length; i++)
		{
			int temp = inputArray[i];

			int j = i - 1;
			for (; j >= 0; j--)
			{
				if (inputArray[j] > temp)
				{
					inputArray[j + 1] = inputArray[j];
				}
				else
				{
					break;
				}
			}

			inputArray[j + 1] = temp;
		}
	}

	public static void main(String[] args)
	{
		System.out.println("=======测试开始=======");
		System.out.println("请输入一组整数数组：");

		Scanner scanner = new Scanner(System.in);
		List<Integer> inputInts = new ArrayList<Integer>();

		while (scanner.hasNextInt())
		{
			Integer eachInputInt = scanner.nextInt();
			inputInts.add(eachInputInt);
			System.out.println(eachInputInt);
		}
		//		scanner.close();
		if (scanner.hasNextLine())
		{
			Integer[] inputArray = inputInts.toArray(new Integer[0]);

			insertSort(inputArray);

			System.out.println("=========通过插入法排序后========");
			for (int j = 0; j < inputInts.size(); j++)
			{
				System.out.println(inputArray[j]);
			}
		}
	}
}
