package Animals;

import Island.Island;

public class Goat extends Herbivore {
    public Goat() {
        super(60, 140, 3, 10);
    }

    @Override
    public void move(Island island, int x, int y) {
        randomMove(island, x, y);
    }

    @Override
    public void reproduce() {
        System.out.println("🐐 Коза размножается...");
    }
}
