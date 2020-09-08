package world.shiyu.queue;

import java.util.Scanner;

@SuppressWarnings("Duplicates")
public class CircleArrayQueueDemo {
    public static void main(String[] args){
        CircleArrayQueue circleArrayQueue = new CircleArrayQueue(4); // 其队列有效数据最多为3
        char key;
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        System.out.println("System online. test: CircleArrayQueueDemo");

        while(loop){
            System.out.println("s(show): show the queue");
            System.out.println("e(exit): exit the app");
            System.out.println("a(add):  add data to the queue");
            System.out.println("g(get):  get data from the queue");
            System.out.println("h(head): show the queue's head");
            key = scanner.next().charAt(0);
            switch (key){
                case 's':
                    circleArrayQueue.showQueue();
                    break;
                case 'a':
                    try {
                        System.out.print("Please type a number: ");
                        int value = scanner.nextInt();
                        circleArrayQueue.addQueue(value);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'g':
                    try {
                        int result = circleArrayQueue.getQueue();
                        System.out.printf("You just got the data: %d\n", result);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try{
                        int result = circleArrayQueue.peekqueue();
                        System.out.printf("You just peaked the data head: %d\n", result);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println("System offline.");

    }
}

class CircleArrayQueue{
    private int maxSize;
    private int front; // front 指向队列的第一个元素， 也就是说arr[front], 初始值为0.
    private int rear; // rear指向队列最后一个元素的后一个位置， 因为希望空出一个空间最为约定， 初始值为0.
    private int[] arr;

    public  CircleArrayQueue(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = 0;
        rear = 0;
    }

    public boolean isFull(){
        return (rear + 1) % maxSize == front;
    }

    public boolean isEmpty(){
        return front == rear;
    }

    public void addQueue(int n){
        // is the queue full?
        if(isFull()){
            throw new RuntimeException("The queue is full!");
        }
        arr[rear] = n;
        rear = (rear + 1) % maxSize; // 将rear后移， 这里必须考虑取模, 这样子保证了不越界
    }

    public int getQueue(){
        // is the queue empty?
        if(isEmpty()){
            throw new RuntimeException("The queue is empty!");
        }
        // 这里需要分析front是指向队列的第一个元素
        // 1，先把front的对应的值保存给临时变量
        // 2，再将front后移, 考虑取摸
        // 3，将临时保存的变量返回
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    public void showQueue(){
        if(isEmpty()){
            System.out.println("The queue is empty!");
        }
        // 思路： 从front开始遍历，遍历多少个原始
        //
        for(int i = front; i < front + size(); i++){
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    public int size(){
        // rear = 2;
        // front = 1;
        // maxSize = 3;
        return (rear + maxSize - front) % maxSize;
    }

    public int peekqueue(){
        if(isEmpty()){
            throw new RuntimeException("The queue is empty!");
        }
        return arr[front];
    }

}
