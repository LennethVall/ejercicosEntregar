package clases;

public abstract class Articulo {
    protected String codigo;
    protected String nombre;
    protected String marca;
    protected double precio;
    protected int stock;

    public Articulo(String codigo, String nombre, String marca, double precio, int stock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.stock = stock;
    }

    public void sumarStock(int cantidad) {
        this.stock += cantidad;
    }

    public void quitarStock(int cantidad) {
        if (cantidad <= stock) {
            this.stock -= cantidad;
        }
    }

    public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getCodigo() { return codigo; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }

    public abstract void printCaracteristicas();
    public abstract boolean sano();
}
