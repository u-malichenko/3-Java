package Lesson_5;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;


public class MainSchedule {
    static Random random = new Random();

    public static void main(String[] args) throws InterruptedException {

//        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);//не больше двцх потоков
//        executorService.scheduleAtFixedRate(new Runnable() { //тут выбираем либо фиксет рате илибо фиксед делай
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName()+" "+ new Date() + " start work");
//                try {
//                    Thread.sleep(random.nextInt(5000));
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(Thread.currentThread().getName()+" "+ new Date() + " end work");
//            }
//        }, 0, 1000L, TimeUnit.MILLISECONDS); //начало старта, период(отрывки стартов), юнит


//        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
//        executorService.scheduleWithFixedDelay(new Runnable() { //запускать через промежуток времени после окончния прошлоц задачи
//            @Override
//            public void run() {
//                System.out.println(new Date() + " start work");
//                try {
//                    Thread.sleep(random.nextInt(1000));
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(new Date() + " end work");
//            }
//        }, 0, 1000L, TimeUnit.MILLISECONDS);  //начало старта, отчечка после завершения прошлой задачи, юнит


//        ArrayBlockingQueue<String> abq = new ArrayBlockingQueue<>(3);
//        //это очередь которая синхронизирована между потоками с методами:
//
//        abq.add("abc");//добавить
//        abq.add("abc");
//        abq.add("abc");
//        //если сделать больше очердь выдаст ошибку
//
//        Boolean res1 = abq.offer("asd"); //возващает тру есл смог добавить
//        Boolean res2 = abq.offer("asd", 100, TimeUnit.MILLISECONDS); //метод, задержка старта, юнит
//        //может быть перегруженный и делаться с задержкой
//
//        abq.put("as"); //если места нет в коллекции то поток встатет в режиме ожидания и ждет
//        // пока освободится хоть одно место как только место освободилось он добавляет
//
//        abq.take(); //извлечь объект получить объект
//
//          Vector<String> vector = new Vector<>();
//          //синхронизированный список, тот же аррэйлисто только синхронизированный в многопоточности
//
//        CopyOnWriteArrayList<String> cow = new CopyOnWriteArrayList<>();
//        //при каждом изменении создается копия объекта если кто то хочет использовать старую версию
//
//        ConcurrentHashMap<String, String> chm = new ConcurrentHashMap<>(); //более современный метод
//        // ключ и ячейка
//        // обращение к какой длибо конкретной ячейки и соответственно блокировка этой конкретной ячейки!
//
//
//        Map<String, String> hm = Collections.synchronizedMap(new HashMap<>()); //устаревший вариант
//        // ключ и ячейка
//        // обращение к какой длибо конкретной ячейки и блокировка !всей коллекции!

//        Semaphore smp = new Semaphore(4); //счетчик максимум
//        //ограничивает колличество потоков к определнному ресурсу
//        // отличие от экзекьюте, ему нужно все добавлятьв пул потоков но бывают потоки
//        // которые разбросаны по коду и собрать их во едино бывает проблематично
//        // задача экзекютера (определяет просто пул потоков),
//        // а семафор внутри этого экзекьютера определяет узкие места в коде
//        //если указать один поток то получим монитор!
//
//        ExecutorService service = Executors.newFixedThreadPool(10);
//        for (int i = 0; i < 10; i++) {
//            service.execute(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        smp.acquire(); //внутррь вот этого блока заходит поток а счетчик семафора уменьшается на 1
//                        // как только дойдет до  0 больше потоки не зайдут
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println(1);
//                    try {
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    smp.release(); // этот стетчик увеличивает счетчик на 1
//                }
//            });
//        }
//        service.shutdown();

//        CountDownLatch cdl = new CountDownLatch(10); //защелка!
//        //барьер!
//        //разные потоки с разными задачами за разное колличество времени
//        //для одновременного старта линия старта
//        //используется только 1 раз или перезадавать значение порога
//
//        for (int i = 0; i < 10; i++) {
//            int w = i;
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println(w + " -1");
//                    try {
//                        Thread.sleep((int)Math.random() * 300000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println(w + " -2");
//                    cdl.countDown(); //уменьшает счетчик на единицу
//                }
//            }).start();
//        }
//        cdl.await(); //как только он уменьшится до нуля запустится все потоки запустятся!
//
//        System.out.println("END!"); //происходит в самом конце



//        CyclicBarrier cb = new CyclicBarrier(10);
//        //аналогичен предыдущенму но автоматиески переинициализируется
//
//        for (int i = 0; i < 20; i++) {
//            int w = i;
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println(w + " start");
//                    try {
//                        Thread.sleep((int) Math.random() * 5000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println(w + " ready");
//                    try {
//                        cb.await(); //счетчик уменьшается на 1
//                        // как только поток попадает сюда он стоит и ждет
//                        //как только параметр барьера доходит до нуля барьер падает
//                        // приостанавливает поток до тех пор пока не будет вызван у всех потоков!
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    } catch (BrokenBarrierException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println(w + " end");
//
//                }
//            }).start();
//        }
//        //рзница с пердыдущим в том что его удобно переиспользовать! он опять выставляет значение барьера


//        ReentrantLock r1 = new ReentrantLock(); // замок! последовательное выполениен потоков
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                r1.lock(); // происходит блокировка
//                System.out.println(1);
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(2);
//                r1.unlock(); // происходит разблокировка
//            }
//        }).start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                r1.lock();
//                System.out.println(3);
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(4);
//                r1.unlock();
//            }
//        }).start();


//        AtomicInteger ai = new AtomicInteger(100); //атомарные перееменные
//        // когда процесс записи и чтения являются не разделимыми операциями и выполняются в одну транзакцию
//        //без разница какое будет колличество потоков потоки за один тик
//        // могут сделать только вот эту операцию - получается синхронизированна
//        //например нужно определить какой из потоков успел сделать первым что либо
//        //какой из потоков первый смог сделать инкремент иэнд гет тот и выполнил работу первым
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 10; i++) {
//                    ai.incrementAndGet();
//
//                }
//            }
//        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 10; i++) {
//                    ai.decrementAndGet();
//                }
//            }
//        }).start();
//        System.out.println(ai);


//        MainSchedule mainSchedule = new MainSchedule(); //запуск экземпляра этого же класса в котором мы и есть, чтоб не делать ни чего статичного
//        mainSchedule.testSc();
    }

    /**
     * ШЕДУЛЕР В JAVA
     */
    void testSc() {
        Calendar calendar = Calendar.getInstance(); //
        calendar.set(Calendar.HOUR_OF_DAY, 15); //ели закоментить то будет срабатывать каждый в каждй чаас, если закоментить минуты то каждую секунду
        calendar.set(Calendar.MINUTE, 36);
        calendar.set(Calendar.SECOND, 0);

        Date time = calendar.getTime();
        Timer timer = new Timer();
        timer.schedule(new RemindTask(), time);
    }

}

class RemindTask extends TimerTask {

    @Override
    public void run() {
        System.out.println("up!");
    }
}
