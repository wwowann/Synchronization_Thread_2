import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        CarsSalon carsSalon = new CarsSalon();
//        Thread threadProducedCar = new Thread();
        new Thread(null, carsSalon::acceptCar, "Продавец ").start();
        startThread(carsSalon);
    }

    public static void startThread(CarsSalon carsSalon) {
        new Thread(null, carsSalon::sellCar, "Покупатель 1 ").start();
        new Thread(null, carsSalon::sellCar, "Покупатель 2 ").start();
        new Thread(null, carsSalon::sellCar, "Покупатель 3 ").start();
    }
}
