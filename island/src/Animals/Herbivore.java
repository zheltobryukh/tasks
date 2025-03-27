package Animals;

import static Island.Main.island;

abstract class Herbivore extends Animal {
    public Herbivore(double weight, int maxPerCell, int speed, double foodNeeded) {
        super(weight, maxPerCell, speed, foodNeeded);
    }

    @Override
    public void eat(int x, int y) {
        Plant plant = island.getLocation(x, y).getPlant();
        if (plant.isAvailable()) {
            plant.consume();
            currentHunger += 0.5;
            System.out.println(getClass().getSimpleName() + " съел растение.");
        } else {
            decreaseHunger();
        }
    }

}