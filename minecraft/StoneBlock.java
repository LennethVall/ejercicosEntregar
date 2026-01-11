package clases;

public class StoneBlock extends Block implements ICollectible {

    public StoneBlock() {
        super("Stone", 1.5f);
    }

    @Override
    public boolean canBreakByHand() {
        return false;
    }

    @Override
    public int getStackSize() {
        return 64;
    }
}
