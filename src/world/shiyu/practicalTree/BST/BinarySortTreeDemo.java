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

        // 测试删除
        binarySortTree.delNode(7);

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

    // 查找要删除的节点
    public Node search(int value){
        if(root == null){
            System.out.println("二叉树为空，无法删除");
            return null;
        } else {
            return root.search(value);
        }
    }

    // 查找父节点
    public Node searchParent(int value){
        if(root == null){
            return null;
        }else {
            return root.searchParent(value);
        }
    }


    /**
     * 返回以node为根节点的二叉排序树的最小节点的值
     * 删除以node为根节点的二叉排序树的最小节点的值
     *
     * @param node 传入的节点（当作一颗二叉排序树的根节点）
     * @return 返回的是以node为根节点的二叉排序树的最小节点的值
     * */
    public int delRightTreeMin(Node node){
        Node temp = node;
        // 循环的查找左节点，就会找到最小值
        while(temp.left != null){
            temp = temp.left;
        }

        // 这时temp就指向了最小节点
        // 删除最小节点
        delNode(temp.value);
        return temp.value;
    }

    // 删除节点
    public void delNode(int value){
        if(root == null){
            return;
        }else {
            // 1. 先去找要删除的节点
            Node targetNode = search(value);
            if(targetNode == null){
                return;
            }
            // 如果当前这颗树只有一个节点
            if(root.left == null && root.right == null){
                root = null;
            }

            // 查找targetNode的父节点
            Node parent = searchParent(value);
            /** 第一种情况 如果要删除的节点是叶子节点 */
            if(targetNode.left == null && targetNode.right == null){
                // 判断targetNode是父节点的左子节点还是右子节点
                if(parent.left != null && parent.left.value == value){
                    parent.left = null;
                }else if(parent.right != null && parent.right.value == value){
                    parent.right = null;
                }
            }else if(targetNode.left != null && targetNode.right != null){
                /**第二种情况 要删除的节点有2颗子树子节点*/

                int minValue = delRightTreeMin(targetNode.right);
                targetNode.value = minValue;

            }else {
                /**第三种情况: 要删除的节点有1颗子树的点*/

                // 如果targetNode有左子节点
                if(targetNode.left != null){
                    // 如果targetNode 是parent的左子节点
                    if(parent.left.value == value){
                        parent.left = targetNode.left;
                    }else {// 如果targetNode 是parent的右子节点
                        parent.right = targetNode.left;
                    }
                }else {  // 如果targetNode有右子节点
                    // 如果targetNode 是parent的左子节点
                    if(parent.left.value == value){
                        parent.left = targetNode.right;
                    }else {// 如果targetNode 是parent的右子节点
                        parent.right = targetNode.right;
                    }
                }

            }


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
            return this.right.search(value);
        }
    }

    /**
     * 查找要删除到父节点
     * @param value 要找到节点到值
     * @return 返回到是要删除到节点到父节点，如果没有返回null
     * */
    public Node searchParent(int value){
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
