package agh.cs.labs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GrassFieldTest {
    IWorldMap map = new GrassField(10);
    Vector2d pos1 = new Vector2d(1,1);   // pozycja pierwszego zwierzęcia
    Animal animal1  = new Animal(map, pos1);
    Vector2d pos2 = new Vector2d(2,1);   // pozycja drugiego  zwierzęcia
    Animal animal2  = new Animal(map, pos2);
    Vector2d vec00 = new Vector2d(0,0);


    // pomocnicza metoda sterująca ruchem jednego zwierzęcia niezależnie
    public static void go(String args[], Animal animal) {
        MoveDirection[] dirs = OptionsParser.parse(args);

        for (MoveDirection dir : dirs)
            animal.move(dir);
    }

    @Test
    public void checkIfCantMoveToPositionOccupiedByAnAnimal() {
        map.place(animal1);
        map.place(animal2);
        String[] args1 = {"r", "f"};
        String[] args2 = {"l", "f"};

        go(args1, animal1);
        assertEquals(pos1,animal1.getPosition());

        go(args2, animal2);
        assertEquals(pos2,animal2.getPosition());
    }


    @Test
    public void checkIfCanMoveToPositionOccupiedByGrassOrNothing() {
        map.place(animal1);
        map.place(animal2);
        String[] args1 = {"l", "f"};
        String[] args2 = {"r", "f"};

        go(args1, animal1);
        assertEquals(new Vector2d(0,1),animal1.getPosition());

        go(args2, animal2);
        assertEquals(new Vector2d(3,1),animal2.getPosition());
    }


    @Test
    public void checkIfNotPlacedOnPositionOccupiedByAnAnimal(){
        Animal animal3  = new Animal(map, pos2);

        map.place(animal2);
        assertFalse(map.place(animal3));
    }

    @Test
    public void checkIfIsOccupiedIfThereIsAnAnimal(){
        map.place(animal1);
        map.place(animal2);
        assertFalse(map.isOccupied(vec00));
        assertTrue(map.isOccupied(pos1));
        assertTrue(map.isOccupied(pos2));
    }



}
