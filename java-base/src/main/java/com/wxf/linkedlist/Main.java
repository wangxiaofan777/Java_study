package com.wxf.linkedlist;


public class Main {
    public static void main(String[] args) {
        /*LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }
        linkedList.add(2, 666);
        System.out.println(linkedList);*/

        /*BST<Integer> bst = new BST<>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for (int i = 0; i < nums.length; i++) {
            bst.add(nums[i]);
        }

        bst.preOrder();
        System.out.println();

        System.out.println(bst);*/

        int lastWordLength = getLastWordLength("hello world");
        System.out.println(lastWordLength);

    }
    public static int getLastWordLength(String str) {
        String [] strArr = str.split(" ");
        if(strArr.length == 0)
            return 0;
        System.out.println(strArr.length);
        return strArr[strArr.length -1].length();
    }
}
