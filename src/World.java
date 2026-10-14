import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class World {
    // Les limites du monde
    private int minX;
    private int maxX;
    private int minY;
    private int maxY;
    private List<Robot> robots;
    // NOUVEAU : Liste des cases de soin
    private List<Soin> casesSoin = new ArrayList<>();
    // Une seule liste pour tous les bonus et malus !
    private List<CaseSpecial> casesSpeciales = new ArrayList<>();


    // Le constructeur fixe la taille du monde à sa création
    public World(int minX, int maxX, int minY, int maxY) {

        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        this.robots = new ArrayList<>(); // Initialisationd de la liste de robots
    }
    //NOUVEAU : Permet de placer une case Soin dans le monde
    public void ajouterSoin(Soin soin) {
        this.casesSoin.add(soin);
    }
    public Soin getSoinSurCase(int x, int y) {
        for (Soin s : casesSoin) {
            if (s.getX() == x && s.getY() == y) {
                return s;
            }
        }
        return null;
    }
    public void consommerSoin(Soin soin) {
        this.casesSoin.remove(soin);
    }
    // Pour router un robot
    public void ajouterRoboot (Robot robot){
        this.robots.add(robot);
    }
    public boolean estPositionValideEtLibre(int cibleX, int cibleY) {

        // 1. Vérification des limites du monde
        if (cibleX < minX || cibleX > maxX || cibleY < minY || cibleY > maxY) {
            return false; // Le mouvement est invalide car on sort de la carte
        } // <-- C'est cette accolade fermante qui manquait pour séparer les deux logiques !

        // 2. Vérification des collisions (ne s'exécute que si les limites sont respectées)
        for (Robot r : robots) {
            if (r.getxPosition() == cibleX && r.getyPosition() == cibleY) {
                return false; // Case occupée !
            }
        }

        return true; // La voie est libre
    }
        // Cette méthode renvoie un tableau de 2 cases contenant [X, Y]
        public int[] trouverPositionLibre() {
            Random rand = new Random();
            int xAleatoire;
            int yAleatoire;

            do {
                xAleatoire = rand.nextInt((this.maxX - this.minX) + 1) + this.minX;
                yAleatoire = rand.nextInt((this.maxY - this.minY) + 1) + this.minY;

                // C'EST CETTE LÉGURATION (le '!') QUI FAIT TOUTE LA DIFFÉRENCE :
                // "Recommence TANT QUE la position N'EST PAS valide et libre"
            } while (!estPositionValideEtLibre(xAleatoire, yAleatoire));

            return new int[]{xAleatoire, yAleatoire};
        }
    // NOUVEAU : Vérifie uniquement si on tape un mur (limites de l'arène)
    public boolean estDansLesLimites(int x, int y) {
        return x >= minX && x <= maxX && y >= minY && y <= maxY;
    }
    // Le radar prend désormais le robot demandeur en paramètre pour l'ignorer
    public Robot getRobotSurCase(int x, int y, Robot demandeur) {
        for (Robot r : robots) {
            if (r != demandeur && r.getxPosition() == x && r.getyPosition() == y) {
                return r; // Renvoie uniquement un AUTRE robot
            }
        }
        return null;
    }

    // NOUVEAU : Le Fossoyeur. Retire un robot détruit du plateau.
    public void retirerRobot(Robot r) {
        robots.remove(r);
    }

    public void afficherEtat() {
        System.out.println("\n=== ÉTAT ACTUEL DU MONDE ===");
        if (robots.isEmpty()) {
            System.out.println("L'arène est vide. Tout le monde est détruit !");
        }
        for (Robot r : robots) {
            System.out.println(r.getNom() + " [" + r.getCapaciteCombat().getNomType() + "] est en ("
                    + r.getxPosition() + ", " + r.getyPosition() + ") | PV : " + r.getPointsDeVieActuels() + "/" + r.getPointsDeVieMax());
        }
        System.out.println("============================\n");
    }
    // Les accessors pour que le Robot puisse consulter les limites
    public int getMinX() { return minX; }
    public int getMaxX() { return maxX; }
    public int getMinY() { return minY; }
    public int getMaxY() { return maxY; }


    public void ajouterRobot(RobotTourelle tourelle) {
    }
    // Méthode générique pour ajouter un Soin ou un Piege
    public void ajouterCaseSpeciale(CaseSpecial c) {
        this.casesSpeciales.add(c);
    }
    public CaseSpecial getCaseSpecialeSurCase(int x, int y) {
        for (CaseSpecial c : casesSpeciales) {
            if (c.getX() == x && c.getY() == y) {
                return c;
            }
        }
        return null;
    }

    public void consommerCaseSpeciale(CaseSpecial c) {
        this.casesSpeciales.remove(c);
    }
}

