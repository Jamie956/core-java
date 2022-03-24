package com.sort;

public class MergeSort {
    /**
     * 数组切分成两个有序子数组，再对子数组合并
     * 输入数组按输入边界切分成2个有序数组，再对他们进行合并，合并后是一个有序数组
     *
     * @param arr 要排序的数组，包含2个有序的序列
     * @param l   左边界，左边界到中间点 的各个数组元素有序
     * @param m   中间点
     * @param r   右边界，中间点到右边界 的各个数组元素有序
     */
    public static void merge(int[] arr, int l, int m, int r) {
        //切分的有序左数组大小
        int leftSize = m - l;
        //切分的有序右数组大小
        int rightSize = r - m + 1;
        //有序左数组
        int[] left = new int[leftSize];
        //有序右数组
        int[] right = new int[rightSize];

        //遍历输入数组存到左数组
        for (int i = l; i < m; i++) {
            left[i - l] = arr[i];
        }
        //遍历输入数组存到右数组
        for (int i = m; i <= r; i++) {
            right[i - m] = arr[i];
        }

        //i j分别是左右数组指针，合并时使用；
        //k是合并数组的指针
        int i = 0;
        int j = 0;
        int k = l;

        //合并左右数组
        while (i < leftSize && j < rightSize) {
            if (left[i] < right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        //左右数组长度不相等时，需要合并剩下还没合并的元素
        while (i < leftSize) {
            arr[k++] = left[i++];
        }
        while (j < rightSize) {
            arr[k++] = right[j++];
        }
    }

    /**
     * 递归切分合并排序
     *
     * @param arr 需要排序的数组
     * @param l   数组要排序的起始位置
     * @param r   数组要排序的结束位置
     */
    public static void mergeSort(int[] arr, int l, int r) {
        //递归中止条件
        if (l == r) {
            return;
        }
        //计算中点
        int m = (l + r) / 2;
        mergeSort(arr, l, m);
        mergeSort(arr, m + 1, r);
        merge(arr, l, m + 1, r);

    }

    public static void testMerge() {
        int[] arr = {2, 8, 9, 10, 4, 5, 6, 7};
        int l = 0;
        int r = 7;
        int m = 4;
        merge(arr, l, m, r);
        for (int e : arr) {
            System.out.println(e);
        }
    }

    public static void testMergeSort() {
        int[] arr = {6, 8, 10, 9, 4, 5, 2, 7};

        mergeSort(arr, 0, 7);
        for (int e : arr) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
//        testMerge();
        testMergeSort();
    }
}
