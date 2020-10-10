package world.shiyu.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {

    private ArrayList<String> vertexList; // 存储顶点集合
    private int[][] edges; // 存储图的对应邻接矩阵
    private int numOfEdges; // 表示边的数目
    // 定义boolean数组，记录某个节点是否被访问
    private boolean[] isVisited;

    public static void main(String[] args) {
        // 测试
        int n = 5; // 节点个数
        String vertexValues[] = {"A", "B", "C", "D", "E"};

        Graph graph = new Graph(n);

        // 添加顶点
        for (String value : vertexValues) {
            graph.insertVertix(value);
        }

        // 添加边
        // A-B A-C B-C B-D B-E
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);

        // 显示邻接矩阵
        graph.showGraph();
        // 深度遍历
        System.out.print("\n深度遍历: ");
//        graph.dfs();

        // 广度遍历
        System.out.print("\n广度遍历: ");
        graph.bsf();

    }

    // 构造器

    public Graph(int n) {
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        numOfEdges = 0;
        isVisited = new boolean[n];
    }

    // 得到第一个邻接节点的下标
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }

        return -1;
    }

    // 根据前一个邻接节点的下标来获取下一个邻接节点
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    /**
     * 深度优先遍历算法
     *
     * @param i 第一次就是0
     */
    private void dfs(boolean[] isVisited, int i) {
        System.out.print(getValueByIndex(i) + " -> ");
        // 将此节点设置为已经访问过
        isVisited[i] = true;

        // 拿到i的第一个邻接点
        int firstNeighbor = getFirstNeighbor(i);
        while (firstNeighbor != -1) {
            // 说明有邻接节点
            if (!isVisited[firstNeighbor]) {
                dfs(isVisited, firstNeighbor);
            }
            // 如果此节点已经被访问过
            firstNeighbor = getNextNeighbor(i, firstNeighbor);
        }

    }

    // 对dfs进行重载，遍历所有对节点
    public void dfs() {
        // 遍历所有的节点进行dfs
        for (int i = 0; i < getNumberofEdges(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    // 对一个节点进行广度优先遍历的方法
    private void bfs(boolean[] isVisited, int i) {
        int u; // 表示队列的头节点的对应下标
        int w; // 邻接点w
        // 队列， 记录节点访问顺序
        LinkedList queue = new LinkedList();

        // 访问节点， 输出节点信息
        System.out.print(getValueByIndex(i) + " -> ");
        // 标记为已访问
        isVisited[i] = true;
        // 将节点加入队列
        queue.addLast(i);

        while (queue.isEmpty()) {
            // 取出队列头节点下标
            u = (Integer) queue.removeFirst();
            // 得到第一个邻接点的下标w
            w = getFirstNeighbor(u);

            while (w != -1) {
                // 找到， 检测是否访问过
                if (isVisited[w]) {
                    System.out.print(getValueByIndex(w) + " -> ");
                    isVisited[w] = true;
                    // 入队列
                    queue.addLast(w);
                }
                // 以u为前驱点， 找w后面的下一个邻接点
                w = getNextNeighbor(u, w); // 体现出广度优先

            }
        }
    }

    // 遍历所有的节点进行广度优先搜索
    public void bsf() {
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }


    // 插入节点
    public void insertVertix(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 添加边
     *
     * @param v1     表示点的下标即使第几个顶点 "A"-"B" "A"->0 "B"->1
     * @param v2     第二个顶点对应的下标
     * @param weight 表示权值
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    // 图中常用的方法， 返回节点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    // 得到边的数目
    public int getNumberofEdges() {
        return numOfEdges;
    }

    // 返回节点i（对应的数据）
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    // 返回v1 和 v2的权值
    public int getWieght(int v1, int v2) {
        return edges[v1][v2];
    }

    // 显示图对应的矩阵
    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }
}
