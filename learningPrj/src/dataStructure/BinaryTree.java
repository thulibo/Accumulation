package dataStructure;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * 二叉树相关常见操作
 *
 * @author libo <br>
 *         E-mail:libo09@mails.tsinghua.edu.cn
 * @date 创建时间：2015年3月17日 上午10:15:32
 * @version 1.0
 *
 */
public class BinaryTree {
    private Node root;

    public BinaryTree(Node initRootNode) {
        root = initRootNode;
    }

    /**
     * 向二叉搜索树中插入一个新节点<BR>
     * 即是构造一颗二叉搜索树
     * 
     * @param key
     * @param data
     */
    public void insert(int key, float data) {
        Node newNode = new Node(key, data);
        if (root == null) {
            root = newNode;
        } else {
            Node current = root;
            Node parent = root;
            boolean isLeftChild = true;

            while (current != null) {
                parent = current;

                if (key < current.keyData) {
                    isLeftChild = true;
                    current = current.leftChildNode;
                } else {
                    isLeftChild = false;
                    current = current.rightChildNode;
                }
            }

            if (isLeftChild) {
                parent.leftChildNode = new Node(key, data);
            } else {
                parent.rightChildNode = new Node(key, data);
            }
        }
    }

    /**
     * 递归中序遍历
     * 
     * @param currentNode
     */
    public void inOrder(Node currentNode) {
        if (currentNode == null) {
            return;
        } else {
            inOrder(currentNode.leftChildNode);
            System.out.println("Node: key=" + currentNode.keyData + ", data=" + currentNode.data);
            inOrder(currentNode.rightChildNode);
        }
    }

    public void inOrderAll() {
        this.inOrder(root);
    }

    /**
     * 分层依次从左到右遍历打印二叉树<BR>
     * 队列解法
     */
    public void printDataLayerByLayer() {
        // 边界检查
        if (null == root) {
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
        while (cursor < queue.size()) {
            // 重新标记新一层的结束节点在队列中的下标
            last = queue.size();
            // 处理该层的节点
            while (cursor < last) {
                Node currentNode = queue.get(cursor);
                System.out.println(currentNode.keyData);

                if (null != currentNode.leftChildNode) {
                    queue.add(currentNode.leftChildNode);
                }
                if (null != currentNode.rightChildNode) {
                    queue.add(currentNode.rightChildNode);
                }

                // 处理完该节点，游标继续右移
                cursor++;
            }
        }
    }

    public static void main(String[] args) {
        BinaryTree binaryTreeExample = new BinaryTree(new Node(15, 0.1f));

        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 10; i++) {
            binaryTreeExample.insert(random.nextInt(99), random.nextFloat());
        }

        System.out.println("in order all: ");
        binaryTreeExample.inOrderAll();

        System.out.println("print all layer by layer: ");
        binaryTreeExample.printDataLayerByLayer();
    }
}

class Node {
    public int keyData;// 二叉搜索树的键值
    public float data;// 存储的数据（更广义的应该是数据对象）

    public Node(int keyDate, float data) {
        this.keyData = keyDate;
        this.data = data;
    }

    public Node leftChildNode;
    public Node rightChildNode;
}