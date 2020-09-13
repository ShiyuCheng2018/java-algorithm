package world.shiyu.sort;

import java.lang.reflect.Array;
import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {

        int arr[] = {3, 9, -1, 10, -2};

        /** 为了容易理解， 我们把冒牌排序的演变过程
         *  冒泡排序的时间复杂度o(n)^2
         * */
        System.out.println("排序前: "+Arrays.toString(arr));
        BubbleSort(arr);


    }

    public static void BubbleSort(int[] arr){
        int temp = 0;
        boolean flag = false; // 表示是否进行过交换
        for(int i =0; i < arr.length - 1; i++){

            for (int j = 0; j < arr.length - 1 - i; j++) {
                // 如果前面的数比后面的数大， 则交换
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }

            System.out.printf("第 %d 趟排序后的数组: ", i+1);
            System.out.println(Arrays.toString(arr));

            if(flag == false){
                // 在一趟排序中， 一次交换都没发生过， 说明已经是有序列表
                break;
            }else {
                flag = false; // 重置 flag， 进行下次判断
            }

        }
    }
}
