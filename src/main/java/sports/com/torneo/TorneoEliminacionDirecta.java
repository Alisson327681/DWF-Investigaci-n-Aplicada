package sports.com.torneo;

import sports.com.model.Equipo;
import sports.com.model.Jugador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TorneoEliminacionDirecta extends Torneo {
    public TorneoEliminacionDirecta(List<Equipo> equipos) {
        super(equipos);
    }

    @Override
    public void iniciarTorneo() {
        System.out.println("Iniciando torneo de Eliminación Directa...");
        List<Equipo> rondaActual = new ArrayList<>(equipos);
        int ronda = 1;

        while(rondaActual.size() > 1){
            System.out.println("Ronda: " + ronda);
            List<Equipo> ganadores = new ArrayList<>();
            Collections.shuffle(rondaActual);

            for (int i = 0; i < rondaActual.size(); i += 2) {
                if (i + 1 < rondaActual.size()) {
                    Equipo equipo1 = rondaActual.get(i);
                    Equipo equipo2 = rondaActual.get(i + 1);
                    Equipo ganador = jugarPartido(equipo1, equipo2);
                    ganadores.add(ganador);
                } else {
                    // Si hay un número impar de equipos, el último pasa directamente
                    ganadores.add(rondaActual.get(i));
                }
            }
            rondaActual = ganadores;
            ronda++;
        }

        if (!rondaActual.isEmpty()) {
            System.out.println("\n🏆 El campeón de la modalidad Eliminacion Directa es: " + rondaActual.get(0).getNombre());
        } else {
            System.out.println("\n❌ No se pudo determinar un campeón.");
        }
    }

    private Equipo jugarPartido(Equipo e1, Equipo e2) {
        Equipo ganador = calcularGanador(e1, e2);
        System.out.println("⚔️ " + e1.getNombre() + " vs " + e2.getNombre() + " ➡️ Ganador: " + ganador.getNombre());
        return ganador;
    }

    @Override
    public void asignarPuntos(Equipo equipo) {
        System.out.println("El equipo " + equipo.getNombre() + " avanza a la siguiente ronda.");
    }

    @Override
    public Equipo calcularGanador(Equipo equipo1, Equipo equipo2) {
        // Calcular la suma de los rankings de los jugadores de cada equipo
        int sumaRankingE1 = equipo1.getJugadores().stream()
                .mapToInt(Jugador::getRanking)
                .sum();

        int sumaRankingE2 = equipo2.getJugadores().stream()
                .mapToInt(Jugador::getRanking)
                .sum();

        // También considerar la cantidad de jugadores como factor
        int jugadoresE1 = equipo1.getCantidadJugadores();
        int jugadoresE2 = equipo2.getCantidadJugadores();

        // Crear un factor compuesto que considere tanto el ranking como el número de jugadores
        double factorE1 = sumaRankingE1 * Math.sqrt(jugadoresE1);
        double factorE2 = sumaRankingE2 * Math.sqrt(jugadoresE2);

        // Determinar probabilidad de victoria
        double probabilidadE1 = factorE1 / (factorE1 + factorE2);

        // Determinar ganador con un elemento de aleatoriedad
        return Math.random() < probabilidadE1 ? equipo1 : equipo2;
    }
}
