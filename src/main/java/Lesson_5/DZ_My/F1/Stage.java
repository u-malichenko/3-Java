package Lesson_5.DZ_My.F1;

public abstract class Stage {
    protected int length;
    protected String description;

    public String getDescription() {
        return description;
    }

    public abstract void go(Car c);
}
