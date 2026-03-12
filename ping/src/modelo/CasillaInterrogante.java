package modelo;


import java.util.Random;

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
