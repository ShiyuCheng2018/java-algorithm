package world.shiyu.practicalTree;

import java.util.Arrays;

public class HeapSortDemo {
    public static void main(String[] args) {

        // 要求将数组进行升序排列
        int arr[] = {4, 6, 8, 5, 9};
        System.out.println("原数组： "+Arrays.toString(arr));
        HeapSort(arr);
        System.out.println("排序后： "+Arrays.toString(arr));

    }

    public static void logic(int arr[]) {
        System.out.println("堆排序： ");

        // 分步完成
        adjustHeap(arr, 1, arr.length);
        System.out.println("第一次： " + Arrays.toString(arr)); // 4, 9, 8, 5, 6

        adjustHeap(arr, 0, arr.length);
        System.out.println("第一次： " + Arrays.toString(arr)); // 9, 6, 8, 5, 4

    }

    public static void HeapSort(int arr[]) {
        int temp = 0;

        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        System.out.println("完成大顶堆： " + Arrays.toString(arr));

        // reduce array size and swap first ele and last ele
        // 将堆顶元素与末尾元素进行交换，使末尾元素最大。然后继续调整堆，再将堆顶元素与末尾元素交换，得到第二大元素。如此反复进行交换、重建、交换。
        for (int arrLength = arr.length - 1; arrLength > 0; arrLength--) {
            // 交换
            swap(arr, 0, arrLength);
            adjustHeap(arr, 0, arrLength); // 继续构建大顶堆
        }

    }

    /**
     * 将一个数组（二叉树）调整成一个大顶堆
     * 功能： 完成将i对应对非叶子节点的树调整成大顶堆， 从左至右，从下至上进行调整。
     * <p>
     * 举例： int arr[] = {4, 6, 8, 5, 9}; => adjustHeap: i = 1 => 得到{4, 9, 8, 5, 6}
     * => adjustHeap: i = 0 => {9, 6, 8, 5, 4}
     *
     * @arr 待排序数组
     * @i 表示非叶子节点的索引
     * @lenth 表示对多少个元素继续调整， length是在逐渐减少的
     */

    public static void adjustHeap(int[] arr, int i, int length) {
        /** 开始调整\
         *  说明
         *  1. pointer = 1 * 2 + 1 * pointer 是指向i 节点的左子节点
         *
         * */
        for (int pointer = i * 2 + 1; pointer < length; pointer = pointer * 2 + 1) {

            if (pointer + 1 < length && arr[pointer] < arr[pointer + 1]) { // (左右至节点对比) 说明左子节点的值小于右子节点
                pointer++; // pointer转向右子节点
            }

            if (arr[pointer] > arr[i]) { // （local父节点与最大的子节点对比), 如果子节点大于父节点
                swap(arr, i, pointer);
                i = pointer; // !!! i 指向pointer, 继续循环比较。 因为排序后可能导致之前的排序结构混乱， i保存之前的pointer索引
            } else {
                break; // !!!
            }
        }
    }

    private static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
