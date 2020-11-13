package agh.cs.labs;

public class World {

    public static void main(String[] args) {
//        MoveDirection[] directions = new OptionsParser().parse(args);
//        IWorldMap map = new RectangularMap(5, 5);
//        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
//        IEngine engine = new SimulationEngine(directions, map, positions);
//
//        System.out.println(map.toString());
//        engine.run();
//        System.out.println(map.toString());

        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new GrassField(10);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);

        System.out.println(map.toString());
        engine.run();
        System.out.println(map.toString());


    }
}