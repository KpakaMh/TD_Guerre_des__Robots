public class RobotCrabe extends Robot {

    public RobotCrabe(String nomPersonnalise, int xPosition, int yPosition, int version, World monde, String nomCreateur) {
        super(nomPersonnalise, xPosition, yPosition, version, monde, nomCreateur);
    }

    @Override
    public void afficherManiereDeplacement() {
        System.out.println("Je suis un RobotCrabe : je marche sur le côté, d'1 case vers la gauche !");
    }

    @Override
    public void deplacer() {
        int nouvellePositionX = this.xPosition - 1; // -1 en X (gauche)
        int nouvellePositionY = this.yPosition;     // Ne change pas en Y

        if (nouvellePositionX >= getMonde().getMinX()) {
            this.xPosition = nouvellePositionX;
            System.out.println(getNom() + " a glissé à gauche : (" + this.xPosition + ", " + this.yPosition + ")");
        } else {
            System.out.println(getNom() + " est bloqué par le bord gauche du monde !");
        }
    }
}