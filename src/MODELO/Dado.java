package MODELO;
import java.util.Random;

public class Dado extends Item {
	private int max;
	private int min;
	
	public Dado(String nombre, int cantidad, int max, int min) {
		super(nombre, cantidad);
		this.max = max;
		this.min = min;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public int getMax() {
		return max;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getMin() {
		return min;
	}
	public int tira(Random r) {
		int resultado = r.nextInt((6 - 1) + 1) + 1;
		return r;
	}

}
