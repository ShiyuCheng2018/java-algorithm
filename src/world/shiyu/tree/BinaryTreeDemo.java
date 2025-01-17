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

        System.out.println();
        // 前序查找
        Node resultNode = binaryTree.preOrderSearch(15);
        if (resultNode != null) {
            System.out.println("已找到节点: " + resultNode);
        } else {
            System.out.println("没有找到此节点");
        }

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

    public Node preOrderSearch(int id) {
        if (root != null) {
            return root.preOrderSearch(id);
        } else {
            return null;
        }
    }

    public Node infixOrderSearch(int id) {
        if (root != null) {
            return this.root.infixOrderSearch(id);
        } else {
            return null;
        }
    }

    public Node postOrderSearch(int id) {
        if (root != null) {
            return this.root.postOrderSearch(id);
        } else {
            return null;
        }
    }

    public void deleteNode(int id) {
        if (root != null) {
            // 立即判断root是否就是要删除节点
            if (root.getId() == id) {
                root = null; // 删除整个树
            } else {
                root.deleteNode(id);
            }
        } else {
            System.out.println("空树， 无法进行删除操作！");
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

    // 前序遍历查找
    public Node preOrderSearch(int id) {
        // 先比较当前节点
        if (this.id == id) {
            return this;
        }

        // 判断当前节点的左子节点是否为空， 如果不为空则递归前序查找
        // 如果左递归找到目标则返回
        Node node = null;
        if (this.left != null) {
            node = this.left.preOrderSearch(id);
        }

        if (node != null) {
            return node;
        }

        // 判断当前节点的右子节点是否为空， 如果不为空则递归前序查找
        if (this.right != null) {
            node = this.right.preOrderSearch(id);
        }

        return node;
    }

    // 中序遍历查找
    public Node infixOrderSearch(int id) {
        Node node = null;

        if (this.left != null) {
            node = this.left.infixOrderSearch(id);
        }

        if (node != null) {
            return node;
        }


        if (this.id == id) {
            return this;
        }

        if (this.right != null) {
            node = this.right.infixOrderSearch(id);
        }

        return node;

    }

    // 后续遍历查找
    public Node postOrderSearch(int id) {
        Node node = null;

        if (this.left != null) {
            node = this.left.postOrderSearch(id);
        }

        if (node != null) {
            return node;
        }


        if (this.right != null) {
            node = this.right.postOrderSearch(id);
        }

        if (node != null) {
            return node;
        }


        if (this.id == id) {
            return this;
        }
        return node;

    }

    /**
     * 递归删除节点
     * 1， 如果删除的节点为叶子节点， 则直接删除此节点
     * 2， 如果删除的该节点为非叶子节点， 则删除该子树
     */

    public void deleteNode(int id) {

        if (this.left != null && this.left.id == id) {
            this.left = null;
            return;
        }

        if (this.right != null && this.right.id == id) {
            this.right = null;
            return;
        }

        if (this.left != null) {
            this.left.deleteNode(id);
        }

        if (this.right != null) {
            this.right.deleteNode(id);
        }

    }
}
