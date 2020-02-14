package Lesson_5.DZ_My.F1;

import java.util.concurrent.atomic.AtomicInteger;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private volatile static AtomicInteger ai = new AtomicInteger(0);
    private Preparer preparer;
    private Starter starter;
    private Finisher finisher;
    static { CARS_COUNT = 0; }
    private Race race;
    private int speed;
    private String name;

    public Car(Race race, int speed, Preparer preparer, Starter starter, Finisher finisher) {
        this.race = race; //объект синхронизации набор препятствий
        this.speed = speed;
        CARS_COUNT++;
        name = "Участник #" + CARS_COUNT;
        this.preparer = preparer;
        this.starter = starter;
        this.finisher = finisher;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            preparer.waitAllCars(); //убавить счетчик и встать в ожидание
            starter.waitAllCars(); //встать вожидане сигнала старта главного потока
            for (int i = 0; i < race.getStages().size(); i++) {
                race.getStages().get(i).go(this);
            }
            if (ai.compareAndSet(0, 1)) {
                System.out.println(this.name + " WIN");
            }
            finisher.waitAllCars();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }

}
