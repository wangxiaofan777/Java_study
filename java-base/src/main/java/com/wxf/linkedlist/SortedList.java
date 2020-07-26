package com.wxf.linkedlist;

/**
 * 有序链表
 */
public class SortedList {

    private Link first;

    public SortedList() {
        first = null;
    }

    //判断是否为空
    public boolean isEmpty() {
        return first == null;
    }

    //新增几点
    public void insert(int num) {
        Link newNode = new Link(num);
        Link current = first;
        Link previous = null;
        while (current != null && num > current.data) {
            previous = current;
            current = current.next;
        }
        //previous为空，则是空链表，将新节点当做头节点
        //previous不为空，则插入新节点
        if (previous == null) {
            first = newNode;
        } else {
            previous.next = newNode;
        }
        newNode.next = current;
    }

    //头部移除法
    public Link removeFirst() {
        Link temp = first;
        first = first.next;
        return temp;
    }

    //打印节点
    public void showList() {
        Link current = first;
        while (current != null) {
            current.showLink();
            current = current.next;
        }
    }

}
