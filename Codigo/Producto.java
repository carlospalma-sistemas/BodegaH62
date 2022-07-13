public class Producto 
{
    private String tipo;
    private String nombre;
    private String marca;
    private String presentacion;
    private String codigo;
    private int precio;
    private int cantidad;

    public Producto() 
    {
        this.tipo = "";
        this.nombre = "";
        this.marca = "";
        this.presentacion = "";
        this.codigo = "";
        this.precio = 0;
        this.cantidad = 0;
    }

    public Producto(String tipo, String nombre, String marca, String presentacion, String codigo, int precio, int cantidad) 
    {
        this.tipo = tipo;
        this.nombre = nombre;
        this.marca = marca;
        this.presentacion = presentacion;
        this.codigo = codigo;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public void setPrecio(int precio) 
    {
        this.precio = precio;
    }

    public void setCantidad(int cantidad) 
    {
        this.cantidad = cantidad;
    }

    public String getTipo() 
    {
        return this.tipo;
    }

    public String getNombre() 
    {
        return this.nombre;
    }

    public String getMarca() 
    {
        return this.marca;
    }

    public String getPresentacion() 
    {
        return this.presentacion;
    }

    public String getCodigo() 
    {
        return this.codigo;
    }

    public int getPrecio() 
    {
        return this.precio;
    }

    public int getCantidad() 
    {
        return this.cantidad;
    }

    public String toString()
    {
        return "Producto cod. "+this.codigo+": "+this.nombre+" Marca "+this.marca + " - "+this.presentacion;
    }
    
}



