package Actions;

import LSClasses.Cow;
import LSClasses.Grass;
import LSClasses.Item;
import LSClasses.Wolf;

public interface ReproduceBehaviour
{
    static Item reproduce(Item item)
    {
        Item childItem;
        if (item instanceof Cow) {
            childItem = new Cow(-1,-1);
        }else if(item instanceof Wolf) {
            childItem = new Wolf(-1,-1);
        }else {
            childItem = new Grass(-1,-1);
        }
            return childItem;
    }

    static boolean canReproduce(int age, int satiety)
    {
        if (age >= 0 && satiety >= 0) {
            return true;
        }
        return false;
    }
}
