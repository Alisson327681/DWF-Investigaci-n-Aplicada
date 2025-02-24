package sports.com.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EquipoTest {
    private Equipo equipo;

    @BeforeEach
    void setUp() {
        equipo = new Equipo("Test Team");
    }

    @Test
    void agregarJugador_CuandoSeAgregaJugador_IncrementaCantidad() {
        Jugador jugador = new Jugador("Test Player", 100, equipo);
        equipo.agregarJugador(jugador);

        assertEquals(1, equipo.getCantidadJugadores());
        assertTrue(equipo.getJugadores().contains(jugador));
    }

    @Test
    void getNombre_RetornaNombreCorrecto() {
        assertEquals("Test Team", equipo.getNombre());
    }

    @Test
    void getCantidadJugadores_EquipoNuevo_RetornaCero() {
        assertEquals(0, equipo.getCantidadJugadores());
    }

    @Test
    void getJugadores_EquipoNuevo_RetornaListaVacia() {
        assertTrue(equipo.getJugadores().isEmpty());
    }
}
