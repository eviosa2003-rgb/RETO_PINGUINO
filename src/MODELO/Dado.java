package MODELO;
import java.util.Random;

public class Dado extends Item {
	private int max;
	private int min;
	
	public Dado(, int max, int min) {
		this.min = min;
        this.max = max;
        random = new Random();
    }

    public int tirar() {
        return random.nextInt(max - min + 1) + min;
    }
}

