package Animals;

import Island.Island;

public class Boar extends Herbivore {
    public Boar() {
        super(400, 50, 2, 50);
    }

    @Override
    public void move(Island island, int x, int y) {
        randomMove(island, x, y);
    }

    @Override
    public void reproduce() {
        System.out.println("🐗 Кабан размножается...");
    }
}
