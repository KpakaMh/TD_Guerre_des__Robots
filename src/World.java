public class World {
    // Les limites du monde
    private int minX;
    private int maxX;
    private int minY;
    private int maxY;

    // Le constructeur fixe la taille du monde à sa création
    public World(int minX, int maxX, int minY, int maxY) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }

    // Les accesseurs pour que le Robot puisse consulter les limites
    public int getMinX() { return minX; }
    public int getMaxX() { return maxX; }
    public int getMinY() { return minY; }
    public int getMaxY() { return maxY; }
}