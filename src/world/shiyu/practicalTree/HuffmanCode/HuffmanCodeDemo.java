package world.shiyu.practicalTree.HuffmanCode;

import java.util.*;

public class HuffmanCodeDemo {

    public static void main(String[] args) {
        String text = "i like like like java do you like a java";
        byte[] textBytes = text.getBytes();
        System.out.println("\n原bytes: " + Arrays.toString(textBytes) + "\n 原bytes长度： " + textBytes.length);

        List<Node> nodes = getNodes(textBytes);
        System.out.println("\nnodes: " + nodes);

        Node HuffmanTree = createHuffmanTree(nodes);
        System.out.println("\nHuffman Tree: ");
        preOrder(HuffmanTree);


        // 生成对应HuffmanCode
        getCodes(HuffmanTree);
        System.out.println("\n生成的HuffmanCode 编码表: " + HuffmanCode);

    }

    /**
     * @param bytes
     * @return nodes, 返还列表包含所有由字符建造的node
     */
    private static List<Node> getNodes(byte[] bytes) {
        // 创建一个ArrayList
        ArrayList<Node> nodes = new ArrayList<Node>();

        // 遍历bytes, 存储每一个byte出现的次数 -> map;
        Map<Byte, Integer> counts = new HashMap();
        for (byte each : bytes) {
            Integer count = counts.get(each);
            if (count == null) {
                // Map还没有这个字符数据
                counts.put(each, 1);
            } else {
                counts.put(each, count + 1);
            }
        }

        // 遍历counts， 把每个键值对转成一个Node对象， 并加入nodes集合
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }

        return nodes;
    }

    // 通过list创建对应的Huffman Tree
    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            // 排序
            Collections.sort(nodes);

            // 取出第一课最小的二叉树

            Node left = nodes.get(0);
            Node right = nodes.get(1);
            Node parent = new Node(null, left.weight + right.weight);
            parent.left = left;
            parent.right = right;

            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);
        }

        return nodes.get(0);
    }

    private static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("Huffman Tree is empty.");
        }
    }

    /**
     * 生成HuffmanTree对应的编码
     * 1， 将HuffmanTree 存放在Map<Byte, String> 形式
     * 2， 在生成Huffman Code时，需要去拼接路径， 定义一个StringBuilder方法存储某个叶子节点的路径
     */
    static Map<Byte, String> HuffmanCode = new HashMap<Byte, String>();
    static StringBuilder stringBuilder = new StringBuilder();

    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }

        // 处理root左子树
        getCodes(root.left, "0", stringBuilder);
        // 处理root右子树
        getCodes(root.right, "1", stringBuilder);
        return HuffmanCode;
    }

    /**
     * 将传入的node节点的所有叶子节点的huffman code得到， 并放入到huffmanCodes集合中
     *
     * @param node          传入节点
     * @param code          路径： 左子节点是0， 右子节点值为1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder1_2 = new StringBuilder(stringBuilder);

        stringBuilder1_2.append(code);

        if (node != null) {
            // 如果node为空不处理
            if (node.data == null) {
                // 非叶子节点
                // 递归
                // 向左
                getCodes(node.left, "0", stringBuilder1_2);
                // 向右
                getCodes(node.right, "1", stringBuilder1_2);
            } else {
                // 得到叶子节点， 得到某个非叶子节点的最后
                HuffmanCode.put(node.data, stringBuilder1_2.toString());
            }

        }
    }


}

class Node implements Comparable<Node> {

    Byte data;  // exp.: 'a' => 97, ' ' => 32
    int weight; // 权值， 字符出现的次数
    Node right;
    Node left;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        // 从小到大
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

}



