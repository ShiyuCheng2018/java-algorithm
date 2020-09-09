package world.shiyu.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ReversePolishNotation {
    public static void main(String[] args) {
        // 先定义一个逆波兰表达式
        // (3+4)*5-6 = 3 4 + 5 * 6 -
        // 说明： 为了方便数字和符号在逆波兰表达式空格隔开
        String suffixExpression = "3 4 + 5 * 6 -";

        /**
         * 思路：
         * 1, 先将"3 4 + 5 * 6 -"放进ArrayList
         * 2, 将ArrayList 传递给一个方法， 遍历列表配合栈完成计算
         *
         * */
        List<String> rpnList = getListString(suffixExpression);

        int result = calculate(rpnList);
        System.out.printf(suffixExpression.strip()+" = %d\n", result);

    }

    // 将一个逆波兰表达式， 依次把数据放入列表
    public static List<String> getListString(String suffixExpression) {
        // 将suffixEexpression分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    /**
     * 完成对逆波兰表达式对运算
     */
    public static int calculate(List<String> list) {
        // 创建一个栈, 只需要一个栈即可
        Stack stack = new Stack<String>();
        // 遍历list
        for (String item : list) {
            // 这里使用正则表达式取出数字
            if (item.matches("\\d+")) { // 匹配多位数
                // 入栈
                stack.push(item);
            } else {
                // 从pop出俩个数字，并进行运算, 结果入栈
                int num2 = Integer.parseInt((String) stack.pop());
                int num1 = Integer.parseInt((String) stack.peek());

                try {
                    int res = process(num1, num2, item);
                    stack.push(res+"");
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }

            }
        }
        // 最后的数据为计算结果
        return Integer.parseInt((String) stack.pop());

    }

    public static int process(int num1, int num2, String oper) {
        int res = 0;
        switch (oper) {
            case "+":
                res = num1 + num2;
                break;
            case "-":
                res = num1 - num2;
                break;
            case "*":
                res = num1 * num2;
                break;
            case "/":
                res = num1 / num2;
                break;
            default:
                throw new RuntimeException("运算符有误");
        }
        return res;
    }


}
