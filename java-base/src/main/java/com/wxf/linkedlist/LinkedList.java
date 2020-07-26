package com.wxf.linkedlist;

public class LinkedList<E> {

    private class Node {
        //当前节点数据域
        public E e;
        //下个节点
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return "" + e ;
        }
    }

    //虚拟头节点
    private Node dummyHead;
//    private Node head;
    int size;

    public LinkedList() {
        this.dummyHead = new Node(null, null);
//        this.head = null;
        this.size = 0;
    }

    //获取size
    public int getSize() {
        return size;
    }

    //是否为空
    public boolean isEmpty() {
        return size == 0;
    }


    //指定位置插入元素
    public void add(int index, E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed!  Illegal Index!");
        /*if (index == 0) {
            addFirst(e);
        } else {
            Node pre = head;
            //获取到index位置元素的pre节点
            for (int i = 0; i < index - 1; i++) {
//            for (int i = 0; i < index; i++) {
                pre = pre.next;
            }
            *//*Node node = new Node(e);
            node.next = pre.next;
            pre.next = node;*//*

            pre.next = new Node(e, pre.next);
            size++;
        }*/

        Node pre = dummyHead;
        //获取到index位置元素的pre节点
        for (int i = 0; i < index - 1; i++) {
//            for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
            /*Node node = new Node(e);
            node.next = pre.next;
            pre.next = node;*/

        pre.next = new Node(e, pre.next);
        size++;
    }

    //头插法
    public void addFirst(E e) {
        /*Node node = new Node(e);
        node.next = head;
        head = node;*/

        //等价于
//        head = new Node(e, head);
//        size++;

        add(0, e);
    }

    //末尾添加
    public void addLast(E e) {
        add(size, e);
    }

    //获取第一个
    public E getFirst() {
        return get(0);
    }

    //获取最后一个
    public E getLast() {
        return get(size -1);
    }

    //获取第index个位置的元素
    public E get(int index) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Get failed. Illegal index.");

        Node curr = dummyHead.next;
        for (int i = 0; i < index; i++)
            curr = curr.next;
        return curr.e;
    }

    //更新
    public void set(int index, E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Update failed. Illegal index.");

        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.e = e;
    }

    //查找链表中是否有元素e
    public boolean contains(E e) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.e.equals(e))
                return true;
            cur = cur.next;
        }
        return false;
    }

    //删除
    public E remove(int index) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Remove failed. Illegal index.");

        Node prev = this.dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node retNode = prev.next;
        prev.next = retNode.next;
        retNode.next = null;
        size--;

        return retNode.e;
    }

    //删除头部
    public E removeFirst() {
        return remove(0);
    }

    //删除尾部
    public E removeLast() {
        return remove(size - 1);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
//        Node cur = dummyHead.next;
//        while (cur != null) {
//            sb.append(cur + "->");
//            cur = cur.next;
//        }
        for (Node cur = dummyHead.next; cur != null; cur = cur.next)
            sb.append(cur + "->");

        sb.append("NULL");
        return sb.toString();
    }
}
