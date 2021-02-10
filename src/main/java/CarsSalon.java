import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class CarsSalon {
    private final List<Car> cars = new ArrayList<>();
    private final ReentrantLock lock = new ReentrantLock();
    private final ReentrantLock lock1 = new ReentrantLock();
    private final ReentrantLock lock2 = new ReentrantLock();
    final Object object1 = new Object();


    public void sellCar() {
        int count = 0;
        while (count < 4) {
            System.out.println(Thread.currentThread().getName() + " покупает");
            lock1.lock();
            synchronized (object1) {
                if (cars.size() == 0) {
                    System.out.println("Автомобилей нет на складе, подождите привоза новых автомобилей");
                }
                try {
                    object1.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                object1.notify();
            }

            System.out.println(Thread.currentThread().getName() + " уехал на новеньком авто");

            cars.remove(0);
            lock1.unlock();
            System.out.println("Осталось " + cars.size() + " автомобилей");
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
        }
    }


    public void acceptCar() {
        int count = 0;
        try {
            while (count < 12) {
                System.out.println("Производитель выпустил новый автомобиль. Можно продавать");
                synchronized (object1) {
                    cars.add(new Car());
                    object1.notify();
                }
                Thread.sleep(2000);
                count++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Производство автомобилей закончено, кина не будет");
    }
}
