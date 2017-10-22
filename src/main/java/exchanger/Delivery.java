package main.java.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

public class Delivery {
    public static final Exchanger<String> EXCHANGER = new Exchanger<>();

    public static void main(String[] args) throws InterruptedException {
        String[] p1 = new String[]{"{посылка A->D}", "{посылка A->C}"}; //Формируем груз для 1-го грузовика
        String[] p2 = new String[]{"{посылка B->C}", "{посылка B->D}"}; //Формируем груз для 2-го грузовика
        new Thread(new Truck(1, "A", "D", p1)).start(); //Отправляем 1-й грузовик из А в D
        TimeUnit.MILLISECONDS.sleep(100);
        new Thread(new Truck(2, "B", "C", p2)).start(); //Отправляем 2-й грузовик из В в С
    }
}
