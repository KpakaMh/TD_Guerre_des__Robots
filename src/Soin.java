public class Soin {
    private int x;
    private int y;

    public Soin(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // La méthode prend en paramètre le Robot qui bénéficie du soin
    public void effet(Robot robot) {
        if (robot != null) {
            // On calcule le nombre de PV nécessaires pour restaurer 100% de sa santé
            int pvManquants = robot.getPointsDeVieMax() - robot.getPointsDeVieActuels();

            if (pvManquants > 0) {
                robot.reparer(pvManquants);
                System.out.println("💚 BONUS SOIN ! " + robot.getNom() + " marche sur une case Soin en ("
                        + x + ", " + y + ") et récupère TOUS ses PV ("
                        + robot.getPointsDeVieActuels() + "/" + robot.getPointsDeVieMax() + ") !");
            } else {
                System.out.println("💚 " + robot.getNom() + " marche sur une case Soin, mais ses PV sont déjà au maximum.");
            }
        }
    }

    // Getters
    public int getX() { return x; }
    public int getY() { return y; }
}