package Lesson_5.DZ.F1;

import java.util.concurrent.*;

public class Car implements Runnable {

    int carsCount = MainClass.getCarsCount();
    CyclicBarrier cb = new CyclicBarrier(carsCount);
    ExecutorService pool = Executors.newFixedThreadPool(carsCount);

    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;

    public Car(Race race, int speed) {
        this.race = race; //объект синхронизации набор препятствий
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        pool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(name + " готовится");
                    Thread.sleep(500 + (int)(Math.random() * 800));
                    System.out.println(name + " готов");
                    cb.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    @Override
    public void run() {
        for (int i = 0; i < race.getStages().size(); i++) {
            /**
             *                  * тут надо вставить Семафор
             *                  * Semaphore smp = new Semaphore(2); половина!!!
             */
            race.getStages().get(i).go(this);
        }
        pool.shutdown();
    }
//    @Override
//    public void run() {
//        for (int i = 0; i < race.getStages().size(); i++) {
//            /**
//             *                  * тут надо вставить Семафор
//             *                  * Semaphore smp = new Semaphore(2); половина!!!
//             */
//            race.getStages().get(i).go(this);
//        }
//        pool.shutdown();
//    }
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
}
