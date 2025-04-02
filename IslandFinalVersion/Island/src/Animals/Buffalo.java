package Animals;

import Island.Island;

public class Buffalo extends Herbivore {
    public Buffalo() {
        super(700, 10, 3, 100);
    }

    @Override
    public void move(Island island, int x, int y) {
        randomMove(island, x, y);
    }

    @Override
    public void reproduce() {
        System.out.println("🐃 Буйвол размножается...");
    }
}
