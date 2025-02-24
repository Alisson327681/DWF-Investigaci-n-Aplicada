package sports.com.model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JugadorTest {
    private Equipo equipo;
    private Jugador jugador;

    @BeforeEach
    void setUp() {
        equipo = new Equipo("Test Team");
        jugador = new Jugador("Test Player", 100, equipo);
    }

    @Test
    void getNombre_RetornaNombreCorrecto() {
        assertEquals("Test Player", jugador.getNombre());
    }

    @Test
    void getRanking_RetornaRankingCorrecto() {
        assertEquals(100, jugador.getRanking());
    }

    @Test
    void getEquipo_RetornaEquipoCorrecto() {
        assertEquals(equipo, jugador.getEquipo());
    }
}
