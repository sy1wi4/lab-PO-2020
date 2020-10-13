package agh.cs.lab1;

import static java.lang.System.out;
// teraz zamiast System.out.print() wystarczy out.print()

public class World {
    public static void main(String[] args) {
        // args - tablica Stringów

        out.println("Start");
        run();
        out.println("Stop");

    }

    public static void run() {
        out.println("Do przodu");
    }
}
