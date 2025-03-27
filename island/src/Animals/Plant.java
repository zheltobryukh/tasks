package Animals;


public class Plant {
    private static final double MAX_GROWTH = 1.0;
    private double size = MAX_GROWTH;

    public void grow() {
        size = MAX_GROWTH;
    }

    public boolean isAvailable() {
        return size > 0;
    }

    public void consume() {
        size = 0;
    }
}