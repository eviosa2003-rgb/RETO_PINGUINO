package CONTROLADOR;
import java.util.ArrayList;
import java.util.Random;

public class GestorPartida {
	private Partida partida;
	private GestorTablero gestorTablero;
	private GestorJugador gestorJugador;
	private GestorBBDD gestorBBDD;
	private Random random;
	
	public GestorPartida(){
		this.gestorTablero = new GestorTablero();
		this.gestorJugador = new GestorJugador();
		this.gestorBBDD = new GestorBBDD();
		this.random = new Random();
	}
	
	public GestorPartida() {
		random = new Random();
	}
	
	public void nuevaPartida(ArrayList<Jugador>jugadores, Tablero tablero) {
	partida = new Partida(tablero, jugadores);
	}
	public int tirarDado (Dado dado){
		return dado.tirar();
	}
	public void siguienteTurno() {
		partida.siguienteTurno();
	}
	public void guardarPartida(){
		gestorBBDD.guardarBBDD(partida);
	}
	public void cargarPartida(int id) {
		partida = gestorBBDD.cargarBBDD(id);
	}
	public Partida getPartida(){
		return partida;
	}
}
