package Actions;

import LSClasses.Ground;
import LSClasses.Item;

public interface MoveBehaviour
{
    static Item move(Item item1, Item item2)
    {
        Item newItem = new Ground(item1.coord.x, item1.coord.y);

        item1.coord.x = item2.coord.x;
        item1.coord.y = item2.coord.y;

        return newItem;
    }
}
