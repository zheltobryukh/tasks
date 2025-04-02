package Island;

import Animals.*;


public class Island {
    private final int width;
    private final int height;
    private final Location[][] grid;

    public Island(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new Location[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                grid[i][j] = new Location();
            }
        }
    }

    public Location getLocation(int x, int y) {
        return grid[x][y];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void printMap() {
        System.out.println("=== Карта острова ===");
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                Location location = grid[i][j];
                if (!location.getAnimals().isEmpty()) {
                    Animal animal = location.getAnimals().get(0);
                    System.out.print(getAnimalIcon(animal) + " ");
                } else if (location.getPlant().isAvailable()) {
                    System.out.print("🌿 ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        System.out.println("=====================");
    }

    private String getAnimalIcon(Animal animal) {
        if (animal instanceof Wolf) return "🐺";
        if (animal instanceof Fox) return "🦊";
        if (animal instanceof Bear) return "🐻";
        if (animal instanceof Rabbit) return "🐇";
        if (animal instanceof Deer) return "🦌";
        if (animal instanceof Boar) return "🐗";
        if (animal instanceof Buffalo) return "🐃";
        if (animal instanceof Caterpillar) return "🐛";
        if (animal instanceof Duck) return "🦆";
        if (animal instanceof Eagle) return "🦅";
        if (animal instanceof Goat) return "🐐";
        if (animal instanceof Horse) return "🐎";
        if (animal instanceof Mouse) return "🐭";
        if (animal instanceof Sheep) return "🐑";
        if (animal instanceof Python) return "🐍";

        return "?";
    }
}