package main.java.phaser;

import java.util.concurrent.TimeUnit;

import static main.java.phaser.Bus.PHASER;

public class Passenger extends Thread {
    private int departure;
    private int destination;

    public Passenger(int departure, int destination) {
        this.departure = departure;
        this.destination = destination;
        System.out.println(this + " ждёт на остановке № " + this.departure);
    }

    @Override
    public void run() {
        System.out.println(this + " сел в автобус.");

        while (PHASER.getPhase() < destination) PHASER.arriveAndAwaitAdvance();
        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this + " покинул автобус.");
        PHASER.arriveAndDeregister();
    }

    public int getDeparture() {
        return departure;
    }

    public int getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        return "Пассажир{" + departure + " -> " + destination + '}';
    }
}
