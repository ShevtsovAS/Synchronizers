package main.java.countDownLatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Race {
    //Создаем CountDownLatch на 8 "условий"
    static final CountDownLatch START = new CountDownLatch(8);
    //Условная длина гоночной трассы
    static final int TRACK_LENGTH = 500000;

    public static void main(String[] args) {
        Random random = new Random();

        try {
            for (int i = 1; i <= 5; i++) {
                new Thread(new Car(i, random.nextInt(100) + 50)).start();
                TimeUnit.SECONDS.sleep(1);
            }

            while (START.getCount() > 3) TimeUnit.MILLISECONDS.sleep(100);

            TimeUnit.SECONDS.sleep(1);
            System.out.println("На старт!");
            START.countDown();//Команда дана, уменьшаем счетчик на 1
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Внимание!");
            START.countDown();//Команда дана, уменьшаем счетчик на 1
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Марш!");
            START.countDown();//Команда дана, уменьшаем счетчик на 1
            //счетчик становится равным нулю, и все ожидающие потоки
            //одновременно разблокируются
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
