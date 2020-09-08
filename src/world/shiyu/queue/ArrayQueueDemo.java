package world.shiyu.queue;

import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args){
        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key;
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        System.out.println("System online.");

        while(loop){
            System.out.println("s(show): show the queue");
            System.out.println("e(exit): exit the app");
            System.out.println("a(add):  add data to the queue");
            System.out.println("g(get):  get data from the queue");
            System.out.println("h(head): show the queue's head");
            key = scanner.next().charAt(0);
            switch (key){
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    try {
                        System.out.print("Please type a number: ");
                        int value = scanner.nextInt();
                        arrayQueue.addQueue(value);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'g':
                    try {
                        int result = arrayQueue.getQueue();
                        System.out.printf("You just got the data: %d\n", result);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try{
                        int result = arrayQueue.peekqueue();
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

class ArrayQueue{
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    public ArrayQueue(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1; // ahead one index of queue head
        rear = -1; // queue tail, the last index of the queue
    }

    public boolean isFull(){
        return rear == maxSize - 1;
    }

    public boolean isEmpty(){
        return front == rear;
    }

    public void addQueue(int n){
        // is the queue full?
        if(isFull()){
            throw new RuntimeException("The queue is full!");
        }
        rear++; // move rear with one index behind
        arr[rear] = n;
    }

    public int getQueue(){
        // is the queue empty?
        if(isEmpty()){
            throw new RuntimeException("The queue is empty!");
        }
        front++;
        return arr[front];
    }

    public void showQueue(){
        if(isEmpty()){
            System.out.println("The queue is empty!");
        }
        for(int i = 0; i < arr.length; i++){
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    public int peekqueue(){
        if(isEmpty()){
            throw new RuntimeException("The queue is empty!");
        }
        return arr[front+1];
    }

}
