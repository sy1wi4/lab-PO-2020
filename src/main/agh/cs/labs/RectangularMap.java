package agh.cs.labs;

import java.util.LinkedHashMap;

public class RectangularMap extends AbstractWorldMap{

    private final int width;
    private final int height;

    private final Vector2d leftCorner;
    private final Vector2d rightCorner;

    // konstruktor
    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
        this.animals  = new LinkedHashMap<>();
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
    public Vector2d getLeftCorner(){
        return leftCorner;
    }


    @Override
    public Vector2d getRightCorner(){
        return rightCorner;
    }

}
