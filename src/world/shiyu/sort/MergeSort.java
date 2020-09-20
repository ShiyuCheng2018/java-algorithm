package world.shiyu.sort;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {

        int arr[] = {83, 42, 56, 75, 12, 31, 67, 23};
        int temp[] = new int[arr.length]; // 归并排序需要一个额外的空间
        System.out.println("原数组: "+ Arrays.toString(arr));
        mergeSort(arr, 0, arr.length-1, temp);
        System.out.println("合并后: "+ Arrays.toString(arr));

    }

    /**
     * 分+合方法
     */

    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) { // 说明可以继续分
            int mid = (left + right) / 2; // 中间索引
            // 向左递归进行分解
            mergeSort(arr, left, mid, temp);
            // 向右递归索引
            mergeSort(arr, mid + 1, right, temp);
            // 合并
            merge(arr, left, mid, right, temp);
        }
    }


    /**
     * 合并方法
     * <p>
     * arr: 排序的原始数组
     * left: 左边索引
     * mid: 中间索引
     * right: 右边索引
     * temp: 中转的数组
     */

    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left; // 初始化i, 左边有序序列的初始索引
        int j = mid + 1; // 初始化j， 右边有序序列的初始索引
        int t = 0; // 指向temp数组的当前索引

        /**
         *  1， 先把左右俩边的（有序）数组按照规则注入进temp数组
         *      直到左右俩边的有序序列其中一边处理完毕
         */

        while (i <= mid && j <= right) {
            /**
             * 如果左边的有序序列的当前元素，小于等于右边有序序列的当前元素
             * 即将左边的当前元素， 拷贝到temp数组
             * 然后t++， i++
             * */

            if (arr[i] <= arr[j]) {
                temp[t] = arr[i]; // 添值
                t += 1; // temp数组指针后移
                i += 1; // 左边数组索引后移
            } else {
                // 反之， 将右边的有序序列的当前元素填充到temp数组
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }

        /** 2, 把有剩余的数据的一边数组元素依次全部注入进去temp
         * */

        while (i <= mid) { // 左边的有序序列还有剩余元素， 全部填充进temp
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }

        while (j <= right) { // 右边的有序序列还有剩余元素， 全部填充进temp
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }

        /** 3, 将temp数组的元素拷贝到arr
         *  注意： 并不是每次都拷贝所有
         * */
        t = 0;
        int tempLeft = left;
        /**
         * 第一次合并tempLeft = 0, right = 1 // tempLeft = 2, right = 3 // tempLeft = 0, right = 3
         * 最后一次tempLeft = 0, right = 7
         * */
        System.out.println("compare arr[tempLeft]: " + arr[tempLeft] + " arr[right]: "+arr[right] );
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }
    }
}
