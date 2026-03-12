package modelo;


public abstract class Casilla {

    private int numero;
    private String tipo;

    public Casilla(int numero, String tipo) {
        this.numero = numero;
        this.tipo = tipo;
    }

    public int getNumero() { return numero; }
    public String getTipo() { return tipo; }

    
    public abstract void accion(Jugador jugador, Partida partida);

    @Override
    public String toString() {
        return "[" + numero + ":" + tipo.charAt(0) + "]";
    }
}
