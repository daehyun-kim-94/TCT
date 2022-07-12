


public class ThreadMain {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Hello world!");
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 9; i++){
                    System.out.println("[Thread1] " + i );
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        thread1.start();

        Thread tmp = new Thread(() -> {System.out.println("asdasdasd");});
        tmp.start();
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 9; i++){
                    System.out.println("[Thread2] " + i );
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        thread2.start();

        for (int i = 0; i <= 9; i++){
            System.out.println("[Main] " + i );
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        tmp.join();
        thread1.join();
        thread2.join();
    }
}