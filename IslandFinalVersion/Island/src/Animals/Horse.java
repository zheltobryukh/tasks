package Animals;

import Island.Island;

public class Horse extends Herbivore {
    public Horse() {
        super(400, 20, 4, 60);
    }

    @Override
    public void move(Island island, int x, int y) {
        randomMove(island, x, y);
    }

    @Override
    public void reproduce() {
        System.out.println("🐴 Лошадь размножается...");
    }
}
