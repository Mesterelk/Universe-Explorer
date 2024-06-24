import java.util.ArrayList;

public class SSInnerModel extends Fetcher{
    private ArrayList<UnivComp>  planets, suns, moons = new ArrayList<>();

    public void fetchSolarSystemDataFromDatabase(int sSS) {
        planets = fetchData(sSS, "solar_system", "planet");
        suns = fetchData(sSS, "solar_system", "sun");

        for (UnivComp forid: planets) {
            ArrayList<UnivComp> moonsPlanet = fetchData(forid.id(), "planet", "moon");
            moons.addAll(moonsPlanet);
        }
    }
    public ArrayList<UnivComp> getPlanets() {return planets;}
    public ArrayList<UnivComp> getSuns() {return suns; }
    public ArrayList<UnivComp> getMoons() {return moons; }
}