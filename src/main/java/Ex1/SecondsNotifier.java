package Ex1;

public class SecondsNotifier implements Runnable {
        Thread tread;
        public volatile int secPass = 0;
        SecondsNotifier() {
            tread = new Thread(this);
            tread.start();
        }
        @Override
        public void run() {
            int secReq = 5;
            while (true) {
                if (secPass % 5 == 0) {
                    System.out.println(secReq + " seconds left");
                    synchronized (this) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        synchronized void countPassed() {
            secPass++;
            notify();
        }
    }

