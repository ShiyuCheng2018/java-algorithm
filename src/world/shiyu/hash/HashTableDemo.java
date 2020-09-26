package world.shiyu.hash;

import java.util.Scanner;

public class HashTableDemo {
    public static void main(String[] args) {
        // 创建一个hash表
        HashTable hashTable = new HashTable(7);

        String key = "";
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("add: 添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");
            System.out.println();

            System.out.print("请输入指令: ");
            key = scanner.next();

            switch (key) {
                case "add":
                    System.out.print("输入id: ");
                    int id = scanner.nextInt();
                    System.out.print("输入名字: ");
                    String name = scanner.next();
                    // 创建雇员
                    Employee employee = new Employee(id, name);
                    hashTable.add(employee);
                    break;
                case "list":
                    hashTable.list();
                    break;
                case "find":
                    System.out.print("请输入要查找的id: ");
                    id = scanner.nextInt();
                    hashTable.findEmployeeById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }


    }


}

class HashTable {
    private EmpLinkedList[] empLinkedLists;
    private int size;

    public HashTable(int size) {
        this.size = size;
        // 初始化 empLinkedLists
        this.empLinkedLists = new EmpLinkedList[size];

        /** !!! 初始化每条链表
         * */
        for(int i = 0; i < size; i++){
            empLinkedLists[i] = new EmpLinkedList();
        }

    }

    // 添加雇员
    public void add(Employee emp) {
        // 根据雇员到id， 得到该雇员应当添加到哪条链表
        int empLinkedListIndex = hashFun(emp.id);
        // 将emp添加进对应到链表中
        empLinkedLists[empLinkedListIndex].add(emp);
    }

    // 根据输入的id查找雇员
    public void findEmployeeById(int id){
        int empLinkedListIndex = hashFun(id);
        Employee employee = empLinkedLists[empLinkedListIndex].findEmployeeById(id);

        if(employee != null){
            System.out.printf("在第%d条列表中找到此雇员信息: id=%d name=%s", empLinkedListIndex+1, employee.id, employee.name);
            System.out.println();
        }else {
            System.out.println("在hashTable中没有找到此雇员");
        }
    }

    // 遍历所有到链表
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedLists[i].list(i);
        }
        System.out.println();
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

    // 根据id查找雇员
    public Employee findEmployeeById(int id){
        // 判断链表是否为空
        if(this.head == null){
            System.out.println("当前链表为空链表");
            return null;
        }

        // 辅助指针
        Employee curEmp = head;
        while(true){
            if(curEmp.id == id){
                break; // 这是curEmp就指向要查找的雇员
            }
            if(curEmp.next == null){
                // 说明遍历完当前列表没有找到该雇员
                curEmp = null;
                break;
            }
            curEmp = curEmp.next; // 后移
        }

        return curEmp;
    }

    // 遍历链表到雇员信息
    public void list(int index) {
        if (head == null) {
            // 空链表
            System.out.println("第"+(index+1)+"条链表为空");
            return;
        }

        System.out.print("第"+(index+1)+"条链表的信息为： ");
        Employee curEmp = head;

        while (true) {
            System.out.printf("=> id = %d name=%s", curEmp.id, curEmp.name);
            System.out.println();
            if (curEmp.next == null) {
                // 这是最后节点
                break;
            }
            curEmp = curEmp.next; // 后移遍历
        }
    }

    /**
     * TODO: 删除列表
     * */
}