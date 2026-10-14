public class Main {
    public static void main(String[] args) {

        System.out.println("=== 1. CRÉATION DU MONDE ===");
        World arene = new World(-5, 5, 0, 10);
        Soin soinMap = new Soin(1, 4);
        arene.ajouterSoin(soinMap);
        arene.afficherEtat();
        // Ajout d'une case Soin et d'une case Piege
        arene.ajouterCaseSpeciale(new Soin(1, 4));
        arene.ajouterCaseSpeciale(new Piege(2, 5));


        // AJOUT DU 7ème PARAMÈTRE (ex: 100, 80, 120...) POUR LES POINTS DE VIE
        int[] p1 = arene.trouverPositionLibre();
        Robot robotClassique = new Robot("RobotClassique", p1[0], p1[1], 1, arene, "Alice", 100);


        // 2. On crée un robot blessé qui va se déplacer vers cette case
        Robot robotBlesse = new Robot("Warrior", 0, 5, 1, arene, "Testeur", 100);
        robotBlesse.subirDegats(80); // Il lui reste 20 PV

        int[] p2 = arene.trouverPositionLibre();
        Robot robotAnonymize = new Robot("Bo", p2[0], p2[1], 1, arene, "Bob", 100);

        int[] p3 = arene.trouverPositionLibre();
        RobotRapide flash = new RobotRapide("FlashBot", p3[0], p3[1], 2, arene, "Charlie", 80);

        int[] p4 = arene.trouverPositionLibre();
        RobotSauteur jumper = new RobotSauteur("JumperBot", p4[0], p4[1], 1, arene, "Alice", 90);

        int[] p5 = arene.trouverPositionLibre();
        RobotCrabe crabe = new RobotCrabe("Crabator", p5[0], p5[1], 1, arene, "Charlie", 120);

        int[] p6 = arene.trouverPositionLibre();
        RobotTorpille torpille = new RobotTorpille ("torpille", p6[0], p6[1], 1, arene, "Adesanya_K", 45);

        int[] p7 = arene.trouverPositionLibre();
        RobotTourelle tourelle = new RobotTourelle("Gardien", p7[0], p7[1], 1, arene, "Adesanya_K", 200);

        System.out.println("Monde initialisé avec succès !");

        System.out.println("\n=== 2. CRÉATION DES ROBOTS ===");
        System.out.println("- " + robotClassique.getNom() + " (Créateur : " + Robot.getNomCreateur() + ")");
        System.out.println("- " + robotAnonymize.getNom() + " (Créateur : " + Robot.getNomCreateur() + ")");
        System.out.println("- " + flash.getNom() + " (Créateur : " + Robot.getNomCreateur() + ")");
        System.out.println("- " + jumper.getNom() + " (Créateur : " + Robot.getNomCreateur() + ")");
        System.out.println("-"+robotBlesse.getNom() + "(Créateur: " + robotBlesse.getNomCreateur() + ")");
        System.out.println("-"+torpille.getNom() + "Créateur:" + torpille.getNomCreateur() + ")");

        System.out.println("\n=== 3. AFFICHAGE DES MANIÈRES DE DÉPLACEMENT ===");
        robotClassique.afficherManiereDeplacement();
        flash.afficherManiereDeplacement();
        jumper.afficherManiereDeplacement();
        crabe.afficherManiereDeplacement();
        robotBlesse.afficherManiereDeplacement();
        torpille.afficherManiereDeplacement();

        System.out.println("\n=== 4. SIMULATION DES DÉPLACEMENTS ===");
        robotClassique.deplacer();
        flash.deplacer();
        jumper.deplacer();
        crabe.deplacer();
        // 3. Le robot se déplace et tombe sur la case Soin (1, 4)
        robotBlesse.deplacer(); // Avance en (1, 4) et active le Soin automatique !
        torpille.deplacer();

        // ---------------- NOUVEAUTÉ ICI ----------------

        System.out.println("\n=== 5. ÉQUIPEMENT DES CAPACITÉS DE COMBAT ===");
        // Par défaut ils sont physiques, mais on peut les forcer pour la clarté
        robotClassique.setCapaciteCombat(new CapacitePhysique());

        // On équipe deux mages
        jumper.setCapaciteCombat(new CapaciteMagique());
        crabe.setCapaciteCombat(new CapaciteMagique());

        // On équipe deux combattants positroniques
        flash.setCapaciteCombat(new CapacitePositronique());
        torpille.setCapaciteCombat(new CapacitePositronique());
        robotAnonymize.deplacer();

        System.out.println("Les capacités ont été distribuées aux robots !");

        System.out.println("\n=== 6. SIMULATION DE COMBAT ===");

        // Cas 1 : Attaque Physique (Normale) -> Dégâts normaux
        System.out.println("\n--- Cas 1 : Attaque Physique ---");
        robotClassique.attaquer(jumper, 20);

        // Cas 2 : Attaque Magique contre Magique -> Immunité
        System.out.println("\n--- Cas 2 : Magique vs Magique (Immunité) ---");
        jumper.attaquer(crabe, 30);

        // Cas 3 : Attaque Positronique contre Positronique -> Dégâts doublés
        System.out.println("\n--- Cas 3 : Positronique vs Positronique (Dégâts x2) ---");
        flash.attaquer(torpille, 25);

        // Cas 4 : Attaque Magique contre Physique -> Dégâts normaux
        System.out.println("\n--- Cas 4 : Magique vs Physique (Normal) ---");
        crabe.attaquer(robotClassique, 15);

        // Bilan des PV
        System.out.println("\n=== BILAN DES POINTS DE VIE APRÈS LE COMBAT ===");
        robotClassique.afficherManiereDeplacement();
        jumper.afficherManiereDeplacement();
        crabe.afficherManiereDeplacement();
        flash.afficherManiereDeplacement();
        torpille.afficherManiereDeplacement();
        robotBlesse.afficherManiereDeplacement();
        torpille.afficherManiereDeplacement();
    }
}