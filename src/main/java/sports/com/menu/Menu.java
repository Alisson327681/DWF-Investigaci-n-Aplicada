package sports.com.menu;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import sports.com.model.Equipo;
import sports.com.model.Jugador;
import sports.com.torneo.TorneoDobleEliminacion;
import sports.com.torneo.TorneoEliminacionDirecta;
import sports.com.torneo.TorneoLiga;
import sports.com.torneo.Torneo;

public class Menu {
        private final Scanner scanner;
        private final List<Equipo> equipos;
        private final List<Jugador> jugadores;
        private final Torneo torneo;

        public Menu(Scanner scanner, List<Equipo> equipos, List<Jugador> jugadores, Torneo torneo) {
            this.scanner = scanner;
            this.equipos = equipos;
            this.jugadores = jugadores;
            this.torneo = torneo;
        }
    public Menu(Torneo torneo) {
        this(new Scanner(System.in), new ArrayList<>(), new ArrayList<>(), torneo);
    }
        public Menu() {
            this(new Scanner(System.in), new ArrayList<>(), new ArrayList<>(), null);
        }

        // Getters para testing
        public List<Equipo> getEquipos() {
            return equipos;
        }
        public List<Jugador> getJugadores() {
            return jugadores;
        }

        // Metodo para validar equipo
        public boolean validarEquipo(Equipo equipo) {
            return equipo != null && equipo.getCantidadJugadores() >= 2;
        }

        // Metodo principal del menú
        public void mostrarMenuPrincipal() {
            boolean salir = false;
            while (!salir) {
                System.out.println("\n=== SISTEMA DE GESTIÓN DE TORNEOS eSPORTS ===");
                System.out.println("\n ===Bienvenidos a nuestro programa===");
                System.out.println("1. Registrar Equipo");
                System.out.println("2. Registrar Jugador");
                System.out.println("3. Modalidad de Torneo");
                System.out.println("4. Ver Equipos Inscritos");
                System.out.println("5. Ver Puntuacion");
                System.out.println("6. Salir");
                System.out.print("Seleccione una opción: ");

                int opcion = leerOpcion();

                switch (opcion) {
                    case 1:
                        registrarEquipo();
                        break;
                    case 2:
                        registrarJugador();
                        break;
                    case 3:
                        seleccionarModalidadTorneo();
                        break;
                    case 4:
                        verEquiposRegistrados();
                        break;
                    case 5:
                        verPuntuacion();
                        break;
                    case 6:
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                        break;
                }
            }
            scanner.close();
        }

    private void verPuntuacion() {
    }

    // Metodo para leer opciones del usuario
        private int leerOpcion() {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                return -1;
            }
        }
        // Metodo para registrar equipo
        private void registrarEquipo() {
            System.out.println("\n=== REGISTRAR EQUIPO ===");
            System.out.print("Ingrese el nombre del equipo que desea inscribir: ");
            String nombreEquipo = scanner.nextLine().trim();

            if (nombreEquipo.isEmpty()) {
                System.out.println("El nombre del equipo no puede estar vacío");
                return;
            }

            Equipo nuevoEquipo = new Equipo(nombreEquipo);
            equipos.add(nuevoEquipo);
            System.out.println("Su Equipo ha sido registrado exitosamente");
        }
        //Metodo para registrar jugador
        private void registrarJugador() {
            if (equipos.isEmpty()) {
                System.out.println("Error: Debe registrar al menos un equipo primero");
                return;
            }

            System.out.println("\n=== REGISTRAR JUGADOR ===");
            System.out.print("Ingrese el nombre del jugador: ");
            String nombre = scanner.nextLine().trim();

            System.out.print("Ingrese el ranking del jugador: ");
            int ranking = leerOpcion();

            // Mostrar equipos disponibles
            System.out.println("\nEquipos disponibles:");
            for (int i = 0; i < equipos.size(); i++) {
                System.out.println((i + 1) + ". " + equipos.get(i).getNombre());
            }

            System.out.print("Seleccione el número del equipo: ");
            int equipoIndex = leerOpcion() - 1;

            if (equipoIndex >= 0 && equipoIndex < equipos.size()) {
                Equipo equipoSeleccionado = equipos.get(equipoIndex);
                Jugador nuevoJugador = new Jugador(nombre, ranking, equipoSeleccionado);
                jugadores.add(nuevoJugador);
                equipoSeleccionado.agregarJugador(nuevoJugador);
                System.out.println("Jugador registrado exitosamente");
            } else {
                System.out.println("Selección de equipo inválida");
            }
        }
        private void seleccionarModalidadTorneo() {
            if (equipos.size() < 4) {
                System.out.println("Error: Se necesitan al menos 4 equipos para iniciar un torneo");
                return;
            }

            System.out.println("\n=== MODALIDAD DE TORNEO ===");
            System.out.println("1. Eliminación Directa");
            System.out.println("   Los equipos juegan rondas eliminatorias hasta que quede un ganador");
            System.out.println("2. Liga");
            System.out.println("   Cada equipo juega contra todos los demás y se acumulan puntos");
            System.out.println("3. Doble Eliminación");
            System.out.println("   Un equipo debe perder dos veces para quedar eliminado");

            int modalidad = leerOpcion();
            Torneo torneo = null;
            switch (modalidad) {
                case 1:
                    torneo = new TorneoEliminacionDirecta(equipos);
                    break;
                case 2:
                    torneo = new TorneoLiga(equipos);
                    break;
                case 3:
                    torneo = new TorneoDobleEliminacion(equipos);
                    break;
                default:
                    System.out.println("Modalidad no válida.");
                    return;}
            torneo.iniciarTorneo();
        }


        //Metodo para ver los equipos que han sido registrados y ver cuantos jugadores hay por equipo
        private void verEquiposRegistrados() {
            System.out.println("\n=== PUNTUACIÓN ACTUAL ===");
            if (equipos.isEmpty()) {
                System.out.println("No hay equipos registrados");
                return;
            }

            for (Equipo equipo : equipos) {
                System.out.println("Equipo: " + equipo.getNombre());
                System.out.println("Cantidad de jugadores: " + equipo.getCantidadJugadores());
                System.out.println("------------------------");
            }
        }

    }
