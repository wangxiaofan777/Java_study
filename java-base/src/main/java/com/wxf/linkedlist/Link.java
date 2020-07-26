package com.wxf.linkedlist;

/**
 * 节点
 */
public class Link {

    public int data;
    public Link next;

    public Link(int data) {
        this.data = data;
    }

    public void showLink() {
        System.out.println("{" + data + "}");
    }
}
