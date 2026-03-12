package CONTROLADOR;

import MODELO.Foca;
import MODELO.Jugador;
import MODELO.Pingüino;
import MODELO.Tablero;

public class GestorJugador {
	public void jugadorUsaItem(String nombreItem) {
		
	}
	public void jugadorSeMueve(Jugador j, int pasos, Tablero t) {
	jugador.mover(pasos);
	
		System.out.println(jugador.getNombre() + 
				" se mueve a la posición " + jugador.getPosicion());
		
				Casilla casillaActual = tablero.obtenerCasilla(jugador.getPosicion());

        if (casillaActual != null) {
            casillaActual.realizarAccion(null, jugador);
		}
	}
	public void finalizaTurno(Jugador j) {
	 System.out.println("Finaliza el turno de " + jugador.getNombre());
	}
	public void pingüinoEvento(Pingüino p) {
	System.out.println(System.out.println("Evento especial para el pinguïno + p.getNombre"));
	}

	public void pingüinoGuerra(Pingüino p1, Pingüino p2) {
	System.out.println(p1.getNombre() + " entra en guerra con" + p2.getNombre);
	
	if(math.random()>0.5){
		System.out.println(p1.getNombre() + "gana la guerra");
	} else {
		System.out.println(p2.getNombre() + "gana la guerra");
	}
	}
	public void focaInteractua(Pingüino p, Foca f) {
		System.out.println("La doca interactúa con el pingüino" + p.getNombre)
		f.setSomnoliento(true);
	}
}
