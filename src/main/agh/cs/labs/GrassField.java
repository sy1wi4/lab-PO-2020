package agh.cs.labs;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import static java.util.Objects.isNull;

public class GrassField extends AbstractWorldMap{
    private final int number;
    public final LinkedHashMap<Vector2d,Grass> grassList;


    public GrassField(int number){
        this.number = number;
        this.grassList  = new LinkedHashMap<>();
        placeGrass();
    }


    private void placeGrass(){
        for (int i = 0; i < number; i++){
            int random1 = ThreadLocalRandom.current().nextInt(0, (int) (Math.sqrt(number * 10)) + 1);
            int random2 = ThreadLocalRandom.current().nextInt(0, (int) (Math.sqrt(number * 10)) + 1);
            Grass toAdd = new Grass(new Vector2d(random1, random2));
            grassList.put(toAdd.getPosition(),toAdd);
        }
    }


    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position) || objectAt(position) instanceof Grass;
    }


    @Override
    public Object objectAt(Vector2d position) {
        // Klasa podrzędna (GrassField) odwołuje się do implementacji
        // z klasy nadrzędnej (AbstractWorldMap)
        Object object = super.objectAt(position);
        if (isNull(object)){
            return grassList.get(position);
        }
        else{
            return object;
        }
    }


    @Override
    public Vector2d getLeftCorner(){

        Iterator<Vector2d> grassIterator = grassList.keySet().iterator();
        Iterator<Vector2d> animalsIterator = animals.keySet().iterator();
        Vector2d first = grassIterator.next();
        Vector2d second = grassIterator.next();

        Vector2d leftCorner = first.lowerLeft(second);

        while (grassIterator.hasNext()) {
            leftCorner = leftCorner.lowerLeft(grassIterator.next());
        }
        while (animalsIterator.hasNext()) {
            leftCorner = leftCorner.lowerLeft(animalsIterator.next());

        }
        return leftCorner;

    }


    @Override
    public Vector2d getRightCorner() {
        Iterator<Vector2d> grassIterator = grassList.keySet().iterator();
        Iterator<Vector2d> animalsIterator = animals.keySet().iterator();
        Vector2d first = grassIterator.next();
        Vector2d second = grassIterator.next();

        Vector2d rightCorner = first.upperRight(second);

        while (grassIterator.hasNext()) {
            rightCorner = rightCorner.upperRight(grassIterator.next());
        }
        while (animalsIterator.hasNext()) {
            rightCorner = rightCorner.upperRight(animalsIterator.next());
        }
        return rightCorner;
    }

}

