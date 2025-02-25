package sports.com.torneo;

import sports.com.interfaces.Competencia;
import sports.com.model.Equipo;

import java.util.List;

public class TorneoEliminacionDirecta  extends Torneo implements Competencia {
    public TorneoEliminacionDirecta(List<Equipo> equipos) {
        super(equipos);
    }
    @Override
    public void iniciarTorneo() {
        System.out.println("Iniciando torneo de Eliminación Directa...");
    }

    @Override
    public void asignarPuntos(Equipo equipo) {
        System.out.println("El equipo " + equipo.getNombre() + " avanza de ronda.");
    }

    @Override
    public Equipo calcularGanador(Equipo equipo1, Equipo equipo2) {
        return equipo1.getCantidadJugadores() > equipo2.getCantidadJugadores() ? equipo1 : equipo2;
    }

}
