package Lesson_5.DZ.F1;

import java.util.concurrent.*;

public class MainClass {
    public static final int CARS_COUNT = 4;
    private static final ExecutorService POOL = Executors.newFixedThreadPool(CARS_COUNT);
    public static CyclicBarrier BARRIER = new CyclicBarrier(CARS_COUNT + 1);

    public static int getCarsCount() {
        return CARS_COUNT;
    }

    public static void main(String[] args) {
        Race race = new Race(new Road(60), new Tunnel(2), new Road(40)); //набор препятствий
        Car[] cars = new Car[CARS_COUNT]; //набор машин

//        Finisher finisher = new Finisher(CARS_COUNT);
//        Starter starter = new Starter();
//        Preparer preparer = new Preparer(CARS_COUNT, starter, finisher);

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        try {
            for (int i = 0; i < cars.length; i++) {
                cars[i] = new Car(race, 20 + (int) (Math.random() * 10)); //создание машины  и установка рандомной скорости20+-10
                POOL.execute(cars[i]);
            }

            MainClass.BARRIER.await();
//            Starter.start();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");

            MainClass.BARRIER.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
            POOL.shutdown();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

//class Preparer {
//
//    public Preparer(int carsCount, Starter starter, Finisher finisher) {
//    }
//}
//
//class Starter {
//    private static final CountDownLatch countDownLatch = new CountDownLatch(1);
//
//    public static void start() {
//        countDownLatch.countDown();
//    }
//
//    public static void waitAllCars() {
//        try {
//            countDownLatch.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//}
//
//class Finisher {
//    int CARS_COUNT;
//
//    public Finisher(int carsCount) {
//    }
//}