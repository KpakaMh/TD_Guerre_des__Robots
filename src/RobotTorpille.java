public class RobotTorpille extends Robot {

    public RobotTorpille(String nomPersonnalise, int xPosition, int yPosition, int version, World monde, String nomCreateur) {
        super(nomPersonnalise, xPosition, yPosition, version, monde, nomCreateur);
    }

    @Override
    public void afficherManiereDeplacement() {
        System.out.println("Je suis un RobotTorpille : je plonge de 3 cases vers le bas !");
    }

    @Override
    public void deplacer() {
        int nouvellePositionX = this.xPosition;     // Ne change pas en X
        int nouvellePositionY = this.yPosition - 3; // -3 en Y (vers le bas)

        if (nouvellePositionY >= getMonde().getMinY()) {
            this.yPosition = nouvellePositionY;
            System.out.println(getNom() + " a plongé vers le bas : (" + this.xPosition + ", " + this.yPosition + ")");
        } else {
            System.out.println(getNom() + " a touché le fond du monde !");
        }
    }
}