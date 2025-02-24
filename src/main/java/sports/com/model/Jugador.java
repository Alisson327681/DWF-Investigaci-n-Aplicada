package sports.com.model;

public class Jugador {
    private String nombre;
    private int ranking;
    private Equipo equipo;

    public Jugador(String nombre, int ranking, Equipo equipo) {
        this.nombre = nombre;
        this.ranking = ranking;
        this.equipo = equipo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getRanking() {
        return ranking;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
}
