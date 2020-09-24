package world.shiyu.serach.images;


public class BinarySearch {
    /**
     * 使用二分查找对前提是该数组有序的
     */

    public static void main(String[] args) {
        int arr[] = {1, 8, 10, 89, 1000, 1234};
        int resultIndex = binarySearch(arr, 0, arr.length-1, 8);
        System.out.println(resultIndex);
    }


    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        // 当left > right 时， 说明递归整个数组， 但是没有找到
        if (left > right){
            return -1;
        }

        int mid = (left + right) / 2;
        int midVal = arr[mid];


        if (findVal > midVal) {
            // 向右递归
            return binarySearch(arr, mid + 1, right, findVal);

        } else if (findVal < midVal) { // 向左递归
            return binarySearch(arr, left, mid - 1, findVal);
        }else {
            return mid;
        }
    }
}
