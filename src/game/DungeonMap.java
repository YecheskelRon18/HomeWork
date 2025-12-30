package game;

import model.exceptions.InvalidActionException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * מחלקה המייצגת את מפת המבוך.
 * משתמשת ב-HashMap למיפוי מזהי מיקום לאובייקטי GameLocation.
 */
public class DungeonMap {
    
    // HashMap ממזהה מיקום לאובייקט המיקום
    private HashMap<String, GameLocation> locations;
    private String currentLocationId;
    private String startLocationId;
    private String bossLocationId;
    
    public DungeonMap() {
        this.locations = new HashMap<>();
        this.currentLocationId = null;
        this.startLocationId = null;
        this.bossLocationId = null;
    }
    
    // ============================================================
    // TODO: ניהול מפה
    // ============================================================
    
    /**
     * TODO: מימוש addLocation
     * מוסיף מיקום חדש למפה.
     * אם זה המיקום הראשון, מגדיר אותו כנקודת ההתחלה.
     * 
     * @param location המיקום להוספה
     */
    public void addLocation(GameLocation location) {
        locations.put(location.getId(), location);
        if (startLocationId == null) {
            startLocationId = location.getId();
            currentLocationId = location.getId();
            location.markAsVisited();
        }
    }


    public void connectLocations(String locationId1, String locationId2)
            throws InvalidActionException {
        if (!locations.containsKey(locationId1) || !locations.containsKey(locationId2)) {
            throw new InvalidActionException("one or two locations do not exist", "");
        }
        locations.get(locationId1).addConnection(locationId2);
        locations.get(locationId2).addConnection(locationId1);
    }


    public GameLocation getLocation(String locationId) {
        return (this.locations.get(locationId));
    }

    public GameLocation getCurrentLocation() {
        return (this.locations.get(currentLocationId));
    }
    

    public void moveTo(String locationId) throws InvalidActionException {
        if (!locations.containsKey(locationId)) {
            throw new InvalidActionException("location does not exist", "");
        }
        GameLocation current = locations.get(currentLocationId);
        if (!current.isConnectedTo(locationId)) {
            throw new InvalidActionException("location is not connected", "");
        }
        currentLocationId = locationId;
        locations.get(locationId).markAsVisited();
    }


    public ArrayList<GameLocation> getAccessibleLocations() {
        ArrayList<GameLocation> arr = new ArrayList<>();

        if (currentLocationId == null) {
            return arr;
        }

        GameLocation current = locations.get(currentLocationId);
        if (current == null) {
            return arr;
        }

        for (String id : current.getConnectedLocationIds()) {
            GameLocation loc = locations.get(id);
            if (loc != null) {
                arr.add(loc);
            }
        }

        return arr;
    }


    public ArrayList<GameLocation> getVisitedLocations() {
        ArrayList<GameLocation> arr = new ArrayList<>();
        for (GameLocation location : locations.values()) {
            if (location.isVisited()) {
                arr.add(location);
            }
        }
        return arr;
    }


    /**
     * TODO: מימוש getUnvisitedLocations
     * מחזיר רשימה של כל המיקומים שעוד לא ביקרנו בהם.
     * 
     * @return רשימת מיקומים לא מבוקרים
     */
    public ArrayList<GameLocation> getUnvisitedLocations() {
        ArrayList<GameLocation> arr = new ArrayList<>();
        for (GameLocation location : locations.values()) {
            if (!location.isVisited()) {
                arr.add(location);
            }
        }
        return arr;
    }


    /**
     * TODO: מימוש getLocationsByDangerLevel
     * מחזיר HashMap שממפה רמת סכנה לרשימת מיקומים.
     * 
     * @return HashMap של (Integer -> ArrayList של GameLocation)
     */
    public HashMap<Integer, ArrayList<GameLocation>> getLocationsByDangerLevel() {
        HashMap<Integer, ArrayList<GameLocation>> map = new HashMap<>();
        for (GameLocation location : locations.values()) {
            int danger = location.getDangerLevel();
            if (!map.containsKey(danger)) {
                map.put(danger, new ArrayList<>());
            }
            map.get(danger).add(location);
        }
        return map;
    }


    /**
     * TODO: מימוש getExplorationProgress
     * מחזיר את אחוז ההתקדמות בחקירת המפה.
     * 
     * @return אחוז בין 0.0 ל-1.0
     */
    public double getExplorationProgress() {
        if (locations.isEmpty()) {
            return 0.0;
        }
        int visited = 0;
        for (GameLocation location : locations.values()) {
            if (location.isVisited()) {
                visited++;
            }
        }
        return (double) visited / locations.size();
    }


    // Setters for special locations
    public void setStartLocation(String locationId) {
        this.startLocationId = locationId;
        this.currentLocationId = locationId;
        if (locations.containsKey(locationId)) {
            locations.get(locationId).markAsVisited();
        }
    }
    
    public void setBossLocation(String locationId) {
        this.bossLocationId = locationId;
        if (locations.containsKey(locationId)) {
            locations.get(locationId).setHasMaster(true);
        }
    }
    
    // Getters
    public String getCurrentLocationId() {
        return currentLocationId;
    }
    
    public String getStartLocationId() {
        return startLocationId;
    }
    
    public String getBossLocationId() {
        return bossLocationId;
    }
    
    public int getTotalLocations() {
        return locations.size();
    }
    
    public HashMap<String, GameLocation> getAllLocations() {
        return new HashMap<>(locations);
    }
}
