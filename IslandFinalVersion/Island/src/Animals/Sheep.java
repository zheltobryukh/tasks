package Animals;

import Island.Island;

public class Sheep extends Herbivore {
    public Sheep() {
        super(70, 140, 3, 15);
    }

    @Override
    public void move(Island island, int x, int y) {
        randomMove(island, x, y);
    }

    @Override
    public void reproduce() {
        System.out.println("🐑 Овца размножается...");
    }
}
