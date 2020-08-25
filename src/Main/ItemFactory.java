package Main;

import LSClasses.*;

import java.util.ArrayList;
import java.util.Random;

public class ItemFactory
{
    static char[][] defaultList;
    private ItemFactory()
    {}

    public static ArrayList<Item> getList(int x, int y)
    {
        return getDefaultList(x,y);
    }

    public static ArrayList<Item> getRandomList(int x, int y)
    {
        ArrayList<Item> list = new ArrayList<>();
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                list.add(ItemFactory.getRandomItem(i, j));
            }
        }
        return list;
    }

    public static ArrayList<Item> getDefaultList(int x, int y)
    {
        ArrayList<Item> list = new ArrayList<>();
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                Item item;
                char value = defaultList[i][j];
                if (value == 'C') {
                    item = new Cow(i, j);
                }else if (value == 'W') {
                    item = new Wolf(i, j);
                }else if (value == 'G') {
                    item = new Ground(i, j);
                }else {
                    item = new Grass(i, j);
                }
                list.add(item);
            }
        }
        return list;
    }

    public static Item getRandomItem(int x, int y)
    {
        Random rand = new Random();
        int value = rand.nextInt(20);

        if (value < 2) {
            return new Cow(x, y);
        }else if (value < 4) {
            return new Wolf(x, y);
        }else if (value < 12) {
            return new Ground(x, y);
        }else {
            return new Grass(x, y);
        }
    }

    public static Item getRandomFreeItem(int x, int y)
    {
        Random rand = new Random();
        int value = rand.nextInt(2);

        if (value == 0) {
            return new Ground(x, y);
        }
        return new Grass(x, y);
    }

    public static ArrayList<Item> getFreeField(int x, int y){
        ArrayList<Item> list = new ArrayList<>();
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                list.add(ItemFactory.getRandomFreeItem(i, j));
            }
        }
        return list;
    }

    static {
        defaultList = new char[][]{
                {'F', 'G', 'W', 'F', 'C', 'G', 'W', 'G', 'F', 'W', 'C', 'G', 'F', 'F', 'F', 'C', 'F', 'C', 'C', 'F'},
                {'G', 'G', 'F', 'F', 'F', 'G', 'G', 'G', 'G', 'F', 'G', 'F', 'F', 'C', 'C', 'G', 'F', 'W', 'C', 'G'},
                {'F', 'F', 'C', 'F', 'F', 'F', 'W', 'C', 'C', 'G', 'F', 'F', 'G', 'F', 'C', 'F', 'W', 'W', 'F', 'F'},
                {'W', 'F', 'F', 'G', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'C', 'G', 'G', 'F', 'F', 'F', 'C'},
                {'C', 'F', 'F', 'F', 'F', 'G', 'F', 'G', 'G', 'W', 'W', 'F', 'W', 'F', 'F', 'G', 'W', 'F', 'F', 'F'},
                {'F', 'F', 'F', 'G', 'F', 'F', 'G', 'C', 'G', 'F', 'G', 'F', 'C', 'F', 'C', 'F', 'G', 'G', 'G', 'W'},
                {'F', 'C', 'F', 'F', 'C', 'F', 'F', 'C', 'C', 'C', 'W', 'G', 'F', 'F', 'F', 'F', 'F', 'G', 'C', 'W'},
                {'F', 'F', 'G', 'F', 'G', 'C', 'F', 'G', 'C', 'C', 'F', 'F', 'F', 'F', 'G', 'G', 'F', 'W', 'G', 'F'},
                {'C', 'W', 'C', 'F', 'G', 'F', 'G', 'W', 'F', 'F', 'G', 'F', 'G', 'F', 'G', 'F', 'F', 'F', 'G', 'G'},
                {'C', 'F', 'G', 'G', 'F', 'F', 'F', 'C', 'F', 'C', 'F', 'W', 'G', 'G', 'G', 'F', 'F', 'G', 'F', 'F'},
                {'F', 'F', 'F', 'F', 'G', 'F', 'F', 'G', 'G', 'F', 'G', 'F', 'W', 'F', 'F', 'C', 'F', 'F', 'F', 'F'},
                {'F', 'W', 'F', 'F', 'F', 'F', 'F', 'F', 'C', 'F', 'C', 'G', 'F', 'G', 'G', 'G', 'F', 'W', 'F', 'G'},
                {'W', 'F', 'F', 'F', 'W', 'F', 'F', 'W', 'C', 'F', 'F', 'F', 'F', 'F', 'F', 'G', 'F', 'F', 'F', 'F'},
                {'F', 'F', 'G', 'G', 'W', 'G', 'F', 'G', 'F', 'G', 'F', 'W', 'W', 'C', 'G', 'F', 'G', 'G', 'F', 'F'},
                {'F', 'C', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'C', 'G', 'C', 'W', 'G', 'F', 'G', 'F'},
                {'G', 'F', 'F', 'F', 'C', 'F', 'G', 'C', 'F', 'G', 'F', 'F', 'W', 'G', 'G', 'G', 'F', 'F', 'W', 'F'},
                {'W', 'C', 'F', 'G', 'F', 'G', 'W', 'F', 'F', 'C', 'F', 'F', 'W', 'W', 'F', 'G', 'G', 'F', 'F', 'F'},
                {'C', 'G', 'F', 'F', 'C', 'F', 'F', 'F', 'W', 'G', 'G', 'W', 'F', 'G', 'F', 'W', 'C', 'G', 'G', 'G'},
                {'W', 'F', 'F', 'F', 'F', 'F', 'C', 'F', 'W', 'G', 'G', 'G', 'F', 'F', 'F', 'G', 'G', 'W', 'C', 'C'},
                {'C', 'F', 'F', 'W', 'F', 'F', 'F', 'F', 'C', 'F', 'G', 'G', 'W', 'F', 'F', 'G', 'W', 'W', 'G', 'F'}
        };
    }
}
