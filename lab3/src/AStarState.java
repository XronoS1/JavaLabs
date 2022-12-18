import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * This class stores the basic state necessary for the A* algorithm to compute a
 * path across a map.  This state includes a collection of "open waypoints" and
 * another collection of "closed waypoints."  In addition, this class provides
 * the basic operations that the A* pathfinding algorithm needs to perform its
 * processing.
 **/
public class AStarState
{
    /** This is a reference to the map that the A* algorithm is navigating. **/
    private Map2D map;

    private Map<Location, Waypoint> openWaypoints = new HashMap<>();

    private Map<Location, Waypoint> closedWaypoints = new HashMap<>();
    
    
    /**
     * Initialize a new state object for the A* pathfinding algorithm to use.
     **/
    public AStarState(Map2D map)
    {
        if (map == null)
            throw new NullPointerException("map cannot be null");

        this.map = map;
    }

    /** Returns the map that the A* pathfinder is navigating. **/
    public Map2D getMap()
    {
        return map;
    }

    /**
     * This method scans through all open waypoints, and returns the waypoint
     * with the minimum total cost.  If there are no open waypoints, this method
     * returns <code>null</code>.
     **/
    public Waypoint getMinOpenWaypoint()
    {
        if (numOpenWaypoints() == 0) {
        	return null;
        }

        Collection<Waypoint> waypoints = openWaypoints.values();

        Waypoint bestWaypoint = null;
        float bestWaypointCost = Float.MAX_VALUE;

        for (Waypoint waypoint : waypoints) {
        	float waypointCost = waypoint.getTotalCost();

        	if (waypointCost < bestWaypointCost) {
        		bestWaypoint = waypoint;
        		bestWaypointCost = waypointCost;
        	}
        }
        
        return bestWaypoint;
    }

    /**
     * This method adds a waypoint to (or potentially updates a waypoint already
     * in) the "open waypoints" collection.  If there is not already an open
     * waypoint at the new waypoint's location then the new waypoint is simply
     * added to the collection.  However, if there is already a waypoint at the
     * new waypoint's location, the new waypoint replaces the old one <em>only
     * if</em> the new waypoint's "previous cost" value is less than the current
     * waypoint's "previous cost" value.
     **/
    public boolean addOpenWaypoint(Waypoint newWaypoint)
    {
        Location location = newWaypoint.getLocation();
     
        // Проверяем есть ли такие координаты среди открытых вершин
        if (openWaypoints.containsKey(location)) {
        	
        	// Если есть, то находим текущую открытую вершину по данным координатам
        	Waypoint currentWaypoint = openWaypoints.get(location);
        	
        	// Если стоимость новой вершины меньше текущей стоимости,
        	// то добавляем новую вершину в список открытых вершин
        	if (newWaypoint.getPreviousCost() < currentWaypoint.getPreviousCost()) {
        		openWaypoints.put(location, newWaypoint);
        		return true;
        		
        	} else {
        		return false;
        	}
        	
        } else {
        	// Если таких координат нет среди открытых вершин,
        	// то добавляем новую вершину в список открытыъ
        	openWaypoints.put(location, newWaypoint);
        	return true;
        }
    }


    /** Returns the current number of open waypoints. **/
    public int numOpenWaypoints()
    {
    	return openWaypoints.size();
    }


    /**
     * This method moves the waypoint at the specified location from the
     * open list to the closed list.
     **/
    public void closeWaypoint(Location loc)
    {
    	// Удаляем вершину из списка открытых вершин
        Waypoint removedWaypoint = openWaypoints.remove(loc);

        // И добавляем ее же в список закрытых вершин
        closedWaypoints.put(loc, removedWaypoint);
    }

    /**
     * Returns true if the collection of closed waypoints contains a waypoint
     * for the specified location.
     **/
    public boolean isLocationClosed(Location loc)
    {
    	// Проверяем, есть ли в списке закрытых вершин требуемые координаты
       	return closedWaypoints.containsKey(loc);
    }
}

