package MODELO;
import java.util.ArrayList;
import java.util.Random;

public class Tablero {
	 private ArrayList<Casilla> casillas;
	 private static final int TOTAL_CASILLAS = 50;
	 
	 public Tablero() {
		 casillas = new ArrayList<>();
		 generarTableroAleatorio();
	 }
	 
	 private void generarTableroAleatorio() {
		 Random r = new Random();
		 for (int i = 0; i < TOTAL_CASILLAS; i++) {
			 String tipo;
			 
			 if(i == 0 || i == TOTAL_CASILLAS - 1) {
				 tipo = "NORMAL";
			 }else {	               	       
		          	int prob = r.nextInt(100);

		          	if (prob < 10) tipo = "OSO";
		          	else if (prob < 20) tipo = "FORAT";
		          	else if (prob < 30) tipo = "TRINEU";
		           	else if (prob < 40) tipo = "INTERROGANT";
		           	else tipo = "NORMAL";
		            }
			 
			 }
		 }
	 public Casilla getCasilla(int pos) {
		 if(pos >= TOTAL_CASILLAS) pos = TOTAL_CASILLAS - 1;
		 return casillas.get(pos);
	 }
	 
	 public int getMeta() {
		 return TOTAL_CASILLAS - 1;
	 }
}

