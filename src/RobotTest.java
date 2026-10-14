import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RobotTest {


    @Test
    // Test sur le nom du Robot
    public void testNomPersonnaliseValide() {
        // 1. Préparation
        World monde = new World(0, 10, -5, 5);

        // AJOUT : on rajoute le 6ème paramètre (le nom du créateur, ex: "Testeur")
        Robot robot = new Robot("Crabot", 0, 0, 1, monde, "Testeur");

        // 2. Vérification
        assertEquals("Crabot", robot.getNom(), "Le nom a plus de 5 caractères");
    }

    @Test
    public void testNomPersonnaliseInvalide() {
        // 1. Préparation (Création d'un robot avec un nom trop court)
        World monde = new World(0, 10, -5, 5);
        Robot robot = new Robot("Bob", 0, 0, 1, monde, "Alice"); // "Bob" = 3 caractères

        // 2. Vérification
        // On utilise assertTrue et startsWith car le compteur peut être à Anonymous1, Anonymous2, etc.
        assertTrue(robot.getNom().startsWith("Anonymous"), "Le nom doit devenir AnonymousX car 'Bob' est trop court");
    }

    // Test sur les limites du monde

    @Test
    public void testDeplacementValide() {
        // 1. Préparation : un monde et un robot au centre (0, 0)
        World monde = new World(0, 10, -5, 5);
        Robot robot = new Robot("Adesanya", 0, 0, 1, monde, "Alice");

        // 2. Action : On déplace le robot en diagonale bas  à droite

        robot.deplacer();

        // 3. Vérification : X a du faire +1 et Y a di faire -1
        assertEquals(1, robot.getxPosition());
        assertEquals(-1, robot.getyPosition());
    }

    @Test
    public void testDeplacementBloqueParLimite() {
        // 1. Préparation : un robot placé dans le coin  en bas à droite du monde
        World monde = new World(0, 10, -5, 5);
        Robot robot = new Robot("Tyranosaurus-X", 10, -5, 1, monde, "Alice");
        // Position maximale atteinte (10, -5)

        // 2. Action : On tente de le déplacer (il devrait être bloqué)
        robot.deplacer();

        // 3. Vérification : Ses coordonnées n'ont pas dû changer
        assertEquals(10, robot.getxPosition());
        assertEquals(-5, robot.getyPosition());
    }
    @Test
    public void testPositionInitialeSansCollision() {
        // 1. Préparation : Un monde standard
        World monde = new World(0, 10, -5, 5);

        // 2. Action : Création de deux robots à des positions de départ distinctes
        Robot robot1 = new Robot("BotUne", 2, 2, 1, monde, "Testeur");
        Robot robot2 = new Robot("BotDeux", 4, 4, 1, monde, "Testeur");

        // 3. Vérification des coordonnées initiales (Indépendance des objets)
        assertEquals(2, robot1.getxPosition(), "La position initiale X du robot 1 doit être 2");
        assertEquals(2, robot1.getyPosition(), "La position initiale Y du robot 1 doit être 2");

        assertEquals(4, robot2.getxPosition(), "La position initiale X du robot 2 doit être 4");
        assertEquals(4, robot2.getyPosition(), "La position initiale Y du robot 2 doit être 4");

        // 4. Vérification de l'absence de blocage immédiat
        // Le robot 1 avance normalement vers (3, 1) en diagonale. On s'assure que le monde autorise ce mouvement.
        assertTrue(monde.estPositionValideEtLibre(3, 1), "Le robot 1 doit avoir la voie libre pour son premier mouvement");
    }
}