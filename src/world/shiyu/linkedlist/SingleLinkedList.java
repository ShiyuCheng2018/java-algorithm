package world.shiyu.linkedlist;


class SingleLikedList{
    private HeroNode head = new HeroNode(0, "", ""); // 初始化头节点， 不存在具体数据


    // 添加节点到单向链表
    // 思路， 当不考虑编号顺序时。
    // 1， 找到当前链表当最后一个节点，
    // 2， 将最后这个节点当next指向新当节点
    public void add(HeroNode heroNode){
        // 因为head节点不能动， 因此文明需要一个辅助变量temp
        HeroNode temp = this.head;
        // 遍历链表， 找到最后
        while(true){
            if(temp.next == null){
                break;
            }
            // 如果不是最后一个节点， temp便后移
            temp = temp.next;
        }
        // 当推出while循环时， temp指向链表当最后
        temp.next = heroNode;
    }

    public void list(){
        // 判断链表是否为空
        if(head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 因为头节点不可动， 因此我们需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while(true){
            if(temp == null){
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

}

// 定义节点， 每个节点对象为一个节点
class HeroNode{
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;

    public HeroNode(int no, String name, String nickName){
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
