package Lesson_8.DZ_My;

import java.util.ArrayList;
import java.util.List;

public class DFS {
    static List<TopObject> topObjects = new ArrayList<>();

    public static void main(String[] args) {
        new TopObject(1, 0);
        new TopObject(2, 0);
        new TopObject(3, 1);
        new TopObject(4, 3);
        new TopObject(5, 4);
        new TopObject(6, 0);
        new TopObject(7, 0);
        new TopObject(8, 3);
        System.out.println(" See topObjects in debug");

        dfs(topObjects);
    }

    public static void dfs(List<TopObject> topObjects) {

        for (int i = 0; i < topObjects.size(); i++) {
            System.out.print(topObjects.get(i).id + " ");
            List<TopObject> child = topObjects.get(i).getChild();
            if (!child.isEmpty()) {
                dfs(child);
            }
        }
    }
}

class TopObject {
//    int id;
//    int parentId;
//    List<TopObject> children = new ArrayList<>();
//
//    public TopObject(int id, int parentId) {
//        this.id = id;
//        this.parentId = parentId;
//    }
//
//    void findNextInPreOrder(TreeNode node, int key) {
//        if (node == null) {
//            return;
//        }
//        if (node.value == key) {
//            if(node.left != null){
//                System.out.print("Next is on left: " + node.left.value);
//            } else if (node.right != null){
//                System.out.print("Next is on right: " + node.right.value);
//            } else {
//                System.out.print("There is no next node.");
//            }
//        }
//        findNextInPreOrder(node.left);   /* then recur on left subtree */
//        findNextInPreOrder(node.right);  /* now recur on right subtree */
//    }
    int id;
    int parentId;
    List<TopObject> child;

    public TopObject(int id, int parentId) {
        this.id = id;
        this.parentId = parentId;

        if (this.parentId == 0) {
            DFS.topObjects.add(this);
            this.child = new ArrayList<>();
        } else {
            addChild(DFS.topObjects);
        }
    }

    public void addChild(List<TopObject> child) {
        for(TopObject i: child) {
            if (i.id == this.parentId) {
                this.child = new ArrayList<>();
                i.child.add(this);
                return ;
            } else addChild(i.child); //if (i.child.isEmpty())
        }
    }

    public List<TopObject> getChild() {
        return child;
    }
}