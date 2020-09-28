package world.shiyu.tree;

import java.util.Arrays;

public class SequentialBinaryTreeDemo {
    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        SequentialBinaryTree sequentialBinaryTree = new SequentialBinaryTree(arr);

        System.out.println("原数组:  " + Arrays.toString(arr));
        System.out.print("前序遍历数组: ");
        sequentialBinaryTree.preOrder();
        System.out.println();
        System.out.print("中序遍历数组: ");
        sequentialBinaryTree.infixOrder();
        System.out.println();
        System.out.print("后序遍历数组: ");
        sequentialBinaryTree.postOrder();
    }
}

// 实现顺序存储二叉树遍历

class SequentialBinaryTree {

    private int[] arr; // 存储数据节点的数组

    public SequentialBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOrder() {
        this.preOrder(0);
    }

    public void infixOrder() {
        this.infixOrder(0);
    }

    public void postOrder() {
        this.postOrder(0);
    }

    // index: 数组的下标
    public void preOrder(int index) {
        // 如果数组为空， 或者arr.length = 0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空， 不能按照二叉树的前序遍历");
        }

        // 输出当前这个元素
        System.out.print(arr[index] + " ");

        // 向左递归遍历
        if ((index * 2 + 1) < arr.length) {
            preOrder(2 * index + 1);
        }
        // 向右递归遍历
        if ((index * 2 + 2) < arr.length) {
            preOrder(2 * index + 2);
        }
    }

    public void infixOrder(int index) {
        // 如果数组为空， 或者arr.length = 0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空， 不能按照二叉树的前序遍历");
        }


        // 向左递归遍历
        if ((index * 2 + 1) < arr.length) {
            preOrder(2 * index + 1);
        }

        // 输出当前这个元素
        System.out.print(arr[index] + " ");


        // 向右递归遍历
        if ((index * 2 + 2) < arr.length) {
            preOrder(2 * index + +2);
        }
    }

    public void postOrder(int index) {
        // 如果数组为空， 或者arr.length = 0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空， 不能按照二叉树的前序遍历");
        }


        // 向左递归遍历
        if ((index * 2 + 1) < arr.length) {
            preOrder(2 * index + 1);
        }

        // 向右递归遍历
        if ((index * 2 + 2) < arr.length) {
            preOrder(2 * index + +2);
        }

        // 输出当前这个元素
        System.out.print(arr[index] + " ");
    }

}
