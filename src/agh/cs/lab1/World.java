package agh.cs.lab1;

import static java.lang.System.out;
// teraz zamiast System.out.print() wystarczy out.print()

public class World {
    public static void main(String[] args) {
        // args - tablica Stringów

        out.println("Start");
        run(args);
        out.println("Stop");

    }

    public static void run(String[] args) {
        for (String arg : args) {

            switch(arg) {
                case "f":
                    out.println("Zwierzak idzie do przodu");
                    break;
                case "b":
                    out.println("Zwierzak idzie do tyłu");
                    break;
                case "r":
                    out.println("Zwierzak skręca w prawo");
                    break;
                case "l":
                    out.println("Zwierzak skręca w lewo");
                    break;
            }
        }
    }
}
