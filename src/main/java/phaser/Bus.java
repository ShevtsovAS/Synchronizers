package main.java.phaser;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Phaser;

public class Bus {
    public static final Phaser PHASER = new Phaser(1);

    public static void main(String[] args) {
        List<Passenger> passengers = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            if (random.nextInt(2) > 0) passengers.add(new Passenger(i, i+1));   //Этот пассажир выходит на следующей
            if (random.nextInt(2) > 0) passengers.add(new Passenger(i, 5));     //Этот пассажир выходит на конечной
        }

        for (int i = 0; i < 7; i++) {
            switch (i) {
                case 0:
                    System.out.println("Автобус выехал из парка.");
                    PHASER.arrive();
                    break;
                case 6:
                    System.out.println("Автобус уехал в парк.");
                    PHASER.arriveAndDeregister(); //Снимаем главный поток, ломаем барьер
                    break;
                default:
                    int currentBusStop = PHASER.getPhase();
                    System.out.println("Остановка № " + currentBusStop);
                    for (Passenger passenger : passengers) {
                        if (passenger.getDeparture() == currentBusStop) {
                            PHASER.register(); //Регистрируем поток, который будет участвовать в фазах
                            passenger.start();
                        }
                    }
                    PHASER.arriveAndAwaitAdvance(); //Сообщаем о своей готовности
            }
        }
    }
}
