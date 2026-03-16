package modelo;

import java.util.Random;

/*Esta clase es sobre una casilla especial, cuando un jugador cae en ella se activa in evento aleatorio.
Luego en el void de accion generamos un evento al azar que puede dar al jugador diferentes recompensas,
como peces, bolas de nieve o dados especiales. */


public class CasillaInterrogante extends Casilla {

    private static final Random random = new Random();

    public CasillaInterrogante(int numero) {
        super(numero, "Interrogante");
    }

    @Override
    public void accion(Jugador jugador, Partida partida) {
        System.out.println("¡" + jugador.getNombre() + " cae en una casilla INTERROGANTE! Evento aleatorio...");
        Evento evento = generarEventoAleatorio();
        evento.aplicar(jugador);
    }

 
    private Evento generarEventoAleatorio() {
        int valor = random.nextInt(100);
        if (valor < 25)      return new Evento(TipoEvento.OBTENER_PEZ);
        else if (valor < 65) return new Evento(TipoEvento.OBTENER_BOLAS_NIEVE);
        else if (valor < 75) return new Evento(TipoEvento.OBTENER_DADO_RAPIDO);  
        else                 return new Evento(TipoEvento.OBTENER_DADO_LENTO);   
    }
}
