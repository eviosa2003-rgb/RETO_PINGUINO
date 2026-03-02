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
	
}