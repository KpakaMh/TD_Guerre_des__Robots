public class Piege extends CaseSpecial {

    public Piege(int x, int y) {
        super(x, y);
    }

    @Override
    public void effet(Robot robot) {
        if (robot != null) {
            // Calcul de la moitié des PV actuels (la division entière int/2 arrondit automatiquement à l'entier inférieur)
            int degats = robot.getPointsDeVieActuels() / 2;

            if (degats > 0) {
                System.out.println("⚠️ MALUS PIÈGE ! " + robot.getNom() + " tombe dans un piège en (" + x + ", " + y + ") !");
                robot.subirDegats(degats);
            } else {
                System.out.println("⚠️ " + robot.getNom() + " tombe dans un piège en (" + x + ", " + y + "), mais ses PV sont trop faibles pour subir des dégâts.");
            }
        }
    }
}