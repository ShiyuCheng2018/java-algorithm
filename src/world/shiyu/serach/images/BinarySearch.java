package world.shiyu.serach.images;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch {
    /**
     * 使用二分查找对前提是该数组有序的
     */

    public static void main(String[] args) {
        int arr_1[] = {1, 8, 10, 89, 1000, 1234};
        int arr_2[] = {1, 8, 10, 1000, 1000, 1000, 1000, 1234, 5566};
        int resultIndex_1 = binarySearch(arr_1, 0, arr_1.length - 1, 1000);
        List<Integer> resultIndex_2 = binarySearchAll(arr_2, 0, arr_2.length - 1, 1000);
        System.out.println(" binarySearch: " + resultIndex_1);
        System.out.println(" binarySearchAll: " + resultIndex_2);
    }


    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        // 当left > right 时， 说明递归整个数组， 但是没有找到
        if (left > right) {
            return -1;
        }

        int mid = (left + right) / 2;
        int midVal = arr[mid];


        if (findVal > midVal) {
            // 向右递归
            return binarySearch(arr, mid + 1, right, findVal);

        } else if (findVal < midVal) { // 向左递归
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }

    public static ArrayList<Integer> binarySearchAll(int[] arr, int left, int right, int findVal) {


        // 当left > right 时， 说明递归整个数组， 但是没有找到
        if (left > right) {
            return new ArrayList<Integer>();
        }

        int mid = (left + right) / 2;
        int midVal = arr[mid];


        if (findVal > midVal) {
            // 向右递归
            return binarySearchAll(arr, mid + 1, right, findVal);

        } else if (findVal < midVal) { // 向左递归
            return binarySearchAll(arr, left, mid - 1, findVal);
        } else {
            /**
             * 思路：
             * 1. 找到mid值， 不要马上返回
             * 2. 向mid索引值的左边扫描， 将所有满足1000的元素下标，加入到一个集合中ArrayList
             * 3. 向mid索引值的右边扫描， 将所有满足1000， 的元素的下标， 加入集合中ArrayList
             * 4， 将ArrayList 返回
             * */
            ArrayList<Integer> resultIndexList = new ArrayList<Integer>();

            // 向mid索引值的左边扫描， 将所有满足1000的元素的下标加入到集合ArrayList
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findVal) {
                    // 退出
                    break;
                }
                // 否则，就把temp放入进集合中
                resultIndexList.add(temp);
                temp -= 1; // temp左移
            }
            resultIndexList.add(mid);
            // 向mid索引值的右边扫描， 将所有满足1000的元素的下标加入到集合ArrayList
            temp = mid + 1;

            while (true) {
                if (temp > arr.length - 1 || arr[temp] != findVal) {
                    // 退出
                    break;
                }
                // 否则，就把temp放入进集合中
                resultIndexList.add(temp);
                temp += 1; // temp左移
            }

            return resultIndexList;

        }

    }
}
