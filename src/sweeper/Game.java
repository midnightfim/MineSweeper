package sweeper;

// контроллер игрового поля

public class Game {

    private Bomb bomb;
    private Flag flag;
    private GameState state;

    public Game(int cols, int rows, int bombs){
        Ranges.setSize(new Coord(cols, rows));   // сет для Ranges массива при вызове конструктора
        bomb = new Bomb(bombs);
        flag = new Flag();
    }

    public void start(){   // метод lля старта игры
        bomb.start();
        flag.start();
        state = GameState.PLAYED;
    }

    public GameState getState() {
        return state;
    }

    public Box getBox(Coord coord){
        if (flag.get(coord) == Box.OPENED)
            return bomb.get(coord);
        else
            return flag.get(coord);
    }

    public void pressLeftButton(Coord coord){   // метод реализации левого клика мышью
        if (gameOver()) return;
        openBox(coord);
        checkWinner();

    }

    private void checkWinner(){  // метод для проверки на победу
        if (state == GameState.PLAYED)
            if (flag.getCountOfClosedBoxes() == bomb.getTotalBombs())
                state = GameState.WINNER;
    }

    private void openBox(Coord coord){
        switch (flag.get(coord))    // пооверяем состояние клетки в матрице FLAG
        {
            case OPENED: return;
            case FLAGED: return;
            case CLOSED:
                switch (bomb.get(coord))  //  проверяем что в клетке в BOMB
                {
                    case ZERO: openBoxesArround(coord); return;
                    case BOMB: openBombs(coord); return;
                    default: flag.setOpenedToBox(coord); return;
                }
        }
    }

    private void openBombs(Coord bombed){
        state = GameState.BOMBED;
        flag.setBombedToBox(bombed);
        for (Coord coord: Ranges.getAllCoords())
            if(bomb.get(coord) == Box.BOMB)
                flag.setOpenedToClosedBox(coord);
            else
                flag.setNoBombToFlagedSafeBox(coord);
    }

    public void pressRightButton(Coord coord){// метод реализации правого клика мышью
        if (gameOver()) return;
        flag.toggleFlagedToBox (coord);
    }

    private void openBoxesArround(Coord coord){
        flag.setOpenedToBox(coord);
        for (Coord around : Ranges.getCoordsAround(coord))
            openBox(around);
    }

    private boolean gameOver(){
        if (state == GameState.PLAYED)
            return false;
        start();
        return true;

    }
}
