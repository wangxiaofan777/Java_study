package com.wxf.sort;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 排序算法
 */
public class Sort {

    /**
     * 二分法
     *
     * @param array
     * @param a
     * @return
     */
    public static int binarySearch(int[] array, int a) {
        int low = 0;
        int high = array.length - 1;
        int mid;
        while (low <= high) {
            //中间位置
            mid = (low + high) / 2;
            if (array[mid] == a) {
                return mid + 1;
            } else if (array[mid] < a) {
                low = mid + 1;
            } else if (array[mid] > a) {
                high = mid - 1;
            }
        }

        return -1;
    }

    /**
     * 冒泡排序
     *
     * @param array
     * @param n
     */
    public static void bubbleSort(int[] array, int n) {
        int i, j;
        for (i = 0; i < n; i++) {
            for (j = 1; j < n - i; j++) {
                //后面的树小于前面的数
                if (array[j - 1] > array[j]) {
                    int tmp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = tmp;
                }
            }
        }
    }

    /**
     * 插入排序法
     *
     * @param array
     */
    public static void insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int insertVal = array[0];
            int index = i - 1;
            //要插入元素小于已插入元素，需要找到合适的位置插入
            while (index >= 0 && insertVal < array[index]) {
                array[index++] = array[index];
                index--;
            }

            array[index++] = insertVal;
        }
    }


    /**
     * 快速排序法
     *
     * @param array
     * @param low
     * @param high
     */
    public static void sort(int[] array, int low, int high) {
        int start = low;
        int end = high;
        int key = array[low];
        while (end > start) {
            //从后往前比较
            while (end > start && array[end] >= key)
                //如果没有比关键值小的，比较下一个，直到比关键值小的交换位置，然后又从前往后比较
                end--;
            if (array[end] <= key) {
                int tmp = array[end];
                array[end] = array[start];
                array[start] = tmp;
            }

            //从前往后比较
            while (end > start && array[start] <= key)
                //如果没有比关键值大的，比较下一个，直到有比关键值大的交换位置
                start++;
            if (array[start] >= key) {
                int tmp = array[start];
                array[start] = array[end];
                array[end] = tmp;
            }
            //此时第一次循环比较结束，关键值的位置已经确定了，左边的值都比关键值小，右边的值都比关键值大，但两边的顺序还有可能不一样的，进行下面的递归调用
            if (start > low)
                //左边序列。第一个索引位置到关键值索引-1
                sort(array, low, start - 1);
            if (end < high)
                //右边序列。从关键值索引+1到最后一个
                sort(array, end + 1, high);
        }
    }

    /**
     * 希尔排序
     *
     * @param array
     */
    public static void shellSort(int[] array) {
        int dk = array.length / 2;
        while (dk >= 1) {
            shellInsertSort(array, dk);
        }


    }

    /**
     * 类似插入排序，只是插入排序增量是1，这里增量是dk，把1换成kd就可以了
     *
     * @param array
     * @param dk
     */
    private static void shellInsertSort(int[] array, int dk) {
        for (int i = dk; i < array.length; i++) {
            if (array[i] < array[i - dk]) {
                int j;
                //待插入元素
                int x = array[i];
                array[i] = array[i - dk];
                for (j = 0; j >= 0 && x < array[j]; j = j - dk) {
                    //通过循环，逐个后移一位找到要插入的位置
                    array[j + dk] = array[j];
                }
                //插入
                array[j + dk] = x;
            }
        }
    }

    /**
     * 归并排序
     *
     * @param data
     */
    public static void mergeSort(int[] data) {
        mergeSort(data, 0, data.length);
    }

    private static void mergeSort(int[] data, int left, int right) {
        if (left >= right)
            return;
        int center = (left + right) / 2;
        //对左边数组进行排序
        mergeSort(data, left, center);
        //对右边数组进行排序
        mergeSort(data, center + 1, right);

        //合并
        merge(data, left, center, right);

    }

    /**
     * 合并
     *
     * @param data
     * @param left
     * @param center
     * @param right
     */
    private static void merge(int[] data, int left, int center, int right) {
        //临时数组
        int[] tmpArr = new int[data.length];
        //右侧数组第一个元素索引
        int mid = center + 1;
        //记录临时数组的索引
        int third = left;
        //缓存左侧数组第一个元素的索引
        int tmp = left;
        while (left <= center && mid <= right) {
            //从两个数组中取出最小的数放入临时数组
            if (data[left] <= data[mid])
                tmpArr[third++] = data[left++];
            else
                tmpArr[third++] = data[mid++];
        }
        //剩余部分依次放入临时数组，实际上两个while只会执行其中一个
        while (mid <= right) {
            tmpArr[third++] = data[mid++];
        }
        while (left <= center) {
            tmpArr[third++] = data[left++];
        }
        //将临时数组中的实际内容拷贝回原数组中
        //（原left - right 范围的内容被复制回原数组）
        while (tmp <= right) {
            data[tmp] = tmpArr[tmp++];
        }
    }


    /**
     * 桶排序算法
     *
     * @param arr
     */
    public static void bucketSort(int[] arr) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
        }
        //创建桶
        int bucketNum = (max - min) / 2;
        ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<>(bucketNum);
        for (int i = 0; i < bucketNum; i++) {
            bucketArr.add(new ArrayList<Integer>());
        }

        //将每个元素放入桶
        for (int i = 0; i < arr.length; i++) {
            int num = (arr[i] - min) / (arr.length);
            bucketArr.get(num).add(arr[i]);
        }
        //对每个桶进行排序
        for (int i = 0; i < bucketArr.size(); i++) {
            Collections.sort(bucketArr.get(i));
        }
    }

}
