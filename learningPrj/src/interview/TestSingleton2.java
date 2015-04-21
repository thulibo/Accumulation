package interview;

/**
*和tx的那个饿汉单例对应，这里是一个懒汉单例
*<br>主要目的是为了突出防止编译优化而使用volatile
*
*@author bjlibo
*@version 创建时间：2015年4月21日 上午11:36:38
*
*/

class Singleton2
{
	private volatile static Singleton2 instance = null;

	private static int count = 1;

	private Singleton2()
	{
		count++;
		System.out.println("count=" + count);
	}

	public static Singleton2 getInstance()
	{
		if (instance == null)
		{
			synchronized (Singleton2.class)
			{
				if (instance == null)
				{
					try
					{
						Thread.currentThread().sleep(20);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}

					instance = new Singleton2();
				}
			}
		}

		return instance;
	}
}

class TestThread implements Runnable
{
	@Override
	public void run()
	{
		System.out.println("The running thread:" + Thread.currentThread().getName());
		Singleton2.getInstance();
	}
}

public class TestSingleton2
{
	public static void main(String[] args)
	{
		for (int i = 0; i < 36; i++)
		{
			new Thread(new TestThread(), "TestThread_" + i).start();
		}
	}
}
