package world.shiyu.graph;

import java.util.ArrayList;
import java.util.Arrays;

public class Graph {

    private ArrayList<String> vertexList; // 存储顶点集合
    private int[][] edges; // 存储图的对应邻接矩阵
    private int numOfEdges; // 表示边的数目

    public static void main(String[] args){
        // 测试
        int n = 5; // 节点个数
        String vertexValues[] = {"A", "B", "C", "D", "E"};

        Graph graph = new Graph(n);

        // 添加顶点
        for(String value : vertexValues){
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

    }

    // 构造器

    public Graph(int n) {
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        numOfEdges = 0;
    }

    // 插入节点
    public void insertVertix(String vertex){
        vertexList.add(vertex);
    }

    /** 添加边
     *
     * @param v1 表示点的下标即使第几个顶点 "A"-"B" "A"->0 "B"->1
     * @param v2 第二个顶点对应的下标
     * @param weight 表示权值
     * */
    public void insertEdge(int v1, int v2, int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    // 图中常用的方法， 返回节点的个数
    public int getNumOfEdges(){
        return vertexList.size();
    }

    // 得到边的数目
    public int getNumberofEdges(){
        return numOfEdges;
    }

    // 返回节点i（对应的数据）
    public String getValueByIndex(int i){
        return vertexList.get(i);
    }

    // 返回v1 和 v2的权值
    public int getWieght(int v1, int v2){
        return edges[v1][v2];
    }

    // 显示图对应的矩阵
    public void showGraph(){
        for(int[] link : edges){
            System.out.println(Arrays.toString(link));
        }
    }
}
