package MODELO;

public class DadoLento extends Dado{
	public DadoLento ( String nombre, int cantidad) {
		super(3, 1, nombre, cantidad);
	}
	@Override
	public int tirar() {
		int resultado = super.tirar();
		return resultado;
	}
}
