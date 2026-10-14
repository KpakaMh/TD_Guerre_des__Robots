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
        System.out.println("Je suis un Robot classique : je me déplace d'1 case en diagonale (bas-droite).");
        System.out.println("Robot : " + this.nom + " | PV : " + this.pointsDeVieActuels + "/" + this.pointsDeVieMax);
    }
    // La méthode de déplacement, en diagonale vers le bas à droite
    // On suppose que "bas à droite" signifie x augmente (+1) et y diminue (-1)
    public void deplacer() {
        int cibleX = this.xPosition + pasX;
        int cibleY = this.yPosition + pasY;

        // 1. Est-ce qu'on tape un mur de l'arène ?
        if (!monde.estDansLesLimites(cibleX, cibleY)) {
            System.out.println(this.nom + " heurte un mur et fait demi-tour !");
            pasX = -pasX;
            pasY = -pasY;
            cibleX = this.xPosition + pasX;
            cibleY = this.yPosition + pasY;
        }

        // 2. Est-ce qu'un autre robot bloque la nouvelle case cible ?
        Robot cible = monde.getRobotSurCase(cibleX, cibleY);

        if (cible != null) { // La case est occupée par un ennemi !
            System.out.println(this.nom + " est bloqué par " + cible.getNom() + " ! Il attaque !");

            // On attaque avec 30 de dégâts de base (vous pouvez changer cette valeur)
            this.attaquer(cible, 30);

            // 3. Vérification de la mort de la cible
            if (cible.getPointsDeVieActuels() == 0) {
                System.out.println("💥 BOOM ! " + cible.getNom() + " a été détruit par " + this.nom + " !");
                monde.retirerRobot(cible); // La cible disparaît de la liste

                // L'ennemi est mort, on prend sa place !
                this.xPosition = cibleX;
                this.yPosition = cibleY;
                System.out.println(this.nom + " avance sur les restes de son ennemi en (" + this.xPosition + ", " + this.yPosition + ")");
            } else {
                System.out.println(cible.getNom() + " survit à l'attaque. " + this.nom + " reste sur place ce tour-ci.");
            }
        } else {
            // 4. La case est totalement vide, déplacement normal
            this.xPosition = cibleX;
            this.yPosition = cibleY;
            System.out.println(this.nom + " s'est déplacé paisiblement en (" + this.xPosition + ", " + this.yPosition + ")");
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
}