package clases;

public abstract class Block implements Comparable<Block> {

    protected String name;
    protected float hardness;

    public Block(String name, float hardness) {
        this.name = name;
        this.hardness = hardness;
    }

    // Getter para name
    public String getName() {
        return name;
    }

    // Getter para hardness
    public float getHardness() {
        return hardness;
    }

    // Setter opcional si quieres modificar dureza
    public void setHardness(float hardness) {
        this.hardness = hardness;
    }

    // 1. Método abstracto
    public abstract boolean canBreakByHand();

    // 7. compareTo por dureza
    @Override
    public int compareTo(Block other) {
        return Float.compare(this.hardness, other.hardness);
    }

    @Override
    public String toString() {
        return name + " (hardness: " + hardness + ")";
    }
}

