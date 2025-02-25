package sports.com.torneo;

import sports.com.model.Equipo;

import java.util.List;

public class TorneoLiga extends Torneo {
    public TorneoLiga(List<Equipo> equipos) {
        super(equipos);
    }

    @Override
    public void iniciarTorneo() {
        System.out.println("Iniciando torneo de Liga...");
    }

    @Override
    public void asignarPuntos(Equipo equipo) {
        System.out.println("El equipo " + equipo.getNombre() + " gana puntos en la liga.");
    }
}
