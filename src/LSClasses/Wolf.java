package LSClasses;

import Actions.MoveBehaviour;
import Actions.ReproduceBehaviour;
import Display.Box;

public class Wolf extends Item
{
    int satiety = 25;
    int age = 20;

    public Wolf(int x, int y)
    {
        super(x,y);
        box = Box.WOLF;
        isFreeField = false;
    }
    @Override
    protected Item interact(Item item)
    {
        Item newItem = null;
        if (item instanceof Cow) {
            newItem = MoveBehaviour.move(this, item);
            satiety = satiety + 5 <= 20 ? satiety + 5 : 20;
        }
        else if (item instanceof Ground) {
            newItem = MoveBehaviour.move(this,item);
        }
        else if (item instanceof Wolf) {
            item.remoteNeeds();
            if (ReproduceBehaviour.canReproduce(age, satiety) &&
                    (ReproduceBehaviour.canReproduce(((Wolf) item).age, ((Wolf) item).satiety)))
                newItem = ReproduceBehaviour.reproduce(item);
        }
        else {
            newItem = MoveBehaviour.move(this,item);
        }
        if ((age < 1)||(satiety < 1)) {
            System.out.println();
        }
        return newItem;
    }

    public boolean isAlive()
    {
        if ((age > 0) && (satiety > 0)) {
            return true;
        }
        return false;
    }

    protected void remoteNeeds()
    {
        age--;
        satiety-=2;
    }
}
