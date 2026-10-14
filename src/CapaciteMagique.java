public class CapaciteMagique extends CapaciteCombat {
    public CapaciteMagique() {
        super("Magique");
    }

    @Override
    public int calculerDegatsEffectifs(Robot cible, int degatsDeBase) {
        // On vérifie si la cible a aussi le mot "Magique" dans son type de combat
        if (cible.getCapaciteCombat() != null && cible.getCapaciteCombat().getNomType().equals("Magique")) {
            return 0; // Immunité, on annule les dégâts
        }
        return degatsDeBase; // Sinon, dégâts normaux
    }
}