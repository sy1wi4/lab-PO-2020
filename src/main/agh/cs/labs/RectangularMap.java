package agh.cs.labs;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap implements  IWorldMap{

    private final int width;
    private final int height;
    public final List<Animal> animals;   // lista zwierząt
    private final MapVisualizer mapVisualizer;

    private final Vector2d leftCorner;
    private final Vector2d rightCorner;

    // konstruktor
    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
        this.animals  = new ArrayList<>();
        this.mapVisualizer = new MapVisualizer(this);
        this.leftCorner = new Vector2d(0,0);
        this.rightCorner = new Vector2d(width - 1,height - 1);

    }

    @Override
    // zwierzę nie może wyjść poza mapę ani na zajęte pole
    public boolean canMoveTo(Vector2d position){

        return  position.follows(leftCorner) &&
                position.precedes(rightCorner) &&
                !isOccupied(position);
    }


    @Override
    // sprawdza czy dane pole jest zajęte
    public boolean isOccupied(Vector2d position) {
        for (Animal animal : animals){
            if (animal.getPosition().equals(position)){
                return true;
            }
        }
        return false;
    }



    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal : animals){
            if (animal.getPosition().equals(position)){
                return animal;
            }
        }
        return null;
    }


    @Override
    public boolean place(Animal animal) {

        if (!isOccupied(animal.getPosition())){
            animals.add(animal);
            return true;
        }
        return false;
    }



    // rysuje aktualną konfigurację mapy
    public String toString(){
        return mapVisualizer.draw(this.leftCorner, this.rightCorner);
    }



}
