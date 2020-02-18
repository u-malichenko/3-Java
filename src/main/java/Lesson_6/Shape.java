package Lesson_6;

interface BuildMaterial{

    void build();
}

abstract class Shape {
    public void draw() {
//ничего не делаем
    }
}

// Класс наследник квадрат
class Square extends Shape implements BuildMaterial {
    public void draw() {
        System.out.println("Квадрат");
    }

    @Override
    public void build() {
        System.out.println("Строим Квадрат");
    }
}



// Класс наследник круг
class Сircle extends Shape implements BuildMaterial {
    public void draw() {
        System.out.println("Круг");
    }

    @Override
    public void build() {
        System.out.println("Строим Круг");
    }
}



// Класс наследник треугольник
class Triangle extends Shape implements BuildMaterial {
    public void draw() {
        System.out.println("Треугольник");
    }

    @Override
    public void build() {
        System.out.println("Строим Треугольник");
    }
}

class Main {
    public static void main(String[] args) {
        Shape[] a = new Shape[]{
                new Triangle(),
                new Square(),
                new Сircle()};

        for(int i = 0; i < a.length; i++) {
            a[i].draw();
        }
    }
}