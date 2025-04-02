package Animals;

import Island.Island;

public class Mouse extends Herbivore {
    public Mouse() {
        super(0.05, 500, 1, 0.01);
    }

    @Override
    public void move(Island island, int x, int y) {
        randomMove(island, x, y);
    }

    @Override
    public void reproduce() {
        System.out.println("🐁 Мышь размножается...");
    }
}
