package ejerciciosEntregar;

public class Articulo {
	

		private double precio;
		private String producto;
		private int stock;
		
		

		@Override
		public String toString() {
			return "Articulo [precio=" + precio + ", producto=" + producto + ", stock=" + stock + "]";
		}

		public Articulo(double precio, String producto, int stock) {
			super();
			this.precio = precio;
			this.producto = producto;
			this.stock = stock;
		}

		/**
		 * @return the precio
		 */
		public double getPrecio() {
			return precio;
		}

		/**
		 * @param precio the precio to set
		 */
		public void setPrecio(int precio) {
			this.precio = precio;
		}

		/**
		 * @return the producto
		 */
		public String getProducto() {
			return producto;
		}

		/**
		 * @param producto the producto to set
		 */
		public void setProducto(String producto) {
			this.producto = producto;
		}

		/**
		 * @return the stock
		 */
		public int getStock() {
			return stock;
		}

		/**
		 * @param stock the stock to set
		 */
		public void setStock(int stock) {
			this.stock = stock;
		}

		public Articulo() {
			super();
			// TODO Auto-generated constructor stub
		}}

