package Lesson_5.DZ.F1;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainClass {
    public static final int CARS_COUNT = 4;

    public static int getCarsCount() {
        return CARS_COUNT;
    }

    private static final ExecutorService POOL = Executors.newFixedThreadPool(CARS_COUNT);
    public static CyclicBarrier BARRIER = new CyclicBarrier(CARS_COUNT+1);

    public static void main(String[] args) {

        Race race = new Race(new Road(60), new Tunnel(), new Road(40)); //набор препятствий
        Car[] cars = new Car[CARS_COUNT]; //набор машин
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");

        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10)); //создание машины  и установка рандомной скорости20+-10
            POOL.execute( cars[i]);
        }
        try {
            MainClass.BARRIER.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        for (int i = 0; i < cars.length; i++) {
        }
        POOL.shutdown();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}


// class Ferry {
//    private static final CyclicBarrier BARRIER = new CyclicBarrier(3, new FerryBoat());
//    //Инициализируем барьер на три потока и таском, который будет выполняться, когда
//    //у барьера соберется три потока. После этого, они будут освобождены.
//
//    public static void main(String[] args) throws InterruptedException {
//        for (int i = 0; i < 9; i++) {
//            new Thread(new Car(i)).start();
//            Thread.sleep(400);
//        }
//    }
//
//    //Таск, который будет выполняться при достижении сторонами барьера
//    public static class FerryBoat implements Runnable {
//        @Override
//        public void run() {
//            try {
//                Thread.sleep(500);
//                System.out.println("Паром переправил автомобили!");
//            } catch (InterruptedException e) {
//            }
//        }
//    }
//
//    //Стороны, которые будут достигать барьера
//    public static class Car implements Runnable {
//        private int carNumber;
//
//        public Car(int carNumber) {
//            this.carNumber = carNumber;
//        }
//
//        @Override
//        public void run() {
//            try {
//                System.out.printf("Автомобиль №%d подъехал к паромной переправе.\n", carNumber);
//                //Для указания потоку о том что он достиг барьера, нужно вызвать метод await()
//                //После этого данный поток блокируется, и ждет пока остальные стороны достигнут барьера
//                BARRIER.await();
//                System.out.printf("Автомобиль №%d продолжил движение.\n", carNumber);
//            } catch (Exception e) {
//            }
//        }
//    }
//}