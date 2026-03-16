package modelo;

/* Esta clase es la de trineo, veremos que si un jugador cae en esta casilla, se mueve automaticamente a otra casilla de trineo.
Si no hay mas trineos adelante, el jugador permanecera en la casilla actual. */

public class Trineo extends Casilla {

    public Trineo(int numero) {
        super(numero, "Trineo");
    }

    @Override
    public void accion(Jugador jugador, Partida partida) {
        System.out.println("¡" + jugador.getNombre() + " sube a un TRINEO!");
        int posActual = jugador.getPosicion();
        int siguienteTrineo = -1;

        for (int i = posActual + 1; i < Tablero.TOTAL_CASILLAS; i++) {
            if (partida.getTablero().getCasilla(i) instanceof Trineo) {
                siguienteTrineo = i;
                break;
            }
        }

        if (siguienteTrineo >= 0) {
            jugador.setPosicion(siguienteTrineo);
            System.out.println(jugador.getNombre() + " avanza al siguiente trineo en casilla " + siguienteTrineo + ".");
        } else {
            System.out.println("No hay más trineos. " + jugador.getNombre() + " se queda en casilla " + posActual + ".");
        }
    }
}
