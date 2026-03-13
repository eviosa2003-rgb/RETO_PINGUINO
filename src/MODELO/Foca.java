package MODELO;

public class Foca extends Jugador {
	
    private boolean bloqueada;
    private boolean soborno;
    private int turnosBloqueo;

    public Foca() {
        this.nombre = "Foca";
        this.color = "gris";
        this.posicion = 0;
        this.bloqueada = false;
        this.turnosBloqueo = 0;
    }
     public boolean isBloqueada(){
        return bloqueada;
     }
    
     public void bloquear(){
        this.bloqueada = true;
        this.turnosBloqueo = 2;
     }

     public void reducirBloqueo() {
        if(bloqueada){
            turnosBloqueo --;
            if (turnosBloqueo <= 0) bloqueada = false;
        }
     }
     public void golpearPinguino(Pinguino p, Tablero tablero) {
        int forat = tablero.getPosicionForatAnterior(p.getPosicion());
        p.setPosicion(forat);
        System.out.println("La foca golpea a " + p.getNombre() + " y lo envía al agujero en casilla " + forat);
    }

    public void pasarPorEncima(Pinguino p) {
        int mitad = p.getInventario().totalItems() / 2;
        for (int i = 0; i < mitad; i++) {
            Item item = p.getInventario().getItemAleatorio();
            if (item != null) {
                p.getInventario().quitarItem(item.getNombre());
            }
        }
        System.out.println("La foca pasa por encima de " + p.getNombre() + " y le quita " + mitad + " objetos.");
    }

    @Override
    public void moverEnSesion(Partida partida) {
        if (bloqueada) {
            reducirBloqueo();
            System.out.println("La foca está bloqueada y no se mueve.");
            return;
        }

        Dado dado = new Dado();
        int pasos = dado.tirar();
        int posAnterior = posicion;
        posicion = posicion + pasos;
        if (posicion >= 50) posicion = 49;
        System.out.println("La foca avanza a la casilla " + posicion);

        for (Jugador j : partida.getJugadores()) {
            if (j instanceof Pinguino) {
                Pinguino p = (Pinguino) j;
                // Coincide en la misma casilla
                if (p.getPosicion() == posicion) {
                    golpearPinguino(p, partida.getTablero());
                }
                else if (p.getPosicion() > posAnterior && p.getPosicion() < posicion) {
                    pasarPorEncima(p);
                }
            }
        }
    }
}




