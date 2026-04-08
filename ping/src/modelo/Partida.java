package modelo;

import java.util.ArrayList;

/* Esta clase gestionara el desarollo de la partida, contiene tablero, jugadores, turno, el estado de la partida y el ganador.
Tambien tenemos siguiente turno para que cambia de turno de jugador, que avance a la meta y cuando llegue que diga el ganador.
Tambien se podra ver la informacion del jugador, participantes, tablero yy si la partida ha finalizado. */




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
