package world.shiyu.recursion;

import java.awt.desktop.SystemSleepEvent;

public class Maze {
    public static void main(String[] args){
        // 先创建一个二维数组模拟迷宫
        int[][]map = new int[8][7];
        // 使用1表示墙， 上下全部置为1
        for (int i = 0; i<7; i++){
            map[0][i] = 1;
            map[7][i] = 1;
        }

        for(int i = 0; i< 8; i++){
            map[i][0] = 1;
            map[i][6] = 1;
        }

        // 设置挡板
        map[2][2] = 1;
        map[3][1] = 1;
        map[3][2] = 1;
        map[5][3] = 1;
        map[5][4] = 1;
        map[6][3] = 1;


        // 输出地图
        System.out.println("地图情况：  ");
        printMaze(map);

        // 使用递归回溯给小球找路
        setWay(map, 1, 1);

        // 输出已经标示过的地图
        printMaze(map);

    }

    public static void printMaze(int[][]map){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 7; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 使用递归帮助小球寻路
     * 1, map表示地图
     * 2, i,j表示从地图的哪个位置开始出发（1,1）
     * 3, 如果小球能到map[6][5]位置， 则说明通路找到
     * 4, 约定： 当map[i][j]为0表示该点没有走过当为1表示墙， 2表示通路可以走， 3改点已经走过但走不通
     * 5， 在走迷宫时，需要确定一个策略（方法）， 下->右->上->左️， 如果改点走不通则回溯
     * */

    public static boolean setWay(int[][]map, int i, int j){
        if(map[6][5] == 2){
            System.out.println("终点！！");
            return true;
        }else {
            if(map[i][j] == 0){
                System.out.println("尝试。。");
                // 如果当前这个点还没有走过
                // 按照策略走 下->右->上->左️
                map[i][j] = 2; // 假的改点是可以走通
                if(setWay(map, i+1, j)){
                    // 向下走
                    return true;
                }else if(setWay(map, i, j+1)){ // 向右走
                    return true;
                }else if(setWay(map, i-1, j)){ // 向上走
                    return true;
                }else if(setWay(map, i, j-1)){ // 向左走
                    return true;
                }else {
                    // 说明该点是走不通，死路
                    map[i][j] = 3;
                    System.out.println("设置死路");
                    return false;
                }
            }else {
                // 如果map[i][j] != 0, 可能是1,2,3
                System.out.println("不能走");
                return false;
            }
        }
    }
}


