package controlador;

import modelo.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GestorPartida {

    private Partida partida;
    private GestorBaseDatos gestorBD;
    private Scanner scanner;

    public GestorPartida() {
        this.gestorBD = new GestorBaseDatos();
        this.scanner  = new Scanner(System.in);
    }


    public void iniciar() {
        System.out.println("JUEGO DEL PINGÜINO");
        System.out.println("1. Nueva partida");
        System.out.println("2. Cargar partida guardada");
        System.out.print("Elige opción: ");

        int opcion = leerEntero();
        if (opcion == 2) {
            partida = gestorBD.cargarPartida();
            if (partida == null) {
                System.out.println("No se pudo cargar. Iniciando nueva partida...");
                partida = nuevaPartida();
            }
        } else {
            partida = nuevaPartida();
        }

        partida.getTablero().mostrar();
        bucleJuego();
    }

    private Partida nuevaPartida() {
        System.out.print("Número de jugadores (2-4): ");
        int numJugadores = Math.min(4, Math.max(2, leerEntero()));

        ArrayList<Jugador> jugadores = new ArrayList<>();
        String[] colores = {"Azul", "Rojo", "Verde", "Amarillo"};

        for (int i = 0; i < numJugadores; i++) {
            System.out.print("Nombre del jugador " + (i + 1) + ": ");
            String nombre = scanner.nextLine().trim();
            if (nombre.isEmpty()) nombre = "Jugador" + (i + 1);
            jugadores.add(new Pinguino(nombre, colores[i]));
        }

        return new Partida(new Tablero(), jugadores);
    }


    private void bucleJuego() {
        while (!partida.isFinalizada()) {
            Jugador jugadorActual = partida.getJugadorActual();

            System.out.println("\n-------------------------");
            System.out.println("TURNO DE: " + jugadorActual.getNombre()
                    + " (" + jugadorActual.getColor() + ")");
            System.out.println("Posición: " + jugadorActual.getPosicion()
                    + " — " + partida.getTablero().getCasilla(jugadorActual.getPosicion()).getTipo());
            System.out.println(jugadorActual.getInventario());
            System.out.println("----------------------------");
            System.out.println("1. Tirar dado normal");
            System.out.println("2. Usar dado especial del inventario");
            System.out.println("3. Lanzar bola de nieve a otro jugador");
            System.out.println("4. Guardar partida");
            System.out.println("5. Ver tablero y posiciones");
            System.out.print("Acción: ");

            int accion = leerEntero();
            boolean cambiaTurno = true;

            switch (accion) {
                case 1: tirarDadoNormal(jugadorActual);       break;
                case 2: usarDadoEspecial(jugadorActual);      break;
                case 3: lanzarBolaNieve(jugadorActual);        break;
                case 4:
                    gestorBD.guardarPartida(partida);
                    cambiaTurno = false;
                    break;
                case 5:
                    partida.getTablero().mostrar();
                    mostrarPosiciones();
                    cambiaTurno = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
                    cambiaTurno = false;
            }

            if (cambiaTurno && !partida.isFinalizada()) {
                partida.siguienteTurno();
            }
        }

        System.out.println("\n🎉🎉🎉 ¡VICTORIA! El ganador es: "
                + partida.getGanador().getNombre()
                + " (" + partida.getGanador().getColor() + ") 🎉🎉🎉");
    }


    private void tirarDadoNormal(Jugador jugador) {
        Dado dado = new Dado(TipoDado.NORMAL);
        int valor = dado.tirar();
        System.out.println(jugador.getNombre() + " tira el dado normal → " + valor);
        moverJugador(jugador, valor);
    }

    private void usarDadoEspecial(Jugador jugador) {
        Inventario inv = jugador.getInventario();
        if (inv.getDados() <= 1) {
            System.out.println("No tienes dados especiales. Se usa el dado normal.");
            tirarDadoNormal(jugador);
            return;
        }
        System.out.println("Dados disponibles:");
        for (int i = 0; i < inv.getListaDados().size(); i++) {
            System.out.println("  " + i + ": " + inv.getListaDados().get(i));
        }
        System.out.print("¿Qué dado quieres usar? (índice): ");
        int idx = leerEntero();
        Dado dado = inv.getDado(idx);
        int valor = dado.tirar();
        System.out.println(jugador.getNombre() + " usa " + dado + " → " + valor);
        if (idx > 0) inv.eliminarDado(idx); 
        moverJugador(jugador, valor);
    }

    private void lanzarBolaNieve(Jugador jugador) {
        Inventario inv = jugador.getInventario();
        if (inv.getBolasDenieve() == 0) {
            System.out.println("¡No tienes bolas de nieve!");
            return;
        }
        ArrayList<Jugador> otros = new ArrayList<>();
        for (Jugador j : partida.getJugadores()) {
            if (j != jugador) otros.add(j);
        }
        System.out.println("¿A quién lanzas la bola de nieve?");
        for (int i = 0; i < otros.size(); i++) {
            System.out.println("  " + i + ": " + otros.get(i).getNombre()
                    + " (posición " + otros.get(i).getPosicion() + ")");
        }
        System.out.print("Jugador objetivo: ");
        int idx = leerEntero();
        if (idx >= 0 && idx < otros.size()) {
            Jugador objetivo = otros.get(idx);
            int retroceso = new Random().nextInt(3) + 1; 
            inv.usarBolaNieve();
            objetivo.moverPosicion(-retroceso);
            System.out.println(jugador.getNombre() + " lanza una bola de nieve a "
                    + objetivo.getNombre() + "! Retrocede " + retroceso
                    + " casillas. Nueva posición: " + objetivo.getPosicion());
        } else {
            System.out.println("Selección no válida.");
        }
    }

    private void moverJugador(Jugador jugador, int pasos) {
        jugador.moverPosicion(pasos);
        System.out.println(jugador.getNombre() + " avanza a la casilla " + jugador.getPosicion());
        Casilla casilla = partida.getTablero().getCasilla(jugador.getPosicion());
        casilla.accion(jugador, partida);
        partida.comprobarVictoria(jugador);
    }

    private void mostrarPosiciones() {
        System.out.println("\nPosiciones actuales:");
        for (Jugador j : partida.getJugadores()) {
            System.out.println("  " + j.getNombre() + " → casilla " + j.getPosicion()
                    + " (" + partida.getTablero().getCasilla(j.getPosicion()).getTipo() + ")");
        }
    }


    private int leerEntero() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (Exception e) {
            return -1;
        }
    }
}
