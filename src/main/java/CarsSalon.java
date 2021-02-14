import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CarsSalon {
    private final List<Car> cars = new ArrayList<>();
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public void sellCar() {
        int count = 0;
        while (count < 4) {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " покупает");
                if (cars.size() == 0) {
                    System.out.println("Автомобилей нет на складе, подождите привоза новых автомобилей");
                    condition.await();
                }
                System.out.println(Thread.currentThread().getName() + " уехал на новеньком авто");

                System.out.println("Осталось " + cars.size() + " автомобилей");
                count++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            cars.remove(0);
        }
    }

    public void acceptCar() {
        int count = 0;
        while (count < 12) {
            try {
                lock.lock();
                System.out.println("Производитель выпустил новый автомобиль. Можно продавать");
                cars.add(new Car());
                condition.signal();
                Thread.sleep(2000);
                count++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
        System.out.println("Производство автомобилей закончено, кина не будет");
    }
}
