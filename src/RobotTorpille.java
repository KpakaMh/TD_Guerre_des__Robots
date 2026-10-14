public class RobotTorpille extends Robot {
    public RobotTorpille(String nomPersonnalise, int xPosition, int yPosition, int version, World monde, String nomCreateur) {
        // Cette unique ligne appelle le constructeur de Robot et règle vos 5 erreurs d'un coup
        super(nomPersonnalise, xPosition, yPosition, version, monde, nomCreateur);
    }

    @Override
    public void afficherManiereDeplacement() {
        System.out.println("Je suis un RobotTorpille : je plonge de 3 cases vers le bas !");
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