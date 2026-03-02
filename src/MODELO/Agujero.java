public class Agujero extends Casilla {

    private int posicionInicio; // normalmente 0

    public Agujero(int posicion, int posicionInicio) {
        super(posicion);
        this.posicionInicio = posicionInicio;
    }

    @Override
    public void realizarAccion(Partida partida, Jugador jugador) {

        System.out.println(jugador.getNombre() + " ha caído en un agujero 🕳");

        if (jugador instanceof Pinguino) {

            jugador.setPosicion(posicionInicio);
            System.out.println("El pingüino vuelve al inicio.");

        } else if (jugador instanceof Foca) {

            System.out.println("La foca es demasiado pesada y no cae.");
        }
    }
}