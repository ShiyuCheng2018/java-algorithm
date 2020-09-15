package world.shiyu.sort;

import java.util.Arrays;

public class InsertionSort {

    public static void main(String[] args){
        int[] arr = {101, 34, 119, 1};
        System.out.println("原数组: "+ Arrays.toString(arr));
        InsertionSort(arr);

    }


    public static void logic(int[] arr){

        // 第一轮 {101, 34, 119, 1} => {34, 101, 119, 1}

        // 定义带插入带数
        int insertVal = arr[1];
        int insertIndex = 1 - 1; // arr[1]的前面这个数的下标

        // 给insertVal 找到插入位置
        // 1. insertIndex >= 0 保证在给insertVal 找插入位置， 不越界
        // 2. insertVal < arr[insertIndex] 待插入待数， 还没有找到插入位置
        // 3。 就需要将arr[insertIndex]后移
        while(insertIndex >= 0 && insertVal < arr[insertIndex]){
            arr[insertIndex + 1] = arr[insertIndex]; //
            insertIndex--;
        }
        // 当推出while循环时， 说明插入当位置找到， insertIndex + 1;
        arr[insertIndex + 1] = insertVal;

        System.out.println("第一轮循环: "+ Arrays.toString(arr));

        // 第二轮
        insertVal = arr[2];
        insertIndex = 2 - 1; // arr[2]的前面这个数的下标
        while(insertIndex >= 0 && insertVal < arr[insertIndex]){
            arr[insertIndex + 1] = arr[insertIndex]; //
            insertIndex--;
        }
        arr[insertIndex + 1] = insertVal;
        System.out.println("第二轮循环: "+ Arrays.toString(arr));

    }

    public static void InsertionSort(int[] arr){

        for (int i = 1; i < arr.length; i++){
            int insertVal = arr[i];
            int insertIndex = i - 1;
            while(insertIndex >= 0 && insertVal < arr[insertIndex]){
                arr[insertIndex + 1] = arr[insertIndex]; //
                insertIndex--;
            }

            arr[insertIndex + 1] = insertVal;

            System.out.printf("第%d轮循环: %s",i,Arrays.toString(arr));
            System.out.println();
        }
    }
}
