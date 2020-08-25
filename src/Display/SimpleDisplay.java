package Display;

import LSClasses.Item;

import java.util.Iterator;

public interface SimpleDisplay // если необходимо выводить поле в консоль
{

    static void consoleArray(Iterator iterator, int maxX, int maxY)
    {
        String[][] arrayToPrint = new String[maxX][maxY];
        while (iterator.hasNext()) {
            Item item = (Item) iterator.next();
            try {
                String type = item.box.name().substring(0, 3);
                arrayToPrint[item.coord.x][item.coord.y] = type;
            }catch(ArrayIndexOutOfBoundsException e){
                System.out.println("Wrong array size/incorrect item exists!");
            }
        }
        for (int i = 0; i < maxX; i++) {
            for (int j = 0; j < maxY; j++) {
                System.out.print(arrayToPrint[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void printArray(Iterator iterator)
    {
        while (iterator.hasNext()){
            Item item = (Item)iterator.next();
            System.out.println(item.box.name().substring(0, 3)+" {"
                    + item.coord.x + "; " + item.coord.y + "}");
        }
    }
}
