package Ex1;

public class Timer {
    public static void main(String[] args) {
        SecondsNotifier notifier = new SecondsNotifier();
        while (true) {
            notifier.countPassed();
            System.out.println(notifier.secPass + " second");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}