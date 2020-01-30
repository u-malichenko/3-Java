package Lesson_1.DZ_My;

/**
 * Есть классы Fruit -> Apple, Orange;(больше фруктов не надо)
 */
public abstract class Fruit{
    protected float weight;

    public abstract Fruit newInstance();// метод создания нового экземпляра класса абстрактный -переопределен у потомков - каждый генерирует свой тип фрукта

    public float getWeight() {//вернуть вес общий метод фруктов
        return weight;
    }

    public Fruit(float weight) {
        this.weight = weight;// в конструкторе добавляем вес каждому виду фруктов
    }
}

class Apple extends Fruit {
    public Apple() {
        super(1.0f);
    }

    @Override
    public Fruit newInstance() {
        return new Apple();
    }
}

class Orange  extends Fruit{
    public Orange() {
        super(1.5f);
    }

    @Override
    public Fruit newInstance() {
        return new Orange();
    }
}