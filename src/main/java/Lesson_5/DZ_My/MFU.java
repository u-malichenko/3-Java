package Lesson_5.DZ_My;

public class MFU {
    Object printLock = new Object();
    Object scanLock = new Object();

    public void print(String doc, int pages) {
        synchronized (printLock) {
            String nameThread = Thread.currentThread().getName();
            System.out.println(nameThread + " Начало печати " + doc);
            for (int i = 0; i < pages; i++) {
                System.out.println(nameThread + " " + i + " " + doc);
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(nameThread + " Конец печати " + doc);
        }
    }
    /**
     * сканирование может идти в сеть и должно быть абсолютно паралеьным
     * сканирование идет на печать(ксерокопия) процесс становится последовательным относительно печати
     */
    public void scan(String doc, int pages, boolean email) {
        synchronized (scanLock) {
            String nameThread = Thread.currentThread().getName();
            System.out.println(nameThread + " Начало сканирования " + doc);
            for (int i = 0; i < pages; i++) {
                System.out.println(nameThread +  " "  + i + " " + doc);
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!email) {
                System.out.println(nameThread + " Сканирование " + doc + " запускает новый поток для печати");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        print(doc, pages);
                    }
                }).start();
            }
            System.out.println(nameThread + " Конец сканирования  " + doc);
        }
    }

    public static void main(String[] args) {
        final MFU mfu = new MFU();

//Печать
        new Thread(new Runnable() {
            @Override
            public void run() {
                mfu.print("Doc 1 print", 10);
            }
        }).start();

//Ксерокопирование
        new Thread(new Runnable() {
            @Override
            public void run() {
                mfu.scan("Doc 2 scan to print", 10, false);
            }
        }).start();

//Сканирование на почту
        new Thread(new Runnable() {
            @Override
            public void run() {
                mfu.scan("Doc 3 scan to email", 10, true);
            }
        }).start();

//Печать
        new Thread(new Runnable() {
            @Override
            public void run() {
                mfu.print("Doc 4 print", 10);
            }
        }).start();

/**
 * процесс печати у нас идет последовательно
 * а процесс сканирования абсолютно паралельно
 */
    }
}
/**
 * скан может печататься и отправлятся на почту
 * сканирование может происходить паралельно с печатью
 * а может и последовательно происходить с печатью
 * два объекта синхронизации печать и сканер
 * <p>
 * очередь для печати и очередь для отправки на почту сканов
 */


//    void testSc() {
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.HOUR_OF_DAY, 22);
//        calendar.set(Calendar.MINUTE, 13);
//        calendar.set(Calendar.SECOND, 0);
//
//        Date time = calendar.getTime();
//        Timer timer = new Timer();
//        timer.schedule(new RemindTask(), time);
//    }
//}
//
//class RemindTask extends TimerTask {
//
//    @Override
//    public void run() {
//        System.out.println("up!");
//    }
//}