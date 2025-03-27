package Animals;
import Island.Island;

public class Rabbit extends Herbivore {
    public Rabbit() {
        super(2, 150, 2, 0.45);
    }

    @Override
    public void move(Island island, int x, int y) {
        randomMove(island, x, y);
    }

    @Override
    public void reproduce() {
        System.out.println("🐇 Кролик размножается...");
    }
}