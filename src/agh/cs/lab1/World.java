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
        out.println("Do przodu");

        // wypisz wartości wszystkich argumentów tej metody oddzielone przecinkami

        int ctr = 0;
        for (String arg : args) {

            if (ctr == args.length - 1) {
                // ostatni wypisuję bez przecinka
                out.print(arg);
            }
            else out.print(arg + ",");

            ctr++;
        }
        out.println("");
    }
}
