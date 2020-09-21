package world.shiyu.sort;

import java.util.Arrays;

public class RadixSort {

    public static void main(String[] args) {
        int arr[] = {53, 3, 542, 748, 14, 214};
        System.out.println("原数组： " + Arrays.toString(arr));
//        logic(arr);
        radixSort(arr);
    }

    public static void logic(int[] arr) {

        /** 第一轮排序（针对每个元素的个位进行排序处理）
         *  定义一个二维数组，表示10个桶， 每个桶代表一个一维数组
         *  说明：
         *  1， 二维数组包含10个一维数组
         *  2， 为了防止数的时候数据溢出， 则每个一维数组（桶）， 大小定为arr.length
         *  3， 很明显基数排序为空间换时间的经典算法
         * */
        int[][] bucket = new int[10][arr.length];

        /** 为了记录每个桶中实际存放了多少个数据， 我们定义一个一维数组来记录各个桶的每次放入的数据个数
         * 可以这样理解，
         * 比如： bucketElementCounts[0], 记录的就是bucket[0] 桶放入的数据个数
         * */
        int[] bucketElementCounts = new int[10];

        // 第一轮（针对每个元素的个位进行排序处理
        for (int j = 0; j < arr.length; j++) {
            // 取出每个元素的个位的值
            int digitOfElement = arr[j] % 10;
            // 放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++; // 所对应的桶子记录加一
        }
        // 按照这个桶的顺序（一维数组的下标依次取出数据， 放入原来的数组）

        int index = 0;
        // 遍历每一个桶， 并将桶里的数据放入进原数组中
        for (int k = 0; k < bucketElementCounts.length; k++) {
            // 如果桶中， 有数据， 我们才放进到原数组
            if (bucketElementCounts[k] != 0) {
                // 循环该桶即第K个桶（即第k个一维数组）里的所有数据
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    // 取出元素放入到arr
                    arr[index] = bucket[k][l];
                    index++;
                }
            }
            // 第一轮处理后需要将每个bucketElementCounts[k] = 0
            bucketElementCounts[k] = 0;
        }

        System.out.println("第一轮： " + Arrays.toString(arr));

        /*********************** 第二轮 **********************/

        for (int j = 0; j < arr.length; j++) {
            int digitOfElement = arr[j] / 10 % 10;
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++; // 所对应的桶子记录加一
        }
        index = 0;
        for (int k = 0; k < bucketElementCounts.length; k++) {
            if (bucketElementCounts[k] != 0) {
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    arr[index] = bucket[k][l];
                    index++;
                }
            }
            // 第一轮处理后需要将每个bucketElementCounts[k] = 0
            bucketElementCounts[k] = 0;
        }

        System.out.println("第二轮： " + Arrays.toString(arr));

        /*********************** 第三轮 （最后一轮， 因为数据最大位数为百位数） **********************/

        for (int j = 0; j < arr.length; j++) {
            int digitOfElement = arr[j] / 100 % 10;
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++; // 所对应的桶子记录加一
        }
        index = 0;
        for (int k = 0; k < bucketElementCounts.length; k++) {
            if (bucketElementCounts[k] != 0) {
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    arr[index] = bucket[k][l];
                    index++;
                }
            }
            // 第一轮处理后需要将每个bucketElementCounts[k] = 0
            bucketElementCounts[k] = 0;
        }

        System.out.println("第三轮： " + Arrays.toString(arr));
    }

    public static void radixSort(int arr[]) {
        int[][] bucket = new int[10][arr.length];
        int[] bucketElementCounts = new int[10];

        /** 根据前面的推导过程， 我们可以得到最终算法代码
         * 1， 得到数组中最大的数的位数
         * */
        int max = arr[0]; // 假设第一数就是最大数
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int maxLength = (max + "").length();

        // 使用循环
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {

            for (int j = 0; j < arr.length; j++) {
                int digitOfElement = arr[j] / n % 10;
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++; // 所对应的桶子记录加一
            }

            int index = 0;
            for (int k = 0; k < bucketElementCounts.length; k++) {
                if (bucketElementCounts[k] != 0) {
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        // 取出元素放入到arr
                        arr[index] = bucket[k][l];
                        index++;
                    }
                }
                bucketElementCounts[k] = 0;
            }

            System.out.printf("第%d轮：%s ", i+1, Arrays.toString(arr));
            System.out.println();

        }
    }
}
