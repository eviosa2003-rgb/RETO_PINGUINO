package MODELO;

import java.util.ArrayList;

public class Inventario {
	private ArrayList<Item> lista;
	
	public Inventario() {
		items = new ArrayList<>();
	}
	 public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}

