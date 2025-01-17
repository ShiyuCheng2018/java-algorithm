# 栈(stack)

## 栈的介绍 

1. 栈的英文为(stack)

2. 栈是一个先入后出(FILO-First In Last Out)的有序列表。

3. 栈(stack)是限制线性表中元素的插入和删除只能在线性表的同一端进行的一种特殊线性表。允许插入和删除的 一端，为变化的一端，称为栈顶(Top)，另一端为固定的一端，称为栈底(Bottom)。

4. 根据栈的定义可知，最先放入栈中元素在栈底，最后放入的元素在栈顶，而删除元素刚好相反，最后放入的元 素最先删除，最先放入的元素最后删除

5. 图解方式说明出栈(pop)和入栈(push)的概念

## 栈的应用场景

1, 子程序的调用：在跳往子程序前，会先将下个指令的地址存到堆栈中，直到子程序执行完后再将地址取出，以 回到原来的程序中。

2, 处理递归调用：和子程序的调用类似，只是除了储存下一个指令的地址外，也将参数、区域变量等数据存入堆 栈中。

3, 表达式的转换[中缀表达式转后缀表达式]与求值(实际解决)。

4, 二叉树的遍历。

5, 图形的深度优先(depth 一 first)搜索法。

## 逆波兰计算器 (Reverse Polish Notation)
我们完成一个逆波兰计算器，要求完成如下任务:

1. 输入一个逆波兰表达式(后缀表达式)，使用栈(Stack), 计算其结果``

2. 支持小括号和多位数整数，因为这里我们主要讲的是数据结构，因此计算器进行简化，只支持对整数的计算。

3. 思路分析

> 例如: (3+4)×5-6 对应的后缀表达式就是 3 4 + 5 × 6 - , 针对后缀表达式求值步骤如下:

>1. 从左至右扫描， 将 3 和 4 压入堆栈； 
>2. 遇到+运算符， 因此弹出 4 和 3（4 为栈顶元素， 3 为次顶元素），计算出 3+4 的值，得 7， 再将 7 入栈； 
>3. 将 5 入栈； 
>4. 接下来是×运算符， 因此弹出 5 和 7， 计算出 7×5=35， 将 35 入栈； 
>5. 将 6 入栈； 
>6. 最后是-运算符， 计算出 35-6 的值，即 29， 由此得出最终结果

## 中缀表达式转换为后缀表达式

大家看到，后缀表达式适合计算式进行运算，但是人却不太容易写出来，尤其是表达式很长的情况下，因此在开发 中，我们需要将 中缀表达式转成后缀表达式。

> 具体步骤如下:
> 1. 初始化两个栈：运算符栈 s1
> 2. 从左至右扫描中缀表达式；
> 3. 遇到操作数时，将其压 s2；
> 4. 遇到运算符时，比较其与 s1 栈顶运算符的优先级：
>   1. 如果 s1 为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈；
>    2. 否则， 若优先级比栈顶运算符的高，也将运算符压入 s1；
>    3. 否则， 将 s1 栈顶的运算符弹出并压入到 s2 中，再次转到(4-1)与 s1 中新的栈顶运算符相比较；
> 5. 遇到括号时：
>    1. 如果是左括号“(”，则直接压入 s1
>    2. 如果是右括号“)”，则依次弹出 s1 栈顶的运算符，并压入 s2， 直到遇到左括号为止，此时将这一对括号丢弃
> 6. 重复步骤 2 至 5， 直到表达式的最右边
> 7. 将 s1 中剩余的运算符依次弹出并压入 s2
> 8. 依次弹出 s2 中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式