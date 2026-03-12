package modelo;


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
