package Animals;
import Island.Island;

public class Wolf extends Predator {
    public Wolf() {
        super(50, 30, 3, 8);
    }

    @Override
    public void move(Island island, int x, int y) {
        randomMove(island, x, y);
    }

    @Override
    public void reproduce() {
        System.out.println("🐺 Волк размножается...");
    }
}
