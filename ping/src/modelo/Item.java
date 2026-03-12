package modelo;


public abstract class Item {

    private String nombre;

    public Item(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() { return nombre; }

    @Override
    public String toString() { return nombre; }
}
