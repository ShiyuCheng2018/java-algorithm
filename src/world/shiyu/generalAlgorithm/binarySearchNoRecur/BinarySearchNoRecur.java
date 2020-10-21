package world.shiyu.generalAlgorithm.binarySearchNoRecur;

public class BinarySearchNoRecur {
    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 5, 8, 23, 25, 56, 58, 70, 88};
        System.out.println("目标index为: " + binarySearch(arr, 70));
    }

    /**
     * 二分查找的非递归实现
     *
     * @param target 需要查找的数
     * @param arr    待查询数组, 默认升序排列
     * @return 返回目标index
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                right = mid - 1; // 向左查找
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }
}
