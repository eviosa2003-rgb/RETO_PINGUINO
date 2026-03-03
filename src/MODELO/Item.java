package MODELO;
/*
 * CLASE ABSTRACTA ITEM
 * CONSTRUCTORES 
 * GETTERS Y SETTERS 
 */
public abstract class Item {
	private String nombre;
	private int cantidad;
	
	public Item(String nombre, int cantidad) {
		this.nombre = nombre;
		this.cantidad = cantidad;
	}
	public String getNombre() {
		return nombre;
	}
	public int getCantidad(){
		return cantidad;
	}
	public void setCantidad(int cantidad){
		this.cantidad = cantidad;
	}
	public abstract void aplicarEfecto (Jugador jugador);
}