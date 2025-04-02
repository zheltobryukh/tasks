package Animals;

import java.util.List;

import static Island.Main.island;

abstract class Predator extends Animal {
    public Predator(double weight, int maxPerCell, int speed, double foodNeeded) {
        super(weight, maxPerCell, speed, foodNeeded);
    }

    @Override
    public void eat(int x, int y) {
        List<Animal> prey = island.getLocation(x, y).getAnimals();
        for (Animal animal : prey) {
            if (animal instanceof Herbivore && animal.isAlive()) {
                animal.die();
                currentHunger += animal.weight * 0.5;
                System.out.println(getClass().getSimpleName() + " съел " + animal.getClass().getSimpleName());
                return;
            }
        }
        decreaseHunger();
    }

}
