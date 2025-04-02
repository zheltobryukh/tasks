package Animals;

import Island.Island;

public class Bear extends Predator {
    public Bear() {
        super(500, 5, 2, 80);
    }

    @Override
    public void move(Island island, int x, int y) {
        randomMove(island, x, y);
    }

    @Override
    public void reproduce() {
        System.out.println("🐻 Медведь размножается...");
    }
}