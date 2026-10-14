public class RobotSauteur extends Robot {

    public RobotSauteur(String nomPersonnalise, int xPosition, int yPosition, int version, World monde, String nomCreateur, int pvMax) {
        super(nomPersonnalise, xPosition, yPosition, version, monde, nomCreateur, pvMax);
    }

    @Override
    public void afficherManiereDeplacement() {
        System.out.println("Je suis un RobotSauteur : je saute d'1 case vers le haut !");
    }

    @Override
    public void deplacer() {
        int cibleX = this.xPosition + 1;
        int cibleY = this.yPosition - 1;
        if (monde.estPositionValideEtLibre(cibleX, cibleY)) {
            this.xPosition = cibleX;
            this.yPosition = cibleY;
            System.out.println(this.nom + " s'est déplacé en (" + this.xPosition + ", " + this.yPosition + ")");
        } else {
            System.out.println(this.nom + " ne peut pas bouger (Bloqué par le bord ou un autre robot !)");
        }
    }
}