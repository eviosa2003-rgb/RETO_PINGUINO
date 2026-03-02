import java.util.Random;

public class SueloQuebradizo extends Casilla {

    private double probabilidadCaer; // 0.0 - 1.0
    private int retroceso;           // posiciones que retrocede si cae
    private Random random;

    public SueloQuebradizo(int posicion, double probabilidadCaer, int retroceso) {
        super(posicion);
        this.probabilidadCaer = probabilidadCaer;
        this.retroceso = retroceso;
        this.random = new Random();
    }

    @Override
    public void realizarAccion(Partida partida, Jugador jugador) {

        double valor = random.nextDouble();

        if (valor < probabilidadCaer) {
            System.out.println("¡El suelo se rompe! " + jugador.getNombre() + " cae.");

            // Retrocede posiciones
            jugador.moverPosicion(-retroceso);

            if (jugador.getPosicion() < 0) {
                jugador.moverPosicion(-jugador.getPosicion());
            }

        } else {
            System.out.println("El suelo aguanta. No pasa nada.");
        }
    }
}