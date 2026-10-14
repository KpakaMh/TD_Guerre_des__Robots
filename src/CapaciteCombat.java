public class CapaciteCombat {
    protected String nomType;

    public CapaciteCombat(String nomType) {
        this.nomType = nomType;
    }

    // Méthode de base qui sera "écrasée" (Override) par les sous-classes si besoin
    public int calculerDegatsEffectifs(Robot cible, int degatsDeBase) {
        // Par défaut (physique), les dégâts sont normaux
        return degatsDeBase;
    }

    public String getNomType() {
        return this.nomType;
    }
}