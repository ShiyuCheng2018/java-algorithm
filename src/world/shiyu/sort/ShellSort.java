package world.shiyu.sort;

import java.util.Arrays;

public class ShellSort {

    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        System.out.println("原数组： " + Arrays.toString(arr));
//        logic(arr);
//        shellSort(arr);
        shellShiftingSort(arr);

    }

    public static void logic(int[] arr) {
        int temp = 0;

        // 第一轮
        //  因为第一轮排序是将10个数据分成5组
        for (int i = 5; i < arr.length; i++) {
            // 遍历各组中当所有元素（共5组， 每组2个元素），步长为5
            for (int j = i - 5; j >= 0; j -= 5) {
                //  如果当前元素大于加上步长后端那个元素， 说明交换
                if (arr[j] > arr[j + 5]) {
                    temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;
                }
            }
        }

        System.out.println("希尔排序1轮后： " + Arrays.toString(arr));

        // 第二轮
        //  第二轮将10个数据分成5/2 = 2组
        for (int i = 2; i < arr.length; i++) {
            // 遍历各组中当所有元素（共5组， 每组2个元素），步长为5
            for (int j = i - 2; j >= 0; j -= 2) {
                //  如果当前元素大于加上步长后端那个元素， 说明交换
                if (arr[j] > arr[j + 2]) {
                    temp = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j + 2] = temp;
                }
            }
        }

        System.out.println("希尔排序2轮后： " + Arrays.toString(arr));

        // 第三轮
        //  第三轮将10个数据分成2/2 = 1组
        for (int i = 1; i < arr.length; i++) {
            // 遍历各组中当所有元素（共5组， 每组2个元素），步长为5
            for (int j = i - 1; j >= 0; j -= 1) {
                //  如果当前元素大于加上步长后端那个元素， 说明交换
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        System.out.println("希尔排序3轮后： " + Arrays.toString(arr));

    }

    public static void shellSort(int[] arr) {
        // 希尔交换法
        int temp = 0;
        int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                // 遍历各组中当所有元素（共gap组， 每组gap /= 2个元素），步长为gap
                for (int j = i - gap; j >= 0; j -= gap) {
                    //  如果当前元素大于加上步长后端那个元素， 说明交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }

            System.out.printf("希尔排序%d轮后： %s", count++, Arrays.toString(arr));
            System.out.println();
        }

    }

    public static void shellShiftingSort(int[] arr) {
        int count = 0;
        // 增量gap， 并逐步缩小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 从gap个元素， 逐个对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        // 移动
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    // 当退出while后， 就给temp， 找到插入位置
                    arr[j] = temp;
                }
            }

            System.out.printf("希尔排序%d轮后： %s", count++, Arrays.toString(arr));
            System.out.println();
        }
    }
}
