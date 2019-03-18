package sweeper;

// класс для контроля поля бомб

class Bomb {

    private Matrix bombMap;    // матрица для работы с бомбами
    private int totalBombs;     // сколько активных бомб

    int getTotalBombs() {
        return totalBombs;
    }

    Bomb (int totalBombs){
        this.totalBombs = totalBombs;
        fixBombsCount();
    }

    void start(){       // расставляем бомбы
        bombMap = new Matrix (Box.ZERO);
        for (int i = 0; i < totalBombs; i++)
            placeBomb();

    }

    Box get(Coord coord) {
        return bombMap.get(coord);
    }

    private void placeBomb(){
        while (true) {
            Coord coord = Ranges.getRandomCoord();
            if (Box.BOMB == bombMap.get(coord))
                continue;
            bombMap.set(coord, Box.BOMB);
            incNumbersAroundBomb(coord);
            break;
        }
    }

    public void fixBombsCount(){
        int maxBombs = Ranges.getSize().x * Ranges.getSize().y / 2;
        if (totalBombs > maxBombs)
            totalBombs = maxBombs;
    }

    private void incNumbersAroundBomb(Coord coord){    // метод для увечичения числе рядом с бомбами
        for (Coord around : Ranges.getCoordsAround(coord))
            if (Box.BOMB != bombMap.get(around))
                bombMap.set(around, bombMap.get(around).getNextNumberBox());

    }


}
