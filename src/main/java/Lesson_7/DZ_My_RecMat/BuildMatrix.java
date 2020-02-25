package Lesson_7.DZ_My_RecMat;

import java.util.Arrays;

public class BuildMatrix {
    public static void main(String[] args) {
        int w = 5;
        int h = 5;
        int[][] matrix = new int[w][h];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                matrix[i][j] = s(w, h, j, i) + 1;
            }
        }
        for (int[] row : matrix)
            System.out.println(Arrays.toString(row));
    }

    private static int s(int w, int h, int x, int y) {
        return y != 0 ? w + s(h - 1, w, y - 1, w - x - 1) : x;
    }
}