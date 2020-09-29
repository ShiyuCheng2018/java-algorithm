package world.shiyu.tree.threadedBinaryTree;

public class ThreadedBianryTreeDemo {

    public static void main(String[] args) {
        // 测试一把中序线索二叉树
        Node root = new Node(1, "Tiger");
        Node node_2 = new Node(3, "Cat");
        Node node_3 = new Node(6, "Dog");
        Node node_4 = new Node(8, "Bird");
        Node node_5 = new Node(10, "Panda");
        Node node_6 = new Node(14, "Lion");

        // 二叉树递归创建
        root.setLeft(node_2);
        root.setRight(node_3);
        node_2.setLeft(node_4);
        node_2.setRight(node_5);
        node_3.setLeft(node_6);

        // 测试线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();

        // 测试： 以10号节点测试
        Node leftNode = node_5.getLeft();
        System.out.println("10号节点的前驱节点是 = " + leftNode);
        System.out.println("10号节点的后驱节点是 = " + node_5.getRight());

    }


}

// ThreadedBinaryTree

class ThreadedBinaryTree {
    private Node root;

    // 为了实现线索化， 需要创建要给指向当前节点对前驱节点对指针。在递归进行线索化时， pre总是保存前一个节点
    private Node pre = null;

    public void threadedNodes() {
        this.threadedNodes(root);
    }

    /**
     * 编写对二叉树进行中序线索化方法
     */

    // @node 就是当前需要线索化对节点
    public void threadedNodes(Node node) {
        // 如果node == null, 不能线索化

        if (node == null) {
            return;
        }

        // 1， 先线索化左子树
        threadedNodes(node.getLeft());


        /************* 2， !!! 线索化当前节点***************/

        // 处理当前节点对前驱节点
        if (node.getLeft() == null) {
            // 让当前节点对左指针指向前驱节点
            node.setLeft(pre);
            // 修改当前节点对左指针类型, 指向前驱节点
            node.setLeftType(1);
        }

        // 处理后继节点
        if (pre != null && pre.getRight() == null) {
            // 让前驱节点对右指针指向当前节点
            pre.setRight(node);
            // 修改前驱节点右指针类型
            pre.setRightType(1);
        }

        // !!! 每处理一个节点， 让当前节点是下一个节点的前驱
        pre = node;
        /************************************************/

        // 3， 线索化右子树
        threadedNodes(node.getRight());

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

// 创建Node
class Node {
    private int id;
    private String name;
    private Node left;
    private Node right;

    /**
     * 1. 如果leftType == 0 表示指向的是左子树， 如果1则表示指向前驱节点
     * 2. 如果rightType == 0 表示指向右子树， 如果1表示指向后继节点
     */

    private int leftType;
    private int rightType;

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public int getLeftType() {
        return leftType;
    }

    public int getRightType() {
        return rightType;
    }

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

