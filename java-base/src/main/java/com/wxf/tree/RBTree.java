package com.wxf.tree;


/**
 * 红黑树
 *
 * @param <K>
 * @param <V>
 */
public class RBTree<K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        public K key;
        public V value;
        public Node left, right;
        public boolean color;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            this.color = RED;
        }
    }

    private Node root;
    private int size;

    public RBTree() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    //判断node颜色
    public boolean isRed(Node node) {
        if (node == null)
            return BLACK;
        return node.color;
    }

    //左旋
    private Node leftRotate(Node node) {
        Node rightNode = node.right;

        node.right = rightNode.left;
        rightNode.left = node;

        rightNode.color = node.color;
        node.color = RED;

        return rightNode;
    }

    //右旋
    private void flipColors(Node node) {

        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    //颜色反转
    private Node rightRotate(Node node) {
        Node leftNode = node.left;

        node.left = leftNode.right;
        leftNode.right = node;

        leftNode.color = node.color;
        node.color = RED;

        return leftNode;
    }

    //向二分搜索树中添加新元素
    public void add(K key, V value) {
        root = add(root, key, value);
        //最终根节点为黑色节点
        root.color = BLACK;
    }

    //递归添加
    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }
        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else {
            node.value = value;
        }

        if (isRed(node.right) && !isRed(node.left)) {
            node = leftRotate(node);
        }

        if (isRed(node.left) && isRed(node.left.left))
            node = rightRotate(node);

        if (isRed(node.left) && isRed(node.right))
            flipColors(node);

        return node;
    }
}
