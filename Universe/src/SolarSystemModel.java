import java.util.ArrayList;

public class SolarSystemModel extends Fetcher{
    private ArrayList<UnivComp> solarSystemData;

    public SolarSystemModel (int id){solarSystemData = fetchData(id, "galaxy", "solar_system");}

    public ArrayList<UnivComp> getSolarSystemData() {
        return solarSystemData;
    }
}
