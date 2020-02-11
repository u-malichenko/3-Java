package Lesson_4.DZ_My;

/**
 * 1. Создать три потока, каждый из которых выводит определенную букву (A, B и C) 5 раз (порядок – ABСABСABС). Используйте wait/notify/notifyAll.
 */
public class MainNotifyAll {
    public static void main(String[] args) {
        SynchronizedClass SynchronizedClass = new SynchronizedClass();
        ThreadABC threadA1 = new ThreadABC(SynchronizedClass, "A");
        ThreadABC threadB1 = new ThreadABC(SynchronizedClass, "B");
        ThreadABC threadC1 = new ThreadABC(SynchronizedClass, "C");
        new Thread(threadA1).start();
        new Thread(threadB1).start();
        new Thread(threadC1).start();
    }
}

class SynchronizedClass {
    private static final String[] MARC = {"A", "B", "C"};
    private static int index = 0;

    public synchronized void waitOrPrintAndChange(String currentLetter) {
        try {
            while (!MARC[index].equals(currentLetter)) {
                wait();
            }
            System.out.print(currentLetter);
            index = index == 2 ? 0 : index+1;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ThreadABC implements Runnable {
    private SynchronizedClass synchronizedClass;
    private String threadLetter;
    public ThreadABC(SynchronizedClass synchronizedClass, String threadLetter) {
        this.threadLetter = threadLetter;
        this.synchronizedClass = synchronizedClass;
    }
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            synchronizedClass.waitOrPrintAndChange(threadLetter);
        }
    }
}