package MODELO;

import java.util.ArrayList;

public class Inventario {
	private ArrayList<Item> items;
	
	public Inventario() {
		items = new ArrayList<>();
	}

    public void agregarItem(Item item) {
        for (Item i : items){
              if (i.getNombre().equalsIgnoreCase(item.getNombre())) {
                i.setCantidad(i.getCantidad() + item.getCantidad());
                return;
              }
        }
        items.add(item);
    }


	 public boolean usarItem(String nombre) {

     for (Item i : items) {
        if (i.getNombre().equalsIgnoreCase(nombre) && i.getCantidad() > 0) {
              i.setCantidad(i.getCantidad() - 1);
              return true;
            }
        }
        return false;
    }

    public ArrayList<Item> getItems() {
        return items;
    }
}


