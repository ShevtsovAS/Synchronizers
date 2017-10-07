package main.java.cyclicBarrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class Ferry {
    //Инициализируем барьер на три потока и таском, который будет выполняться, когда
    //у барьера соберется три потока. После этого, они будут освобождены.
    protected static final CyclicBarrier BARRIER = new CyclicBarrier(3, new FerryBoat());

    public static void main(String[] args) {
        for (int i = 1; i <= 9; i++) {
            try {
                new Thread(new Car(i)).start();
                TimeUnit.MILLISECONDS.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
