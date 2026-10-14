

public class RobotRapide extends Robot {


    public RobotRapide(String nomPersonnalise, int xPosition, int yPosition, int version, World monde, String nomCreateur, int pvMax) {
        // Et n'oubliez pas de l'ajouter à la fin du super() ici :
        super(nomPersonnalise, xPosition, yPosition, version, monde, nomCreateur, pvMax);
    }

    @Override
    public void afficherManiereDeplacement() {
        System.out.println("Je suis un RobotRapide : je fonce de 2 cases vers la droite !");
    }

    @Override
    public void deplacer() {
        // Comportement spécifique : avance de 2 en X, ne bouge pas en Y
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



