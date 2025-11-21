package ejerciciosEntregar;

public class ArrayDeObjetos {
	
		

		public static void main(String[] args) {
			// TODO Auto-generated method stub

			Articulo [] inventario = new Articulo [4];
			inventario [0] = new Articulo (3.40,"pera", 7);
			inventario [1] = new Articulo (2.60, "manzana", 6);
			inventario [2] = new Articulo (1.20, "fresa", 0);
			inventario [3] = new Articulo (0.60,"melon",9);
			
			double total = 0; 
			for (int i = 0; i < inventario.length; i++)
				if(inventario[i].getStock()%2 !=0) {
					total = total + (inventario[i].getPrecio());
				}
					
						System.out.println(total);
						
			String producto = "";			
			int stock = 0;			
			double precio = 0;
			Articulo articulo;
			for (int i = 0; i < inventario.length; i++) {
				if (inventario[i].getPrecio() > precio && inventario[i].getStock() > 5) {
					precio = inventario[i].getPrecio();
					stock = inventario[i].getStock();
					producto = inventario[i].getProducto();
					
					articulo=inventario[i];
				}
			}
			System.out.println (producto);
			
			
			int pos = 0;
			for(int i = 0; i < inventario.length; i++) {
						
				if (inventario[i] != null && inventario[i].getPrecio() !=0 && inventario[i].getStock() !=0 && inventario[i].getProducto() !=null) {
					inventario [pos++] = inventario[i];
							
			}
		}
			
					for (int i = 0; i < pos; i++) {
					    System.out.println(inventario[i]);
			
					}
	       
			
		}
	
	}

