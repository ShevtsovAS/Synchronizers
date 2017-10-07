package main.java.cyclicBarrier;

import java.util.concurrent.TimeUnit;

public class FerryBoat implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println("Паром переправляет автомобили...");
            TimeUnit.MILLISECONDS.sleep(500);
            System.out.println("Паром переправил автомобили!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
