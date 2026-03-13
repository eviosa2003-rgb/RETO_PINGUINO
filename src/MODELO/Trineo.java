package MODELO;

public class Trineo extends Casilla {

	public Trineo( int posicion) {
		this.posicion = posicion;
	}

	public void realizarAccion(Partida partida, Jugador jugador){
		int siguiente = partida.getTablero().getPosicionSiguienteTrineo(jugador.getPosicion());
		System.out.println(jugador.getNombre()+ "coge el trineo y avanza a la casilla" + siguiente);
	}
	public String getTipo() {
		return "Trineo";
	}
}
