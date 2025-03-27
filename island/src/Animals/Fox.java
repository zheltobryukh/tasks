package Animals;
import Island.Island;

public class Fox extends Predator {
    public Fox() {
        super(8, 30, 2, 2);
    }

    @Override
    public void move(Island island, int x, int y) {
        randomMove(island, x, y);
    }

    @Override
    public void reproduce() {
        System.out.println("🦊 Лиса размножается...");
    }
}