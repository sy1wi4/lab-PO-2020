package agh.cs.labs;


public class Animal {

    public MapDirection orientation;
    public Vector2d position;
    private IWorldMap map;


    // konstruktor bezparametrowy
    public Animal(){
        this.orientation = MapDirection.NORTH;
        this.position = new Vector2d(2, 2);
    }


    public Animal(IWorldMap map){
        this();
        this.map = map;
    }


    public Animal(IWorldMap map, Vector2d initialPosition){

        // dodatkowo określa początkowe położenie zwierzęcia na mapie
        this(map);
        this.position = initialPosition;
    }


    public String toString() {

        // zwraca schematyczną orientację
        return this.orientation.toString();
    }


    public MapDirection getOrientation() {
        return this.orientation;
    }


    public Vector2d getPosition() {
        return this.position;
    }


    public void tryToMove(Vector2d newPosition) {
        if (this.map.canMoveTo(newPosition))
            this.position = newPosition;
    }


    public void move(MoveDirection direction) {

        Vector2d newPosition;
        switch (direction) {

            case RIGHT:
                this.orientation = this.orientation.next();
                break;

            case LEFT:
                this.orientation = this.orientation.previous();
                break;

            case FORWARD:
                newPosition = position.add(orientation.toUnitVector());
                tryToMove(newPosition);
                break;

            case BACKWARD:
                newPosition = position.subtract(orientation.toUnitVector());
                tryToMove(newPosition);
                break;

        }
    }
}
