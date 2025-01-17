package agh.cs.labs;

import java.util.Arrays;

public class OptionsParser {

    public static MoveDirection[] parse(String[] args){
        MoveDirection[] dirs = new MoveDirection[args.length];

        int ctr = 0;

        for (String arg : args){
            switch(arg) {

                case "f":
                case "forward":
                    dirs[ctr] = MoveDirection.FORWARD;
                    ctr++;
                    break;

                case "b":
                case "backward":
                    dirs[ctr] = MoveDirection.BACKWARD;
                    ctr++;
                    break;

                case "r":
                case "right":
                    dirs[ctr] = MoveDirection.RIGHT;
                    ctr++;
                    break;

                case "l":
                case "left":
                    dirs[ctr] = MoveDirection.LEFT;
                    ctr++;
                    break;
                default:
                    throw new IllegalArgumentException(arg + " is not legal move specification");
            }
        }
        return Arrays.copyOfRange(dirs,0,ctr);
    }

}

