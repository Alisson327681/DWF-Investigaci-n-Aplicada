package sports.com.torneo;
import sports.com.model.Equipo;

import java.util.List;
public abstract class Torneo {
    protected List<Equipo> equipos;

    public Torneo(List<Equipo> equipos) {
        if (equipos.size() < 4 || equipos.size() > 16) {
            throw new IllegalArgumentException("El torneo debe tener entre 4 y 16 equipos.");
        }
        this.equipos = equipos;
    }
    public abstract void iniciarTorneo(); // Cada tipo de torneo implementa su versión
    public abstract void asignarPuntos(Equipo equipo);

    // Metodo para registrar un equipo
    public void registrarEquipo(Equipo equipo) {
        if (equipo != null && !equipos.contains(equipo) && equipo.getCantidadJugadores() >= 2) {
            equipos.add(equipo);
            System.out.println("Equipo registrado en el torneo: " + equipo.getNombre());
        } else {
            System.out.println("No se puede registrar el equipo. Verifique los requisitos.");
        }
    }
}