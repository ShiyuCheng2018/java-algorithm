# 递归 （Recursion）

## 递归的概念

简单的说: 递归就是方法自己调用自己,每次调用时传入不同的变量.递归有助于编程者解决复杂的问题,同时 可以让代码变得简洁。

递归调用机制

我列举两个小案例,来帮助大家理解递归，部分学员已经学习过递归了，这里在给大家回顾一下递归调用机制

1. 打印问题
~~~java
class Print{
    public static void test(int n){
         if(n>2){
             test(n-1);
         }
         System.out.print("n = "+n);
     }
}
~~~

2. 阶乘问题
~~~java
class Factorial{
    public static int factorial(int n){
        if(n==1){
            return 1;
        }else{
            return factorial(n-1)*n;
        }
    }
}
~~~

3. 使用图解方式说明了递归的调用机制

## 递归需要遵守的重要规则

1. 执行一个方法时，就创建一个新的受保护的独立空间(栈空间)

2. 方法的局部变量是独立的，不会相互影响, 比如 n 变量

3. 如果方法中使用的是引用类型变量(比如数组)，就会共享该引用类型的数据.

4. 递归必须向退出递归的条件逼近，否则就是无限递归,出现 StackOverflowError，死龟了:)

5. 当一个方法执行完毕，或者遇到 return，就会返回，遵守谁调用，就将结果返回给谁，同时当方法执行完毕或 者返回时，该方法也就执行完毕.

## 对迷宫问题的讨论

1. 小球得到的路径，和程序员设置的找路策略有关即：找路的上下左右的顺序相关

2. 再得到小球路径时，可以先使用(下右上左)，再改成(上右下左)，看看路径是不是有变化

3. 测试回溯现象

4. 思考: 如何求出最短路径? 思路 -> 代码实现.

## 八皇后问题 (回溯算法)

> 八皇后问题，是一个古老而著名的问题，是回溯算法的典型案例。该问题是国际西洋棋棋手马克斯·贝瑟尔于 1848 年提出：在 8×8 格的国际象棋上摆放八个皇后，使其不能互相攻击，即：任意两个皇后都不能处于同一行、 同一列或同一斜线上，问有多少种摆法(92)。

### 八皇后问题算法思路分析

1. 第一个皇后先放第一行第一列

2. 第二个皇后放在第二行第一列、然后判断是否 OK， 如果不 OK，继续放在第二列、第三列、依次把所有列都 放完，找到一个合适

3. 继续第三个皇后，还是第一列、第二列……直到第 8 个皇后也能放在一个不冲突的位置，算是找到了一个正确 解

4. 当得到一个正确解时，在栈回退到上一个栈时，就会开始回溯，即将第一个皇后，放到第一列的所有正确解， 全部得到.

5. 然后回头继续第一个皇后放第二列，后面继续循环执行 1,2,3,4 的步骤

> 说明： 理论上应该创建一个二维数组来表示棋盘，但是实际上可以通过算法，用一个一维数组即可解决问题. arr[8] = {0 , 4, 7, 5, 2, 6, 1, 3} //对应 arr 下标 表示第几行，即第几个皇后，arr[i] = val , val 表示第 i+1 个皇后，放在第 i+1 行的第 val+1 列