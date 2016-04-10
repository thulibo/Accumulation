package dataStructure;

/**
 * 自定义的链表类<BR>
 * 实现链表常用方法：add、get、reverse等
 * 
 * @author libo <br>
 * E-mail:libo09@mails.tsinghua.edu.cn
 * @date 创建时间：2016年4月10日 下午6:23:21
 * @version 1.0
 */
public class LinkedList
{
	/**
	 * 链表头
	 */
	private Node head;

	/**
	 * 链表尾
	 */
	private Node tail;

	/**
	 * 链表大小
	 */
	private int size;

	/**
	 * 向链表插入一个节点
	 * 
	 * @param node
	 * @return
	 */
	public boolean add(Node node)
	{
		if (null == head)
		{
			head = node;
			tail = node;
			size = 1;
			return true;
		}

		tail.next = node;
		tail = node;
		size++;
		return true;
	}

	/**
	 * 获取指定下标的节点
	 * 
	 * @param i
	 * @return
	 */
	public Node get(int i)
	{
		if (i < 0 || i >= size)
		{
			throw new IndexOutOfBoundsException("index out of bounds!");
		}

		Node destination = head;
		for (int j = 1; j <= i; j++)
		{
			destination = destination.next;
		}

		return destination;
	}

	/**
	 * 获取链表大小
	 * 
	 * @return
	 */
	public int size()
	{
		return size;
	}

	/**
	 * 反转链表
	 * 
	 * @return
	 */
	public LinkedList reverse()
	{
		Node currentNode = null;
		Node nextNode = head;

		tail = head;
		while (null != nextNode)
		{
			Node tmpNode = nextNode.getNext();
			nextNode.setNext(currentNode);
			currentNode = nextNode;
			nextNode = tmpNode;
		}
		head = currentNode;

		return this;
	}

	/**
	 * 链表节点<BR>
	 * 这里为了简便直接使用内部类
	 * 
	 * @author libo
	 *
	 */
	public static class Node
	{
		/**
		 * 节点值
		 */
		private String valueStr;

		/**
		 * 下一节点
		 */
		private Node next;

		public Node()
		{

		}

		public Node(String valueStr)
		{
			this.valueStr = valueStr;
			this.next = null;
		}

		public String getValueStr()
		{
			return valueStr;
		}

		public void setValueStr(String valueStr)
		{
			this.valueStr = valueStr;
		}

		public Node getNext()
		{
			return next;
		}

		public void setNext(Node next)
		{
			this.next = next;
		}
	}

	public static void main(String[] args)
	{
		LinkedList list = new LinkedList();
		list.add(new Node("node_1"));
		list.add(new Node("node_2"));
		list.add(new Node("node_3"));
		list.add(new Node("node_4"));

		System.out.println("The original list is: ");
		for (int i = 0; i < list.size(); i++)
		{
			System.out.println("index=" + i + ", nodeValue=" + list.get(i).getValueStr());
		}
		System.out.println();

		list.reverse();

		System.out.println("The reversed list is: ");
		for (int i = 0; i < list.size(); i++)
		{
			System.out.println("index=" + i + "; nodeValue=" + list.get(i).getValueStr());
		}
		System.out.println();
	}
}
