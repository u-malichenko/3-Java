package Lesson_5.DZ.F1;

public class MainClass {
    public static final int CARS_COUNT = 4;

    public static int getCarsCount() {
        return CARS_COUNT;
    }

    public static void main(String[] args) {
        Race race = new Race(new Road(60), new Tunnel(), new Road(40)); //набор препятствий
        Car[] cars = new Car[CARS_COUNT]; //набор машин
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        try {

            Thread  t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < cars.length; i++) {
                        cars[i] = new Car(race, 20 + (int) (Math.random() * 10)); //создание машины  и установка рандомной скорости20+-10
                    }
                }
            });
            t1.start();
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        for (int i = 0; i < cars.length; i++) {
            try {

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}
