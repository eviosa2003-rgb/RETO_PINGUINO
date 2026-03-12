package modelo;



public class Agujero extends Casilla {

    public Agujero(int numero) {
        super(numero, "Agujero");
    }

    @Override
    public void accion(Jugador jugador, Partida partida) {
        System.out.println("¡" + jugador.getNombre() + " cae por un AGUJERO EN EL HIELO!");
        int posActual = jugador.getPosicion();
        int agujeroAnterior = -1;

        for (int i = posActual - 1; i >= 0; i--) {
            if (partida.getTablero().getCasilla(i) instanceof Agujero) {
                agujeroAnterior = i;
                break;
            }
        }

        if (agujeroAnterior >= 0) {
            jugador.setPosicion(agujeroAnterior);
            System.out.println(jugador.getNombre() + " cae al agujero anterior en casilla " + agujeroAnterior + ".");
        } else {
            jugador.setPosicion(0);
            System.out.println("No hay agujero anterior. ¡" + jugador.getNombre() + " vuelve al inicio!");
        }
    }
}
