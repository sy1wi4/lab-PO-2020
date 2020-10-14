package agh.cs.lab1;

import java.util.Arrays;

import static java.lang.System.out;
// teraz zamiast System.out.print() wystarczy out.print()

public class World {
    public static void main(String[] args) {
        // args - tablica Stringów

        out.println("Start");
        Direction[] dirs = convert(args);
        run(dirs);
        out.println("Stop");

    }

    public static void run(Direction[] dirs) {
        for (Direction dir : dirs) {

            switch(dir) {
                case FORWARD:
                    out.println("Zwierzak idzie do przodu");
                    break;
                case BACKWARD:
                    out.println("Zwierzak idzie do tyłu");
                    break;
                case RIGHT:
                    out.println("Zwierzak skręca w prawo");
                    break;
                case LEFT:
                    out.println("Zwierzak skręca w lewo");
                    break;
            }
        }
    }

    // zamiana tablicy łańcuchów znaków na tablicę wartości typu enum
    public static Direction[] convert(String[] args) {

        Direction[] dirs = new Direction[args.length];

        int ctr = 0;
        for (String arg : args){
            switch(arg) {
                case "f":
                    dirs[ctr] = Direction.FORWARD;
                    ctr++;
                    break;
                case "b":
                    dirs[ctr] = Direction.BACKWARD;
                    ctr++;
                    break;
                case "r":
                    dirs[ctr] = Direction.RIGHT;
                    ctr++;
                    break;
                case "l":
                    dirs[ctr] = Direction.LEFT;
                    ctr++;
                    break;
            }
        }
        return Arrays.copyOfRange(dirs,0,ctr);
    }
}
