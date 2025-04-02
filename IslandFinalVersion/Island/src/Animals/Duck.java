package Animals;

import Island.Island;

public class Duck extends Herbivore {
    public Duck() {
        super(1, 200, 4, 0.15);
    }

    @Override
    public void move(Island island, int x, int y) {
        randomMove(island, x, y);
    }

    @Override
    public void reproduce() {
        System.out.println("🦆 Утка размножается...");
    }
}
