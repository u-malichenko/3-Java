package Lesson_5.DZ_My.F1;

import java.util.concurrent.*;

public class MainClass {

    public static void main(String[] args) {
        final int CARS_COUNT = 4;
        Preparer preparer = new Preparer(CARS_COUNT);
        Starter starter = new Starter();
        Finisher finisher = new Finisher(CARS_COUNT);
        ExecutorService POOL = Executors.newFixedThreadPool(CARS_COUNT);
        Race race = new Race(new Road(60), new Tunnel(2), new Road(40)); //набор препятствий
        Car[] cars = new Car[CARS_COUNT]; //набор машин

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), preparer, starter, finisher); //создание машины  и установка рандомной скорости20+-10
            POOL.execute(cars[i]);
        }
        preparer.waitAllCars();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        starter.start();
        finisher.waitAllCars();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        POOL.shutdown();
    }
}

class Preparer {
    private CyclicBarrier cyclicBarrierPreparer;

    public Preparer(int carsCount) {
        cyclicBarrierPreparer = new CyclicBarrier(carsCount + 1);
    }

    public void waitAllCars() { //очередь для всех машин
        try {
            cyclicBarrierPreparer.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

class Starter {
    private final CountDownLatch countDownLatchStarter = new CountDownLatch(1);

    public void start() {
        countDownLatchStarter.countDown();
    }

    public void waitAllCars() {
        try {
            countDownLatchStarter.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Finisher {
    private CyclicBarrier cyclicBarrierFinisher;

    public Finisher(int carsCount) {
        cyclicBarrierFinisher = new CyclicBarrier(carsCount + 1);
    }

    public void waitAllCars() {
        try {
            cyclicBarrierFinisher.await();
        } catch (BrokenBarrierException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
