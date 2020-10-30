package agh.cs.lab2;

public class World {

    public static void main(String[] args) {
        Animal animal = new Animal();
        System.out.println("start location:\n " + animal.toString() + "\n");
        go(args, animal);
        System.out.println("finish location:\n" + animal.toString() + "\n");

    }
    public static void go(String args[], Animal animal) {
        MoveDirection[] dirs = OptionsParser.parse(args);

        for (MoveDirection dir : dirs)
            animal.move(dir);
    }
}