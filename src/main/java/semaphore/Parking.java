package main.java.semaphore;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Parking {
    private static final List<Boolean> PARKING_PLACES = new CopyOnWriteArrayList<>(Arrays.asList(
            false,
            false,
            false,
            false,
            false,
            false
    ));
    private static final Semaphore SEMAPHORE = new Semaphore(5, true);

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        for (int i = 1; i <= 15; i++) {
            new Thread(new Car(i)).start();
            TimeUnit.MILLISECONDS.sleep(random.nextInt(1000));
        }
    }

    public static class Car implements Runnable {
        private int carNumber;
        private Random random;

        public Car(int carNumber) {
            this.carNumber = carNumber;
            this.random = new Random();
        }

        @Override
        public void run() {
            System.out.printf("Автомобиль №%d подъехал к парковке.\n", carNumber);
            try {
                // Запрашиваем въезд на парковку (ждём если нету)
                SEMAPHORE.acquire();
                int parkingNumber = -1;
                // Ищем свободное место на парковке
                for (int i = 1; i <= 5; i++) {
                    if (!PARKING_PLACES.get(i)) {
                        PARKING_PLACES.set(i, true);
                        parkingNumber = i;
                        System.out.printf("Автомобиль №%d припарковался на месте %d.\n", carNumber, i);
                        break;
                    }
                }

                // Если не нашли - ошибка, т.к. мы не должны были сюда попасть!
                if (parkingNumber == -1) {
                    System.err.printf("Автомобиль №%d не нашёл свободного места на парковке!\n", carNumber);
                    return;
                }

                // Уходим за покупками...
                TimeUnit.SECONDS.sleep(random.nextInt(15));

                // По возвращению освобождаем парковочное место
                PARKING_PLACES.set(parkingNumber, false);

                // Уезжаем с парковки
                SEMAPHORE.release();
                System.out.printf("Автомобиль №%d покинул парковку.\n", carNumber);
            } catch (InterruptedException e) {
                System.out.printf("Автомобиль №%d забыл выключить утюг дома, развернулся и уехал.\n", carNumber);
            }
        }
    }
}
