package MODELO;
/*
 * CLASE ABSTRACTA ITEM
 * CONSTRUCTORES 
 * GETTERS Y SETTERS 
 */
public abstract class Item {
	protected String nombre;
	protected int cantidad;
	
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
	@Override
	public String toString() {
		return nombre;
	}
}	