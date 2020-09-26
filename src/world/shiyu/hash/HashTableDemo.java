package world.shiyu.hash;

public class HashTableDemo {
    public static void main(String[] args) {


    }


}

class HashTabl {
    private EmpLinkedList[] empLinkedLists;
    private int size;

    public HashTabl(int size) {
        this.size = size;
        // 初始化 empLinkedLists
        this.empLinkedLists = new EmpLinkedList[size];

    }

    // 添加雇员
    public void add(Employee emp) {
        // 根据雇员到id， 得到该雇员应当添加到哪条链表
        int empLinkedListIndex = hashFun(emp.id);
        // 将emp添加进对应到链表中
        empLinkedLists[empLinkedListIndex].add(emp);
    }

    // 遍历所有到链表
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedLists[i].list();
        }
    }

    // 编写一个散列函数, 使用简单到取模法
    public int hashFun(int id) {
        return id % size;
    }

}


class Employee {

    public int id;
    public String name;
    public Employee next;

    public Employee(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}

class EmpLinkedList {

    // 头指针指向第一个雇员, 因此我们的这个链表的head是指向第一个雇员Employee的
    private Employee head; // default null


    /*** 添加雇员到链表
     *  1. 假设，当添加雇员时id是自增长，即id的分配总是从小到大。 因此我们将该雇员直接加入到本链表到最后一个即可
     * */
    public void add(Employee emp) {
        // 如果是添加第一个雇员
        if (head == null) {
            head = emp;
            return;
        }

        // 如果不是添加第一个雇员， 则使用第一个辅助指针，帮助定位到最后
        Employee curEmp = head;
        while (true) {
            if (curEmp.next == null) {
                // 说明到链表最后
                break;
            }
            curEmp = curEmp.next; // 后移
        }
        // 退出循环时直接将emp加入链表；
        curEmp.next = emp;
    }

    // 遍历链表到雇员信息
    public void list() {
        if (head == null) {
            // 空链表
            System.out.println("当前链表为空");
            return;
        }

        System.out.println("当前链表到信息为： ");
        Employee curEmp = head;

        while (true) {
            System.out.printf("=> id = %d name=%s", curEmp.id, curEmp.name);
            if (curEmp.next == null) {
                // 这是最后节点
                break;
            }
            curEmp = curEmp.next; // 后移遍历
        }
    }
}