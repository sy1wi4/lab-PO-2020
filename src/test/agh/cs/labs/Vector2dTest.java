package agh.cs.labs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {

    @Test
    public void equalsTest() {
        assertTrue(new Vector2d(2, 1).equals(new Vector2d(2, 1)));
        assertFalse(new Vector2d(2, 1).equals(new Vector2d(0, 1)));
    }

    @Test
    public void toStringTest() {
        assertEquals("(1, 2)", new Vector2d(1, 2).toString());
        assertEquals("(5, 5)", new Vector2d(5, 5).toString());
        assertEquals("(-101, 200)", new Vector2d(-101, 200).toString());
    }

    @Test
    public void precedesTest() {
        assertTrue(new Vector2d(2, 1).precedes(new Vector2d(2, 3)));
        assertTrue(new Vector2d(-10, 66).precedes(new Vector2d(-10, 66)));
        assertFalse(new Vector2d(5, 0).precedes(new Vector2d(6, -1)));
    }

    @Test
    public void followsTest() {
        assertFalse(new Vector2d(2, 1).follows(new Vector2d(2, 3)));
        assertTrue(new Vector2d(-10, 66).follows(new Vector2d(-10, 66)));
        assertFalse(new Vector2d(5, 0).follows(new Vector2d(6, -1)));
    }

    @Test
    public void upperRightTest() {
        assertEquals(new Vector2d(16, 40), new Vector2d(15, 34).upperRight(new Vector2d(16, 40)));
        assertEquals(new Vector2d(3, 9), new Vector2d(0, 9).upperRight(new Vector2d(3, -1)));
        assertEquals(new Vector2d(200, 999), new Vector2d(100, 999).upperRight(new Vector2d(200, 999)));
    }

    @Test
    public void lowerLeftTest() {
        assertEquals(new Vector2d(15, 34), new Vector2d(15, 34).lowerLeft(new Vector2d(16, 40)));
        assertEquals(new Vector2d(0, -1), new Vector2d(0, 9).lowerLeft(new Vector2d(3, -1)));
        assertEquals(new Vector2d(100, 999), new Vector2d(100, 999).lowerLeft(new Vector2d(200, 999)));
    }

    @Test
    public void addTest() {
        assertEquals(new Vector2d(31, 74), new Vector2d(15, 34).add(new Vector2d(16, 40)));
        assertEquals(new Vector2d(3, 8), new Vector2d(0, 9).add(new Vector2d(3, -1)));
        assertEquals(new Vector2d(0, 0), new Vector2d(-1, 1).add(new Vector2d(1, -1)));
    }

    @Test
    public void subtractTest() {
        assertEquals(new Vector2d(-1, -6), new Vector2d(15, 34).subtract(new Vector2d(16, 40)));
        assertEquals(new Vector2d(-3, 10), new Vector2d(0, 9).subtract(new Vector2d(3, -1)));
        assertEquals(new Vector2d(-2, 2), new Vector2d(-1, 1).subtract(new Vector2d(1, -1)));
    }

    @Test
    public void oppositeTest(){
        assertEquals(new Vector2d(-15,-34),new Vector2d(15,34).opposite());
        assertEquals(new Vector2d(0,-9),new Vector2d(0,9).opposite());
        assertEquals(new Vector2d(1,2),new Vector2d(-1,-2).opposite());
    }

}
