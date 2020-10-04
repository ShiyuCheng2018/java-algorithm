package world.shiyu.practicalTree.HuffmanCode;

import java.util.*;

public class HuffmanCodeDemo {

    public static void main(String[] args) {
        String text = "i like like like java do you like a java";
        System.out.println("\n原字符串: "+text);
        byte[] textBytes = text.getBytes();
        /**
         //        System.out.println("\n原bytes: " + Arrays.toString(textBytes) + "\n 原bytes长度： " + textBytes.length);
         //
         //        List<Node> nodes = getNodes(textBytes);
         //        System.out.println("\nnodes: " + nodes);
         //
         //        Node HuffmanTree = createHuffmanTree(nodes);
         //        System.out.println("\nHuffman Tree: ");
         //        preOrder(HuffmanTree);
         //
         //
         //        // 生成对应HuffmanCode
         //        Map<Byte, String> huffmanCodes = getCodes(HuffmanTree);
         //        System.out.println("\n生成的HuffmanCode 编码表: " + HuffmanCode);
         //
         //        byte[] huffmanCodeBytes = zip(textBytes, huffmanCodes);
         //        System.out.println("\nhuffmanCodeBytes = " + Arrays.toString(huffmanCodeBytes));
         */
        byte[] huffmanCodeBytes = huffmanZip(textBytes);
        System.out.println("\nhuffmanCodeBytes = " + Arrays.toString(huffmanCodeBytes));

        String source = huffmanUnzip(huffmanCodeBytes);
        System.out.println("\nSource: " + source);
    }

    /**
     * 编写一个方法对压缩数据对解码
     *
     * @param huffmanCodes 编码表
     * @param huffmanBytes Huffman编码得到对字节数组
     * @return 原来对字符串对应对数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        // 1, 先得到HuffmanBytes 对应得到的二进制的字符串
        StringBuilder stringBuilder = new StringBuilder();

        // 将byte数组转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            // 判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, huffmanBytes[i]));
        }

        // 把字符串按照指定的Huffman编码表进行解码
        Map<String, Byte> map = new HashMap<String, Byte>();

        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }

        // 创建集合存放byte
        List<Byte> list = new ArrayList<>();
        // i 可以理解成一个索引， 扫描stringBuilder
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte b = null;

            while (flag) {
                // 递增取出一个字符
                String key = stringBuilder.substring(i, i + count); // i 不动， count移动，指定匹配到一个字符
                b = map.get(key);
                if (b == null) {
                    // 说明没有匹配到
                    count++;
                } else {
                    flag = false;
                }
            }

            list.add(b);
            i += count; // i 直接递增到count
        }

        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }

        return b;
    }

    /**
     * 将一个byte转成一个二进制到字符串
     *
     * @param b
     * @param flag 标志是否需要补高位
     * @return 是该b 对应到二进制到字符串 （注意按照补码返回）
     */
    private static String byteToBitString(boolean flag, byte b) {
        // 使用一个变量保存b
        int temp = b; // 将b转成int
        if (flag) {
            temp |= 256; //  按位与256
        }
        String str = Integer.toBinaryString(temp); // 返回到是temp对应到二进制补码

        if (flag) {
            return str.substring(str.length() - 8);

        } else {
            return str;
        }
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

    /**
     * 将编码表生成压缩后的字节byte【】
     *
     * @param bytes       原始字符串对应的bytes数组
     * @param huffmanCode Huffman 编码表
     * @return 返回Huffman 处理后的byte[]
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCode) {

        // 1， 编码表将byes[]转成Huffman编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        // 遍历bytes[]
        for (byte each : bytes) {
            stringBuilder.append(huffmanCode.get(each));
        }

        // 将对应字符串转成byte[]
        // 统计返回的byte[] textBytes 长度
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }

        // 创建存储压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0; // 记录是第几个byte
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            // 因为每8位对应一个byte， 所以步长+8
            String strByte;
            if (i + 8 > stringBuilder.length()) {
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            // 将strByte 转成一个byte，放入到
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;
    }

    /**
     * @param bytes 原始字符串对应的bytes数组
     * @return 返回Huffman 处理后的byte[](压缩后)
     */
    private static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        Node HuffmanTree = createHuffmanTree(nodes);
        Map<Byte, String> huffmanCodes = getCodes(HuffmanTree);

        return zip(bytes, huffmanCodes);
    }

    private static String huffmanUnzip(byte[] bytes) {
        return new String(decode(HuffmanCode, bytes));
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



