package dataStructure;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 * 二叉树相关常见操作
 *
 * @author libo <br>
 *         E-mail:libo09@mails.tsinghua.edu.cn
 * @date 创建时间：2015年3月17日 上午10:15:32
 * @version 1.0
 *
 */
public class BinaryTree
{
	private Node root;

	public BinaryTree(Node initRootNode)
	{
		root = initRootNode;
	}

	/**
	 * 向二叉搜索树中插入一个新节点<BR>
	 * 即是构造一颗二叉搜索树
	 * 
	 * @param key
	 * @param data
	 */
	public void insert(int key, float data)
	{
		Node newNode = new Node(key, data);
		if (root == null)
		{
			root = newNode;
		}
		else
		{
			Node current = root;
			Node parent = root;
			boolean isLeftChild = true;

			while (current != null)
			{
				parent = current;

				if (key < current.keyData)
				{
					isLeftChild = true;
					current = current.leftChildNode;
				}
				else
				{
					isLeftChild = false;
					current = current.rightChildNode;
				}
			}

			if (isLeftChild)
			{
				parent.leftChildNode = new Node(key, data);
			}
			else
			{
				parent.rightChildNode = new Node(key, data);
			}
		}
	}

	/**
	 * 递归中序遍历
	 * 
	 * @param currentNode
	 */
	public void inOrder(Node currentNode)
	{
		if (currentNode == null)
		{
			return;
		}

		inOrder(currentNode.leftChildNode);
		System.out.println("Node: key=" + currentNode.keyData + ", data=" + currentNode.data);
		inOrder(currentNode.rightChildNode);
	}

	public void inOrderAll()
	{
		this.inOrder(root);
	}

	/**
	 * 非递归中序遍历（这是一个通用的非递归方法，只需类似递归那样调整顺序即可）
	 * 
	 * @param currentNode
	 */
	public void inOrderNonIncursive(Node currentNode)
	{
		if (null == currentNode)
		{
			return;
		}

		Stack<WrapperedNode> stack = new Stack<WrapperedNode>();
		WrapperedNode currentWrapperedNode = new WrapperedNode(currentNode, false);
		stack.push(currentWrapperedNode);

		while (!stack.isEmpty())
		{
			currentWrapperedNode = stack.pop();

			if (currentWrapperedNode.isVisited)
			{
				System.out.println("Node: key=" + currentWrapperedNode.node.keyData + ", data="
						+ currentWrapperedNode.node.data);
			}
			else
			{
				if (currentWrapperedNode.node.rightChildNode != null)
				{
					stack.push(new WrapperedNode(currentWrapperedNode.node.rightChildNode, false));
				}

				//特别注意：这里不可直接压入currentWrapperedNode，引用后续会改！重新new一个压入
				stack.push(new WrapperedNode(currentWrapperedNode.node, true));

				if (currentWrapperedNode.node.leftChildNode != null)
				{
					stack.push(new WrapperedNode(currentWrapperedNode.node.leftChildNode, false));
				}
			}
		}
	}

	public void inOrderAllNonIncursive()
	{
		inOrderNonIncursive(this.root);
	}

	/**
	 * 分层依次从左到右遍历打印二叉树<BR>
	 * 队列解法
	 */
	public void printDataLayerByLayer()
	{
		// 边界检查
		if (null == root)
		{
			System.out.println("The tree is empty!");
			return;
		}

		List<Node> queue = new LinkedList<Node>();
		// 根节点首先入队列
		queue.add(root);

		// 定位游标
		int cursor = 0;
		// 每一层结束节点下标记录
		int last = queue.size();

		// 当游标小于队列大小时，持续移动
		while (cursor < queue.size())
		{
			// 重新标记新一层的结束节点在队列中的下标
			last = queue.size();
			// 处理该层的节点
			while (cursor < last)
			{
				Node currentNode = queue.get(cursor);
				System.out.println(currentNode.keyData);

				if (null != currentNode.leftChildNode)
				{
					queue.add(currentNode.leftChildNode);
				}
				if (null != currentNode.rightChildNode)
				{
					queue.add(currentNode.rightChildNode);
				}

				// 处理完该节点，游标继续右移
				cursor++;
			}
		}
	}

	/**
	 * 打印从当前节点到末梢叶节点的所有路径
	 * 
	 * @param currentNode
	 */
	public void printEachBranch(Node currentNode, List<Integer> queue, int level)
	{
		if (null == currentNode)
		{
			return;
		}

		queue.add(level, currentNode.keyData);

		if (currentNode.leftChildNode == null && currentNode.rightChildNode == null)
		{
			queue.add(level + 1, -1);

			StringBuilder result = new StringBuilder();
			for (Integer item : queue)
			{
				if (item == -1)
				{
					break;
				}

				result.append(item).append("->");
			}

			System.out.println(result.toString().replaceAll("->$", ""));
			return;
		}

		printEachBranch(currentNode.leftChildNode, queue, level + 1);
		printEachBranch(currentNode.rightChildNode, queue, level + 1);
	}

	public static void main(String[] args)
	{
		BinaryTree binaryTreeExample = new BinaryTree(new Node(15, 0.1f));

		System.out.println("=====the insert order: =====");
		Random random = new Random(System.currentTimeMillis());
		for (int i = 0; i < 10; i++)
		{
			int keyData = random.nextInt(99);
			float data = random.nextFloat();
			// 打印出插入顺序，以便恢复树型
			System.out.println("key: " + keyData + ", value: " + data);

			binaryTreeExample.insert(keyData, data);
		}

		System.out.println("=====in order all (incursive): =====");
		binaryTreeExample.inOrderAll();

		System.out.println("=====in order all (non incursive): =====");
		binaryTreeExample.inOrderAllNonIncursive();

		System.out.println("=====print all layer by layer: =====");
		binaryTreeExample.printDataLayerByLayer();

		System.out.println("=====print all path from root to leaf: =====");
		binaryTreeExample.printEachBranch(binaryTreeExample.root, new LinkedList<Integer>(), 0);
	}
}

/**
 * 二叉树节点
 *
 * @author libo <br>
 *         E-mail:libo09@mails.tsinghua.edu.cn
 * @date 创建时间：2015年3月17日 上午10:15:32
 * @version 1.0
 *
 */
class Node
{
	public int keyData;// 二叉搜索树的键值
	public float data;// 存储的数据（更广义的应该是数据对象）
	public Node leftChildNode;
	public Node rightChildNode;

	public Node(int keyDate, float data)
	{
		this.keyData = keyDate;
		this.data = data;
	}
}

/**
 * 通过组合，包装后的二叉树节点<BR>
 * （基于基本节点数据结构添加一个boolean型属性，用以在借助栈的非递归中序遍历中标识是否已访问）
 *
 * @author libo <br>
 *         E-mail:libo09@mails.tsinghua.edu.cn
 * @date 创建时间：2016年5月3日 上午10:25:32
 * @version 1.0
 */
class WrapperedNode
{
	/**
	 * 用以在借助栈的非递归中序遍历中标识是否已访问
	 */
	public boolean isVisited;
	public Node node;

	public WrapperedNode(Node oriNode, boolean isVisited)
	{
		this.node = oriNode;
		this.isVisited = isVisited;
	}
}
