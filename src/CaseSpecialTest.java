import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CaseSpecialTest {

    private World world;
    private Robot robot;

    @BeforeEach
    public void setUp() {
        world = new World(-5, 5, 0, 10);
        robot = new Robot("TestBot", 0, 0, 1, world, "Tester", 100);
    }

    @Test
    public void testSoinRestaureTousLesPV() {
        Soin soin = new Soin(0, 0);
        robot.subirDegats(80); // PV actuels : 20 / 100

        soin.effet(robot);

        assertEquals(100, robot.getPointsDeVieActuels(), "Le soin doit restaurer l'intégralité des PV max.");
    }

    @Test
    public void testPiegeRetireMoitiePVLese() {
        Piege piege = new Piege(0, 0); // PV actuels : 100 / 100

        piege.effet(robot);

        assertEquals(50, robot.getPointsDeVieActuels(), "Le piège doit retirer 50% des 100 PV actuels.");
    }

    @Test
    public void testPiegeArrondiInferieur() {
        Piege piege = new Piege(0, 0);
        robot.subirDegats(75); // PV actuels : 25 / 100
        // 25 / 2 = 12 dégâts (arrondi inférieur) -> PV restants : 13

        piege.effet(robot);

        assertEquals(13, robot.getPointsDeVieActuels(), "25 PV - 12 dégâts de piège doit laisser 13 PV.");
    }
}