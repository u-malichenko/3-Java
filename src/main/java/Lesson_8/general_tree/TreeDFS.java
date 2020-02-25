//package Lesson_8.general_tree;
//
//import TaskTree.tree.Node;
//import java.util.ArrayList;
//
///**
// * Все дерево
// */
//public class TreeDFS implements CreateTree{
//    private ArrayList<Node> treeArr = new ArrayList<>();
//
//    public TreeDFS() {
//        Node node = new Node(0, 0, new ArrayList<>());
//        treeArr.add(node);
//    }
//
//    public ArrayList<Node> getTreeDfs() {
//        return treeArr;
//    }
//
//    /**
//     * List(int number, int parent, ArrayList<List> children)
//     * @param str: 0 - number, 1 - parent
//     */
//    @Override
//    public void createTree(String[] str, ArrayList<Node> tree) {
//        for (Node parent : tree) {
//            if (parent.getNumber() == Integer.parseInt(str[2])){
//                Node node = new Node (Integer.parseInt(str[0]), Integer.parseInt(str[2]), new ArrayList<>());
//                parent.getChildren().add(node);
//                return;
//            }
//            else createTree(str, parent.getChildren());
//        }
//    }
//}
