package world.shiyu.stack;

public class Calculator {

    public static void main(String[] args) {
        String expression = "70+3*6+20";
        // 创建数栈与符号栈
        ArrayStack_2 numStack = new ArrayStack_2(10);
        ArrayStack_2 operStack = new ArrayStack_2(10);

        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';
        String keepNum = ""; // 用于拼接多位数字

        while (true) {
            // 依次得到expression的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            // 判断ch是什么类型来对应处理
            if (operStack.isOper(ch)) {
                // 判断当前符号栈是否为空
                if (!operStack.isEmpty()) {
                    if (operStack.priority(ch) <= operStack.priority((char) operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, (char) oper);
                        // 把运算结果进数栈'
                        numStack.push(res);
                        // 当前符号进符号栈
                        operStack.push(ch);
                    } else {
                        // 如果当前符号优先级大于栈栈符号， 直接入栈
                        operStack.push(ch);
                    }
                } else {
                    // 如果为空直接进符号栈
                    operStack.push(ch);
                }
            } else {
                // 可能是多位数字， 在处理数字时，需要像expression当表达式index后再看一位， 如果是数就进行扫描， 如果是符号就入栈
                // 需要定义一个字符串变量用于拼接
                keepNum += ch;

                // 如果ch已经是expression当最后一位，直接入栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    // 判断下一个数字是否为数字， 如果是数字则继续扫描， 如果是运算符就入栈
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        // 判断为操作符
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }

            }

            index++;

            if (index >= expression.length()) {
                break;
            }
        }

        // 当表达式扫描完毕后， 按顺序从数栈和符号栈pop出相应数据进行运算
        while (true) {
            // 如果符号栈为空， 数栈只有一个数字--结果
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, (char) oper);
            numStack.push(res);
        }
        System.out.printf("表达式 %s = %d", expression, numStack.pop());
    }
}


class ArrayStack_2 {

    private int maxSize;
    private int[] stack; // 数组模拟栈存储数据
    private int top = -1; // 表示栈顶

    public ArrayStack_2(int maxSize) {
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

    // 偷看栈顶数据
    public int peek() {
        return stack[top];
    }

    // 返回运算符优先级， 优先级使用数字表示， 数字越大则优先级越高
    public int priority(char oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        }
        return -1; // 目前计算器不支持此符号
    }

    // 判断是不是一个运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '/' || val == '*';
    }

    //计算方法
    public int cal(int num1, int num2, char oper) {
        int res = 0; // res 用于存放计算的结果
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1; // 注意顺序；
                break;
            case '/':
                res = num2 / num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            default:
                break;
        }
        return res;
    }

}


