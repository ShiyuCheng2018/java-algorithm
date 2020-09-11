package world.shiyu.recursion;

public class Queens {

    //定义一个max标示共有多少个皇后
    int max = 8;
    // 定义数组array， 保存皇后放置的结构， 比如arr = 【0， 4， 7， 5， 2， 6， 1， 3】
    int[] array = new int[max];
    static int count = 0;
    public static void main(String [] args){
        Queens queens = new Queens();
        queens.check(0);
        System.out.printf("一共有 %d 种解法。", count);
    }

    // 编写一个方法， 放置第n个皇后
    // 特别注意， check是每一次递归时， 进入check中都有for(int i = 0; i< max; i++)， 因此产生回溯
    private void check(int n){
        if(n == max){
            // 已经放置路最后一个皇后
            print();
            return;
        }
        // 依次放进皇后， 并判断是否冲突
        for(int i = 0; i< max; i++){
            // 先把当前皇后n， 放到第一列
            array[n] = i;
            // 判断当放置第n个皇后到i列， 是否冲突
            if(judge(n)){
                // 接着放n+1个皇后， 开始递归
                check(n+1);
            }

            // 如果冲突， 就继续执行array[n] = i; 即将第n个皇后放置在本行第后移一个位置

        }
    }

    // 查看当我们放置第n个皇后时， 去检测该皇后是否和前面已经摆放当皇后冲突
    // n 标示放第n个皇后
    private boolean judge(int n){
        for(int i = 0; i<n; i++){
            /**
             * 说明：
             * 1） array[i] == array[n] 表示判断第n个皇后是否和前面第n-1个皇后在同一列
             * 2） Math.abs(n-i) == Math.abs(array[n] - array[i]) 表示第n个皇后是否第i个皇后是否在同一斜线
             * 3）不可能会在同一行， 因为n每次都在递增
             * * */
            if(array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n] - array[i])){
                return false;
            }
        }
        return  true;
    }

    // 写一个方法， 可以将皇后的位置打印出来
    private void print() {
        count ++;
        for (int i =0; i<array.length; i++){
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
