package agh.cs.labs;

import java.util.ArrayList;

public class Animal implements IMapElement {

    private MapDirection orientation;
    private Vector2d position;
    private IWorldMap map;
    private ArrayList<IPositionChangeObserver> observers;

    // konstruktor bezparametrowy
    public Animal(){
        this.orientation = MapDirection.NORTH;
        this.position = new Vector2d(2, 2);
        this.observers = new ArrayList<>();
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

        if (this.map.canMoveTo(newPosition)) {
            Vector2d oldPosition = this.position;

            this.position = newPosition;
            // informuje obserwatorów o zmianie pozycji

            positionChanged(oldPosition, newPosition);
        }
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

    public void addObserver(IPositionChangeObserver observer){
        this.observers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer){
        this.observers.remove(observer);
    }

    //  informuje wszystkich obserwatorów, o tym że pozycja została zmieniona
    private void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        for (IPositionChangeObserver observer : observers){
            observer.positionChanged(oldPosition, newPosition);
        }
    }
}
