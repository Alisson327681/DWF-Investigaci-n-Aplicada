package sports.com.torneo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import sports.com.model.Equipo;
import sports.com.model.Jugador;

public class TorneoLiga extends Torneo {
    public TorneoLiga(List<Equipo> equipos) {
        super(equipos);
    }

    @Override
    public void iniciarTorneo() {
        System.out.println("Iniciando torneo de Liga...");

        Map<Equipo, Integer> tablaPuntos = new HashMap<>();

        // Inicializar puntos en 0
        for (Equipo equipo : equipos) {
            tablaPuntos.put(equipo, 0);
        }
        //Cada equipo contra todos los demas
        // Cada equipo juega contra todos los demás
        for (int i = 0; i < equipos.size(); i++) {
            for (int j = i + 1; j < equipos.size(); j++) {
                Equipo e1 = equipos.get(i);
                Equipo e2 = equipos.get(j);
                Equipo ganador = jugarPartido(e1, e2);
                // Asignar puntos
                tablaPuntos.put(ganador, tablaPuntos.get(ganador) + 3);
            }
        }
        // Mostrar resultados
        System.out.println("\n📊 Tabla de posiciones:");
        tablaPuntos.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .forEach(entry -> System.out.println(entry.getKey().getNombre() + ": " + entry.getValue() + " puntos"));

        // Determinar campeón
        Equipo campeon = tablaPuntos.entrySet().stream()
                .max(Map.Entry.comparingByValue()).get().getKey();
        System.out.println("\n🏆 El campeón es: " + campeon.getNombre());
    }

    private Equipo jugarPartido(Equipo e1, Equipo e2) {
        Equipo ganador = calcularGanador(e1, e2);
        System.out.println("⚔️ " + e1.getNombre() + " vs " + e2.getNombre() + " ➡️ Ganador: " + ganador.getNombre());
        return ganador;
    }

    @Override
    public void asignarPuntos(Equipo equipo) {
        System.out.println("El equipo " + equipo.getNombre() + " gana puntos en la liga.");
    }

    @Override
    public Equipo calcularGanador(Equipo equipo1, Equipo equipo2) {
        // Calcular ranking promedio del equipo1
        double rankingPromedio1 = equipo1.getJugadores().stream()
                .mapToInt(Jugador::getRanking)
                .average()
                .orElse(0);

        // Calcular ranking promedio del equipo2
        double rankingPromedio2 = equipo2.getJugadores().stream()
                .mapToInt(Jugador::getRanking)
                .average()
                .orElse(0);

        // El equipo con mayor ranking tiene más probabilidades de ganar
        double probabilidadEquipo1 = rankingPromedio1 / (rankingPromedio1 + rankingPromedio2);

        return Math.random() < probabilidadEquipo1 ? equipo1 : equipo2;
    }
}
