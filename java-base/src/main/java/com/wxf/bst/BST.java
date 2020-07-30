package com.wxf.bst;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二分搜索树
 *
 * @param <E>
 */
public class BST<E extends Comparable<E>> {

    private class Node {
        public E e;
        public Node left, right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    //添加节点
    public void add(E e) {
        /*if (root == null) {
            root = new Node(e);
            size++;
        } else {
            add(root, e);
        }*/
        root = add(root, e);
    }


    //递归插入数据

    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        }
        return node;
    }
    /*private void add(Node node, E e) {
        if (e.equals(node.e)) {
            return;
        } else if (e.compareTo(node.e) < 0 && node.left ==  null) {
            node.left = new Node(e);
            size++;
            return;
        } else if (e.compareTo(node.e) < 0 && node.right == null) {
            node.right = new Node(e);
            size++;
            return;
        }

        if (e.compareTo(node.e) < 0) {
            add(node.left, e);
        } else {
            add(node.right, e);
        }
    }*/

    //查找
    public boolean contains(E e) {
        return contains(root, e);
    }

    private boolean contains(Node node, E e) {
        if (node == null)
            return false;
        if (e.equals(node.e)) {
            return true;
        } else if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else {
            return contains(node.right, e);
        }
    }

    //前序遍历
    public void preOrder() {
        preOrder(root);
    }

    //递归
    private void preOrder(Node node) {
        if (node == null)
            return;

        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }


    //使用非递归方式实现前序遍历
    public void preOrderNR() {
        Stack<Node> stack = new Stack<Node>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println(cur.e);

            if (cur.right != null)
                stack.push(cur.right);
            if (cur.left != null)
                stack.push(cur.left);
        }

    }


    //中序遍历
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null)
            return;

        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }


    //后续遍历
    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node node) {
        if (node == null)
            return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    //层序遍历，广度优先级遍历
    public void levelOrder() {
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node cur = queue.remove();
            System.out.println(cur.e);

            if (cur.left != null)
                queue.add(cur.left);
            if (cur.right != null)
                queue.add(cur.right);
        }
    }

    //找最小值
    public E minimum() throws IllegalAccessException {
        if (size == 0)
            throw new IllegalAccessException("BST is Empty!");

        return minimum(root).e;
    }

    //递归查找最小值节点
    private Node minimum(Node node) {
        if (node.left == null)
            return node;

        return minimum(node.left);
    }

    //找最大值
    public E max() throws IllegalAccessException {
        if (size == 0)
            throw new IllegalAccessException("BST is Empty!");

        return max(root).e;
    }

    //递归找最大值
    private Node max(Node node) {
        if (node.right == null)
            return node;

        return max(node.right);
    }


    /**
     * 删除最小值
     *
     * @return
     */
    public Node removeMin() {
        Node node = minimum(root);

        root = removeMin(root);
        return node;
    }

    /**
     * 删除掉以node为根的二分搜索树中的最小节点
     * 返回删除后新的二分搜索树的根
     *
     * @param node
     * @return
     */
    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }


    /**
     * 删除元素为e的节点
     *
     * @param e
     */
    public void remove(E e) {
        root = remove(root, e);
    }

    /**
     * 删除以node为根的二分搜索树中值为e的节点，递归算法
     * 返回删除后的新的二分搜索树的跟
     *
     * @param node
     * @param e
     */
    private Node remove(Node node, E e) {

        if (node == null)
            return null;

        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
            return node;
        } else {//e == node.e

            //待删除节点左子树为空
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            //待删除节点右子树为空
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }


            //待删除节点左右子树都不为空的情况
            //找到比待删除节点大的最小节点，即待删除节点右子树的最小节点
            //用这个节点顶替待删除节点的位置
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;

            return successor;

        }

    }

    /**
     * 删除掉最大值
     *
     * @return
     */
    public Node removeNax() {
        Node node = max(root);
        root = removeNax(root);
        return node;
    }


    /**
     * 删除掉以node为根的二分搜索树中最大节点
     * 返回删除后新的二分搜索树的跟
     *
     * @param node
     * @return
     */
    private Node removeNax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }

        node.right = removeNax(node.right);
        return node;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        generateBSTString(root, 0, sb);
        return sb.toString();
    }

    //生产以node为根节点，深度为depth的描述二叉树的字符串
    private void generateBSTString(Node node, int depth, StringBuilder sb) {

        if (node == null) {
            sb.append(generateDepthString(depth) + "null\n");
            return;
        }

        sb.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, sb);
        generateBSTString(node.right, depth + 1, sb);
    }

    private String generateDepthString(int depth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append("--");
        }
        return sb.toString();
    }
}
