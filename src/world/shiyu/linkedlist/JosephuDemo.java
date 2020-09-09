package world.shiyu.linkedlist;

public class JosephuDemo {
    public static void main(String[] args) {
        CircleSingleLinkedList csll = new CircleSingleLinkedList();
        csll.addBoys(5);
        csll.showBoys();
    }
}

// 创建一个环形的单向链表
class CircleSingleLinkedList {
    // 创建一个first节点， 当前没有编号
    private Boy first = null;

    // 添加小孩节点， 构建成一个环形链表
    public void addBoys(int nums) {
        // 数据校验
        if (nums < 1) {
            System.out.println("num 值不正确");
            return;
        }
        Boy curBoy = null; // 辅助变量，帮助构建环形链表
        // 使用for循环创建我们的环形链表
        for (int i = 1; i <= nums; i++) {
            // 根据编号， 创建小孩节点
            Boy boy = new Boy(i);
            // 如果是第一个小孩
            if (i == 1) {
                this.first = boy;
                this.first.setNext(this.first); // 构成自我环状
                curBoy = this.first; // 让curBoy指向第一个小孩
            } else {
                curBoy.setNext(boy);
                boy.setNext(this.first);
                curBoy = boy;
            }
        }
    }

    // 遍历当前环形链表
    public void showBoys() {
        // 链表是否为空
        if (this.first == null) {
            System.out.println("链表为空， 没有任何孩子");
        }
        // 因为first不能动， 因此我们需要辅助指针变量完成遍历
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号 %d \n", curBoy.getNo());
            if (curBoy.getNext() == first) {
                // 已经遍历完毕
                break;
            }
            curBoy = curBoy.getNext(); // curBoy后移
        }
    }
}

// 定义节点， 每个节点对象为一个节点
class Boy {
    public int no;
    public Boy next; // 指向下一个节点, null

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return this.no;
    }

    public Boy getNext() {
        return this.next;
    }

    public void setNext(Boy boy) {
        this.next = boy;
    }

    // 为了显示方便， 重写toString
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no;
    }
}

