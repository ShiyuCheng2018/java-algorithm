package world.shiyu.practicalTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTreeDemo {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node root = createHuffmanTree(arr);
        System.out.println("\n测试HuffmanTree（前序遍历）: ");
        preOrder(root);
    }

    public static void preOrder(Node root){
        if(root != null){
            root.preOrder();
        }else {
            System.out.println("不能遍历空树");
        }
    }

    public static Node createHuffmanTree(int[] arr) {
        /**
         * 第一步， 为了操作方便
         * 1，遍历arr数组
         * 2， 将arr的每个元素构成一个Node
         * 3， 将Node放入ArrayList
         * */
        List<Node> nodes = new ArrayList<Node>();

        for (int value : arr) {
            nodes.add(new Node(value));
        }

        while (nodes.size() > 1) {

            // 排序： 从小到大
            Collections.sort(nodes);
            System.out.println("nodes: " + nodes);

            // 1, 取出根节点权值最小的俩颗二叉树
            // 2, 取出权值最小的节点（二叉树）
            Node left = nodes.get(0);
            // 取出权值第二小的节点（二叉树）
            Node right = nodes.get(1);
            // 3, 构建一颗新的二叉树
            Node parent = new Node(left.value + right.value);
            parent.left = left;
            parent.right = right;

            // 4, 从ArrayList删除处理过的二叉树
            nodes.remove(left);
            nodes.remove(right);

            // 5, 将parent加入进nodes
            nodes.add(parent);
        }

        // 返回Huffman root
        System.out.println("nodes: " + nodes);
        return nodes.get(0);

    }
}


class Node implements Comparable<Node> {
    int value; // 节点权值
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        // 从小到大排序
        return this.value - o.value;
    }

    // 前序遍历
    public void preOrder(){
        System.out.println(this);
        if(this.left != null){
            this.left.preOrder();
        }
        if(this.right != null){
            this.right.preOrder();
        }
    }
}
