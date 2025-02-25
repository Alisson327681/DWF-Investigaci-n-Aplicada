package sports.com;

import sports.com.menu.Menu;
import sports.com.model.Equipo;
import sports.com.model.Jugador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sports.com.torneo.Torneo;
import sports.com.torneo.TorneoLiga;

import java.util.ArrayList;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

class MenuTest {
    private Menu menu;
    private ArrayList<Equipo> equipos;
    private ArrayList<Jugador> jugadores;
    private Torneo torneo;

    @BeforeEach
    void setUp() {
        equipos = new ArrayList<>();
        jugadores = new ArrayList<>();
        torneo = new TorneoLiga(equipos);
        menu = new Menu(new Scanner(System.in), equipos, jugadores, torneo);
    }

    @Test
    void validarEquipo_ConMenosDeDosJugadores_RetornaFalso() {
        Equipo equipo = new Equipo("Test Team");
        equipo.agregarJugador(new Jugador("Player1", 100, equipo));

        assertFalse(menu.validarEquipo(equipo));
    }

    @Test
    void validarEquipo_ConDosJugadores_RetornaVerdadero() {
        Equipo equipo = new Equipo("Test Team");
        equipo.agregarJugador(new Jugador("Player1", 100, equipo));
        equipo.agregarJugador(new Jugador("Player2", 90, equipo));

        assertTrue(menu.validarEquipo(equipo));
    }

    @Test
    void validarEquipo_ConEquipoNulo_RetornaFalso() {
        assertFalse(menu.validarEquipo(null));
    }
}
