package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Tablero {

    public static final int TOTAL_CASILLAS = 50;

    private ArrayList<Casilla> casillas;

    public Tablero() {
        casillas = new ArrayList<>();
        generarTableroAleatorio();
    }

    private void generarTableroAleatorio() {
        ArrayList<String> tipos = new ArrayList<>();
        for (int i = 0; i < 25; i++) tipos.add("PINGUINO");
        for (int i = 0; i < 5;  i++) tipos.add("OSO");
        for (int i = 0; i < 6;  i++) tipos.add("AGUJERO");
        for (int i = 0; i < 6;  i++) tipos.add("TRINEO");
        for (int i = 0; i < 8;  i++) tipos.add("INTERROGANTE");

        Collections.shuffle(tipos, new Random());

        for (int i = 0; i < TOTAL_CASILLAS; i++) {
            String tipo = (i == 0 || i == TOTAL_CASILLAS - 1) ? "PINGUINO" : tipos.get(i);
            casillas.add(crearCasilla(i, tipo));
        }
    }

    private Casilla crearCasilla(int numero, String tipo) {
        switch (tipo) {
            case "OSO":          return new Oso(numero);
            case "AGUJERO":      return new Agujero(numero);
            case "TRINEO":       return new Trineo(numero);
            case "INTERROGANTE": return new CasillaInterrogante(numero);
            default:             return new CasillaPinguino(numero);
        }
    }

    public Casilla getCasilla(int numero) {
        if (numero < 0)                  return casillas.get(0);
        if (numero >= TOTAL_CASILLAS)    return casillas.get(TOTAL_CASILLAS - 1);
        return casillas.get(numero);
    }

    public void mostrar() {
        System.out.println("\n========== TABLERO (50 casillas) ==========");
        System.out.println("P=Pingüino | O=Oso | A=Agujero | T=Trineo | ?=Interrogante");
        for (int i = 0; i < TOTAL_CASILLAS; i++) {
            System.out.print(casillas.get(i).toString() + " ");
            if ((i + 1) % 10 == 0) System.out.println();
        }
        System.out.println("===========================================");
    }
}
