package Lesson_8.DZ_My;

import java.util.ArrayList;
import java.util.List;

public class DFS1 {
    static class Node {
        int data;
        boolean visited;
        List<Node> child;

        Node(int data) {
            this.data = data;
            this.child = new ArrayList<>();
        }

        public void addChild(Node neighbourNode) {
            this.child.add(neighbourNode);
        }

        public List<Node> getChild() {
            return child;
        }

        public void setChild(List<Node> child) {
            this.child = child;
        }
    }
    // Recursive DFS
    public void dfs(Node node) {
        System.out.print(node.data + " ");
        List<Node> neighbours = node.getChild();
        node.visited = true;
        for (int i = 0; i < neighbours.size(); i++) {
            Node n = neighbours.get(i);
            if (n != null && !n.visited) {
                dfs(n);
            }
        }
    }
    // Iterative DFS using stack
//    public void dfsUsingStack(Node node) {
//        Stack<Node> stack = new Stack<Node>();
//        stack.add(node);
//        while (!stack.isEmpty()) {
//            Node element = stack.pop();
//            if (!element.visited) {
//                System.out.print(element.data + " ");
//                element.visited = true;
//            }
//
//            List<Node> neighbours = element.getChild();
//            for (int i = 0; i < neighbours.size(); i++) {
//                Node n = neighbours.get(i);
//                if (n != null && !n.visited) {
//                    stack.add(n);
//                }
//            }
//        }
//    }
    public static void main(String arg[]) {
//        id parentId
//        1  0
//        2  0
//        3  1
//        4  3
//        5  4
//        6  0
        Node node0 = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        node0.addChild(node1);
        node0.addChild(node2);
        node1.addChild(node3);
        node3.addChild(node4);
        node4.addChild(node5);
        node0.addChild(node6);

        DFS1 dfs1Example = new DFS1();
//
//        System.out.println("The DFS traversal of the graph using stack ");
//        dfsExample.dfsUsingStack(node0);
//
//        System.out.println();
//
//        // Resetting the visited flag for nodes
//        node0.visited = false;
//        node1.visited = false;
//        node2.visited = false;
//        node3.visited = false;
//        node4.visited = false;
//        node5.visited = false;
//        node6.visited = false;
        System.out.println("The DFS traversal of the graph using recursion ");
        dfs1Example.dfs(node0);
    }
}