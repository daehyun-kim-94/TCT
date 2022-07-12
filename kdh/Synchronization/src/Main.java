import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello world!");

        ThreadPrint th1 = new ThreadPrint();
        ThreadPrint th2 = new ThreadPrint();

        th1.start();
        th2.start();
        ThreadPrint.lock.lock();
        for(int i = 1; i <= 30; i++){
            System.out.println(i);
        }
        ThreadPrint.lock.unlock();
        th1.join();
        th2.join();
    }
}

class ThreadPrint extends Thread implements Runnable {

    static ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        lock.lock();
        try {
            for(int i = 1; i <= 30; i++){
                System.out.println(i);
            }
        }finally {
            lock.unlock();
        }

    }
}