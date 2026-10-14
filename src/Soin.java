public class Soin extends CaseSpecial {

    public Soin(int x, int y) {
        super(x, y);
    }

    @Override
    public void effet(Robot robot) {
        if (robot != null) {
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
}