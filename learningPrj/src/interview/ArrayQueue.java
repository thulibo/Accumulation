package interview;

import java.lang.reflect.Array;

/**
 * 基于array实现queue，提供基本的add/remove/size方法
 * <br>非线程安全的实现
 * 
 * @author libo <br>
 * E-mail:libo09@mails.tsinghua.edu.cn
 * @date 创建时间：2015年4月5日 上午12:54:00
 * @version 1.0
 */
public class ArrayQueue<T>
{
	/**
	 * 内部数组的初始容量，队列满时需要扩容
	 */
	private int capacity = 10;

	/**
	 * 队列元素的数据类型，构造内部泛型数组需要
	 */
	private Class<T> classType;

	/**
	 * 实现队列的内部泛型数组
	 * <br>需要注意：
	 * <br>1.  <font color=red>错误:</font> T[] internalArray = new T[capacity];
	 * <br>2. 因为类型擦除引起的泛型数组与对象数组(类似：Object[] internalArray = new Object[maxSize])在相互转换时引起的几种运行时异常
	 */
	private T[] internalArray;

	/**
	 * 队列头标记
	 */
	private int headIndex = 0;
	/**
	 * 队列尾标记
	 */
	private int tailIndex = 0;

	/**
	 * 默认构造函数，初始容量为10
	 */
	public ArrayQueue(Class<T> classType)
	{
		this.classType = classType;
		this.internalArray = (T[]) Array.newInstance(classType, capacity);
	}

	/**
	 * 可以指定初始容量的构造函数
	 * 
	 * @param capacity
	 */
	public ArrayQueue(Class<T> classType, int capacity)
	{
		this.classType = classType;
		this.capacity = capacity;
		this.internalArray = (T[]) Array.newInstance(classType, this.capacity);
	}

	/**
	 * 出队列，从队列头获取并删除一个元素
	 * 
	 * @return
	 */
	public T remove()
	{
		if (size() == 0)
		{
			throw new EmptyException();
		}

		return internalArray[headIndex++ % capacity];
	}

	/**
	 * 在队列尾插入一个新元素，如果队列满，会默认加倍扩容
	 * 
	 * @param element
	 */
	public void add(T element)
	{
		if (size() == capacity)
		{
			System.out.println("Now, the queue is full! Start to enlarge and copy.");
			enlargeCapacity();
		}

		internalArray[tailIndex++ % capacity] = element;
	}

	/**
	 * 队列满时，对内部数组进行复制和扩容,此处采用容量加倍扩容
	 * <br>可以新加属性扩容指数在构造函数中指定
	 * 
	 */
	private void enlargeCapacity()
	{
		//扩容
		this.capacity *= 2;
		T[] newInternalArray = (T[]) Array.newInstance(this.classType, this.capacity);

		//copy老数组中元素
		int i = 0;
		for (; i < size(); i++)
		{
			newInternalArray[i] = internalArray[(headIndex + i) % internalArray.length];
		}

		//属性调整
		internalArray = newInternalArray;
		headIndex = 0;
		tailIndex = i;
	}

	/**
	 * 队列大小
	 * 
	 * @return
	 */
	public int size()
	{
		return tailIndex - headIndex;
	}

	public static class EmptyException extends RuntimeException
	{
		@Override
		public String getMessage()
		{
			return "Remove failed! The Queue is empty!";
		}
	}

	public static void main(String[] args)
	{
		try
		{
			ArrayQueue<Integer> testQueue = new ArrayQueue<Integer>(Integer.class, 8);

			for (int i = 0; i < 100; i++)
			{
				testQueue.add(i);

				if (i % 2 == 0)
				{
					testQueue.remove();
				}
			}

			System.out.println("testQueue.size=" + testQueue.size());

			int length = testQueue.size();

			for (int i = 0; i < length; i++)
			{
				System.out.println(testQueue.remove() + ", ");
			}
		}
		catch (Exception e)
		{
			System.out.println("悲剧鸟~" + e);
			e.printStackTrace();
		}
	}
}
