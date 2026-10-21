import java.util.List;

public class RobotTourelle extends Robot {

    private int portee;
    private int maxToursTir;
    private int maxToursRecharge;

    private int toursTirRestants;
    private int toursRechargeRestants;
    private boolean enRecharge;

    public RobotTourelle(String nomPersonnalise, int x, int y,int version,  World monde, String NomCreateur, int pvMax, int portee, int toursTir, int toursRecharge) {
        super(nomPersonnalise, x, y, 0, monde, NomCreateur, pvMax); // Vitesse = 0 (immobile)
        this.portee = portee;
        this.maxToursTir = toursTir;
        this.maxToursRecharge = toursRecharge;
        this.toursTirRestants = toursTir;
        this.toursRechargeRestants = 0;
        this.enRecharge = false;
    }

    // Une tourelle ne se déplace jamais
    @Override
    public void deplacer() {
        System.out.println("🛡️ " + getNom() + " (Tourelle) reste fixe en position (" + getXPosition() + ", " + getYPosition() + ").");
    }

    public String getXPosition() {
        return xPosition + "";
    }
    public String getYPosition() {
        return yPosition + "";
    }

    // Un robot ne peut pas infliger de dégâts à une Tourelle
    @Override
    public void subirDegats(int degats) {
        System.out.println("🛡️ " + getNom() + " (Tourelle) est totalement blindée et insensible aux attaques !");
    }
    @Override
    public void afficherManiereDeplacement() {
        System.out.println("Je suis une Tourelle (" + getNom() + ") : immobile en position (" + getXPosition() + ", " + getYPosition() + ") !");
        System.out.println("Robot : " + getNom() + " | PV : " + getPointsDeVieActuels() + "/" + getPointsDeVieMax());
    }

    // Méthode appelée à chaque tour de jeu pour gérer le tir ou la recharge
    public void jouerTour(int degatsTir) {
        if (enRecharge) {
            toursRechargeRestants--;
            System.out.println("⏳ " + getNom() + " recharge... (" + toursRechargeRestants + " tour(s) restant(s)).");
            if (toursRechargeRestants <= 0) {
                enRecharge = false;
                toursTirRestants = maxToursTir;
                System.out.println("⚡ " + getNom() + " est rechargée et prête à tirer !");
            }
        } else {
            tirerEnZone(degatsTir);
            toursTirRestants--;
            if (toursTirRestants <= 0) {
                enRecharge = true;
                toursRechargeRestants = maxToursRecharge;
                System.out.println("🔋 " + getNom() + " a épuisé ses munitions et entre en recharge.");
            }
        }
    }

    // Tire sur tous les robots se trouvant dans la portée x
    private void tirerEnZone(int degats) {
        System.out.println("💥 " + getNom() + " (Tourelle) déclenche un tir de zone (Portée : " + portee + ") !");

        List<Robot> robotsDuMonde = monde.getRobots(); // Assure-toi d'avoir un getter getRobots() dans World
        for (Robot r : robotsDuMonde) {
            // Ne tire ni sur elle-même ni sur une autre tourelle
            if (r != this && !(r instanceof RobotTourelle)) {
                if (estAPortee(r)) {
                    System.out.print("🎯 Cible en portée : ");
                    r.subirDegats(degats);
                }
            }
        }
    }

    // Calcul de distance (distance de Tchebychev / cases adjacentes directes et diagonales)
    private boolean estAPortee(Robot r) {
        int distX = Math.abs(this.getxPosition() - r.getxPosition());
        int distY = Math.abs(this.getyPosition() - r.getyPosition());
        return Math.max(distX, distY) <= this.portee;
    }
}