public class RobotTourelle extends Robot {

    public RobotTourelle(String nomPersonnalise, int xPosition, int yPosition, int version, World monde, String nomCreateur) {
        super(nomPersonnalise, xPosition, yPosition, version, monde, nomCreateur);
    }

    @Override
    public void afficherManiereDeplacement() {
        System.out.println("Je suis un RobotTourelle : je suis ancré au sol, je ne bouge pas !");
    }

    @Override
    public void deplacer() {
        // La position reste strictement la même
        System.out.println(getNom() + " reste solidement en position en (" + this.xPosition + ", " + this.yPosition + ").");
    }
}