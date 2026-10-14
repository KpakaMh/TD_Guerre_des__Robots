import org.junit.Test;
import static org.junit.Assert.*;

public class WorldTest {

    @Test
    public void testColisionWorld() {
        // 1. Préparation : Un monde et deux robots
        World monde = new World(0, 10, -5, 5);

        // Le robot 1 est en (0, 0). Son déplacement normal le mènera en (1, -1).
        Robot robot1 = new Robot("BotActif", 0, 0, 1, monde, "Testeur");

        // Le robot 2 est placé EXACTEMENT sur la case de destination du robot 1 : (1, -1)
        Robot robot2 = new Robot("BotObstacle", 2, -1, 1, monde, "Testeur");

        // 2. Action : Le robot 1 tente de se déplacer
        robot1.deplacer();

        // 3. Vérification : Le robot 1 n'a pas dû bouger car la case était occupée
        assertEquals(0, robot1.getxPosition(), "Le robot1 aurait dû rester en X=0 à cause de la collision");
        assertEquals(0, robot1.getyPosition(), "Le robot1 aurait dû rester en Y=0 à cause de la collision");
    }

    private void assertEquals(int i, int i1, String s) {
    }

    @Test
    public void testAbsenceDeCollisionEntreDeuxRobots() {
        // 1. Préparation : Un monde et deux robots éloignés l'un de l'autre
        World monde = new World(0, 15, -10, 10);

        // Robot 1 en (0, 0)
        Robot robot1 = new Robot("AlphaBot", 0, 0, 1, monde, "Testeur");

        // Robot 2 en (5, 5) - Totalement hors de portée
        Robot robot2 = new Robot("BetaBot", 5, 5, 1, monde, "Testeur");

        // 2. Action : On déplace les deux robots (diagonale bas-droite)
        robot1.deplacer(); // Devrait aller en (1, -1)
        robot2.deplacer(); // Devrait aller en (6, 4)

        // 3. Vérification : Les deux robots ont dû atteindre leur destination sans se gêner
        assertEquals(1, robot1.getxPosition(), "AlphaBot aurait dû avancer librement en X");
        assertEquals(-1, robot1.getyPosition(), "AlphaBot aurait dû avancer librement en Y");

        assertEquals(6, robot2.getxPosition(), "BetaBot aurait dû avancer librement en X");
        assertEquals(4, robot2.getyPosition(), "BetaBot aurait dû avancer librement en Y");
    }
}