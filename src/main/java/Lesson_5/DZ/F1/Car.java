package Lesson_5.DZ.F1;

public class Car implements Runnable  {

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
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            MainClass.BARRIER.await();
            for (int i = 0; i < race.getStages().size(); i++) {
                race.getStages().get(i).go(this);
            }
            MainClass.BARRIER.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void call (){
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
    }
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }

}
