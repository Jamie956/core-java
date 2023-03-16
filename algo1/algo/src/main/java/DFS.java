import java.util.*;

public class DFS {
    public static void dfs(Map<String, String[]> graph, String s) {
        List<String> stack = new ArrayList<>();
        //图起始点入栈
        stack.add(s);
        Set<String> seen = new HashSet<>();
        seen.add(s);
        //遍历图
        while (stack.size()>0) {
            //出栈
            String v = stack.remove(stack.size()-1);
            String[] nodes = graph.get(v);
            //遍历相邻节点，如果节点没出现过就加入队列
            for (String node : nodes) {
                if (!seen.contains(node)) {
                    stack.add(node);
                    seen.add(node);
                }
            }
            System.out.println(v);
        }
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
//        dfs(graph, "A");
        dfs(graph, "E");
    }
}
