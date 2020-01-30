package Lesson_1.DZ;

/**
 * Есть классы Fruit -> Apple, Orange;(больше фруктов не надо)
 */
public class Fruit {
    private float weightOneFruit; //вес одного фрукта


    public Fruit(float v) {
        this.weightOneFruit = v;
    }

    public float getWeightOneFruit() {
        return weightOneFruit;
    }
}

class Apple extends Fruit {

    public Apple() {
        super(1.0f);
    }

}

class Orange  extends Fruit{

    public Orange() {
        super(1.5f);
    }

}