package modelo;


public class Oso extends Casilla {

    public Oso(int numero) {
        super(numero, "Oso");
    }

    @Override
    public void accion(Jugador jugador, Partida partida) {
        System.out.println("¡Un OSO ataca a " + jugador.getNombre() + "!");
        if (jugador.getInventario().getPeces() > 0) {
            jugador.getInventario().usarPez();
            System.out.println(jugador.getNombre() + " soborna al oso con un pez. ¡Se salva! Peces restantes: "
                    + jugador.getInventario().getPeces());
        } else {
            System.out.println(jugador.getNombre() + " no tiene peces. ¡Vuelve al inicio!");
            jugador.setPosicion(0);
        }
    }
}
