/**
 * CLASE BASE DE LA CUAL HEREDAN TODOS LOS ITEMS DEL JUEGO
 */

public class Item {
    private String nombre;
    private int cantidad;

    public Item(String nombre, int cantidad){
        this.nombre = nombre;
        this.cantidad = cantidad;
    }
    public String getNombre(){
        return nombre;
    }
    public cantidad getCantidad(){
        return cantidad;
    }

    //USO DE if  PARA CONTROLARA QUE LA CANTIDAD AGREGADA ES MAYOR O IGUAL A 0 
    public void setCantidad(int cantidad){
        if(cantidad >= 0) this.cantidad = cantidad;
    }

    @Override
    public String toString(){
        return nombre +": " + cantidad;
    }
}