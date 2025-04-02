package Animals;

import java.util.concurrent.ThreadLocalRandom;
import Island.Island;
import Island.Location;

abstract public class Animal {
    protected double currentHunger;
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
    public abstract void eat(int x, int y);
    public abstract void reproduce();

    public boolean isAlive() {
        return isAlive;
    }

    public void decreaseHunger() {
        currentHunger -= foodNeeded * 0.5;
        if (currentHunger <= 0) {
            die();
            System.out.println(getClass().getSimpleName() + " умер от голода.");
        }
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