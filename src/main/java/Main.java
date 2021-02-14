import java.util.ArrayList;

import java.util.List;


public class Main {

    public static void main(String[] args) throws InterruptedException {
        CarsSalon carsSalon = new CarsSalon();

        List<Thread> threadList = new ArrayList<>();
        threadList.add(new Thread(null, carsSalon::sellCar, "Покупатель 1 "));
        threadList.add(new Thread(null, carsSalon::sellCar, "Покупатель 2 "));
        threadList.add(new Thread(null, carsSalon::sellCar, "Покупатель 3 "));
        for (Thread thread : threadList) {
            thread.start();
        }
        new Thread(null, carsSalon::acceptCar, "Продавец ").start();
    }
}
