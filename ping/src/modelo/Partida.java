package modelo;

import java.util.ArrayList;


public class Partida {

    private Tablero tablero;
    private ArrayList<Jugador> jugadores;
    private int turnoActual;       
    private boolean finalizada;
    private Jugador ganador;

    public Partida(Tablero tablero, ArrayList<Jugador> jugadores) {
        this.tablero = tablero;
        this.jugadores = jugadores;
        this.turnoActual = 0;
        this.finalizada = false;
        this.ganador = null;
    }

  
    public void siguienteTurno() {
        turnoActual = (turnoActual + 1) % jugadores.size();
    }

    public void comprobarVictoria(Jugador jugador) {
        if (jugador.getPosicion() >= Tablero.TOTAL_CASILLAS - 1) {
            jugador.setPosicion(Tablero.TOTAL_CASILLAS - 1);
            setGanador(jugador);
        }
    }

    public Jugador getJugadorActual()      { return jugadores.get(turnoActual); }
    public ArrayList<Jugador> getJugadores() { return jugadores; }
    public Tablero getTablero()            { return tablero; }
    public boolean isFinalizada()          { return finalizada; }
    public Jugador getGanador()            { return ganador; }
    public int getTurnoActual()            { return turnoActual; }

    public void setGanador(Jugador ganador) {
        this.ganador = ganador;
        this.finalizada = true;
    }
}
