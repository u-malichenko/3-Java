package Lesson_4.NewGroup;

public class MainThreadLesson4 {
    public static void main(String[] args) {
        //1 реализация через Абстрактный класс
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(1);
            }
        });
        t1.start();
    }
}

//2 релизация через наследование
class MyThreadClass extends Thread{
    @Override
    public void run() {
        System.out.println(1);
    }
}

//3 реализация через подписка на интерфейс
class MyRunnableClass implements Runnable{
    @Override
    public void run() {
        System.out.println(1);
    }
}