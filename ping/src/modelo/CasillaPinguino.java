package modelo;

/* Es una clase que cuando caes en ella no ocurre ningun efecto especial.
En el void simplemente muestra un mensaje indicando que el jugador esta en esa casilla, pero su posicion no cambia. */

public class CasillaPinguino extends Casilla {

    public CasillaPinguino(int numero) {
        super(numero, "Pinguino");
    }

    @Override
    public void accion(Jugador jugador, Partida partida) {
        System.out.println(jugador.getNombre() + " está en la casilla " + getNumero() + " (Pingüino). Sin efecto.");
    }
}
