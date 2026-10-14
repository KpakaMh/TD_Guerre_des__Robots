public class CapacitePositronique extends CapaciteCombat {
    public CapacitePositronique() {
        super("Positronique");
    }

    @Override
    public int calculerDegatsEffectifs(Robot cible, int degatsDeBase) {
        // On vérifie si la cible a aussi une capacité "Positronique"
        if (cible.getCapaciteCombat() != null && cible.getCapaciteCombat().getNomType().equals("Positronique")) {
            return degatsDeBase * 2; // Dégâts doublés !
        }
        return degatsDeBase;
    }
}