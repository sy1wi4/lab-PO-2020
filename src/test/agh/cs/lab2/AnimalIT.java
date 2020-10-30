package agh.cs.lab2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static agh.cs.lab2.MoveDirection.*;
import static agh.cs.lab2.MapDirection.*;

public class AnimalIT {

    @Test
    public void checkOrientation() {

        Animal animal  = new Animal();

        String[] args1 = {"r", "r", "r","r"};
        World.go(args1, animal);
        assertEquals(NORTH, animal.getOrientation());

        String[] args2 = {"l", "l", "l", "l"};
        World.go(args2, animal);
        assertEquals(NORTH, animal.getOrientation());

        String[] args3 = {"l", "l", "r", "backward", "left", "right", "r", "x", "r"};
        World.go(args3, animal);
        assertEquals(EAST, animal.getOrientation());

    }

    @Test
    public void checkPosition() {
        Animal animal  = new Animal();

        String[] args1 = {"r", "r", "r"};
        World.go(args1, animal);
        assertEquals(new Vector2d(2, 2), animal.getPosition());

        String[] args2 = {"f", "b", "f", "b", "backward"};
        World.go(args2, animal);
        assertEquals(new Vector2d(3, 2), animal.getPosition());

        String[] args3 = {"x"};
        World.go(args3, animal);
        assertEquals(new Vector2d(3, 2), animal.getPosition());

    }

    @Test
    public void checkIfNotLeaving() {
        Animal animal  = new Animal();

        // north
        String[] args1 = {"f", "x", "f", "forward"};
        World.go(args1, animal);
        assertEquals(new Vector2d(2, 4), animal.getPosition());

        // east & south
        String[] args2 = {"r", "f", "f", "forward", "left", "b", "b", "backward", "x", "b", "b"};
        World.go(args2, animal);
        assertEquals(new Vector2d(4, 0), animal.getPosition());

        // west
        String[] args3 = {"l", "f", "f", "f", "f", "f"};
        World.go(args3, animal);
        assertEquals(new Vector2d(0, 0), animal.getPosition());

    }

    @Test
    public void checkInput() {

        String[] args1 = {"r", "left", "RIGHT", "f", "idk", "b", ""};
        MoveDirection[] expected = {RIGHT, LEFT, FORWARD, BACKWARD};
        MoveDirection[] actual = OptionsParser.parse(args1);

        assertEquals(4, actual.length);
        if (actual.length == 4) {
            for (int i = 0; i < 4; i++) {
                assertEquals(expected[i], actual[i]);
            }
        }


        String[] args2 = {"RIGHT", "java", "", "???", "idk", "back", ":)"};
        MoveDirection[] actual2 = OptionsParser.parse(args2);
        assertEquals(0, actual2.length);


        Animal animal = new Animal();
        String[] args3 = {"left", "backward", "r", "no", "idk", "b", "Left", "f"};
        World.go(args3, animal);
        assertEquals(new Vector2d(3, 2), animal.getPosition());
        assertEquals(NORTH, animal.getOrientation());
    }

}
