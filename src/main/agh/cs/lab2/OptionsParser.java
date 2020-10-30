package agh.cs.lab2;

import agh.cs.lab1.Direction;

import java.util.Arrays;

public class OptionsParser {
    // ?? statyczna żeby móc ją zawołać w main-ie
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
            }
        }
        return Arrays.copyOfRange(dirs,0,ctr);
    }

}

