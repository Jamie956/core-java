package sort;

/*
堆的特点
- 是一棵完全二叉树
- 父节点的值大于子节点

对于索引为i的节点
- parentNodeIndex=(i-1)/2
- leftNodeIndex = 2i+1
- rightNodeIndex = 2i+2

input=[4, 10, 3, 5, 1, 2]
output=[10, 5, 3, 4, 1, 2]
 */
public class HeapSort {
    /*
    递归转化成堆
    - 算出左右节点索引
    - 判断左右节点索引是否越界
    - 在左右节点和父节点中找出最大值
    - 最大值的节点与父节点交换
    - 向下递归

    ree 树的组数，n 数组的长度，要heapify 的索引位置
     */
    public static void heapify(int[] tree, int n, int i) {
        int leftNodeIndex = 2 * i + 1;
        int rightNodeIndex = 2 * i + 2;
        int maxIndex = i;
        if (leftNodeIndex < n && tree[leftNodeIndex] > tree[maxIndex]) {
            maxIndex = leftNodeIndex;
        }
        if (rightNodeIndex < n && tree[rightNodeIndex] > tree[maxIndex]) {
            maxIndex = rightNodeIndex;
        }
        if (maxIndex != i) {
            swap(tree, i, maxIndex);
            //小的数去了 maxIndex 的位置
            heapify(tree, n, maxIndex);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /*
    从下往上heapify
     */
    public static void buildHeap(int[] tree, int n) {
        int lastNodeIndex = n - 1;
        int parentIndex = (lastNodeIndex - 1) / 2;
        for (int i = parentIndex; i >= 0; i--) {
            heapify(tree, n, i);
        }
    }

    /*
    堆排序
    - 根节点与最后节点交换
    - 移除最后节点
    - 根节点 heapify
    - 重复步骤
     */
    public static void heapSort(int[] tree, int n) {
        buildHeap(tree, n);
        for (int i = n - 1; i >= 0; i--) {
            swap(tree, 0, i);
            heapify(tree, i, 0);
        }
    }

    public static void main(String[] args) {
        int[] tree = {4, 10, 3, 5, 1, 2};
//        heapify(tree, 6, 0);
//        for (int e : tree) {
//            System.out.println(e);
//        }

//        buildHeap(tree, 6);
//        for (int e : tree) {
//            System.out.println(e);
//        }

        heapSort(tree, 6);
        for (int e : tree) {
            System.out.println(e);
        }
    }
}
