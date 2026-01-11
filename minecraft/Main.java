package main;

import java.util.*;

import clases.Block;
import clases.StoneBlock;

public class Main {
    public static void main(String[] args) {

        // 4. ArrayList
        ArrayList<Block> inventory = new ArrayList<>();
        inventory.add(new StoneBlock());
        inventory.add(new StoneBlock());

        // 5. HashMap
        HashMap<String, Integer> blocks = new HashMap<>();
        blocks.put("Stone", 32);
        blocks.put("Dirt", 16);

        int cantidadStone = blocks.get("Stone");

        // 6. TreeMap
        TreeMap<String, Integer> sortedBlocks = new TreeMap<>();

        // 8. Ordenar lista
        Collections.sort(inventory);

        // 9. Recorrido for-each mostrando nombre
        for (Block b : inventory) {
            System.out.println("Bloque: " + b.getName());
        }

        // 10. Contar elementos
        int total = inventory.size();
        System.out.println("Total bloques: " + total);

        // 11. Borrar posición 0
        if (!inventory.isEmpty()) {
            inventory.remove(0);
        }

        // 12. Cambiar dureza a 2.0 usando setter
        for (Block b : inventory) {
            b.setHardness(2.0f);
        }

        // 13. Recorrer HashMap
        for (String key : blocks.keySet()) {
            System.out.println("Bloque: " + key + " - Cantidad: " + blocks.get(key));
        }

        // 14. Añadir 10 Stone
        blocks.put("Stone", blocks.get("Stone") + 10);

        // 15. Borrar Dirt
        blocks.remove("Dirt"); // si no existe, no pasa nada

        // 16. Recorrer TreeMap
        for (String key : sortedBlocks.keySet()) {
            System.out.println(key + " = " + sortedBlocks.get(key));
        }

        // 18. Borrado condicional con Iterator
        Iterator<Block> it = inventory.iterator();
        while (it.hasNext()) {
            if (it.next().getHardness() < 1.0f) {
                it.remove();
            }
        }

        // 19. Ejemplo de Iterator
        Iterator<Block> it2 = inventory.iterator();
        while (it2.hasNext()) {
            Block b = it2.next();
            if (b.getHardness() < 1.0f) {
                it2.remove(); // evita ConcurrentModificationException
            }
        }

        // 20. Recorrido combinado
        TreeMap<String, ArrayList<Block>> mapa = new TreeMap<>();

        for (String tipo : mapa.keySet()) {
            for (Block b : mapa.get(tipo)) {
                System.out.println(b);
            }
        }
    }
}
