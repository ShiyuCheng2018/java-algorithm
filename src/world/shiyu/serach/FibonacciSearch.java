package world.shiyu.serach;

import java.util.Arrays;

public class FibonacciSearch {
    public static int maxSize = 20;

    public static void main(String[] args) {
        /**
         * !!! 数组数据必须为有序数列
         * */

        int[] arr = {1, 8, 10, 89, 1000, 1234};

        int resultIndex = fibSearch(arr, 8);
        System.out.println("resultIndex: " + resultIndex);


    }

    public static int[] fib() {
        /**
         * 先得到一个斐波那契数列
         * 利用斐递归方法
         * */

        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;

        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    // 编写斐波那契查找算法
    public static int fibSearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int k = 0; // 表示斐波那契分割数值到下标
        int mid = 0; // 存放mid值
        int f[] = fib();

        // 获取斐波那契分割值到下标
        while (high > f[k] - 1) {
            k++;
        }

        // 因为f[k]值可能大于a的长度， 因此我们需要使用Array类，构造一个新的数组， 并指向temp[]
        // 不足的部分使用0填充
        int[] temp = Arrays.copyOf(a, f[k]);
        // 实际上需求使用a数组的最后的数值填充temp
        // 举例
        // temp = {1, 8, 10, 80, 1000, 1234, 0, 0, 0} = temp = {1, 8, 10, 80, 1000, 1234, 1234, 1234, 1234}
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = a[high];
        }

        // 使用while来循环处理， 找到我们的数key
        while (low <= high) {
            // 只要这个条件满足
            mid = low + f[k - 1] - 1;

            if (key < temp[mid]) { // 我们应该继续向数组的左边查找
                high = mid - 1;
                /**
                 * 说明；
                 * 1， 全部元素 = 前面的元素+后边的元素
                 * 2， f[k] = f[k-1] + f[k+2]
                 * 因为前面有f[k-1]个元素， 所以可以继续拆除f[k-1] = f[k-2] + f[k-3]
                 * 即在f[k-1] 的前面继续查找k - -
                 * 即下次循环mid = f[k-1-1]-1
                 * */
                k--;
            } else if (key > temp[mid]) {
                low = mid + 1;
                /**
                 * 1, 全部元素= 前面的元素 + 后面的元素
                 * 2, f[k] = f[k-1] + f[k-2]
                 * 3, 因为后面我们有f[k-2], 所以可以继续拆分f[f-1] = f[k-3] + f[k-4]
                 * 4, 即在f[k-2] 的前面进行查找k -= 2
                 * 5, 即下次循环mid = f[k - 1- 2]-1
                 */
                k -= 2;

            } else {
                // 需要确定返回哪个下标
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }

        return -1;
    }
}
