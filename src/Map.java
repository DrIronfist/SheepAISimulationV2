import java.util.ArrayList;
import java.util.List;

public class Map {
    public List<Object>[][] objectHolder;
    public int width = 100;

    public Map(){
        objectHolder = new List[width][width];
        for(int x= 0; x < width; x++){
            for(int y = 0; y < width; y++){
                objectHolder[x][y] = new ArrayList<Object>();
            }
        }
    }

}
