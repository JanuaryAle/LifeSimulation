package Main;

import Display.GUI;
import LSClasses.Item;

import java.util.ArrayList;

import java.util.Collections;

public class LifeSimulation
{
    private static LifeSimulation lifeSimulation;
    private static int HEIGHT;
    private static int WIDTH;

    public void setStopped(boolean stopped)
    {
        isStopped = stopped;
    }

    private boolean isStopped = true;
    private boolean[] isLocked;
    private ArrayList<Item> field;

    public boolean isStopped() {
        return isStopped;
    }

    public LifeSimulation(int HEIGHT, int WIDTH)
    {
        this.HEIGHT = HEIGHT;
        this.WIDTH = WIDTH;
        field = ItemFactory.getFreeField(HEIGHT,WIDTH);
        new GUI( HEIGHT, WIDTH, this);
    }

    public void start()
    {
        isStopped = false;
        field = ItemFactory.getDefaultList(HEIGHT, WIDTH);
    }

    public void update()
    {
        deleteDeadItems();
        interact();
    }

    private void deleteDeadItems()
    {
        for (Item item: field) {
            if (item.isAlive()) {
                continue;
            }
            int x = item.coord.x;
            int y = item.coord.y;
            Item newItem = ItemFactory.getRandomFreeItem(x, y);
            int index = field.indexOf(item);
            field.set(index, newItem);
        }
    }

    private void interact()
    {
        isLocked = new boolean[WIDTH * HEIGHT];
        for (Item item: field) {
            int itemIndex = field.indexOf(item);

            if (!field.get(itemIndex).isValid || isLocked[itemIndex]) {
                continue;
            }

            int neighbourIndex = getRandomNeighbourIndex(item);
            if (neighbourIndex == -1) continue;

            Item neighbour = field.get(neighbourIndex);
            Item newItem = item.doSomething(neighbour);
            setNewItem(newItem, item, neighbour, itemIndex, neighbourIndex);
        }
    }

    private int getRandomNeighbourIndex(Item item)
    {
        ArrayList<Item> neighbours = findNeighbors(item);
        Collections.shuffle(neighbours);

        for (Item neighbour: neighbours) {
            int index = field.indexOf(neighbour);
            if ((!isLocked[index])||(neighbour.isFreeField)) {
                return index;
            }
        }
        return -1;
    }

    private void setNewItem(Item newItem, Item item1, Item item2, int index1, int index2)
    {
        if (newItem == null) {
            return;
        }
        if (newItem.coord.x >= 0) {
            field.set(index2, item1);
            field.set(index1, newItem);
            if (!item1.isFreeField) {
                isLocked[index2] = true;
            }

        }else {
            int freeNeighbourIndex = getFreePlaceForChild(item1, item2);
            if (freeNeighbourIndex >= 0) {
                Item freeNeighbour = field.get(freeNeighbourIndex);
                if (!item1.isFreeField) {
                    isLocked[index1] = true;
                    isLocked[index2] = true;
                    isLocked[freeNeighbourIndex] = true;
                }
                newItem.coord.x = freeNeighbour.coord.x;
                newItem.coord.y = freeNeighbour.coord.y;
                field.set(freeNeighbourIndex, newItem);
            }
        }
    }

   private int getFreePlaceForChild(Item item1, Item item2)
    {
        ArrayList<Item> neighbours = findNeighbors(item1);
        neighbours.addAll(findNeighbors(item2));
        for (Item neighbour: neighbours) {
            if ((neighbour.isFreeField)) {
                return field.indexOf(neighbour);
            }
        }
        return -1;
    }

    private ArrayList<Item> findNeighbors(Item item)//******
    {
        ArrayList<Item> neighbours = new ArrayList<>();
        int itemRow = item.coord.x;
        int itemColumn = item.coord.y;

        if (itemRow - 1 >= 0) {
            int a = (itemRow - 1) * WIDTH + itemColumn;
            neighbours.add(field.get(a));
        }
        if (itemRow + 1 < HEIGHT) {
            int a = (itemRow + 1) * WIDTH + itemColumn;
            neighbours.add(field.get(a));
        }
        if (itemColumn - 1 >= 0) {
            int a = itemRow * WIDTH + itemColumn - 1;
            neighbours.add(field.get(a));
        }
        if (itemColumn + 1 < WIDTH) {
            int a = itemRow * WIDTH + itemColumn + 1;
            neighbours.add(field.get(a));
        }
        return neighbours;
    }

    public ArrayList<Item> getField(){
        return field;
    }
}
