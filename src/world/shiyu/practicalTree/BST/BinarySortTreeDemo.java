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

    // 查找删除节点
    public Node search(int value){
        if(value == this.value){
            // 找到该节点
            return this;
        }else if(value < this.value){
            // 查找到值小于当前节点， 向左子树递归查找
            if(this.left == null){
                return null;
            }
            return this.left.search(value);
        }else {
            // 查找到值小于当前节点， 向右子树递归查找
            if(this.right == null){
                return null;
            }
            return this.search(value);
        }
    }

    /**
     * 查找要删除到父节点
     * @param value 要找到节点到值
     * @return 返回到是要删除到节点到父节点，如果没有返回null
     * */
    private Node searchParent(int value){
        if((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)){
            return this;
        }else{
            // 如果查找的值小于当前节点的值并且当前节点的左子节点不为空
            if(value < this.value && this.left != null){
                return this.left.searchParent(value);
            }else if(value >= this.value && this.right != null){
                return this.right.searchParent(value); // 向右子树递归查找
            }else {
                return null; // 没有找到父节点
            }
        }
    }
}
