package principal;



public  class Producto { 

   

	public Producto(String nombre,  int stock) {
		super();
		this.nombre = nombre;
		
		this.stock = stock;
	}





	private String nombre; 
   
    private int stock; 

  

       public String getNombre() {
		return nombre;
	}





	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getStock() {
		return stock;
	}





	public void setStock(int stock) {
		this.stock = stock;
	}



} 