package LSClasses;

import Auxiliary.Coord;
import Display.Box;

public abstract class Item
{
    public Coord coord;
    public Box box;
    public int number;
    private static int lastNumber = 0;
    public boolean isValid = true;
    public boolean isFreeField;

    protected abstract Item interact(Item item);

    public Item(int x, int y)
    {
        lastNumber++;
        number = lastNumber;
        coord = new Coord(x, y);
    }

    final public Item doSomething(Item item)
    {
        remoteNeeds();
        Item newItem  = interact(item);
        return newItem;
    }

    protected void remoteNeeds() {}
    public boolean isAlive()
    {
        return true;
    }
}
