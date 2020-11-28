package agh.cs.labs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class RectangularMapTest {
    IWorldMap map = new RectangularMap(5,5);

    Vector2d pos1 = new Vector2d(1,1);   // pozycja pierwszego zwierzęcia
    Animal animal1  = new Animal(map, pos1);
    Vector2d pos2 = new Vector2d(2,1);   // pozycja drugiego  zwierzęcia
    Animal animal2  = new Animal(map, pos2);


    // pomocnicza metoda sterująca ruchem jednego zwierzęcia niezależnie
    public static void go(String args[], Animal animal) {
        MoveDirection[] dirs = OptionsParser.parse(args);

        for (MoveDirection dir : dirs)
            animal.move(dir);
    }

    @Test
    public void checkIfCantMoveToOccupiedPosition() {
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
    public void checkIfCantMoveBeyondMap() {
        map.place(animal1);
        map.place(animal2);
        String[] args1 = {"l", "f", "f"};
        String[] args2 = {"r", "r", "f"};

        go(args1, animal1);
        assertEquals(new Vector2d(0,1),animal1.getPosition());

        go(args2, animal2);
        assertEquals(new Vector2d(2,0),animal2.getPosition());

    }

    @Test
    public void checkIfNotPlacedOnOccupiedPosition(){
        Animal animal3  = new Animal(map, pos2);

        map.place(animal2);
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> map.place(animal3));

    }

    @Test
    public void checkIfIsOccupiedIfThereIsAnAnimal(){
        map.place(animal1);
        map.place(animal2);
        assertFalse(map.isOccupied(new Vector2d(0,0)));
        assertTrue(map.isOccupied(pos1));
        assertTrue(map.isOccupied(pos2));
    }

    @Test
    public void checkIfReturnsCorrectObject(){
        map.place(animal1);
        map.place(animal2);
        assertEquals(null,map.objectAt(new Vector2d(0,0)));
        assertEquals(animal1,map.objectAt(pos1));
    }

}
