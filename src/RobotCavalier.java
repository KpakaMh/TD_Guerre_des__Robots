public class RobotCavalier extends Robot {

    public RobotCavalier(String nomPersonnalise, int xPosition, int yPosition, int version, World monde, String nomCreateur) {
        super(nomPersonnalise, xPosition, yPosition, version, monde, nomCreateur);
    }

    @Override
    public void afficherManiereDeplacement() {
        System.out.println("Je suis un RobotCavalier : je me déplace en L (+2 à droite, +1 en haut) !");
    }

    @Override
    public void deplacer() {
        int nouvellePositionX = this.xPosition + 2;
        int nouvellePositionY = this.yPosition + 1;

        // Il faut vérifier les deux limites en même temps
        if (nouvellePositionX <= getMonde().getMaxX() && nouvellePositionY <= getMonde().getMaxY()) {
            this.xPosition = nouvellePositionX;
            this.yPosition = nouvellePositionY;
            System.out.println(getNom() + " a bondi en L vers : (" + this.xPosition + ", " + this.yPosition + ")");
        } else {
            System.out.println(getNom() + " ne peut pas faire ce saut, il sortirait du monde !");
        }
    }
}