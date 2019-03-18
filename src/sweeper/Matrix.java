package sweeper;

// класс для хранения элементов на поле

class Matrix {
    private Box [] [] matrix;

    Matrix (Box defauftBox){      // записывам элементы в матрицу
        matrix = new Box[Ranges.getSize().x][Ranges.getSize().y];
        for (Coord coord : Ranges.getAllCoords()){
            matrix[coord.x][coord.y] = defauftBox;
        }
    }

    Box get (Coord coord){     // геттер одного элемента
        if (Ranges.inRange (coord))
            return matrix[coord.x][coord.y];
        return null;
    }

    public void set(Coord coord, Box box) {   // установка одного элемента
        if (Ranges.inRange (coord))
            matrix[coord.x][coord.y] = box;
    }
}
