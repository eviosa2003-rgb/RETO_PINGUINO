package MODELO;

public abstract class Jugador {
	private int posicion;
	private String nombre;
	private String color;
	
	public Jugador(int posicion, String nombre, String color) {
		this.posicion = posicion;
		this.nombre = nombre;
		this.color = color;
	}
	public void moverPosicion(int p) {
		 this.posicion += p;
	}
	public void siguienteTurno() {
        turnoActual = (turnoActual + 1) % jugadores.size();
    }
}


