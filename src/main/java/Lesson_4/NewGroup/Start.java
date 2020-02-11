package Lesson_4.NewGroup;

/**
 * объяснение объекта синхронизации
 */
public class Start {
    public static void main(String[] args) {
        TestThread e1 = new TestThread();
        TestThread e2 = new TestThread();

        new Thread(new Runnable() {
            @Override
            public void run() {
                //e1.method1();
                TestThread.methodStatic1();
            }
        }).start();
                                        // если e1+e1 последовательно
                                        // если e1+e2 - паралельно
                                        // так как объект синхронизации экземпляр класса
                                        // e1 и e2 имеют разные мониторы,
                                        // объекты разные и ни как не связаны между собой и их мониторы
                                        // то что до точки (e1. и e2.) то и яляется объектом синхронизации!!!!!
                                        // у кокого-то объекта вызываем синхронизированнфй метод - объект
                                        // этот метод принадлежит экземпляру класса метод не может быть объектом (синхронизации) метод принадлежит объекту
        new Thread(new Runnable() {
            @Override
            public void run() {
               // e2.method1();
                TestThread.methodStatic1();
            }
        }).start();
                                        //TestThread.methodStatic1(); -последовательно!
                                        // тут происходит синхронизация Класса
                                        // то что до точки (TestThread.) то и яляется объектом синхронизации!!!!!
                                        // у кокого-то объекта вызываем синхронизированнфй метод так как здесь статика то выступает сам класс
        // когда статика идет синхронизация по самому классу
    }
}

class TestThread {
    public synchronized static void methodStatic1(){
        System.out.println("S1");
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("S2");
    }

    public synchronized void method1(){
        System.out.println("M1");
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("M2");
    }
}