package agh.cs.labs;

import java.util.Comparator;
import java.util.TreeSet;

// przechowuje informacje o obszarze zajmowanym przez obiekty na mapie
public class MapBoundary implements IPositionChangeObserver{
    private final TreeSet<IMapElement> xSet;
    private final TreeSet<IMapElement> ySet;
    private final IWorldMap map;

    public MapBoundary(IWorldMap map){
        this.xSet = new TreeSet<>(new xComparator());
        this.ySet = new TreeSet<>(new yComparator());
        this.map = map;
    }

    @Override
    public void positionChanged( Vector2d oldPosition, Vector2d newPosition) {

        IMapElement el = (IMapElement) map.objectAt(newPosition);

        // usuwam i dodaję na nowo, by utrzymać posortowanie względem pozycji (która się zmieniła)
        xSet.remove(el);
        ySet.remove(el);

        xSet.add(el);
        ySet.add(el);
    }

    public void add(IMapElement el){
        xSet.add(el);
        ySet.add(el);

    }

    public Vector2d getLeftCorner(){
        Vector2d minX = xSet.first().getPosition();
        Vector2d minY = ySet.first().getPosition();
        return minX.lowerLeft(minY);
    }

    public Vector2d getRightCorner(){
        Vector2d maxX = xSet.last().getPosition();
        Vector2d maxY = ySet.last().getPosition();
        return maxX.upperRight(maxY);
    }
}



class xComparator implements Comparator<IMapElement>{

    @Override
    public int compare(IMapElement o1, IMapElement o2) {
        Vector2d o1Pos = o1.getPosition();
        Vector2d o2Pos = o2.getPosition();
        // komparator zwraca wartość mniejszą od 0, gdy o1 "<" o2 względem kolejno x, y, typu obiektu
        // w p.p. wartość większą od 0
        // nie ma możliwości, by porównywane obiekty były "równe"

        if (o1Pos.x != o2Pos.x) {
            return o1Pos.x - o2Pos.x;

        }
        else {
            if (o1Pos.y != o2Pos.y) {
                return o1Pos.y - o2Pos.y;
            }
            else{
                // na jednej pozycji mogą być maksymalnie 2 obiekty (różnych typów)
                if (o1 instanceof Animal && o2 instanceof Grass){
                    return 1;
                }
                else{
                    return -1;
                }
            }
        }
    }
}

class yComparator implements Comparator<IMapElement>{

    @Override
    public int compare(IMapElement o1, IMapElement o2) {
        Vector2d o1Pos = o1.getPosition();
        Vector2d o2Pos = o2.getPosition();

        if (o1Pos.y != o2Pos.y) {
            return o1Pos.y - o2Pos.y;
        }
        else {
            if (o1Pos.x != o2Pos.x) {
                return o1Pos.x - o2Pos.x;
            }
            else{
                if (o1 instanceof Animal && o2 instanceof Grass){
                    return 1;
                }
                else{
                    return -1;
                }
            }
        }
    }
}
