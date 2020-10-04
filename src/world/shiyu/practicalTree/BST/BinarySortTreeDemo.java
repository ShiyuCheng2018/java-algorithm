package world.shiyu.practicalTree.BST;

public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9};
        BinarySortTree binarySortTree = new BinarySortTree();

        // 循环数组添加节点进二叉排序树
        for (int each : arr) {
            binarySortTree.add(new Node(each));
        }

        binarySortTree.add(new Node(8));

        binarySortTree.infixOrder();
    }
}


class BinarySortTree {
    private Node root;

    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    public void infixOrder() {
        if (root == null) {
            System.out.println("二叉树为空，无法遍历");
        } else {
            root.infixOrder();
        }
    }

}


class Node {

    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    public void add(Node node) {
        if (node == null) {
            return;
        }

        if (node.value < this.value) {
            if (this.left == null) {
                // 如果当前节点左子节点为空
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                // 如果当前节点右子节点为空
                this.right = node;
            } else {
                this.right.add(node);
            }
        }

    }

    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }

        System.out.println(this);

        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
