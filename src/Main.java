public class Main {
    public static void main(String[] args) {

        System.out.println("=== 1. CRÉATION DU MONDE ===");
        World arene = new World(-5, 5, 0, 10);

        // Ajout des cases spéciales (Soin et Piège) via le polymorphisme
        arene.ajouterCaseSpeciale(new Soin(1, 4));
        arene.ajouterCaseSpeciale(new Piege(2, 5));

        // Instanciation des robots
        int[] p1 = arene.trouverPositionLibre();
        Robot robotClassique = new Robot("RobotClassique", p1[0], p1[1], 1, arene, "Alice", 100);

        // Robot blessé (Warrior) initialisé pour tester la case de soin
        Robot robotBlesse = new Robot("Warrior", 0, 5, 1, arene, "Testeur", 100);
        robotBlesse.subirDegats(80); // Il lui reste 20 PV sur 100

        int[] p2 = arene.trouverPositionLibre();
        Robot robotAnonymize = new Robot("Bo", p2[0], p2[1], 1, arene, "Bob", 100);

        int[] p3 = arene.trouverPositionLibre();
        RobotRapide flash = new RobotRapide("FlashBot", p3[0], p3[1], 2, arene, "Charlie", 80);

        int[] p4 = arene.trouverPositionLibre();
        RobotSauteur jumper = new RobotSauteur("JumperBot", p4[0], p4[1], 1, arene, "Alice", 90);

        int[] p5 = arene.trouverPositionLibre();
        RobotCrabe crabe = new RobotCrabe("Crabator", p5[0], p5[1], 1, arene, "Charlie", 120);

        int[] p6 = arene.trouverPositionLibre();
        RobotTorpille torpille = new RobotTorpille("torpille", p6[0], p6[1], 1, arene, "Adesanya_K", 45);

        int[] p7 = arene.trouverPositionLibre();
        RobotTourelle tourelle = new RobotTourelle("Tourelle",  p7[0], p7[1],1,  arene, "Adesanay_K", 50, 10, 5, 200);
        int [] p8 = arene.trouverPositionLibre();
        RobotTourelle tourelle1 = new RobotTourelle("tourelle1", p8[0], p8[1],1,  arene, "Adesanya_K", 50, 10, 5, 200);
        int[] p9 = arene.trouverPositionLibre();
        RobotTourelle tourelle2 = new RobotTourelle("tourelle2", p9[0], p9[1],1,  arene, "Adesanya_K", 50, 10, 5, 200);



        System.out.println("Monde initialisé avec succès !");

        // Affichage de l'état du monde APPRÈS le placement de tous les robots
        arene.afficherEtat();

        System.out.println("\n=== 2. CRÉATION DES ROBOTS ===");
        System.out.println("- " + robotClassique.getNom() + " (Créateur : " + robotClassique.getNomCreateur() + ")");
        System.out.println("- " + robotAnonymize.getNom() + " (Créateur : " + robotAnonymize.getNomCreateur() + ")");
        System.out.println("- " + flash.getNom() + " (Créateur : " + flash.getNomCreateur() + ")");
        System.out.println("- " + jumper.getNom() + " (Créateur : " + jumper.getNomCreateur() + ")");
        System.out.println("- " + crabe.getNom() + " (Créateur : " + crabe.getNomCreateur() + ")");
        System.out.println("- " + robotBlesse.getNom() + " (Créateur : " + robotBlesse.getNomCreateur() + ")");
        System.out.println("- " + torpille.getNom() + " (Créateur : " + torpille.getNomCreateur() + ")");
        System.out.println("- " + tourelle.getNom() + " (Créateur : " + tourelle.getNomCreateur() + ")");
        System.out.println("-" + tourelle1.getNom() + "(Créateur: " +  tourelle1.getNomCreateur() + ")");
        System.out.println("-" + tourelle2.getNom() +   "Créateur: "+  tourelle2.getNomCreateur()  );

        System.out.println("\n=== 3. AFFICHAGE DES MANIÈRES DE DÉPLACEMENT ===");
        robotClassique.afficherManiereDeplacement();
        flash.afficherManiereDeplacement();
        jumper.afficherManiereDeplacement();
        crabe.afficherManiereDeplacement();
        robotBlesse.afficherManiereDeplacement();
        torpille.afficherManiereDeplacement();
        tourelle.afficherManiereDeplacement();
        tourelle1.afficherManiereDeplacement();
        tourelle2.afficherManiereDeplacement();

        System.out.println("\n=== 4. SIMULATION DES DÉPLACEMENTS ===");
        robotClassique.deplacer();
        flash.deplacer();
        jumper.deplacer();
        crabe.deplacer();
        robotBlesse.deplacer(); // Avance et déclenche le Soin s'il atterrit sur (1, 4)
        torpille.deplacer();
        robotAnonymize.deplacer();
        tourelle.deplacer();
        tourelle1.deplacer();
        tourelle2.deplacer();

        System.out.println("\n=== 5. ÉQUIPEMENT DES CAPACITÉS DE COMBAT ===");
        robotClassique.setCapaciteCombat(new CapacitePhysique());
        jumper.setCapaciteCombat(new CapaciteMagique());
        crabe.setCapaciteCombat(new CapaciteMagique());
        flash.setCapaciteCombat(new CapacitePositronique());
        torpille.setCapaciteCombat(new CapacitePositronique());
        tourelle.setCapaciteCombat(new CapacitePositronique());
        tourelle1.setCapaciteCombat(new CapacitePositronique());
        tourelle2.setCapaciteCombat(new CapacitePositronique());

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

        // Bilan final des PV
        System.out.println("\n=== BILAN DES POINTS DE VIE APRÈS LE COMBAT ===");
        robotClassique.afficherManiereDeplacement();
        jumper.afficherManiereDeplacement();
        crabe.afficherManiereDeplacement();
        flash.afficherManiereDeplacement();
        torpille.afficherManiereDeplacement();
        robotBlesse.afficherManiereDeplacement();
        tourelle.afficherManiereDeplacement();

        System.out.println("\n--- Phase d'attaque des Tourelles ---");
        tourelle.jouerTour(20);
        tourelle1.jouerTour(25);
        tourelle2.jouerTour(30);


    }

}