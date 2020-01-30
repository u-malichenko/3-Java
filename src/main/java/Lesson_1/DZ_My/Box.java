package Lesson_1.DZ_My;

import java.util.ArrayList;

/**
 * b. Класс Box в который можно складывать фрукты, коробки условно сортируются по типу фрукта, поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
 */
public class Box<T extends Fruit> {
    private ArrayList<T> fruitList; //c. Для хранения фруктов внутри коробки можете использовать ArrayList;

    public Box() {
        fruitList = new ArrayList<>();
    }

    /**
     * d. Сделать метод getWeight() который высчитывает вес коробки, зная количество фруктов и вес одного фрукта(вес яблока - 1.0f, апельсина - 1.5f, не важно в каких это единицах);
     * @return
     */
    public float getWeight(){
        if(fruitList.size()==0) return 0.0f;
        return fruitList.get(0).getWeight() * fruitList.size();
//        float weight = 0;
//        for (Fruit fruit: fruitList) {
//            weight += fruit.getWeight();
//        }
//        return weight;
    }

    /**
     * e. Внутри класса коробка сделать метод compare, который позволяет сравнить текущую коробку с той,
     * которую подадут в compare в качестве параметра, true - если их веса равны, false в противном случае
     * (коробки с яблоками мы можем сравнивать с коробками с апельсинами);
     */
    public boolean compare(Box<?> another) { //ограничения снизу накладываем в методе super Fruit
        return Math.abs(this.getWeight() - another.getWeight()) < 0.0001;
    }

    /**
     * f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую коробку
     * (помним про сортировку фруктов, нельзя яблоки высыпать в коробку с апельсинами),
     * соответственно в текущей коробке фруктов не остается,
     * а в другую перекидываются объекты, которые были в этой коробке;
     */
    public void changeBox(Box<? super T> another){
        another.fruitList.addAll(this.fruitList);
        this.fruitList.clear();
//    public Box<T> changeBox() {
//        Box<T> newBox = new Box<T>();
//        //newBox.fruitList.addAll(this.fruitList);
//        newBox.fruitList = (ArrayList<T>) this.fruitList.clone();
//        this.fruitList.clear();
//        return newBox;
    }

    /**
     *g. Не забываем про метод добавления фрукта в коробку.
     */
    public void add(T fruit) {
            fruitList.add(fruit);
    }
}
