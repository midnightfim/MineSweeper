package sweeper;

// внешняя карта игры

class Flag {

    private Matrix flagMap;
    private int CountOfClosedBoxes;

    void start(){
        flagMap = new Matrix(Box.CLOSED);
        CountOfClosedBoxes = Ranges.getSize().x * Ranges.getSize().y;
    }

    Box get(Coord coord){
        return flagMap.get(coord);
    }

    void setOpenedToBox(Coord coord){
        flagMap.set(coord, Box.OPENED);
        CountOfClosedBoxes --;
    }

    void toggleFlagedToBox(Coord coord){
        switch (flagMap.get(coord))
        {
            case FLAGED: setClosedToBox (coord); break;
            case CLOSED: setFlagedToBox (coord); break;
        }
    }


    void setFlagedToBox(Coord coord){
        flagMap.set(coord, Box.FLAGED);
    }

    void setClosedToBox(Coord coord){
        flagMap.set(coord, Box.CLOSED);
    }

    int getCountOfClosedBoxes() {
        return CountOfClosedBoxes;
    }

    public void setBombedToBox(Coord coord){
        flagMap.set(coord, Box.BOMBED);
    }
    void setOpenedToClosedBox(Coord coord){
        if (flagMap.get(coord) == Box.CLOSED)
            flagMap.set(coord, Box.OPENED);
    }

     void setNoBombToFlagedSafeBox(Coord coord){
         if (flagMap.get(coord) == Box.FLAGED)
             flagMap.set(coord, Box.NOBOMB);
    }
}
