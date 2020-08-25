package LSClasses;

import Display.Box;

public class Ground extends Item{
    public Ground(int x, int y)
    {
        super(x,y);
        box = Box.GROUND;
        isFreeField = true;
        isValid = false;
    }
    @Override
    protected Item interact(Item item)
    {
        throw new UnsupportedOperationException();
    }
}
