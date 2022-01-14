package com.jamie.algo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TreeCreate {
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

    public static Node list2Tree(List<Node> list) {
        //map id to node
        Map<String, Node> nodeMap = list.stream().collect(Collectors.toMap(Node::getId, e -> e));

        Node root = null;
        for (Node node : list) {
            String parentId = node.getParentId();
            if (parentId == null) {
                root = node;
                continue;
            }
            //当前节点挂到父节点的child
            Node parent = nodeMap.get(parentId);
            if (parent.getChild() != null) {
                parent.getChild().add(node);
            } else {
                List<Node> child = new ArrayList<>();
                child.add(node);
                parent.setChild(child);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        List<Node> list = new ArrayList<>();
        list.add(new Node("3", "PC", "1"));
        list.add(new Node("1", "PA", "2"));
        list.add(new Node("4", "PD", "1"));
        list.add(new Node("2", "PB", "0"));
        list.add(new Node("5", "PE", "2"));
        list.add(new Node("0", "RR", null));
        list.add(new Node("6", "PF", "3"));
        list.add(new Node("7", "PG", "2"));
        list.add(new Node("8", "PH", "4"));
        list.add(new Node("9", "JI", "5"));

        Node root = list2Tree(list);
        System.out.println(root);
    }
}
