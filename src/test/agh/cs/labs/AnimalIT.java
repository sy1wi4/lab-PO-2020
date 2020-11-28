package agh.cs.labs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static agh.cs.labs.MoveDirection.*;
import static agh.cs.labs.MapDirection.*;

public class AnimalIT {

    IWorldMap map = new RectangularMap(5,5);
    IWorldMap smallMap = new RectangularMap(2,2);
    IWorldMap bigMap = new RectangularMap(15, 15);
    Animal animal  = new Animal(map);

    Vector2d[] positionsSmallMap = {new Vector2d(0,0), new Vector2d(0,1),
                                    new Vector2d(1,0), new Vector2d(1,1)};


    Vector2d[] positionsBigMap = {new Vector2d(0,0), new Vector2d(0,1),
                                  new Vector2d(14,13), new Vector2d(14,14),
                                  new Vector2d(5,7), new Vector2d(2,10)};


    // pomocnicza metoda sterująca ruchem jednego zwierzęcia niezależnie
    public static void go(String args[], Animal animal) {
        MoveDirection[] dirs = OptionsParser.parse(args);

        for (MoveDirection dir : dirs)
            animal.move(dir);
    }

    @Test
    public void checkAnimalsMovementCompatibilityAndMapVisualizing(){
        String[] args = {"b", "b", "f", "r", "f", "forward", "b", "l", "r"};
        MoveDirection[] dirs1 = OptionsParser.parse(args);
        IEngine engine1 = new SimulationEngine(dirs1, bigMap, positionsBigMap);
        engine1.run();
        assertEquals( " y\\x  0 1 2 3 4 5 6 7 8 91011121314\r\n"
                            + " 15: -------------------------------\r\n"
                            + " 14: | | | | | | | | | | | | | | |>|\r\n"
                            + " 13: | | | | | | | | | | | | | | |>|\r\n"
                            + " 12: | | | | | | | | | | | | | | | |\r\n"
                            + " 11: | | |^| | | | | | | | | | | | |\r\n"
                            + " 10: | | | | | | | | | | | | | | | |\r\n"
                            + "  9: | | | | | | | | | | | | | | | |\r\n"
                            + "  8: | | | | | |^| | | | | | | | | |\r\n"
                            + "  7: | | | | | | | | | | | | | | | |\r\n"
                            + "  6: | | | | | | | | | | | | | | | |\r\n"
                            + "  5: | | | | | | | | | | | | | | | |\r\n"
                            + "  4: | | | | | | | | | | | | | | | |\r\n"
                            + "  3: | | | | | | | | | | | | | | | |\r\n"
                            + "  2: | | | | | | | | | | | | | | | |\r\n"
                            + "  1: |<| | | | | | | | | | | | | | |\r\n"
                            + "  0: |^| | | | | | | | | | | | | | |\r\n"
                            + " -1: -------------------------------\r\n"
                ,bigMap.toString());


        String[] args2 = {"b", "r", "r", "r","f", "r", "f", "b","r","l"};
        MoveDirection[] dirs2 = OptionsParser.parse(args2);
        IEngine engine2 = new SimulationEngine(dirs2, smallMap, positionsSmallMap);
        engine2.run();
        assertEquals( " y\\x  0 1\r\n"
                            + "  2: -----\r\n"
                            + "  1: |>|>|\r\n"
                            + "  0: |>|>|\r\n"
                            + " -1: -----\r\n"
                ,smallMap.toString());
    }


    @Test
    // sprawdza, czy zwierzę po stworzeniu ma prawidłową orientację
    public void checkInitialOrientation(){
        assertEquals(NORTH,animal.getOrientation());
    }


    @Test
    // sprawdza, czy zwierzę po sekwencji ruchów ma prawidłową orientację
    public void checkOrientation() {

        String[] args1 = {"r", "r", "r","r"};
        go(args1, animal);
        assertEquals(NORTH, animal.getOrientation());

        String[] args2 = {"l", "l", "l", "l"};
        go(args2, animal);
        assertEquals(NORTH, animal.getOrientation());

        String[] args3 = {"l", "l", "r", "backward", "left", "right", "r", "r"};
        go(args3, animal);
        assertEquals(EAST, animal.getOrientation());

    }



    @Test
    // sprawdza, czy zwierzę po sekwencji ruchów ma prawidłową pozycję
    public void checkPosition() {


        String[] args1 = {"r", "r", "r"};
        go(args1, animal);
        assertEquals(new Vector2d(2, 2), animal.getPosition());

        String[] args2 = {"f", "b", "f", "b", "backward"};
        go(args2, animal);
        assertEquals(new Vector2d(3, 2), animal.getPosition());

    }

    @Test
    // sprawdza, czy zwierzę nie wychodzi poza mapę
    public void checkIfNotLeaving() {

        // north
        String[] args1 = {"f", "x", "f", "forward"};
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> go(args1, animal));

        // east & south
        String[] args2 = {"r", "f", "f", "forward", "left", "b", "b", "backward", "b", "b"};
        go(args2, animal);
        assertEquals(new Vector2d(4, 0), animal.getPosition());

        // west
        String[] args3 = {"l", "f", "f", "f", "f", "f"};
        go(args3, animal);
        assertEquals(new Vector2d(0, 0), animal.getPosition());

    }


    @Test
    // sprawdza, czy dane wejściowe są poprawnie interpretowane
    public void checkInput() {

        String[] args1 = {"r", "left", "RIGHT", "f", "idk", "b", ""};
        MoveDirection[] expected = {RIGHT, LEFT, FORWARD, BACKWARD};

        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> OptionsParser.parse(args1));




        String[] args2 = {};
        MoveDirection[] actual2 = OptionsParser.parse(args2);
        assertEquals(0, actual2.length);


        String[] args3 = {"left", "backward", "r", "no", "idk", "b", "Left", "f"};

        Exception ex1 = assertThrows(IllegalArgumentException.class,
                () -> go(args3, animal));
    }

}
