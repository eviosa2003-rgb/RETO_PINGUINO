package MODELO;

public abstract class Casilla {
	private int posicion;
	private String tipo;
	
	public Casilla(int posicion, String tipo) {
		this.posicion = posicion;
		this.tipo = tipo;
	}
	
	public int getPosicion() {
		return posicion;
	}
	public String getTipo() {
		return tipo;
	}
	public abstract void realizarAccion(Jugador jugador, Partida partida);
			
}
