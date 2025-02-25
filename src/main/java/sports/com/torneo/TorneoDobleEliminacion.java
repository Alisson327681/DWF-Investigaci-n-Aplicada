package sports.com.torneo;

import sports.com.model.Equipo;
import sports.com.model.Jugador;
import sports.com.torneo.Torneo;

import java.util.*;

public class TorneoDobleEliminacion extends Torneo {
    private Map<Equipo,Integer> derrotas;

    public TorneoDobleEliminacion(List<Equipo> equipos) {
        super(equipos);
        derrotas = new HashMap<>();
        for(Equipo equipo : equipos){
            derrotas.put(equipo,0);
        }
    }

    @Override
    public void iniciarTorneo() {
        System.out.println(" Iniciando Torneo de Doble Eliminación...");

        List<Equipo> rondaActual = new ArrayList<>(equipos);
        int ronda = 1;

        while(rondaActual.size() > 1){
            System.out.println("\n🔹 Ronda " + ronda);
            List<Equipo> siguienteRonda = new ArrayList<>();
            Collections.shuffle(rondaActual);

            for (int i = 0; i < rondaActual.size(); i += 2) {
                if (i + 1 < rondaActual.size()) {
                    Equipo e1 = rondaActual.get(i);
                    Equipo e2 = rondaActual.get(i + 1);
                    Equipo perdedor = jugarPartido(e1, e2);
                    Equipo ganador = (perdedor == e1) ? e2 : e1;

                    derrotas.put(perdedor, derrotas.get(perdedor) + 1);

                    // El ganador siempre avanza
                    siguienteRonda.add(ganador);

                    // Si el perdedor tiene menos de 2 derrotas, sigue en el torneo
                    if (derrotas.get(perdedor) < 2) {
                        siguienteRonda.add(perdedor);
                    }
                } else {
                    // Si hay un número impar de equipos, el último pasa directamente
                    siguienteRonda.add(rondaActual.get(i));
                }
            }
            rondaActual = siguienteRonda;
            ronda++;
        }

        if (!rondaActual.isEmpty()) {
            System.out.println("\n El campeón es: " + rondaActual.get(0).getNombre());
        } else {
            System.out.println("\n No se pudo determinar un campeón.");
        }
    }

    private Equipo jugarPartido(Equipo e1, Equipo e2) {
        // Calcular el ganador usando la interfaz Competencia
        Equipo ganador = calcularGanador(e1, e2);
        // Determinar el perdedor
        Equipo perdedor = (ganador == e1) ? e2 : e1;
        System.out.println("--- " + e1.getNombre() + " vs " + e2.getNombre() + " => Perdedor: " + perdedor.getNombre());
        return perdedor;
    }

    @Override
    public void asignarPuntos(Equipo equipo) {
        System.out.println("El equipo " + equipo.getNombre() + " sigue en el torneo con una vida extra.");
    }

    @Override
    public Equipo calcularGanador(Equipo equipo1, Equipo equipo2) {
        // Calcular fortaleza basada en el ranking de los jugadores y experiencia previa
        double fortaleza1 = calcularFortaleza(equipo1);
        double fortaleza2 = calcularFortaleza(equipo2);

        // Probabilidad de victoria basada en fortaleza relativa
        double probabilidadEquipo1 = fortaleza1 / (fortaleza1 + fortaleza2);

        return Math.random() < probabilidadEquipo1 ? equipo1 : equipo2;
    }

    private double calcularFortaleza(Equipo equipo) {
        // Base: promedio de rankings de jugadores
        double rankingPromedio = equipo.getJugadores().stream()
                .mapToInt(Jugador::getRanking)
                .average()
                .orElse(0);

        // Factor de experiencia: equipos con menos derrotas tienen ventaja
        int derrotasEquipo = derrotas.getOrDefault(equipo, 0);
        double factorExperiencia = 1.0 - (derrotasEquipo * 0.2); // 20% de penalización por derrota

        return rankingPromedio * factorExperiencia;
    }
}
