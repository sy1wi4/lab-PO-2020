package agh.cs.lab2;

public enum MapDirection {
    NORTH,
    EAST,
    SOUTH,
    WEST;


    public String toString(){
        switch(this) {
            case NORTH: return "Północ";
            case SOUTH: return "Południe";
            case WEST: return "Zachód";
            case EAST: return "Wschód";
            default: return null;
        }
    }

    public MapDirection next(){
        // zamiast switch'a
        int idx = this.ordinal();
        return this.values()[(idx + 1) % 4];

    }

    public MapDirection previous(){
        switch(this) {
            case NORTH: return WEST;
            case SOUTH: return EAST;
            case WEST: return SOUTH;
            case EAST: return NORTH;
            default: return null;
        }
    }

    public Vector2d toUnitVector(){
        switch(this) {
            case NORTH: return new Vector2d(0, 1);
            case SOUTH: return new Vector2d(0, -1);
            case WEST: return new Vector2d(-1, 0);
            case EAST: return new Vector2d(1, 0);
            default: return null;
        }
    }
}