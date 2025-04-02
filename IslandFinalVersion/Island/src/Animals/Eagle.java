package Animals;

import Island.Island;

public class Eagle extends Predator {
    public Eagle() {
        super(6, 20, 3, 1);
    }

    @Override
    public void move(Island island, int x, int y) {
        randomMove(island, x, y);
    }

    @Override
    public void reproduce() {
        System.out.println("🦅 Орел размножается...");
    }
}
