package sports.com.torneo;

import sports.com.model.Equipo;

import java.util.List;

public class TorneoDobleEliminacion extends Torneo {
    public TorneoDobleEliminacion(List<Equipo> equipos) {
        super(equipos);
    }

    @Override
    public void iniciarTorneo() {
        System.out.println("Iniciando torneo de Doble Eliminación...");
    }

    @Override
    public void asignarPuntos(Equipo equipo) {
        System.out.println("El equipo " + equipo.getNombre() + " sigue en el torneo con una vida extra.");
    }
}
