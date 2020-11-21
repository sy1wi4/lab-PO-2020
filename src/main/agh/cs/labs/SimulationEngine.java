package agh.cs.labs;


import java.util.ArrayList;
import java.util.List;


public class SimulationEngine implements IEngine{
    private final MoveDirection[] directions;
    private final IWorldMap map;
    private final Vector2d[] positions;
    private final List<Animal> animals;



    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] positions){
        this.directions = directions;
        this.map = map;
        this.positions = positions;
        this.animals  = new ArrayList<>();

        // dla każdego wektora umieszczam zwierzę na mapie, jeśli jeszcze go tam nie ma
        for (Vector2d position : this.positions){
            Animal newAnimal = new Animal(this.map,position);
            if (this.map.place(newAnimal)){
                // jeśli zwierzę zostało umieszczone na mapie, możemy dodać je do listy zwierząt w tej klasie
                animals.add(newAnimal);
            }
        }
    }


    @Override
    public void run() {

        int i = 0;
        for (MoveDirection direction : directions){

            if (i == animals.size()) {
                i = 0;
            }

            Animal currentAnimal = animals.get(i);

            currentAnimal.move(direction);

            i++;
        }
    }
}
