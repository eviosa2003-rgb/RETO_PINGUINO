package MODELO;

import java.util.ArrayList;

public class Inventario {
	public static final int MAX_DADOS = 3;
	public static final int MAX_PECES = 2;
	public static final int MAX_BOLANIEVE = 6;
	
	private ArrayList<Item>items;
	private ArrayList<Dado>dados;
	
	private int peces;
	private int bolasNieve;
	
	public Inventario() {
		items = new ArrayList<>();
		dados = new ArrayList<>();
		peces = 0;
		bolasNieve = 0;	
	}
	public void agregarItem(Item item) {
		for(Item i : items) {
			if(i.getNombre().equalsIgnoreCase(item.getNombre())) {
				i.setCantidad(i.getCantidad() + item.getCantidad());
				return;
			}
		}
		items.add(item);
	}
	public boolean usarItem(String nombre) {
		for(Item i : items) {
			if(i.getNombre().equalsIgnoreCase(nombre) && i.getCantidad() > 0) {
				i.setCantidad(i.getCantidad() - 1);
				return true;
			}
		}
		return false;
	}
	public ArrayList<Item> getItems(){
		return items;
	}
	
	public int getDados() {
		return dados.size();
	}
	public ArrayList<Dado> getListaDados(){
		return dados;
	}
	public Dado getDedo(int indice) {
		if(indice >= 0 && indice < dados.size())
			return dados.get(indice);
		return dados.get(0);
	}
	public boolean agregarDado(Dado d) {
		if(dados.size() < MAX_DADOS) {
			dados.add(d);
			return true;
		}
		return false;
	}
	public boolean eliminarDado(int indice) {
		if (indice > 0 && indice < dados.size()) {
			dados.remove(indice);
			return true;
		}
		return false;
	}
	public int getPeces() {
		return peces;
	}
	public boolean agregarPez() {
		if (peces < MAX_PECES) {
			peces++;
			return true;
		}
		return false;
	}
	public boolean usarPez() {
		if (peces > 0) {
			peces--;
			return true;
		}
		return false;
	}
	public int getBolasNieve() {
		return bolasNieve;
	}
	public boolean agregarBolaNieve() {
		if(bolasNieve < MAX_BOLANIEVE) {
			bolasNieve++;
			return true;
		}
		return false;
	}
	public boolean usarBolaNieves() {
		if(bolasNieve > 0) {
			bolasNieve--;
			return true;
		}
		return false;
	}
	@Override
	public String toString() {
		return "Items:" + items.size() +", Dados:" + dados.size() +"Peces:" + peces + "Bolas de nieve:" +bolasNieve;
	}
}


