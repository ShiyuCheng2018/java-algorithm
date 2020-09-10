package world.shiyu.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ReversePolishNotation {
    public static void main(String[] args) {

        /** 将一个中缀表达式转为后缀表达式
         *  说明
         *  1, 1+((2+3)*4)-5 => 1 2 3 + 4 * + 5 -
         *  2, 因为直接对一个字符串进行操作不方便， 因此现将这个字符串转成中缀表达式对应对list
         *  即ArrayList【1, +, (, (, 2, +, 3, ), *, 4, ), -, 5 】
         *
         * */

        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        // 逆波兰表达式列表: [1, 2, 3, +, 4, *, +, 5, -]
        System.out.println("中缀表达式列表: " + infixExpressionList);

        // 将中缀list转换为后缀list


        // 先定义一个逆波兰表达式
        // (3+4)*5-6 = 3 4 + 5 * 6 -
        // 说明： 为了方便数字和符号在逆波兰表达式空格隔开
//        String suffixExpression = "3 4 + 5 * 6 -";
        List<String> parsedSuffixExpressionList = toParseSuffixExpressionList(infixExpressionList);
        System.out.println("逆波兰表达式列表: " + parsedSuffixExpressionList);

        int result = calculate(parsedSuffixExpressionList);
        System.out.printf(expression.strip()+" = %d\n", result);

    }

    public static List<String> toParseSuffixExpressionList(List<String> ls) {
        // 初始化俩个栈
        Stack<String> s1 = new Stack<String>(); // 符号栈
        // 因为 s2 在整个转换过程中， 没有pop操作， 且最后需要逆序，所以可以直接使用List<Sting>
        List<String> s2 = new ArrayList<String>();

        // 遍历ls
        for (String item : ls) {
            // 如果是一个数， 入栈
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                while (!s1.peek().equals("(")) { // 如果是"）"， 则s1一直弹出数据知道栈顶为"（"， 并且将这一对括号丢弃
                    s2.add(s1.pop());
                }
                s1.pop(); // 消掉了一对括号
            } else {
                // 当item当优先级小于等于s1的栈顶运算符，将s1 栈顶运算符弹出并且加入到s2中， 之后再次与s1栈顶比较
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                // 还需要将item压入栈顶
                s1.push(item);
            }
        }

        // 将s1中剩余顶运算符加入s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }

        return s2; // 返回逆波兰表达式列表
    }

    ;

    // 将中缀表达式转成对应对list
    public static List<String> toInfixExpressionList(String s) {
        // 先定义一个List， 存放中缀表达对应对内容

        List<String> ls = new ArrayList<String>();
        int i = 0; // 这是一个指针， 用于遍历中缀表达式字符串
        String str; // 对多位数对拼接
        char c; // 没遍历一个字符， 放入c

        do {
            // 如果c为非数字， 我们就需要加入到ls
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add("" + c);
                i++;
            } else {
                str = ""; // 先将str制成空串 '0'[48] -> '9'[57]
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c;
                    i++;
                }
                ls.add(str);
            }

        } while (i < s.length());
        return ls;
    }

    ;

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
                    stack.push(res + "");
                } catch (Exception e) {
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

// 编写一个operation顶类， 可以返回运算符优先级

class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    public static int getValue(String oper) {
        int result = 0;
        switch (oper) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            case "(":
                break;
            case ")":
                break;
            default:
                throw new RuntimeException("运算符有误");
        }
        return result;
    }

}
