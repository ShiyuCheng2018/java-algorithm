package world.shiyu.generalAlgorithm.dividedConquer;

public class Hanoitower {
    public static void main(String[] args) {
        hanoiTower(3, 'A', 'B', 'C');
    }

    // 汉诺塔解决方案, 使用分治算法
    public static void hanoiTower(int num, char a, char b, char c) {
        if (num == 1) { // 只有一个盘
            System.out.println("第1个盘从 " + a + "->" + c);
        } else {
            //1. 先把最上面盘A->B,移动过程会使用到C
            hanoiTower(num - 1, a, c, b);
            // 2， 把最下边到盘A->C
            System.out.println("第" + num + "个盘从 " + a + "->" + c);
            // 3, 把B塔的所有盘从B->C， 移动过程使用A
            hanoiTower(num - 1, b, a, c);
        }
    }

}