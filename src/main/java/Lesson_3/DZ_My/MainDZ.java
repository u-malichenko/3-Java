package Lesson_3.DZ_My;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class MainDZ {

    public static void main(String[] args) {
        fileArrRead();
        mergeFiles();
    }

    public static void fileArrRead() {
        try (FileInputStream in = new FileInputStream("123/1DZ.txt")) {
            byte[] arr = new byte[24];//если массив сделать маленьким то кодировка слетает
            int x;
            while ((x = in.read(arr)) > 0) {
                System.out.print(new String(arr, 0, x, "UTF-8")); //массив с0 до х с кодировкой утф8
            }
        } catch (IOException e) {
            System.out.println("File nit found!");
            e.printStackTrace();
        }
    }

    public static void mergeFiles() {
        ArrayList<InputStream> files = new ArrayList<>(); //списк инпутстримов, несколько потоков читающих данные из разных файлов

        try {
            files.add(new FileInputStream("123/2DZ_1.txt"));
            files.add(new FileInputStream("123/2DZ_2.txt"));
            files.add(new FileInputStream("123/2DZ_3.txt"));
            files.add(new FileInputStream("123/2DZ_4.txt"));
            files.add(new FileInputStream("123/2DZ_5.txt"));
            SequenceInputStream in = new SequenceInputStream(Collections.enumeration(files)); //одно обобщение, типа один файл
            File nf = new File("123/2DZ.txt");
            OutputStream out = new FileOutputStream(nf);
            int x;
            while ((x = in.read()) != -1) {
                out.write(x);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
