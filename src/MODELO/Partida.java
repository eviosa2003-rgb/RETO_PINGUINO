package MODELO;

public class Partida {

    private Tablero tablero;
    private ArrayList<Jugador> jugadores;
    private int turnos;
    private Jugador jugadorActual;
    private boolean finalizada;
    private Jugador ganador;

     public Partida(Tablero tablero, ArrayList<Jugador> jugadores) {
        this.tablero = tablero;
        this.jugadores = jugadores;
        this.turnoActual = 0;
    }
    
    public void siguienteTurno() {
        turnoActual = (turnoActual + 1) % jugadores.size();
    }

    public Jugador getJugadorActual() {
        return jugadores.get(turnoActual);
    }
}

