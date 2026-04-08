package modelo;

import java.util.ArrayList;

/* Esta clas egestiona los objetos que tiene cada jugador durante la partida.
Puedes almacenar dados, peces y bolas de nieve, cada uno con un limite maximo.

Incluye un usar, añadir o eliminar.*/





public class Inventario {

    public static final int MAX_DADOS       = 3;
    public static final int MAX_PECES       = 2;
    public static final int MAX_BOLAS_NIEVE = 6;

    private ArrayList<Dado> dados;
    private int peces;
    private int bolasDenieve;

    public Inventario() {
        this.dados = new ArrayList<>();
        this.peces = 0;
        this.bolasDenieve = 0;
       
        this.dados.add(new Dado(TipoDado.NORMAL));
    }



    public int getDados() { return dados.size(); }
    public ArrayList<Dado> getListaDados() { return dados; }

    public Dado getDado(int indice) {
        if (indice >= 0 && indice < dados.size()) return dados.get(indice);
        return dados.get(0);
    }

    public boolean agregarDado(Dado d) {
        if (dados.size() < MAX_DADOS) {
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

   

    public int getPeces() { return peces; }

    public boolean agregarPez() {
        if (peces < MAX_PECES) { peces++; return true; }
        return false;
    }

    public boolean usarPez() {
        if (peces > 0) { peces--; return true; }
        return false;
    }

   

    public int getBolasDenieve() { return bolasDenieve; }

    public boolean agregarBolaNieve() {
        if (bolasDenieve < MAX_BOLAS_NIEVE) { bolasDenieve++; return true; }
        return false;
    }

    public boolean usarBolaNieve() {
        if (bolasDenieve > 0) { bolasDenieve--; return true; }
        return false;
    }

    @Override
    public String toString() {
        return "Inventario[Dados:" + dados.size() + " Peces:" + peces + " BolasDenieve:" + bolasDenieve + "]";
    }
}
