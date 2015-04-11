package dataStruction;

import java.util.Random;

/**
*
*@author bjlibo
*
*@version 创建时间：2015年3月17日 上午10:15:32
*
*/

public class BinaryTree
{
	private Node root;

	public BinaryTree(Node initRootNode)
	{
		root = initRootNode;
	}

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

	public void inOrder(Node currentNode)
	{
		if (currentNode == null)
		{
			return;
		}
		else
		{
			inOrder(currentNode.leftChildNode);
			System.out.println("Node: key=" + currentNode.keyData + ", data=" + currentNode.data);
			inOrder(currentNode.rightChildNode);
		}
	}

	public void inOrderAll()
	{
		this.inOrder(root);
	}

	public static void main(String[] args)
	{
		BinaryTree binaryTreeExample = new BinaryTree(new Node(15, 0.1f));

		Random random = new Random(System.currentTimeMillis());
		for (int i = 0; i < 10; i++)
		{
			binaryTreeExample.insert(random.nextInt(99), random.nextFloat());
		}

		binaryTreeExample.inOrderAll();
	}
}

class Node
{
	public int keyData;//二叉搜索树的键值
	public float data;//存储的数据（更广义的应该是数据对象）

	public Node(int keyDate, float data)
	{
		this.keyData = keyDate;
		this.data = data;
	}

	public Node leftChildNode;
	public Node rightChildNode;
}