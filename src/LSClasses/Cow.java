package LSClasses;

import Actions.MoveBehaviour;
import Actions.ReproduceBehaviour;
import Display.Box;

public class Cow extends Item
{
    int satiety = 15;
    public int age = 20;

    public Cow(int x, int y)
    {
        super(x,y);
        box = Box.COW;
        isFreeField = false;
    }

    @Override
    protected Item interact(Item item)
    {
        Item newItem = null;
        if (item instanceof Cow) {
            item.remoteNeeds();
            if (ReproduceBehaviour.canReproduce(age, satiety) &&
                    (ReproduceBehaviour.canReproduce(((Cow) item).age, ((Cow) item).satiety)))
                newItem = ReproduceBehaviour.reproduce(item);
        }
        else if (item instanceof Grass) {
            newItem = MoveBehaviour.move(this, item);
            satiety = satiety + 3 <= 15 ? satiety + 3 : 15;
        }
        else if (item instanceof Ground) {
            newItem = MoveBehaviour.move(this,item);
        }
        return newItem;
    }
    @Override
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
