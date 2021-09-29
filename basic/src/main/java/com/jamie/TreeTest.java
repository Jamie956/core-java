package com.jamie;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TreeTest {
    @Data
    static class Node{
        private String id;
        private String name;
        private String parentId;
        private List<Node> child;

        public Node(String id, String name, String parentId) {
            this.id = id;
            this.name = name;
            this.parentId = parentId;
        }
    }

    public static void main(String[] args) {
        List<Node> list = new ArrayList<>();
        list.add(new Node("2", "PB", "0"));
        list.add(new Node("3", "PC", "1"));
        list.add(new Node("1", "PA", "2"));
        list.add(new Node("4", "PD", "1"));
        list.add(new Node("5", "PE", "2"));
        list.add(new Node("6", "PF", "3"));
        list.add(new Node("7", "PG", "2"));
        list.add(new Node("8", "PH", "4"));

        List<Node> roots = list2Tree(list, "0");
        System.out.println(roots);
    }

    public static List<Node> list2Tree(List<Node> list, String rootId) {
        //id作为map key，用来搜索节点
        Map<String, Node> collect = list.stream().collect(Collectors.toMap(Node::getId, e -> e));

        List<Node> roots = new ArrayList<>();
        for (Node e : list) {
            String parentId = e.getParentId();
            //根节点存入根节点数组
            if ("0".equals(parentId)) {
                roots.add(e);
                continue;
            }
            //搜索节点
            Node node = collect.get(parentId);
            //当前节点挂到父节点的child
            if (node.getChild() != null) {
                node.getChild().add(e);
            } else {
                List<Node> child = new ArrayList<>();
                child.add(e);
                node.setChild(child);
            }
        }
        return roots;
    }

}
