package Lesson_1.DZ;

/**
 * Есть классы Fruit -> Apple, Orange;(больше фруктов не надо)
 */
public abstract class Fruit{

    public abstract float getWeight();
}

class Apple extends Fruit {
    private final  float WIGHT = 1.0f; //вес одного фрукта

    public Apple() {
    }

    @Override
    public float getWeight() {
        return WIGHT;
    }
}

class Orange  extends Fruit{
    private final  float WIGHT = 1.5f; //вес одного фрукта

    public Orange() {
    }

    @Override
    public float getWeight() {
        return WIGHT;
    }
}