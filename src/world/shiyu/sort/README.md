# 排序算法 （Sort）

>排序也成之为排序算法(sort algorithm)， 排序是将一组数据， 指定的顺序进行排序的过程。

## 排序的分类：
1. 内容排序：
指将需要处理的所有数据都加载到内部储存器中进行排序。
2.外部排序法：
数据量过大， 无法全部加载到内存中， 需要借助外部存储进行排序
3. 常见到排序算法分类
~~~javascript
排序 = {
    内部排序: {
        插入排序:{
            直接插入排序:"Straight Insertion Sort",
            希尔: "Shell's Sort)"
        },
        选择排序: {
            简单选择排序:"Selection sort",
            堆排序: "Heapsort"
        },
        交换排序: {
            冒泡排序: "Bubble sort",
            快速排序: "Quick sort"
        },
        归并排序:"Merge sort",
        基数排序:"Radix sort"
    },
    
    外部排序: {
        // 使用内存和外部结合
    }
}
~~~

## 度量一个程序(算法)执行时间的两种方法

1. 事后统计的方法

    这种方法可行, 但是有两个问题：一是要想对设计的算法的运行性能进行评测，需要实际运行该程序；二是所 得时间的统计量依赖于计算机的硬件、软件等环
    境因素, 这种方式，要在同一台计算机的相同状态下运行，才能比较那个算法速度更快。
                              
2. 事前估算的方法 通过分析某个算法的时间复杂度来判断哪个算法更优.

## 时间频度

基本介绍 时间频度：一个算法花费的时间与算法中语句的执行次数成正比例，哪个算法中语句执行次数多，它花费时间 就多。一个算法中的语句执行次数称
为语句频度或时间频度。记为 T(n)。

## 时间复杂度

1. 一般情况下，算法中的基本操作语句的重复执行次数是问题规模 n 的某个函数，用 T(n)表示，若有某个辅 助函数 f(n)，使得当 n 趋近于无穷大时，
T(n) / f(n) 的极限值为不等于零的常数，则称 f(n)是 T(n)的同数量级函数。 记作 T(n)=Ｏ( f(n) )，称Ｏ( f(n) ) 为算法的渐进时间复杂度，
简称时间复杂度。

2. T(n) 不同，但时间复杂度可能相同。 如：T(n)=n²+7n+6 与 T(n)=3n²+2n+2 它们的 T(n) 不同，但时间复杂 度相同，都为 O(n²)。

3. 计算时间复杂度的方法：  用常数 1 代替运行时间中的所有加法常数 T(n)=n²+7n+6 => T(n)=n²+7n+1  修改后的运行次数函数中，只保留最高
阶项 T(n)=n²+7n+1 => T(n) = n²  去除最高阶项的系数 T(n) = n² => T(n) = n² => O(n²)

## 常见的时间复杂度

1. 常数阶 O(1)

2. 对数阶 O(log2n)

3. 线性阶 O(n)

4. 线性对数阶 O(nlog2n)

5. 平方阶 O(n^2)

6. 立方阶 O(n^3)

7. k 次方阶 O(n^k)

8. 指数阶 O(2^n)

算法的空间复杂度简介

## 基本介绍

1. 类似于时间复杂度的讨论，一个算法的空间复杂度(Space Complexity)定义为该算法所耗费的存储空间，它也是 问题规模 n 的函数。

2. 空间复杂度(Space Complexity)是对一个算法在运行过程中临时占用存储空间大小的量度。有的算法需要占用的 临时工作单元数与解决问题的规模 n 有关，它随着 n 的增大而增大，当 n 较大时，将占用较多的存储单元，例 如快速排序和归并排序算法, 基数排序就属于这种情况

3. 在做算法分析时，主要讨论的是时间复杂度。从用户使用体验上看，更看重的程序执行的速度。一些缓存产品 (redis, memcache)和算法(基数排序)本质就是用空间换时间.

## 冒泡排序

### 基本介绍

冒泡排序（Bubble Sorting）的基本思想是：通过对待排序序列从前向后（从下标较小的元素开始）,依次比较 相邻元素的值，若发现逆序则交换，使值较
大的元素逐渐从前移向后部，就象水底下的气泡一样逐渐向上冒。

规则：
1. 一共要进行数组到大小-1次大的循环
2. 每一趟排序的次数在一次逐渐减少
3. 如果我们发现在某趟排序中， 没有发生一次交换， 可以提前结束冒泡排序， 进行优化

优化： 因为排序的过程中，各元素不断接近自己的位置，如果一趟比较下来没有进行过交换，就说明序列有序，因此要在 排序过程中设置一个标志 flag 判
断元素是否进行过交换。从而减少不必要的比较。(这里说的优化，可以在冒泡排 序写好后，在进行)