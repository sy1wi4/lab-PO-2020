package agh.cs.lab2;


public class Animal {
    // początkowe wartości przypisane bezpośrednio do pól
    // można tez w konstruktorze

    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2, 2);

    // pomocnicze wektory uniemożliwiające wyjście poza mapę
    private Vector2d rightCorner = new Vector2d(4,4);
    private Vector2d leftCorner = new Vector2d(0,0);


    public String toString() {
        String orient = this.orientation.toString();
        String pos = this.position.toString();

        // informacja o położeniu zwierzęcia
        return pos + " " + orient;
    }

    public MapDirection getOrientation() {
        return this.orientation;
    }

    public Vector2d getPosition() {
        return this.position;
    }

    public void canMove(Vector2d newPosition) {
        if (newPosition.precedes(rightCorner) && newPosition.follows(leftCorner))
            this.position = newPosition;
    }


    public void move(MoveDirection direction) {

        Vector2d newPosition;
        switch (direction) {

            case RIGHT:
                this.orientation = this.orientation.next();
                break;

            case LEFT:
                this.orientation = this.orientation.previous();
                break;

            case FORWARD:
                newPosition = position.add(orientation.toUnitVector());
                canMove(newPosition);
                break;

            case BACKWARD:
                newPosition = position.subtract(orientation.toUnitVector());
                canMove(newPosition);
                break;
        }
    }
}
