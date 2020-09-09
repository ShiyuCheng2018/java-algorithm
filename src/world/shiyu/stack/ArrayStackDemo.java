package world.shiyu.stack;

public class ArrayStackDemo {

    public static void main(String[] args){

    }
}

class ArrayStack{

    private int maxSize;
    private int[] stack; // 数组模拟栈存储数据
    private int top = -1; // 表示栈顶

    public  ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    public boolean isFull(){
        return top == this.maxSize - 1;
    }

    public boolean isEmpty(){
        return top == -1;
    }

    public void push(int value){
        if(isFull()){
            throw new RuntimeException("栈已满！");
        }
        top ++;
        stack[top] = value;
    }

    // 将栈顶的数据返回
    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈已空！");
        }
        int value = stack[top];
        top--;
        return value;
    }

    // 遍历栈, 遍历使需要从栈顶显示数据
    public void list(){
        if(isEmpty()){
            throw new RuntimeException("栈已空！");
        }
        for (int i = top; i>=0; i--){
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

}
