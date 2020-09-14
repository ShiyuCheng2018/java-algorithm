package world.shiyu.sort;

import java.lang.reflect.Array;
import java.util.Arrays;

public class SelectionSort {

    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1};
        System.out.println("原数组： " + Arrays.toString(arr));
        // 选择排序的时间复杂度o(n)^2
        selectSort(arr);
    }

    public static void logic(int[] arr) {
        /** 原始数组: 101, 34, 119, 1
         *
         *  第一轮排序: 1, 34, 119, 101
         *
         * */

        // 第一轮
        int minIndex = 0;
        int min = arr[0];
        for (int j = 0 + 1; j < arr.length; j++) { // 找第一轮最小值
            if (min > arr[j]) {
                // 说明假定的最小值， 并不是最小
                min = arr[j]; // 重置min
                minIndex = j; // 重置minIndex
            }
        }

        // 将最小值， 放在arr[0]， 即交换
        if (minIndex != 0) {
            arr[minIndex] = arr[0];
            arr[0] = min;
            minIndex = 1;
        }

        System.out.print("第一轮后： " + Arrays.toString(arr));
        System.out.println();

        // 第二轮

        for (int j = 1 + 1; j < arr.length; j++) { // 找第二轮最小值
            if (min > arr[j]) {
                // 说明假定的最小值， 并不是最小
                min = arr[j]; // 重置min
                minIndex = j; // 重置minIndex
            }
        }

        // 将最小值， 放在arr[0]， 即交换
        if (minIndex != 1) {
            arr[minIndex] = arr[0];
            arr[0] = min;
            minIndex = 2;
        }

        System.out.print("第二轮后： " + Arrays.toString(arr));
        System.out.println();

        // 在推到的过程中， 我们已经发现规律， 因此只需要嵌套就可以解决

    }

    public static void selectSort(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];

            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }

            System.out.printf("第 %d 后： %s", i + 1, Arrays.toString(arr));
            System.out.println();
        }

    }
}
