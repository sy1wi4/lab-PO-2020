package agh.cs.labs;

import java.util.*;


abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{
    protected LinkedHashMap<Vector2d,Animal> animals = new LinkedHashMap<>();
    protected MapVisualizer mapVisualizer = new MapVisualizer(this);

    public abstract boolean canMoveTo(Vector2d position);

    @Override
    public Object objectAt(Vector2d position) {
        return animals.get(position);
    }

    protected abstract Vector2d getLeftCorner();
    protected abstract Vector2d getRightCorner();

    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())){
            animals.put(animal.getPosition(),animal);
            // dodając zwierzę, rejestrujemy mapę jako jego obserwatora
            animal.addObserver(this);
            return true;
        }
        return false;
    }

    public String toString(){
        // wzorzec projektowy - metoda szablonowa
        return mapVisualizer.draw(getLeftCorner(), getRightCorner());
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Animal animal = animals.get(oldPosition);
        animals.remove(oldPosition);
        animals.put(newPosition, animal);
    }
}
