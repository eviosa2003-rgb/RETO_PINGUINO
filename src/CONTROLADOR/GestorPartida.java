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
	

}
