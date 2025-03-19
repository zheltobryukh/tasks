import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class MultiThreadingTasks {

    // Задача 1: Общий счётчик с многопоточностью
    static class Counter {
        private int count = 0;

        public synchronized void increment() {
            count++;
        }

        public int getCount() {
            return count;
        }
    }

    // Задача 2: Генерация последовательности чисел с потокобезопасной коллекцией
    static class NumberSequence {
        public static void generateSequence() throws InterruptedException {
            CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
            Runnable task = () -> {
                for (int i = 1; i <= 100; i++) {
                    list.add(i);
                }
            };

            Thread[] threads = new Thread[10];
            for (int i = 0; i < 10; i++) {
                threads[i] = new Thread(task);
                threads[i].start();
            }

            for (Thread thread : threads) {
                thread.join();
            }

            System.out.println("List size: " + list.size());
        }
    }

    // Задача 3: Распределение задач с использованием пула потоков
    static class TaskDistribution {
        public static void distributeTasks() {
            ExecutorService executorService = Executors.newFixedThreadPool(4);
            for (int i = 1; i <= 20; i++) {
                int taskId = i;
                executorService.submit(() -> {
                    System.out.println("Task " + taskId + " executed by " + Thread.currentThread().getName());
                });
            }
            executorService.shutdown();
        }
    }

    // Задача 4: Симуляция работы банка с потокобезопасным переводом
    static class Bank {
        private static class Account {
            private int balance;
            private final ReentrantLock lock = new ReentrantLock();

            public Account(int balance) {
                this.balance = balance;
            }

            public void transfer(Account target, int amount) {
                if (this != target) {
                    this.lock.lock();
                    target.lock.lock();
                    try {
                        this.balance -= amount;
                        target.balance += amount;
                    } finally {
                        this.lock.unlock();
                        target.lock.unlock();
                    }
                }
            }
        }

        public static void simulateBank() throws InterruptedException {
            Account account1 = new Account(1000);
            Account account2 = new Account(2000);

            Runnable task = () -> account1.transfer(account2, 100);

            Thread[] threads = new Thread[5];
            for (int i = 0; i < 5; i++) {
                threads[i] = new Thread(task);
                threads[i].start();
            }

            for (Thread thread : threads) {
                thread.join();
            }

            System.out.println("Account 1 balance: " + account1.balance);
            System.out.println("Account 2 balance: " + account2.balance);
        }
    }

    // Задача 5: Барьер синхронизации с использованием CyclicBarrier
    static class BarrierExample {
        public static void syncBarrier() throws InterruptedException {
            CyclicBarrier barrier = new CyclicBarrier(5, () -> {
                System.out.println("All threads have reached the barrier, starting next phase.");
            });

            Runnable task = () -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " is working.");
                    barrier.await();
                    System.out.println(Thread.currentThread().getName() + " passed the barrier.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };

            Thread[] threads = new Thread[5];
            for (int i = 0; i < 5; i++) {
                threads[i] = new Thread(task);
                threads[i].start();
            }

            for (Thread thread : threads) {
                thread.join();
            }
        }
    }

    // Задача 6: Ограниченный доступ к ресурсу с использованием Semaphore
    static class ResourceAccess {
        public static void limitAccess() throws InterruptedException {
            Semaphore semaphore = new Semaphore(2); // Only 2 threads can access at the same time

            Runnable task = () -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " is accessing the resource.");
                    Thread.sleep(1000); // Simulate work
                    semaphore.release();
                    System.out.println(Thread.currentThread().getName() + " has finished using the resource.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };

            Thread[] threads = new Thread[5];
            for (int i = 0; i < 5; i++) {
                threads[i] = new Thread(task);
                threads[i].start();
            }

            for (Thread thread : threads) {
                thread.join();
            }
        }
    }

    // Задача 7: Обработка результатов задач с использованием Callable и Future
    static class FactorialCalculation {
        public static void calculateFactorial() throws InterruptedException, ExecutionException {
            ExecutorService executorService = Executors.newFixedThreadPool(10);

            Callable<Long> task = () -> {
                long result = 1;
                for (int i = 1; i <= 10; i++) {
                    result *= i;
                }
                return result;
            };

            List<Future<Long>> results = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                results.add(executorService.submit(task));
            }

            for (Future<Long> result : results) {
                System.out.println("Factorial: " + result.get());
            }

            executorService.shutdown();
        }
    }

    // Задача 8: Симуляция производственной линии с использованием BlockingQueue
    static class ProductionLine {
        public static void simulateProduction() throws InterruptedException {
            BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);

            Runnable producer = () -> {
                try {
                    for (int i = 0; i < 10; i++) {
                        queue.put(i);
                        System.out.println("Produced: " + i);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };

            Runnable consumer = () -> {
                try {
                    for (int i = 0; i < 10; i++) {
                        int item = queue.take();
                        System.out.println("Consumed: " + item);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };

            Thread producerThread = new Thread(producer);
            Thread consumerThread = new Thread(consumer);

            producerThread.start();
            consumerThread.start();

            producerThread.join();
            consumerThread.join();
        }
    }

    // Задача 9: Многопоточная сортировка
    static class ParallelSort {
        public static void sortArray() throws InterruptedException {
            int[] array = {5, 3, 8, 6, 2, 7, 1, 4};

            Runnable sortTask = () -> {
                Arrays.sort(array);
                System.out.println("Sorted Array: " + Arrays.toString(array));
            };

            Thread sortingThread = new Thread(sortTask);
            sortingThread.start();
            sortingThread.join();
        }
    }

    // Задача 10: Обед философов
    static class DiningPhilosophers {
        private static final Lock[] forks = {new ReentrantLock(), new ReentrantLock(), new ReentrantLock(), new ReentrantLock(), new ReentrantLock()};

        private static class Philosopher extends Thread {
            private final int leftFork;
            private final int rightFork;

            public Philosopher(int leftFork, int rightFork) {
                this.leftFork = leftFork;
                this.rightFork = rightFork;
            }

            @Override
            public void run() {
                try {
                    while (true) {
                        think();
                        eat();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            private void think() throws InterruptedException {
                System.out.println(Thread.currentThread().getName() + " is thinking.");
                Thread.sleep(1000);
            }

            private void eat() throws InterruptedException {
                if (leftFork < rightFork) {
                    forks[leftFork].lock();
                    forks[rightFork].lock();
                } else {
                    forks[rightFork].lock();
                    forks[leftFork].lock();
                }
                try {
                    System.out.println(Thread.currentThread().getName() + " is eating.");
                    Thread.sleep(1000);
                } finally {
                    forks[leftFork].unlock();
                    forks[rightFork].unlock();
                }
            }
        }

        public static void simulateDining() throws InterruptedException {
            Philosopher[] philosophers = new Philosopher[5];
            for (int i = 0; i < 5; i++) {
                philosophers[i] = new Philosopher(i, (i + 1) % 5);
                philosophers[i].start();
            }

            for (Philosopher philosopher : philosophers) {
                philosopher.join();
            }
        }
    }

    // Задача 11: Расчёт матрицы в параллельных потоках
    static class MatrixMultiplication {
        public static void multiplyMatrices() throws InterruptedException, ExecutionException {
            int[][] matrixA = {{1, 2}, {3, 4}};
            int[][] matrixB = {{5, 6}, {7, 8}};
            int[][] result = new int[2][2];

            ExecutorService executorService = Executors.newFixedThreadPool(2);

            List<Callable<Void>> tasks = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                final int row = i;
                tasks.add(() -> {
                    for (int j = 0; j < 2; j++) {
                        result[row][j] = matrixA[row][0] * matrixB[0][j] + matrixA[row][1] * matrixB[1][j];
                    }
                    return null;
                });
            }

            executorService.invokeAll(tasks);
            executorService.shutdown();

            System.out.println("Result matrix: ");
            for (int[] row : result) {
                System.out.println(Arrays.toString(row));
            }
        }
    }

    // Задача 12: Таймер с многопоточностью
    static class TimerExample {
        public static void runTimer() throws InterruptedException {
            Runnable timerTask = () -> {
                int time = 0;
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println("Time: " + time + " seconds");
                    time++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            };

            Thread timerThread = new Thread(timerTask);
            timerThread.start();

            Thread.sleep(10000); // Wait for 10 seconds
            timerThread.interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // Включение нужных задач по порядку:
        Counter counter = new Counter();
        Runnable counterTask = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        };

        // Задача 1: Общий счётчик с многопоточностью
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(counterTask);
            threads[i].start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println("Final count: " + counter.getCount());

        // Задача 2: Генерация последовательности чисел
        NumberSequence.generateSequence();

        // Задача 3: Распределение задач
        TaskDistribution.distributeTasks();

        // Задача 4: Симуляция работы банка
        Bank.simulateBank();

        // Задача 5: Барьер синхронизации
        BarrierExample.syncBarrier();

        // Задача 6: Ограниченный доступ к ресурсу
        ResourceAccess.limitAccess();

        // Задача 7: Обработка результатов задач
        FactorialCalculation.calculateFactorial();

        // Задача 8: Симуляция производственной линии
        ProductionLine.simulateProduction();

        // Задача 9: Многопоточная сортировка
        ParallelSort.sortArray();

        // Задача 10: Обед философов
        DiningPhilosophers.simulateDining();

        // Задача 11: Расчёт матрицы
        MatrixMultiplication.multiplyMatrices();

        // Задача 12: Таймер
        TimerExample.runTimer();
    }
}
