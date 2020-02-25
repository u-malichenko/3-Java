//package Lesson_8.general_tree;
//
//import TaskTree.tree.Node;
//
//import java.util.*;
//
//public class TreeBFS implements CreateTree{
//    private ArrayList<Node> treeBfs = new ArrayList<>();
//
//    public TreeBFS() {
//        Node node = new Node(0, 0, new ArrayList<>());
//        treeBfs.add(node);
//    }
//
//    public ArrayList<Node> getTreeBfs() {
//        return treeBfs;
//    }
//
//    @Override
//    public void createTree(String[] str, ArrayList<Node> tree) {
//        LinkedList<Node> queue = new LinkedList<>();
//        queue.add(treeBfs.get(0));
//
//        do {
//            if (queue.getFirst().getNumber() == Integer.parseInt(str[2])) {
//                Node nodeNew = new Node(Integer.parseInt(str[0]), Integer.parseInt(str[2]), new ArrayList<>());
//                queue.getFirst().getChildren().add(nodeNew);
//                queue.removeFirst();
//                return;
//            } else {
//                if (queue.getFirst().getChildren().size() != 0) {
//                    queue.addAll(queue.getFirst().getChildren());
//                }
//                queue.removeFirst();
//            }
//        }while (!queue.isEmpty());
//    }
//}
