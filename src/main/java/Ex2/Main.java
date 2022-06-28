package Ex2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Main {
    static int number = 15;
    static volatile int counter = 1;
    static List<String> listOfNumbers = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) throws InterruptedException {

        Thread fizzThread = new Thread(new Runnable() {
            @Override
            public void run() {
                fizz();
            }
        });

        Thread buzzThread = new Thread(new Runnable() {
            @Override
            public void run() {
                buzz();
            }
        });

        Thread fizzBuzzThread = new Thread(new Runnable() {
            @Override
            public void run() {
                fizzBuzz();
            }
        });

        Thread numberThread = new Thread(new Runnable() {
            @Override
            public void run() {
                numbers();
            }
        });

        fizzThread.start();
        buzzThread.start();
        fizzBuzzThread.start();
        numberThread.start();
        Thread.currentThread().sleep(1000);

        for (String d : listOfNumbers) {
            System.out.println(d);
        }
    }

    public static void fizz() {
        synchronized (listOfNumbers) {
            while (counter <= number) {
                if (counter % 3 == 0 && counter % 5 != 0) {
                    listOfNumbers.add("fizz");
                    counter++;
                    listOfNumbers.notifyAll();
                } else {
                    try {
                        listOfNumbers.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void buzz() {
        synchronized (listOfNumbers) {
            while (counter <= number) {
                if (counter % 5 == 0 && counter % 3 != 0) {
                    listOfNumbers.add("buzz");
                    counter++;
                    listOfNumbers.notifyAll();
                } else {
                    try {
                        listOfNumbers.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void fizzBuzz() {
        synchronized (listOfNumbers) {
            while (counter <= number) {
                if (counter % 3 == 0 && counter % 5 == 0) {
                    listOfNumbers.add("fizzbuzz");
                    counter++;
                    listOfNumbers.notifyAll();
                } else {
                    try {
                        listOfNumbers.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void numbers() {
        synchronized (listOfNumbers) {
            while (counter <= number) {
                if (counter % 3 != 0 && counter % 5 != 0) {
                    listOfNumbers.add(String.valueOf(counter));
                    counter++;
                    listOfNumbers.notifyAll();
                } else {
                    try {
                        listOfNumbers.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}