package world.shiyu.queue;

public class ArrayQueueDemo {
    public static void main(String[] args){

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
            System.out.println("The queue is full!");
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
