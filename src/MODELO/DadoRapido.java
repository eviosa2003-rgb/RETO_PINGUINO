package MODELO;

public class DadoRapido extends Dado{
	public DadoRapido ( String nombre, int cantidad) {
		super(10, 6, nombre, cantidad);
	}
	@Override
	public int tirar() {
		int resultado = super.tirar();
		return resultado;
	}
}
