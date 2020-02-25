package Lesson_7.DZ_My_RecMat;

public class RecMatrix {
    static  int size = 5;
    static int[][] matrix = new int[size][size];
    public static void main(String[] args) {

        buildSpiralMatrix(0, 0, 0, 1, 1);
    }

    public static void buildSpiralMatrix(int i, int j, int di, int dj, int value){
        //if (i > 1 && i < size + 2 && j > 1 && j <= size + 1 && !(matrix[i][j] > 0)){
        if (i < size && j <= size && !(matrix[i][j] > 0)){
            matrix[i][j] = value;
            buildSpiralMatrix(i + di, j + dj, di, dj, value + 1);
        }else if(value <= size * size){
            buildSpiralMatrix(i - di + dj, j - di - dj, dj, -di, value);
        }
    }
}
