package sweeper;

import java.util.ArrayList;
import java.util.Random;

public class Ranges {
    private static Coord size;
    private static Random random = new Random();

    private static ArrayList<Coord> allCoords;   // коллекция всех коородинат


    public static void setSize(Coord _size) {   // устанавливаем картинки в поля
        size = _size;
        allCoords = new ArrayList<Coord>();
        for (int y = 0 ; y < size.y; y++)
            for (int x = 0 ; x < size.x; x++)
                allCoords.add(new Coord(x,y));
    }

    public static Coord getSize() {
        return size;
    }

    public static ArrayList<Coord> getAllCoords() {
        return allCoords;
    }

    static boolean inRange(Coord coord){   // проверка на вхождение в поле
        return coord.x >= 0 && coord.x < size.x && coord.y >= 0 && coord.y < size.y;
    }

    static Coord getRandomCoord(){
        return new Coord(random.nextInt(size.x),
                         random.nextInt(size.y));
    }

    static ArrayList<Coord> getCoordsAround(Coord coord){  // метод для перебора всех координат вокруг клетки
        Coord around;
        ArrayList<Coord> list = new ArrayList<Coord>();
        for (int x = coord.x -1; x <= coord.x + 1; x++)
            for (int y = coord.y -1; y <= coord.y + 1; y++)
                if (inRange(around = new Coord(x, y)))
                    if (!around.equals(coord))
                        list.add(around);
        return list;
    }
}
