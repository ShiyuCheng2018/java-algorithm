package world.shiyu.stack;

import java.util.Scanner;

public class ArrayStackDemo {

    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(4);
        char key;
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);

        System.out.println("System online. test: ArrayStackDemo");
        while (loop) {
            while (loop) {
                System.out.println("s(show): show the stack");
                System.out.println("e(exit): exit the app");
                System.out.println("a(add):  add data to the stack");
                System.out.println("p(pop):  get data from the stack");
                System.out.printf("请输入你的选择： ");
                key = scanner.next().charAt(0);
                switch (key) {
                    case 's':
                        stack.list();
                        break;
                    case 'a':
                        try {
                            System.out.print("Please type a number: ");
                            int value = scanner.nextInt();
                            stack.push(value);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 'p':
                        try {
                            int result = stack.pop();
                            System.out.printf("You just got the data: %d\n", result);
                        } catch (Exception e) {
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

        }
        System.out.println("System offline.");
    }
}

/**
 * TODO: 使用链表来实现栈
 */

class ArrayStack {

    private int maxSize;
    private int[] stack; // 数组模拟栈存储数据
    private int top = -1; // 表示栈顶

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    public boolean isFull() {
        return top == this.maxSize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(int value) {
        if (isFull()) {
            throw new RuntimeException("栈已满！");
        }
        top++;
        stack[top] = value;
    }

    // 将栈顶的数据返回
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈已空！");
        }
        int value = stack[top];
        top--;
        return value;
    }

    // 遍历栈, 遍历使需要从栈顶显示数据
    public void list() {
        if (isEmpty()) {
            throw new RuntimeException("栈已空！");
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

}
