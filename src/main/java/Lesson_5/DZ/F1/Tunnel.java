package Lesson_5.DZ.F1;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    public static Semaphore smp = new Semaphore((int)MainClass.getCarsCount()/2);
    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }
    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                smp.acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
            }
            smp.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
