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

    public void addByOrder(HeroNode heroNode){
        // 因为头节点不能动， 依然通过辅助指针（变量）来帮助找到加入位置
        // temp位于添加位置动前一个节点
        HeroNode temp = head;
        boolean flag = false; // 标示添加编号是否存在， 默认为false
        while(true){
            if(temp.next == null){ // 说明temp已经在链表最后
                break;
            }
            if(temp.next.no > heroNode.no){ // target位置找到， 后面一位index为target
                break;
            }else if(temp.next.no == heroNode.no){ // 编号已经存在
                flag = true;
                break;
            }
            temp = temp.next; // temp后移，遍历链表
        }

        // 判断flag值
        if(flag){
            System.out.println("已存在编号: "+ heroNode.no);
        }else {
            // 插入链表, temp后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    public void update(HeroNode newHeroNode){
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        // 找到需要修改动节点， 根据编号
        HeroNode temp = head.next;
        boolean flag = false;
        while(true){
            if(temp == null){ // 链表最后节点， 遍历完毕
                break;
            }
            if(temp.no == newHeroNode.no){ // 目标节点
                flag = true;
                break;
            }
            temp = temp.next;
        }
        // 根据flag判断是否找到目标节点
        if(flag){
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        }else {
            System.out.printf("没有找到编号 %d 的节点， 不能修改\n", newHeroNode.no);
        }
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
