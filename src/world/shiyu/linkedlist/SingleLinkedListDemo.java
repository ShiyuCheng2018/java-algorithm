package world.shiyu.linkedlist;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode hero_1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero_2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero_3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero_4 = new HeroNode(4, "林冲", "豹子头");

        SingleLikedList singleLinkedList = new SingleLikedList();
        singleLinkedList.add(hero_1);
        singleLinkedList.add(hero_2);
        singleLinkedList.add(hero_3);
        singleLinkedList.add(hero_4);

        singleLinkedList.list();

        singleLinkedList.del(2);

        System.out.println("删除后链表情况");
        singleLinkedList.list();

        System.out.println("有效的节点个数= " + getLength(singleLinkedList.getHead()));

    }


    /**
     * 查找单链表中的倒数第K个节点 （新浪）
     * 1， 编写一个方法， 接受head节点， 同时接受一个index
     * 2， index标示是倒数第index节点
     * 3， 先把链表从头到尾遍历， 得到链表的总长度getLength()
     * 4， 得到size后， 我们从链表的第一个开始遍历（size - index）个
     * 5,  如果找到即返回该节点， 否则返回null
     */
    public static HeroNode findInverseIndexNode(HeroNode head, int index) {
        if (head.next == null) {
            return null;
        }
        // 第一次遍历得到链表的长度
        int size = getLength(head);
        // 第二次遍历size-index位置，为我们的倒数第K个节点
        if (index <= 0 || index > size) {
            return null;
        }
        HeroNode cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    // 方法： 获取到单链表到节点个数（如果是带头节点的链表， 需求不统计头节点）（百度）
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            return 0;
        }
        int length = 0;
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }

    /**
     * 反转单链表 （腾讯）
     * 思路：
     * 1， 先定义一个节点reverseHead = new HeroNode();
     * 2, 从头到尾遍历原来单链表， 每遍历一个节点， 就将其取出， 并放在新单链表reverseHead最前端。
     * 3, 原来单链表单head.next = reverseHead.next
     */
    public static void reverseList(HeroNode head) {
        // 如果当前列表为空， 或者只有一个节点， 则无需反转，直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }

        // 定义一个辅助变量指针， 帮助我们遍历原来单链表
        HeroNode curr = head.next;
        HeroNode next = null; // 指向当前节点【cur】下一个节点
        HeroNode reversedHead = new HeroNode(0, "", "");
        // 遍历原来单链表， 并从头到尾遍历原来单链表， 每遍历一个节点， 就将其取出， 并放在新单链表reverseHead最前端。
        while (curr != null) {
            next = curr.next; // 先暂时保存当前节点单下一个节点， 因为后面需要使用
            curr.next = reversedHead.next; // 将curr的下一个节点指向新链表的最前端
            reversedHead.next = curr; // 拼接reverseHead与curr， 成为新链表（reversed）
            curr = next; // 移动后一次继续进行遍历
        }
        //  将head.next指向reverseHead.next, 实现单链表单反转
        head.next = reversedHead.next;
    }
}
