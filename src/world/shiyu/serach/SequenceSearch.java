package world.shiyu.serach;

public class SequenceSearch {

    public static void main(String[] args) {
        int arr[] = {1, 9, 11, -1, 34, 89};
        int index = seqSearch(arr, 11);

        if (index == -1) {
            System.out.println("没有查找到目标值");
        } else {
            System.out.println("已找到目标值下标为： " + index);
        }
    }

    // 我们实现的线性查找是找到一个满足条件的值就返回
    public static int seqSearch(int[] arr, int value) {
        // 线性查找逐一对比， 然后返回
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }

        return -1;
    }
}
