package main.java.exchanger;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static main.java.exchanger.Delivery.EXCHANGER;

public class Truck implements Runnable {
    private int number;
    private String source;
    private String dest;
    private String[] parcels;

    public Truck(int number, String source, String dest, String[] parcels) {
        this.number = number;
        this.source = source;
        this.dest = dest;
        this.parcels = parcels;
    }

    @Override
    public void run() {
        Random random = new Random();
        try {
            System.out.printf("В грузовик №%d погрузили: %s и %s.\n", number, parcels[0], parcels[1]);
            System.out.printf("Грузовик №%d выехал из пункта %s в пункт %s.\n", number, source, dest);
            TimeUnit.MILLISECONDS.sleep(1000 + random.nextInt(5000));
            System.out.printf("Грузовик №%d приехал в пункт Е.\n", number);
            parcels[1] = EXCHANGER.exchange(parcels[1]); //При вызове exchange() поток блокируется и ждет
            System.out.printf("В грузовик №%d переместили посылку для пункта %s.\n", number, dest);
            TimeUnit.MILLISECONDS.sleep(1000 + random.nextInt(5000));
            System.out.printf("Грузовик №%d приехал в %s и доставил: %s и %s.\n", number, dest, parcels[0], parcels[1]);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
