import java.util.ArrayList;

public class GalaxyModel extends Fetcher{
    private ArrayList<UnivComp> galaxyData;

    public GalaxyModel(int id){
        galaxyData = fetchData(id,"universe", "galaxy");
    }
    public ArrayList<UnivComp> getGalaxyData() {
        return galaxyData;
    }
}
