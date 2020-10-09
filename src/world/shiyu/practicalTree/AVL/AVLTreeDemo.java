package world.shiyu.practicalTree.AVL;

public class AVLTreeDemo {
    public static void main(String[] args) {
        int[] arr = {4, 3, 6, 5, 7, 8};

        // 创建AVL对象
        AVLTree avlTree = new AVLTree();

        for (int each : arr) {
            avlTree.add(new Node(each));
        }

        System.out.println("\n中序遍历: ");
        avlTree.infixOrder();

        System.out.println("在没有在平衡处理之前: ");
        System.out.println("\n树的高度: " + avlTree.getRoot().height());
        System.out.println("树的左子树高度: " + avlTree.getRoot().leftHeight());
        System.out.println("树的右子树高度: " + avlTree.getRoot().rightHeight());

    }
}

class AVLTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

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
    public Node search(int value) {
        if (root == null) {
            System.out.println("二叉树为空，无法删除");
            return null;
        } else {
            return root.search(value);
        }
    }

    // 查找父节点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }


    /**
     * 返回以node为根节点的二叉排序树的最小节点的值
     * 删除以node为根节点的二叉排序树的最小节点的值
     *
     * @param node 传入的节点（当作一颗二叉排序树的根节点）
     * @return 返回的是以node为根节点的二叉排序树的最小节点的值
     */
    public int delRightTreeMin(Node node) {
        Node temp = node;
        // 循环的查找左节点，就会找到最小值
        while (temp.left != null) {
            temp = temp.left;
        }

        // 这时temp就指向了最小节点
        // 删除最小节点
        delNode(temp.value);
        return temp.value;
    }

    // 删除节点
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            // 1. 先去找要删除的节点
            Node targetNode = search(value);
            if (targetNode == null) {
                return;
            }
            // 如果当前这颗树只有一个节点
            if (root.left == null && root.right == null) {
                root = null;
            }

            // 查找targetNode的父节点
            Node parent = searchParent(value);
            /** 第一种情况 如果要删除的节点是叶子节点 */
            if (targetNode.left == null && targetNode.right == null) {
                // 判断targetNode是父节点的左子节点还是右子节点
                if (parent.left != null && parent.left.value == value) {
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) {
                /**第二种情况 要删除的节点有2颗子树子节点*/

                int minValue = delRightTreeMin(targetNode.right);
                targetNode.value = minValue;

            } else {
                /**第三种情况: 要删除的节点有1颗子树的点*/

                // 如果targetNode有左子节点
                if (targetNode.left != null) {

                    if (parent != null) {

                        // 如果targetNode 是parent的左子节点
                        if (parent.left.value == value) {
                            parent.left = targetNode.left;
                        } else {// 如果targetNode 是parent的右子节点
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else {  // 如果targetNode有右子节点
                    if (parent != null) {
                        // 如果targetNode 是parent的左子节点
                        if (parent.left.value == value) {
                            parent.left = targetNode.right;
                        } else {// 如果targetNode 是parent的右子节点
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
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

        // 当添加完 一个节点后， 如果右子树的高度 - 左子树的高度 > 1, 左旋转
        if (rightHeight() - leftHeight() > 1) {
            leftRotate();
        }

        // 但添加完一个节点后， 如果左子树的高度  - 右子树的高度 > 1, 右旋转
        if (leftHeight() - rightHeight() > 1) {
            rightRotate();
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
    public Node search(int value) {
        if (value == this.value) {
            // 找到该节点
            return this;
        } else if (value < this.value) {
            // 查找到值小于当前节点， 向左子树递归查找
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {
            // 查找到值小于当前节点， 向右子树递归查找
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    /**
     * 查找要删除到父节点
     *
     * @param value 要找到节点到值
     * @return 返回到是要删除到节点到父节点，如果没有返回null
     */
    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            // 如果查找的值小于当前节点的值并且当前节点的左子节点不为空
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value); // 向右子树递归查找
            } else {
                return null; // 没有找到父节点
            }
        }
    }

    // 返回当前节点的高度， 以该节点为根节点的树的高度
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    // 返回左子树的高度
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();

    }

    // 返回右子树高度
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();

    }

    // 左旋转
    private void leftRotate() {

        // 创建新节点, 以当前根节点的值
        Node newNode = new Node(value);
        // 把新节点的左子树设置为当前节点的左子树
        newNode.left = left;
        // 把新节点的右子树设置为当前节点的右子树的左子树
        newNode.right = right.left;
        // 当前节点的值换成右子节点的值
        value = right.value;
        // 把当前节点的右子树设置成当前节点右子树的右子树
        right = right.right;
        // 把当前节点的左子树设置成新节点
        left = newNode;

    }

    private void rightRotate() {

        Node newNode = new Node(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;

    }
}
