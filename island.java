import java.util.concurrent.*;
import java.util.*;

abstract class Animal {
    protected double weight;
    protected int maxPerCell;
    protected int speed;
    protected double foodNeeded;
    protected boolean isAlive = true;

    public Animal(double weight, int maxPerCell, int speed, double foodNeeded) {
        this.weight = weight;
        this.maxPerCell = maxPerCell;
        this.speed = speed;
        this.foodNeeded = foodNeeded;
    }

    public abstract void move();
    public abstract void eat();
    public abstract void reproduce();

    public boolean isAlive() {
        return isAlive;
    }

    public void die() {
        isAlive = false;
    }
}

abstract class Predator extends Animal {
    public Predator(double weight, int maxPerCell, int speed, double foodNeeded) {
        super(weight, maxPerCell, speed, foodNeeded);
    }

    @Override
    public void eat() {
        System.out.println(getClass().getSimpleName() + " охотится...");
    }
}

class Wolf extends Predator {
    public Wolf() {
        super(50, 30, 3, 8);
    }

    @Override
    public void move() {
        System.out.println("🐺 Волк перемещается...");
    }

    @Override
    public void reproduce() {
        System.out.println("🐺 Волк размножается...");
    }
}

abstract class Herbivore extends Animal {
    public Herbivore(double weight, int maxPerCell, int speed, double foodNeeded) {
        super(weight, maxPerCell, speed, foodNeeded);
    }

    @Override
    public void eat() {
        System.out.println(getClass().getSimpleName() + " ест растения...");
    }
}

class Rabbit extends Herbivore {
    public Rabbit() {
        super(2, 150, 2, 0.45);
    }

    @Override
    public void move() {
        System.out.println("🐇 Кролик скачет...");
    }

    @Override
    public void reproduce() {
        System.out.println("🐇 Кролик размножается...");
    }
}

class Plant {
    private static final double MAX_GROWTH = 1.0;
    private double size = MAX_GROWTH;

    public void grow() {
        size = MAX_GROWTH;
    }

    public boolean isAvailable() {
        return size > 0;
    }

    public void consume() {
        size = 0;
    }
}

class Location {
    private List<Animal> animals = new CopyOnWriteArrayList<>();
    private Plant plant = new Plant();

    public synchronized void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public synchronized void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public Plant getPlant() {
        return plant;
    }
}

class Island {
    private final int width;
    private final int height;
    private final Location[][] grid;

    public Island(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new Location[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                grid[i][j] = new Location();
            }
        }
    }

    public Location getLocation(int x, int y) {
        return grid[x][y];
    }
}

public class Main {
    private static final int WIDTH = 100;
    private static final int HEIGHT = 20;
    private static final int CYCLE_TIME = 1; // 1 секунда

    private static final Island island = new Island(WIDTH, HEIGHT);
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);
    private static final ExecutorService animalPool = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        // Добавляем начальных животных на остров
        for (int i = 0; i < 10; i++) {
            island.getLocation(ThreadLocalRandom.current().nextInt(WIDTH), ThreadLocalRandom.current().nextInt(HEIGHT)).addAnimal(new Wolf());
            island.getLocation(ThreadLocalRandom.current().nextInt(WIDTH), ThreadLocalRandom.current().nextInt(HEIGHT)).addAnimal(new Rabbit());
        }

        scheduler.scheduleAtFixedRate(Main::simulateCycle, 0, CYCLE_TIME, TimeUnit.SECONDS);
    }

    private static void simulateCycle() {
        System.out.println("=== Новый цикл симуляции ===");

        // Рост растений
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                island.getLocation(i, j).getPlant().grow();
            }
        }

        // Движение и размножение животных
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                for (Animal animal : island.getLocation(i, j).getAnimals()) {
                    animalPool.execute(animal::move);
                    animalPool.execute(animal::eat);
                    animalPool.execute(animal::reproduce);
                }
            }
        }
    }
}
