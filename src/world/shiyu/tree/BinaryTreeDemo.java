package world.shiyu.tree;

import java.awt.desktop.SystemSleepEvent;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();

        Node root = new Node(1, "Tiger");
        Node node_2 = new Node(2, "Cat");
        Node node_3 = new Node(3, "Dog");
        Node node_4 = new Node(4, "Bird");
        Node node_5 = new Node(5, "Panda");
//       Node node_6 =new Node(6, "Griffie");
//       Node node_7 = new Node(7, "Eagle");
//       Node node_8 =new Node(8, "Rabbit");

        // 手动创建二叉树
        root.setLeft(node_2);
        root.setRight(node_3);
        node_3.setLeft(node_5);
        node_3.setRight(node_4);
        binaryTree.setRoot(root);

        // test
        System.out.println("前序遍历： "); // 1， 2，3，5, 4
        binaryTree.preOrder();

        // test
        System.out.println("中序遍历： "); // 2, 1, 5, 3, 4
        binaryTree.infixOrder();

        // test
        System.out.println("后序遍历： "); //  2, 5, 4, 3, 1
        binaryTree.postOrder();

    }
}


class BinaryTree {
    private Node root;

    public BinaryTree() {

    }

    public void setRoot(Node root) {
        this.root = root;
    }

    // 前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder(); // 指向this.root
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    // 中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder(); // 指向this.root
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    // 后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder(); // 指向this.root
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }
}


class Node {
    private int id;
    private String name;
    private Node left;
    private Node right;

    public Node(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    // 编写前序遍历
    public void preOrder() {
        System.out.println(this); // 先输出父节点

        // 递归左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }

        // 递归右子树
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    // 中序遍历
    public void infixOrder() {
        // 递归左子树遍历
        if (this.left != null) {
            this.left.infixOrder();
        }

        // 输出父节点
        System.out.println(this);

        // 递归右子树
        if (this.right != null) {
            this.right.infixOrder();
        }
    }
    // 后续遍历

    public void postOrder() {
        // 递归左子树
        if (this.left != null) {
            this.left.postOrder();
        }

        // 递归右子树
        if (this.right != null) {
            this.right.postOrder();
        }

        // 输出父节点
        System.out.println(this);
    }
}
