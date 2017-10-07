package main.java.countDownLatch;

import java.util.concurrent.TimeUnit;

public class Car implements Runnable {
    private int carNumber;
    private int carSpeed;

    Car(int carNumber, int carSpeed) {
        this.carNumber = carNumber;
        this.carSpeed = carSpeed;
    }

    @Override
    public void run() {
        try {
            System.out.printf("Автомобиль №%d подъехал к стартовой прямой.\n", carNumber);
            //Автомобиль подъехал к стартовой прямой - условие выполнено
            //уменьшаем счетчик на 1
            Race.START.countDown();
            //метод await() блокирует поток, вызвавший его, до тех пор, пока
            //счетчик CountDownLatch не станет равен 0
            Race.START.await();
            //ждем пока проедет трассу
            TimeUnit.MILLISECONDS.sleep(Race.TRACK_LENGTH / carSpeed);
            System.out.printf("Автомобиль №%d финишировал!\n", carNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
