package MODELO;

public class Partida {

    private Tablero tablero;
    private ArrayList<Jugador> jugadores;
    private int turnos;
    private Jugador jugadorActual;
    private boolean finalizada;
    private Jugador ganador;

    public Partida() {}

    public Jugador getJugadorActual() {
        return jugadorActual;
    }
}

