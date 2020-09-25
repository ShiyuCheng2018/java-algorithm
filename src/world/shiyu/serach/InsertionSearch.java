package world.shiyu.serach;

import java.util.Arrays;

public class InsertionSearch {
    /**
     * 插值查找算法需要数组有序
     */
    public static void main(String[] args) {

        int[] arr = new int[1000];

        for (int i = 0; i < 1000; i++) { // [1, 2,3 ,4 ....., 100 ]
            arr[i] = i + 1;
        }

        // System.out.println(Arrays.toString(arr));
        int resultIndex = insertionSearch(arr, 0, arr.length - 1, 45);
        System.out.println("目标索引为： "+resultIndex);
    }

    public static int insertionSearch(int[] arr, int left, int right, int findVal) {
        System.out.println("search....");
        /**
         * !!! 由于数组为有序， 如果目标值小于数组最小值或数组最大值， 或left>right， 则无效查找。返回-1.
         * !!! 否则mid值会越界
         * */
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }

        /**
         * !!! 自适应找到mid
         * */
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];

        if (findVal > midVal) { // 向右方向递归查找
            return insertionSearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) { // 向左方向递归查找
            return insertionSearch(arr, left, right - 1, findVal);
        } else {
            // 找到了目标值
            return mid;
        }

    }
}
