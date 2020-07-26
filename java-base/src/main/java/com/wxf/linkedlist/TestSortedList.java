package com.wxf.linkedlist;

public class TestSortedList {

    public static void main(String[] args) {
        SortedList sortedList = new SortedList();
        sortedList.insert(10);
        sortedList.insert(90);
        sortedList.insert(50);
        sortedList.insert(60);
        sortedList.insert(80);
        sortedList.insert(230);
        sortedList.insert(20);

        sortedList.showList();

    }
}
