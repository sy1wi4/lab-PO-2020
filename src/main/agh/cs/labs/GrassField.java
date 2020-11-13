package agh.cs.labs;

import java.util.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class GrassField extends AbstractWorldMap{
    private final int number;
    public final List<Grass> grassList;


    public GrassField(int number){
        this.number = number;
        this.grassList  = new ArrayList<>();
        placeGrass();
    }


    private void placeGrass(){
        for (int i = 0; i < number; i++){
            int random1 = ThreadLocalRandom.current().nextInt(0, (int) (Math.sqrt(number * 10)) + 1);
            int random2 = ThreadLocalRandom.current().nextInt(0, (int) (Math.sqrt(number * 10)) + 1);
            Grass toAdd = new Grass(new Vector2d(random1, random2));
            grassList.add(toAdd);
        }
    }


    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position) || objectAt(position) instanceof Grass;
    }


    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal : animals){
            if (animal.getPosition().equals(position)){
                return animal;
            }
        }

        for (Grass grass : grassList){
            if (grass.getPosition().equals(position)){
                return grass;
            }
        }
        return null;
    }


    @Override
    public Vector2d getLeftCorner(){
        Vector2d first = grassList.get(0).getPosition();
        Vector2d second = grassList.get(1).getPosition();

        Vector2d leftCorner = first.lowerLeft(second);

        for (int i = 2; i < grassList.size(); i++) {
            leftCorner = leftCorner.lowerLeft(grassList.get(i).getPosition());
        }

        for (Animal animal : animals) {
            leftCorner = leftCorner.lowerLeft(animal.getPosition());
        }

        return leftCorner;
    }


    @Override
    public Vector2d getRightCorner(){
        Vector2d first = grassList.get(0).getPosition();
        Vector2d second = grassList.get(1).getPosition();

        Vector2d rightCorner = first.upperRight(second);

        for (int i = 2; i < grassList.size(); i++) {
            rightCorner = rightCorner.upperRight(grassList.get(i).getPosition());
        }

        for (Animal animal : animals) {
            rightCorner = rightCorner.upperRight(animal.getPosition());
        }

        return rightCorner;
    }

}

