import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RobotTest {


    @Test
    // Test sur le nom du Robot
    public void testNomPersonnaliseValide() {
        // 1. Préparation (Création du monde et d'un robot avec un nom long)
        World monde = new World(0, 10, -5, 5);
        Robot robot = new Robot("Flavonoide", 0, 0, 1, monde); // "Optimus" = 7 caractères

        // 2. Vérification
        assertEquals("Flavonoide", robot.getNom(), "Le nom a plus de 5 caractère");
    }

    @Test
    public void testNomPersonnaliseInvalide() {
        // 1. Préparation (Création d'un robot avec un nom trop court)
        World monde = new World(0, 10, -5, 5);
        Robot robot = new Robot("Bob", 0, 0, 1, monde); // "Bob" = 3 caractères

        // 2. Vérification
        // On utilise assertTrue et startsWith car le compteur peut être à Anonymous1, Anonymous2, etc.
        assertTrue(robot.getNom().startsWith("Anonymous"), "Le nom doit devenir AnonymousX car 'Bob' est trop court");
    }

    // Test sur les limites du monde

    @Test
    public void testDeplacementValide() {
        // 1. Préparation : un monde et un robot au centre (0, 0)
        World monde = new World(0, 10, -5, 5);
        Robot robot = new Robot("Adesanya", 0, 0, 1, monde);

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
        Robot robot = new Robot("Tyranosaurus-X", 10, -5, 1, monde);
        // Position maximale atteinte (10, -5)

        // 2. Action : On tente de le déplacer (il devrait être bloqué)
        robot.deplacer();

        // 3. Vérification : Ses coordonnées n'ont pas dû changer
        assertEquals(10, robot.getxPosition());
        assertEquals(-5, robot.getyPosition());
    }
}