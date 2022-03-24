package com;

import java.util.*;

public class BFS {
    public static void bfs(Map<String, String[]> graph, String s) {
        List<String> queue = new ArrayList<>();
        //图起始点头插队列
        queue.add(s);
        Set<String> seen = new HashSet<>();
        seen.add(s);
        //遍历图
        while (queue.size()>0) {
            //出队
            String v = queue.remove(0);
            String[] nodes = graph.get(v);
            //遍历相邻节点，如果节点没出现过就加入队列
            for (String node : nodes) {
                if (!seen.contains(node)) {
                    queue.add(node);
                    seen.add(node);
                }
            }
            System.out.println(v);
        }
    }

    //存储parent
    public static Map<String, String> bfs2(Map<String, String[]> graph, String s) {
        List<String> queue = new ArrayList<>();
        queue.add(s);
        Set<String> seen = new HashSet<>();
        seen.add(s);

        Map<String, String> parent = new HashMap<>();
        parent.put(s, null);

        while (queue.size()>0) {
            String vertex = queue.remove(0);
            String[] nodes = graph.get(vertex);
            for (String w : nodes) {
                if (!seen.contains(w)) {
                    queue.add(w);
                    seen.add(w);
                    parent.put(w, vertex);
                }
            }
        }
        return parent;
    }

    /*
    graph = {
        "A": ["B", "C"],
        "B": ["A", "C", "D"],
        "C": ["A", "B", "D", "E"],
        "D": ["B", "C", "E", "F"],
        "E": ["C", "D"],
        "F": ["D"]
    }
     */
    public static void main(String[] args) {
        Map<String, String[]> graph = new HashMap<>();
        graph.put("A", new String[]{"B", "C"});
        graph.put("B", new String[]{"A", "C", "D"});
        graph.put("C", new String[]{"A", "B", "D", "E"});
        graph.put("D", new String[]{"B", "C", "E", "F"});
        graph.put("E", new String[]{"C", "D"});
        graph.put("F", new String[]{"D"});

        //从A出发
//        bfs(graph, "A");
//        bfs(graph, "E");
        Map<String, String> parent = bfs2(graph, "E");
//        System.out.println(parent);

        //B的路径
        String v = "B";
        while (v != null){
            System.out.println(v);
            v = parent.get(v);
        }
    }
}
