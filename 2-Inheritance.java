import java.util.HashMap;

class UndergroundSystem {

    private HashMap<Integer, CheckIn> checkIns;
    private HashMap<String, Route> routes;

    public UndergroundSystem() {

        checkIns = new HashMap<>();
        routes = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        checkIns.put(id, new CheckIn(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {

        CheckIn checkIn = checkIns.get(id);
        String routeKey = checkIn.station + "->" + stationName;
        int travelTime = t - checkIn.time;
        Route route = routes.getOrDefault(routeKey, new Route());
        route.totalTime += travelTime;
        route.tripCount++;
        routes.put(routeKey, route);
        checkIns.remove(id);
    }

    public double getAverageTime(String startStation, String endStation) {

        String routeKey = startStation + "->" + endStation;

        Route route = routes.get(routeKey);

        return (double) route.totalTime / route.tripCount;
    }

    class CheckIn {

        String station;
        int time;
       CheckIn(String station, int time) {
            this.station = station;
            this.time = time;
        }
    }

    class Route {

        int totalTime;
        int tripCount;
        Route() {
            totalTime = 0;
            tripCount = 0;
        }
    }
}
