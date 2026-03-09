package MODELO;
import java.util.Random;

public abstract class Dado extends Item {
	private int max;
	private int min;
	private Random random;
	
	public Dado (int max, int min, String nombre, int cantidad) {
		super (nombre, cantidad);
		this.min = min;
        this.max = max;
        this.random = new Random();
    }
	
    public int tirar() {
        return random.nextInt(max - min + 1) + min;
    }
}

