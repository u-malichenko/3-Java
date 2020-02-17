package Lesson_6.DZ_My;

import java.util.ArrayList;

public class TestArray {
    public int[] createArray(int[] startArray, int num) {
        int x;
        ArrayList<Integer> arrayList = toArrayList(startArray);
        if(arrayList.isEmpty()) {
            throw new RuntimeException("Array is empty");
        }else if ((x = arrayList.lastIndexOf(num)) == -1) {
            throw new RuntimeException("The array does not contain the number "+ num);
        }
        arrayList.subList(0, x + 1).clear();
        return toArray(arrayList);
    }

    public boolean contains1And4(int[] startArray) {
        ArrayList<Integer> arrayList = toArrayList(startArray);
        if (!arrayList.contains(1)) {
            return false;
        } else if (!arrayList.contains(4)) {
            return false;
        }
        for (int i : arrayList) {
            if (i != 4 && i != 1) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Integer> toArrayList(int[] startArray) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i : startArray) {
            arrayList.add(i);
        }
        return arrayList;
    }

    public int[] toArray(ArrayList<Integer> arrayList) {
        int[] arr = new int[arrayList.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arrayList.get(i);
        }
        return arr;
    }
}
