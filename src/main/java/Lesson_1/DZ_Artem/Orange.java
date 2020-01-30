package Lesson_1.DZ_Artem;

public class Orange extends Fruit {
    public Orange() {
        super(1.5f);
    }

    @Override
    public Fruit newInstance() {
        return new Orange();
    }
}