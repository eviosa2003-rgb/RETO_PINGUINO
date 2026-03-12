package modelo;



public class CasillaPinguino extends Casilla {

    public CasillaPinguino(int numero) {
        super(numero, "Pinguino");
    }

    @Override
    public void accion(Jugador jugador, Partida partida) {
        System.out.println(jugador.getNombre() + " está en la casilla " + getNumero() + " (Pingüino). Sin efecto.");
    }
}
