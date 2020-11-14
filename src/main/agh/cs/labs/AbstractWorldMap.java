package agh.cs.labs;

import java.util.*;
import java.util.ArrayList;

abstract class AbstractWorldMap implements IWorldMap{
    protected List<Animal> animals = new ArrayList<>();
    protected MapVisualizer mapVisualizer = new MapVisualizer(this);

    public abstract boolean canMoveTo(Vector2d position);

    public abstract Object objectAt(Vector2d position);

    protected abstract Vector2d getLeftCorner();
    protected abstract Vector2d getRightCorner();

    public boolean isOccupied(Vector2d position) {

        return objectAt(position) != null;
    }

    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())){
            animals.add(animal);
            return true;
        }
        return false;
    }

    public String toString(){
        // wzorzec projektowy - metoda szablonowa
        return mapVisualizer.draw(getLeftCorner(), getRightCorner());
    }
}
