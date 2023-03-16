package sort;

/**
 * 插入排序
 */
public class InsertionSort {


    /**
     * 数组n位置元素插入到前面的数组
     */
    private static void insert(int[] arr, int n) {
        int key = arr[n];
        int i = n;
        while (arr[i - 1] > key) {
            arr[n] = arr[n - 1];
            i--;
            if (i == 0) {
                break;
            }
        }
        arr[i] = key;
    }


    public static void insertionSort(int[] arr, int n) {
        for (int i = 1; i < n; i++) {
            insert(arr, i);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 6, 3, 8, 2, 9};
        insertionSort(arr, arr.length);
        for (int e : arr) {
            System.out.println(e);
        }
    }
}
