package modelo;


import java.util.Random;

/* Usamos un metodo tirar que genera un numero aleatoria del 1 al 6 y uno lento del 1 al 3. 
Luego lo mostramos por pantalla dando el tipo de dado y su rango de valores. */


public class Dado {

    private TipoDado tipo;
    private static final Random random = new Random();

    public Dado(TipoDado tipo) {
        this.tipo = tipo;
    }

  
    public int tirar() {
        switch (tipo) {
            case RAPIDO: return random.nextInt(6) + 5;  
            case LENTO:  return random.nextInt(3) + 1;  
            default:     return random.nextInt(6) + 1;  
        }
    }

    public TipoDado getTipo() { return tipo; }

    @Override
    public String toString() {
        switch (tipo) {
            case RAPIDO: return "Dado Rápido (5-10)";
            case LENTO:  return "Dado Lento (1-3)";
            default:     return "Dado Normal (1-6)";
        }
    }
}
