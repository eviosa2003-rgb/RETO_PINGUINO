package MODELO;

public abstract class Casilla {
	private int posicion;
	
	public Casilla(int posicion) {
		this.posicion = posicion;
	}
	
	public int getPosicion() {
		return posicion;
	}
	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}
	
	public void realizarAccion(Partida partida, Jugador jugador) {
		
		
	}
}
