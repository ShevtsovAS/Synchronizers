package main.java.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;

public class Car implements Runnable {
    private int carNumber;

    public Car(int carNumber) {
        this.carNumber = carNumber;
    }

    @Override
    public void run() {
        System.out.printf("Автомобиль №%d подъехал к паромной переправе.\n", carNumber);
        //Для указания потоку о том что он достиг барьера, нужно вызвать метод await()
        //После этого данный поток блокируется, и ждет пока остальные стороны достигнут барьера
        try {
            Ferry.BARRIER.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

        System.out.printf("Автомобиль №%d продолжил движение.\n", carNumber);
    }
}
