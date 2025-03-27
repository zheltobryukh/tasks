package Animals;

import Island.Island;


public class Deer extends Herbivore {
    public Deer() {
        super(300, 20, 4, 50);
    }

    @Override
    public void move(Island island, int x, int y) {
        randomMove(island, x, y);
    }

    @Override
    public void reproduce() {
        System.out.println("🦌 Олень размножается...");
    }
}