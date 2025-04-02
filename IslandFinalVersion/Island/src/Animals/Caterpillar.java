package Animals;
import Island.Island;


public class Caterpillar extends Herbivore {
    public Caterpillar() {
        super(0.01, 1000, 0, 0);
    }

    @Override
    public void move(Island island, int x, int y) {
        // Гусеница не передвигается
    }

    @Override
    public void reproduce() {
        System.out.println("🐛 Гусеница размножается...");
    }
}
