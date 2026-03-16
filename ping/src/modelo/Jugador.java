package modelo;

/* Aqui vemos la clase de jugador donde se mostrara la posicion en el tablero, el nombre, el color y el inventario.
Ademas hemos hecho que se pueda mover de posicion, obtener sus datos y controlar que no salga de los limites.  */


public abstract class Jugador {

    private int posicion;
    private String nombre;
    private String color;
    private Inventario inventario;

    public Jugador(int posicion, String nombre, String color) {
        this.posicion = posicion;
        this.nombre = nombre;
        this.color = color;
        this.inventario = new Inventario();
    }

   
    public void moverPosicion(int p) {
        this.posicion += p;
        if (this.posicion >= Tablero.TOTAL_CASILLAS) {
            this.posicion = Tablero.TOTAL_CASILLAS - 1;
        }
        if (this.posicion < 0) {
            this.posicion = 0;
        }
    }

    public int getPosicion()           { return posicion; }
    public String getNombre()          { return nombre; }
    public String getColor()           { return color; }
    public Inventario getInventario()  { return inventario; }

    public void setPosicion(int posicion) {
        if (posicion < 0) posicion = 0;
        if (posicion >= Tablero.TOTAL_CASILLAS) posicion = Tablero.TOTAL_CASILLAS - 1;
        this.posicion = posicion;
    }

    @Override
    public String toString() {
        return nombre + " [" + color + "] pos:" + posicion + " " + inventario;
    }
}
