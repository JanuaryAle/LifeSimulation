package LSClasses;

import Actions.ReproduceBehaviour;
import Display.Box;

public class Grass extends Item
{
    int age = 7;
    public Grass(int x, int y)
    {
        super(x,y);
        box = Box.GRASS;
        isFreeField = true;
    }
    @Override
    protected Item interact(Item item)
    {
        Item newItem = null;
        age--;
        if (item instanceof Grass) {
            item.remoteNeeds();
            if (ReproduceBehaviour.canReproduce(age, 10) &&
                    (ReproduceBehaviour.canReproduce(((Grass) item).age, 10)))
                newItem = ReproduceBehaviour.reproduce(item);
        }
        return newItem;
    }

    public boolean isAlive()
    {
        if (age > 0) {
            return true;
        }
        return false;
    }

    protected void remoteNeeds()
    {
        age--;
    }
}
