package Island;

import Animals.Animal;
import Animals.Plant;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Location {
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