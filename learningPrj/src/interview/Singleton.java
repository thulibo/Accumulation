package interview;

/**
*
*
*tx一个单例中的类加载时，类的各个成员（方法、代码块、属性）<b>初始化</b>的先后顺序问题!【静态方法->静态方法引用的属性->静态代码块->静态属性】
*<br>本例中，程序的初始化按如下流程走：
*<br>入口static的main方法->Singleton的static方法getInstance()引用了static的instance属性->初始化instance属性，调起了构造方法
*<br>这个时候，static属性count因为没有static方法调用过，所以这个时候还尚未初始化，仍然为默认值0（static的int未初始化时默认为0）
*<br>所以输出为0；且因为是饱汉式单例，循环任意次也只打印一次（instance是static的，只初始化一次）
*<hr>
*
*
*@version 创建时间：2015年4月2日 下午8:59:19
*
*@author bjlibo
*
*/

public class Singleton
{
	//类加载器加载能保证是线程安全的（实际bootstrap是一个native的方法）。并且static属性只会实例化一次！
	private static Singleton instance = new Singleton();

	private static int count = 1;

	private Singleton()
	{
		System.out.println("the attribute count=" + count);
	}

	public static Singleton getInstance()
	{
		return instance;
	}

	public static void main(String[] args)
	{
		for (int i = 0; i < 5; i++)
		{
			Singleton.getInstance();
		}
	}

}
