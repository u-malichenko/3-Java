//package Lesson_8.general_tree;
//
//import java.io.BufferedReader;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//public class FileRead {
//    CreateTree[] tree;
//    TreeDFS treeDFS;
//    TreeBFS treeBFS;
//
//    public FileRead() {
//        tree = new CreateTree[2];
//        treeDFS = new TreeDFS();
//        treeBFS = new TreeBFS();
//
//        tree[0] = treeDFS;
//        tree[1] = treeBFS;
//    }
//
//    public void insertFromFile(String fileName){
//        String[] node;
//        String str;
//
//        if (fileName.length() == 0) {
//            System.out.println("Не задано имя файла!");
//            return;
//        }
//
//        try(final BufferedReader br = new BufferedReader(new InputStreamReader
//                (new FileInputStream(fileName), "Windows-1251"))) {
//
//            while((str = br.readLine()) != null){
//                node = str.split(" ");
//                tree[0].createTree(node, treeDFS.getTreeDfs());
//                tree[1].createTree(node, treeBFS.getTreeBfs());
//            }
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//    }
//}
