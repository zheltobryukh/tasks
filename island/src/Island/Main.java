package Island;

import Animals.*;

import java.util.concurrent.*;
import java.util.*;






public class Main {
    private static final int WIDTH = 20;
    private static final int HEIGHT = 10;
    private static final int CYCLE_TIME = 2;

    public static final Island island = new Island(WIDTH, HEIGHT);
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
                for (Animal animal : new ArrayList<>(island.getLocation(i, j).getAnimals())) {
                    animal.decreaseHunger();
                }
            }
        }

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                final int x = i;
                final int y = j;

                for (Animal animal : new ArrayList<>(island.getLocation(x, y).getAnimals())) {
                    animalPool.execute(() -> animal.move(island, x, y));
                    animalPool.execute(() -> animal.eat(x, y));
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