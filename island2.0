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

    public abstract void move(Island island, int x, int y);
    public abstract void eat();
    public abstract void reproduce();

    public boolean isAlive() {
        return isAlive;
    }

    public void die() {
        isAlive = false;
    }

    protected void randomMove(Island island, int x, int y) {
        int newX = Math.max(0, Math.min(island.getWidth() - 1, x + ThreadLocalRandom.current().nextInt(-speed, speed + 1)));
        int newY = Math.max(0, Math.min(island.getHeight() - 1, y + ThreadLocalRandom.current().nextInt(-speed, speed + 1)));

        if (newX != x || newY != y) {
            island.getLocation(x, y).removeAnimal(this);
            island.getLocation(newX, newY).addAnimal(this);
            System.out.println(getClass().getSimpleName() + " переместился в (" + newX + ", " + newY + ")");
        }
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
    public void move(Island island, int x, int y) {
        randomMove(island, x, y);
    }

    @Override
    public void reproduce() {
        System.out.println("🐺 Волк размножается...");
    }
}

class Fox extends Predator {
    public Fox() {
        super(8, 30, 2, 2);
    }

    @Override
    public void move(Island island, int x, int y) {
        randomMove(island, x, y);
    }

    @Override
    public void reproduce() {
        System.out.println("🦊 Лиса размножается...");
    }
}

class Bear extends Predator {
    public Bear() {
        super(500, 5, 2, 80);
    }

    @Override
    public void move(Island island, int x, int y) {
        randomMove(island, x, y);
    }

    @Override
    public void reproduce() {
        System.out.println("🐻 Медведь размножается...");
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
    public void move(Island island, int x, int y) {
        randomMove(island, x, y);
    }

    @Override
    public void reproduce() {
        System.out.println("🐇 Кролик размножается...");
    }
}

class Deer extends Herbivore {
    public Deer() {
        super(300, 20, 4, 50);
    }

    @Override
    public void move(Island island, int x, int y) {
        randomMove(island, x, y);
    }

    @Override
    public void reproduce() {
        System.out.println("🦌 Олень размножается...");
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void printMap() {
        System.out.println("=== Карта острова ===");
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                Location location = grid[i][j];
                if (!location.getAnimals().isEmpty()) {
                    Animal animal = location.getAnimals().get(0);
                    System.out.print(getAnimalIcon(animal) + " ");
                } else if (location.getPlant().isAvailable()) {
                    System.out.print("🌿 ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        System.out.println("=====================");
    }

    private String getAnimalIcon(Animal animal) {
        if (animal instanceof Wolf) return "🐺";
        if (animal instanceof Fox) return "🦊";
        if (animal instanceof Bear) return "🐻";
        if (animal instanceof Rabbit) return "🐇";
        if (animal instanceof Deer) return "🦌";
        return "?";
    }
}

public class Main {
    private static final int WIDTH = 20;
    private static final int HEIGHT = 10;
    private static final int CYCLE_TIME = 2;

    private static final Island island = new Island(WIDTH, HEIGHT);
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);
    private static final ExecutorService animalPool = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            island.getLocation(random(WIDTH), random(HEIGHT)).addAnimal(new Wolf());
            island.getLocation(random(WIDTH), random(HEIGHT)).addAnimal(new Fox());
            island.getLocation(random(WIDTH), random(HEIGHT)).addAnimal(new Rabbit());
            island.getLocation(random(WIDTH), random(HEIGHT)).addAnimal(new Deer());
            island.getLocation(random(WIDTH), random(HEIGHT)).addAnimal(new Bear());
        }

        scheduler.scheduleAtFixedRate(Main::simulateCycle, 0, CYCLE_TIME, TimeUnit.SECONDS);
    }

    private static void simulateCycle() {
        System.out.println("=== Новый цикл симуляции ===");

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                island.getLocation(i, j).getPlant().grow();
            }
        }

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                final int x = i;
                final int y = j;

                for (Animal animal : new ArrayList<>(island.getLocation(x, y).getAnimals())) {
                    animalPool.execute(() -> animal.move(island, x, y));
                    animalPool.execute(animal::eat);
                    animalPool.execute(animal::reproduce);
                }
            }
        }

        island.printMap();
    }

    private static int random(int max) {
        return ThreadLocalRandom.current().nextInt(max);
    }
}
