package Lesson_1.DZ;

import java.util.ArrayList;
import java.util.Arrays;

public class MainDz {
    public static void main(String[] args) {
        System.out.println("1. Написать метод, который меняет два элемента массива местами.(массив может быть любого ссылочного типа);");

        Integer[] inums = {1, 2, 3, 4, 5}; //создали массив
        MyObj<Integer> iob = new MyObj<>(inums); //создали обект из массива
        iob.change(iob, 0); //заустили метод смены значений, второй параметр задает начальную позицию замены

        Double[] dnums = {1.0, 2.0, 3.0, 4.0, 5.0};
        MyObj<Double> dob = new MyObj<>(dnums);
        dob.change(dob, 3);

        String[] strings = {"a", "b", "c", "d", "e"};
        MyObj<String> sob = new MyObj<>(strings);
        sob.change(sob, 4);

        MyObj[] myObjs = {iob, dob, sob};
        MyObj<MyObj> oob = new MyObj<>(myObjs);
        oob.change(oob, 1);

        System.out.println("Конец 1го задания.");

        ArrayList<Double> aL = dob.toArrayList(dnums);
        aL.add(6.0);
        System.out.println(aL);
        System.out.println(sob.toArrayList(strings)+ " - ArrayList");

        System.out.println("Конец 2го задания.");

        Box<Apple> appleBox1 = new Box<>();
        for (int i = 0; i < 5; i++) {
            appleBox1.add(new Apple());
        }
        System.out.println("Сделать метод getWeight() который высчитывает вес коробки");
        System.out.println(appleBox1.getWeight()+" - appleBox1.getWeight");

        Box<Orange> orangeBox1 = new Box<>();
        for (int i = 0; i < 7; i++) {
            orangeBox1.add(new Orange());
        }
        System.out.println(orangeBox1.getWeight()+" - orangeBox1.getWeight");

        System.out.println("Внутри класса коробка сделать метод compare, который позволяет сравнить текущую коробку с той, которую подадут в compare в качестве параметра");
        boolean compare = orangeBox1.compare(appleBox1);
        System.out.println(compare+" - orangeBox1.compare(appleBox1)");


        System.out.println("Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую коробку orangeBox2");
        Box<Orange> orangeBox2 = orangeBox1.changeBox();
        System.out.println(orangeBox2.getWeight()+" - orangeBox2 .getWeight");

        orangeBox2.add(new Orange());
        System.out.println(orangeBox2.getWeight()+" - orangeBox2 .getWeight after add 1 fruit");

        System.out.println(orangeBox1.compare(orangeBox2)+" - orangeBox1.compare(orangeBox2)");


    }

}

class MyObj<T> { //описываем ограничения сверху - extends Number
    private T[] array;

    public MyObj(T[] mass) {
        this.array = mass;
    }
    public T[] getArray() {
        return array;
    }

    /**
     * 1. Написать метод, который меняет два элемента массива местами.(массив может быть любого ссылочного типа);
     */
    public void change(MyObj<T> myObj, Integer shift) { //ограничения снизу накладываем в методе - super Number
        T[] array = myObj.getArray();
        String string = Arrays.toString(array);
        System.out.println(string + ", исходный массив");
        if (myObj.array.length < (shift + 1) || shift <= 0) { //если длинна массива меньше сдвига+1 и отрицательных инедексов
            System.out.println("Массив не изменен");
        } else {
            T temp = myObj.array[shift - 1];
            myObj.array[shift - 1] = myObj.array[shift];
            myObj.array[shift] = temp;
            array = myObj.getArray();
            string = Arrays.toString(array);
            System.out.println(string + " заменены элементы " + shift +" и "+ (shift+1));
        }
    }

    /**
     * 2. Написать метод, который преобразует массив в ArrayList;
     * @param stringArray
     * @return
     */
    public ArrayList<T> toArrayList(T[] stringArray) {
        return new ArrayList<T>(Arrays.asList(stringArray));
    }
}