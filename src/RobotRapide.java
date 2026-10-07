

public class RobotRapide extends Robot {

    static String Tyranosaurus7;

    // Le constructeur transmet les informations à la classe mère via 'super'
    public RobotRapide(String nomPersonnalise, int xPosition, int yPosition, int version, World monde, String nomCreateur) {
        super(nomPersonnalise, xPosition, yPosition, version, monde, "AdesanyaK");
    }

    @Override
    public void afficherManiereDeplacement() {
        System.out.println("Je suis un RobotRapide : je fonce de 2 cases vers la droite !");
    }

    @Override
    public void deplacer() {
        // Comportement spécifique : avance de 2 en X, ne bouge pas en Y
        int nouvellePositionX = this.xPosition + 2;

        if (nouvellePositionX <= getMonde().getMaxX()) {
            this.xPosition = nouvellePositionX;
        }
    }
}



