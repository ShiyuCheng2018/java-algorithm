package world.shiyu.generalAlgorithm.dynamicProgramming;

public class Knapsack {

    public static void main(String[] args) {
        int[] weights = {1, 4, 3}; // 物品重量
        int[] values = {1500, 3000, 2000}; // 物品价值
        int capacity = 4; // 背包容量
        int num = values.length; // 物品总个数
        int[][] path = new int[num + 1][capacity + 1];
        /** 创建二维数组，表示
         *  V[i][j] 表示在前i个物品中能够装入容量为j的背包最大价值
         * */
        int[][] DP = new int[num + 1][capacity + 1];

        // 初始化第一行和第一列可以不去处理， 因为默认为0
        for (int i = 0; i < DP.length; i++) {
            DP[i][0] = 0; // 将第一列设置为0
        }
        for (int i = 0; i < DP[0].length; i++) {
            DP[0][i] = 0; // 将第一行设置为0
        }

        for (int i = 1; i < DP.length; i++) { // 不处理第一行
            for (int j = 1; j < DP[0].length; j++) { // 不处理第一列
                if (weights[i - 1] > j) { // 从第一个商品计算
                    DP[i][j] = DP[i - 1][j];
                } else {
                    if (DP[i - 1][j] < DP[i - 1][j - weights[i - 1]] + values[i - 1]) { // 判断最优解
                        DP[i][j] = DP[i - 1][j - weights[i - 1]] + values[i - 1];
                        path[i][j] = 1; // 标记最佳解
                    } else {
                        DP[i][j] = DP[i - 1][j]; // 标记最佳解
                    }
                }
            }
        }

        /** 需要逆向遍历去拿到最优解*/
        int i = path.length - 1; // 行最大下标
        int j = path[0].length - 1; // 列最大下标
        while (i > 0 && j > 0) { // 从path最后开始找
            if (path[i][j] == 1) {
                System.out.printf("第%d个商品放入到背包\n", i);
                j -= weights[i - 1];
            }
            i--;
        }
    }
}
