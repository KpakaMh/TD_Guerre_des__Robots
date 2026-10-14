public class RobotTorpille extends Robot {

    private int pasY = -3; // Plongeon de 3 cases vers le bas

    // Ligne 6 : On ajoute 'int pvMax' à la fin des paramètres reçus
    public RobotTorpille(String nomPersonnalise, int xPosition, int yPosition, int version, World monde, String nomCreateur, int pvMax) {

        // Ligne 7 : 'pvMax' est maintenant reconnu et transmis à la classe mère Robot
        super(nomPersonnalise, xPosition, yPosition, version, monde, nomCreateur, pvMax);
    }
    @Override
    public void deplacer() {
        int cibleX = this.xPosition;
        int cibleY = this.yPosition + pasY;

        // On utilise getMonde() hérité de Robot pour garantir l'accès à l'instance
        if (!getMonde().estDansLesLimites(cibleX, cibleY)) {
            System.out.println(getNom() + " touche le fond et remonte !");
            pasY = -pasY; // Inversion de la direction
            cibleY = this.yPosition + pasY;
        }

        Robot cible = getMonde().getRobotSurCase(cibleX, cibleY, this);

        if (cible != null) {
            System.out.println(getNom() + " est bloqué par " + cible.getNom() + " ! Il attaque !");
            this.attaquer(cible, 30);

            if (cible.getPointsDeVieActuels() == 0) {
                System.out.println("💥 BOOM ! " + cible.getNom() + " a été détruit par " + getNom() + " !");
                getMonde().retirerRobot(cible);
                this.yPosition = cibleY;
            }
        } else if (getMonde().estDansLesLimites(cibleX, cibleY)) {
            this.yPosition = cibleY;
            System.out.println(getNom() + " a plongé en (" + this.xPosition + ", " + this.yPosition + ")");
        }
    }
}