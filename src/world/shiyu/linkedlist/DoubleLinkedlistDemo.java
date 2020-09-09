package world.shiyu.linkedlist;

public class DoubleLinkedlistDemo {

    public static void main(String[] args) {
        Node hero_1 = new Node(1, "宋江", "及时雨");
        Node hero_2 = new Node(2, "卢俊义", "玉麒麟");
        Node hero_3 = new Node(3, "吴用", "智多星");
        Node hero_4 = new Node(4, "林冲", "豹子头");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero_1);
        doubleLinkedList.add(hero_2);
        doubleLinkedList.add(hero_3);
        doubleLinkedList.add(hero_4);

        doubleLinkedList.list();

        doubleLinkedList.del(2);

        System.out.println("删除后链表情况");
        doubleLinkedList.list();

    }

}

class DoubleLinkedList {
    private Node head = new Node(0, "", ""); // 初始化头节点， 不存在具体数据

    public Node getHead() {
        return head;
    }

    public void list() {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 因为头节点不可动， 因此我们需要一个辅助变量来遍历
        Node temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    /**TODO: 按序添加双向链表节点
     * */


    // 添加节点到双向链表
    // 思路， 当不考虑编号顺序时。
    // 1， 找到当前链表当最后一个节点，
    // 2， 将最后这个节点当next指向新当节点
    public void add(Node node) {
        // 因为head节点不能动， 因此文明需要一个辅助变量temp
        Node temp = this.head;
        // 遍历链表， 找到最后
        while (true) {
            if (temp.next == null) {
                break;
            }
            // 如果不是最后一个节点， temp便后移
            temp = temp.next;
        }
        // 当推出while循环时， temp指向链表当最后
        // 形成一个双向链表
        temp.next = node;
        node.pre = temp;
    }

    public void update(Node newNode) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 找到需要修改动节点， 根据编号
        Node temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) { // 链表最后节点， 遍历完毕
                break;
            }
            if (temp.no == newNode.no) { // 目标节点
                flag = true;
                break;
            }
            temp = temp.next;
        }
        // 根据flag判断是否找到目标节点
        if (flag) {
            temp.name = newNode.name;
            temp.nickName = newNode.nickName;
        } else {
            System.out.printf("没有找到编号 %d 的节点， 不能修改\n", newNode.no);
        }
    }


    /**
     * 删除双向链表节点
     * 1， 对于删除双向链表节点， 我们可以直接定位到待删除节点， 然后进行自我删除
     */
    public void del(int no) {
        Node temp = head.next; // 指示带删除节点
        // 判断当前列表是否为空
        if(temp == null){
            System.out.println("链表为空， 不可进行删除操作");
            return;
        }

        boolean flag = false; // 标示是否找到待删除节点
        while (true) {
            if (temp == null) { // 链表最后节点
                break;
            }
            if (temp.no == no) { // 目标删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            // temp.next = temp.next.next; 【单向链表删除方式】
            temp.pre.next = temp.next;
            // 如果是最后一个节点， 就不需要执行下行代码， 否则会出现空指针错误
            if(temp.next!=null){
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("要删除的%d节点不存在\n", no);
        }
    }
}

// 定义节点， 每个节点对象为一个节点
class Node {
    public int no;
    public String name;
    public String nickName;
    public Node next; // 指向下一个节点, null
    public Node pre; // 指向前一个节点， null

    public Node(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    // 为了显示方便， 重写toString

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '}';
    }
}
