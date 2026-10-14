public abstract class CaseSpecial {
    protected int x;
    protected int y;

    public CaseSpecial(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Méthode abstraite que chaque type de case spéciale doit implémenter
    public abstract void effet(Robot robot);

    public int getX() { return x; }
    public int getY() { return y; }
}