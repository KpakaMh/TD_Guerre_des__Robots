public class Robot {

    // Le compteur global pour tous les robots (pour Anonymous1, Anonymous2...)
    private static int counter = 1;
    private String nom;
    protected int xPosition;
    protected int yPosition;
    private int version;
    private String AdesanyaK;

    // Le robot connaît le monde dans lequel il évolue
    private World monde;

    // Constructer
    public Robot(String nomPersonnalise, int xPosition, int yPosition, int version, World monde, String AdesanyaK) {

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
        this.AdesanyaK = AdesanyaK;
    }

    // La méthode de déplacement, en diagonale vers le bas à droite
    // On suppose que "bas à droite" signifie x augmente (+1) et y diminue (-1)
    public void deplacer() {
        int nouvellePositionX = this.xPosition + 1;
        int nouvellePositionY = this.yPosition - 1;

        // On vérifie les limites du monde AVANT de valider le déplacement
        if (nouvellePositionX >= monde.getMinX() && nouvellePositionX <= monde.getMaxX() &&
                nouvellePositionY >= monde.getMinY() && nouvellePositionY <= monde.getMaxY()) {

            this.xPosition = nouvellePositionX;
            this.yPosition = nouvellePositionY;
            System.out.println(this.nom + " s'est déplacé en (" + this.xPosition + ", " + this.yPosition + ")");
        } else {
            System.out.println(this.nom + " est bloqué par les limites du monde !");
        }
    }

    // Getters
    public String getNom() { return nom; }
    public int getxPosition() { return xPosition; }
    public int getyPosition() { return yPosition; }
}