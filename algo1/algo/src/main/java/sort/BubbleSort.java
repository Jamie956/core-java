package sort;

/**
 * 冒泡刨析
 */
public class BubbleSort {
    /**
     * 数组相邻元素比较，大的元素放后面，最后数组前n位的最大值移到n位置
     *
     * @param arr 数组
     * @param n   长度
     */
    public static void bubble(int[] arr, int n) {
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                int temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
    }

    public static void bubbleSort(int[] arr, int n) {
        for (int i = n; i >= 1; i--) {
            bubble(arr, i);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 6, 3, 8, 2, 9};
        bubbleSort(arr, arr.length);
        for (int e : arr) {
            System.out.println(e);
        }
    }
}
