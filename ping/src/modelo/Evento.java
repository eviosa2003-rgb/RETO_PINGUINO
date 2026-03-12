package modelo;


import java.util.Random;


public class Evento {

    private TipoEvento tipo;
    private static final Random random = new Random();

    public Evento(TipoEvento tipo) {
        this.tipo = tipo;
    }

    public TipoEvento getTipo() { return tipo; }

  
    public void aplicar(Jugador jugador) {
        Inventario inv = jugador.getInventario();
        switch (tipo) {
            case OBTENER_PEZ:
                if (inv.getPeces() < Inventario.MAX_PECES) {
                    inv.agregarPez();
                    System.out.println("¡Evento! " + jugador.getNombre() + " obtiene un PEZ. Total peces: " + inv.getPeces());
                } else {
                    System.out.println("¡Evento! Inventario de peces lleno (" + Inventario.MAX_PECES + " máximo).");
                }
                break;

            case OBTENER_BOLAS_NIEVE:
                int cantidad = random.nextInt(3) + 1; 
                int agregadas = 0;
                for (int i = 0; i < cantidad; i++) {
                    if (inv.agregarBolaNieve()) agregadas++;
                }
                System.out.println("¡Evento! " + jugador.getNombre() + " obtiene " + agregadas + " bola(s) de nieve. Total: " + inv.getBolasDenieve());
                break;

            case OBTENER_DADO_RAPIDO:
                if (inv.getDados() < Inventario.MAX_DADOS) {
                    inv.agregarDado(new Dado(TipoDado.RAPIDO));
                    System.out.println("¡Evento! " + jugador.getNombre() + " obtiene un DADO RÁPIDO (avanza 5-10). Total dados: " + inv.getDados());
                } else {
                    System.out.println("¡Evento! Inventario de dados lleno (" + Inventario.MAX_DADOS + " máximo).");
                }
                break;

            case OBTENER_DADO_LENTO:
                if (inv.getDados() < Inventario.MAX_DADOS) {
                    inv.agregarDado(new Dado(TipoDado.LENTO));
                    System.out.println("¡Evento! " + jugador.getNombre() + " obtiene un DADO LENTO (valores 1-3). Total dados: " + inv.getDados());
                } else {
                    System.out.println("¡Evento! Inventario de dados lleno (" + Inventario.MAX_DADOS + " máximo).");
                }
                break;
        }
    }

    @Override
    public String toString() {
        return "Evento[" + tipo + "]";
    }
}
