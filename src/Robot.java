public class Robot {

    // Le compteur global pour tous les robots (pour Anonymous1, Anonymous2...)
    private static int counter = 1;
    protected String nom;
    protected int xPosition;
    protected int yPosition;
    protected int pasX = 1;  // Avance vers la droite
    protected int pasY = -1; // Avance vers le bas
    protected int pointsDeVieMax;
    protected int pointsDeVieActuels;
    private int version;
    private static String NomCreateur = "Adesanya_K";


    // Le robot connaît le monde dans lequel il évolue
    protected World monde;
    protected CapaciteCombat capaciteCombat = new CapacitePhysique();

    // Constructer
    public Robot(String nomPersonnalise, int xPosition, int yPosition, int version, World monde, String NomCreateur, int pvMax) {

        // Règle du nom : au moins 5 caractères. Sinon, on utilise AnonymousX
        if (nomPersonnalise != null && nomPersonnalise.length() >= 5) {
            this.nom = nomPersonnalise;
        } else {
            this.nom = "Anonymous" + counter;
            counter++; // On prépare le numéro pour le prochain robot
        }

        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.version = version;
        this.monde = monde;
        this.NomCreateur= NomCreateur;
        this.monde.ajouterRoboot(this);
        this.pointsDeVieMax = pvMax;
        this.pointsDeVieActuels = pvMax;
        this.pasX = pasX;
        this.pasY = pasY;

    }

    public Robot(String botActif, int xPosition, int yPosition, int version, World monde, String testeur) {
    }
    // Getter et Setter pour lire ou modifier la capacité de ce robot
    public CapaciteCombat getCapaciteCombat() {
        return capaciteCombat;
    }

    public void setCapaciteCombat(CapaciteCombat capaciteCombat) {
        this.capaciteCombat = capaciteCombat;
    }
    // NOUVEAU : La méthode pour attaquer un autre robot
    public void attaquer(Robot cible, int degatsDeBase) {
        System.out.println("\n>>> " + this.nom + " lance une attaque " + this.capaciteCombat.getNomType() + " sur " + cible.getNom() + " !");

        // C'est ici que la magie du polymorphisme opère ! Java va utiliser le bon calcul selon l'objet (Magique, Positronique...)
        int degatsInfliges = this.capaciteCombat.calculerDegatsEffectifs(cible, degatsDeBase);

        if (degatsInfliges == 0) {
            System.out.println("Immunité ! Aucun dégât.");
        } else {
            cible.subirDegats(degatsInfliges); // On utilise votre méthode existante
        }
    }
    // 2. Méthode orientée tâche : Subir une attaque
    public void subirDegats(int degats) {
        this.pointsDeVieActuels -= degats;
        if (this.pointsDeVieActuels < 0) {
            this.pointsDeVieActuels = 0; // On ne peut pas avoir une vie négative
        }
        System.out.println(this.nom + " subit " + degats + " dégâts !");
    }
    // 3. Méthode orientée tâche : Se soigner
    public void reparer(int soins) {
        this.pointsDeVieActuels += soins;
        if (this.pointsDeVieActuels > this.pointsDeVieMax) {
            this.pointsDeVieActuels = this.pointsDeVieMax; // On ne dépasse pas le max
        }
    }


    // NOUVEAU : La méthode qui décrit le déplacement
    public void afficherManiereDeplacement() {
        System.out.println("Je suis un Robot "+getNom()+" et   je me déplace suivant ("+ getyPosition()+ ",  "+getxPosition());
        System.out.println("Robot : " + this.nom + " | PV : " + this.pointsDeVieActuels + "/" + this.pointsDeVieMax);
    }
    // La méthode de déplacement, en diagonale vers le bas à droite
    // On suppose que "bas à droite" signifie x augmente (+1) et y diminue (-1)
    public void deplacer() {
        int cibleX = this.xPosition + pasX;
        int cibleY = this.yPosition + pasY;

        // 1. Rebond sur les limites de la carte
        if (!monde.estDansLesLimites(cibleX, cibleY)) {
            System.out.println(this.nom + " heurte un mur et fait demi-tour !");
            pasX = -pasX;
            pasY = -pasY;
            cibleX = this.xPosition + pasX;
            cibleY = this.yPosition + pasY;
        }

        // 2. Recherche d'un ENNEMI sur la case cible (en s'ignorant soi-même)
        Robot cible = monde.getRobotSurCase(cibleX, cibleY, this);

        if (cible != null) { // Un autre robot occupe la case !
            System.out.println(this.nom + " est bloqué par " + cible.getNom() + " ! Il attaque !");
            this.attaquer(cible, 30);

            // Si l'ennemi meurt, on prend sa place
            if (cible.getPointsDeVieActuels() == 0) {
                System.out.println("💥 BOOM ! " + cible.getNom() + " a été détruit par " + this.nom + " !");
                monde.retirerRobot(cible);
                this.xPosition = cibleX;
                this.yPosition = cibleY;
                System.out.println(this.nom + " avance sur les restes de son ennemi en (" + this.xPosition + ", " + this.yPosition + ")");

                verifierCaseSoin();
            } else {
                System.out.println(cible.getNom() + " survit à l'attaque. " + this.nom + " reste sur place ce tour-ci.");
            }
        } else {
            // Case libre : déplacement classique
            this.xPosition = cibleX;
            this.yPosition = cibleY;
            System.out.println(this.nom + " s'est déplacé en (" + this.xPosition + ", " + this.yPosition + ")");

            verifierCaseSoin();
        }
    }

    private void verifierCaseSoin() {
        Soin bonusSoin = monde.getSoinSurCase(this.xPosition, this.yPosition);
        if (bonusSoin != null) {
            bonusSoin.effet(this);
            monde.consommerSoin(bonusSoin);
        }
    }
    // Getters
    public String getNom() { return this.nom; }
    public int getxPosition() { return xPosition; }
    public int getyPosition() { return yPosition; }
    public static String getNomCreateur() { return NomCreateur; }
    public int getVersion() { return version; }
    public World getMonde() { return monde; }

    public int getPointsDeVieActuels() {
        return pointsDeVieMax;
    }
    public int getPointsDeVieMax() {
        return pointsDeVieMax;
    }
    private void verifierCaseSpeciale() {
        CaseSpecial caseSpeciale = monde.getCaseSpecialeSurCase(this.xPosition, this.yPosition);
        if (caseSpeciale != null) {
            caseSpeciale.effet(this); // Polymorphisme : exécute le bon effet (Soin ou Piege)
            monde.consommerCaseSpeciale(caseSpeciale); // La case est consommée
        }
    }
}