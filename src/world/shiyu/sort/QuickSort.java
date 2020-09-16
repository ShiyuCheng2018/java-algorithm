package world.shiyu.sort;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args){
            int[] arr = {-9, 78, 0, 23, -34, 45, 34, 23, 2, 80};
            System.out.println("排列前: "+ Arrays.toString(arr));
            quickSort(arr, 0, arr.length-1);
            System.out.println("快速排列后: "+ Arrays.toString(arr));
    }


    public static void quickSort(int[] arr, int left, int right){
        int l = left; // 左索引
        int r = right; // 右索引

        int pivot = arr[(left + right) / 2];
        int temp = 0; // 临时变量， 作为交换时使用

        while(l < r){ // while循环目的是让比pivot值小的放到左边， 比pivot大大值放在右边

            // 在pivot左边一直找， 找到一个大于或者等于pivot大值， 才退出
            while(arr[l] < pivot){
                l += 1;
            }
            // 在pivot右边一直找， 找到小于等于pivot值， 才退出
            while(arr[r] > pivot){
                r -= 1;
            }

            // 如果  1>= r 说明pivot左右俩边的值， 已经按照规则完成
            if(l >= r){
                break;
            }

            // 交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            // 如果交换完后， 发现arr[l] == pivot值相等 r--， 前移
            if(arr[l] == pivot){
                r -= 1;
            }
            // 如果交换完后， 发现arr[r] == pivot值相等 l++， 后移
            if(arr[r] == pivot){
                l += 1;
            }
        }

        // 如果l == r, 必须l++, r--, 否则出现栈溢出. 目的是为了是l > r, 跳出while（l < r）
        if(l == r){
            l += 1;
            r -= 1;
        }

        // 向左递归
        if(left < r){
            quickSort(arr, left, r);
        }
        // 向右递归
        if(right > l){
            quickSort(arr, l, right);
        }

    }
}
