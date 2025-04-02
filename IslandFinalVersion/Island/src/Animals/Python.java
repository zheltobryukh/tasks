package Animals;

import Island.Island;

public class Python extends Predator {
    public Python() {
        super(15, 30, 1, 3);
    }

    @Override
    public void move(Island island, int x, int y) {
        randomMove(island, x, y);
    }

    @Override
    public void reproduce() {
        System.out.println("🐍 Удав размножается...");
    }
}
