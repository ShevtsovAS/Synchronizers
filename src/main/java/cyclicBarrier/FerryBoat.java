package main.java.cyclicBarrier;

import java.util.concurrent.TimeUnit;

public class FerryBoat implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println("Паром переправляет автомобили...");
            // Если 500ms изменить на несколько секунд, то автомобили будут успевать скапливаться перед переправой
            // В этом случае освобождённые потоки ведут себя не адекватно, успевает освободиться только один поток
            // остальные видимо лочатся вместе со следующими 3-мя потоками
            TimeUnit.MILLISECONDS.sleep(500);
            System.out.println("Паром переправил автомобили!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
