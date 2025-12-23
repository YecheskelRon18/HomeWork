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
     * מוסיף מיקום חדש למפה.
     * אם זה המיקום הראשון, מגדיר אותו כנקודת ההתחלה.
     * 
     * @param location המיקום להוספה
     */
    public void addLocation(GameLocation location) {
        locations.put(location.getId(), location);
        if (startLocationId == null) {
            setStartLocation(location.getId());
        }
    }
    
    /**
     * מחבר שני מיקומים זה לזה (דו-כיווני).
     * 
     * @param locationId1 מזהה מיקום ראשון
     * @param locationId2 מזהה מיקום שני
     * @throws InvalidActionException אם אחד המיקומים לא קיים
     */
    public void connectLocations(String locationId1, String locationId2) 
            throws InvalidActionException {
        if (!locations.containsKey(locationId1)) {
            throw new InvalidActionException("connect", "Location " + locationId1 + " does not exist");
        }
        if (!locations.containsKey(locationId2)) {
            throw new InvalidActionException("connect", "Location " + locationId2 + " does not exist");
        }
        
        locations.get(locationId1).addConnection(locationId2);
        locations.get(locationId2).addConnection(locationId1);
    }
    
    /**
     * מחזיר מיקום לפי מזהה.
     * 
     * @param locationId מזהה המיקום
     * @return המיקום, או null אם לא קיים
     */
    public GameLocation getLocation(String locationId) {
        return locations.get(locationId);
    }
    
    /**
     * מחזיר את המיקום הנוכחי.
     * 
     * @return המיקום הנוכחי
     */
    public GameLocation getCurrentLocation() {
        return locations.get(currentLocationId);
    }
    
    /**
     * מזיז את השחקן למיקום אחר.
     * ניתן לזוז רק למיקום מחובר!
     * 
     * @param locationId מזהה המיקום החדש
     * @throws InvalidActionException אם המיקום לא קיים או לא מחובר
     */
    public void moveTo(String locationId) throws InvalidActionException {
        if (!locations.containsKey(locationId)) {
            throw new InvalidActionException("move", "Location " + locationId + " does not exist");
        }
        
        GameLocation current = getCurrentLocation();
        if (!current.isConnectedTo(locationId)) {
            throw new InvalidActionException("move", "Cannot reach " + locationId + " from current location");
        }
        
        currentLocationId = locationId;
        locations.get(locationId).markAsVisited();
    }
    
    /**
     * מחזיר רשימה של כל המיקומים שניתן להגיע אליהם מהמיקום הנוכחי.
     * 
     * @return רשימת מיקומים נגישים
     */
    public ArrayList<GameLocation> getAccessibleLocations() {
        ArrayList<GameLocation> accessible = new ArrayList<>();
        GameLocation current = getCurrentLocation();
        
        if (current != null) {
            for (String id : current.getConnectedLocationIds()) {
                GameLocation loc = locations.get(id);
                if (loc != null) {
                    accessible.add(loc);
                }
            }
        }
        return accessible;
    }
    
    /**
     * מחזיר רשימה של כל המיקומים שכבר ביקרנו בהם.
     * 
     * @return רשימת מיקומים מבוקרים
     */
    public ArrayList<GameLocation> getVisitedLocations() {
        ArrayList<GameLocation> visited = new ArrayList<>();
        for (GameLocation loc : locations.values()) {
            if (loc.isVisited()) {
                visited.add(loc);
            }
        }
        return visited;
    }
    
    /**
     * מחזיר רשימה של כל המיקומים שעוד לא ביקרנו בהם.
     * 
     * @return רשימת מיקומים לא מבוקרים
     */
    public ArrayList<GameLocation> getUnvisitedLocations() {
        ArrayList<GameLocation> unvisited = new ArrayList<>();
        for (GameLocation loc : locations.values()) {
            if (!loc.isVisited()) {
                unvisited.add(loc);
            }
        }
        return unvisited;
    }
    
    /**
     * מחזיר HashMap שממפה רמת סכנה לרשימת מיקומים.
     * 
     * @return HashMap של (Integer -> ArrayList של GameLocation)
     */
    public HashMap<Integer, ArrayList<GameLocation>> getLocationsByDangerLevel() {
        HashMap<Integer, ArrayList<GameLocation>> byDanger = new HashMap<>();
        for (GameLocation loc : locations.values()) {
            int danger = loc.getDangerLevel();
            if (!byDanger.containsKey(danger)) {
                byDanger.put(danger, new ArrayList<>());
            }
            byDanger.get(danger).add(loc);
        }
        return byDanger;
    }
    
    /**
     * מחזיר את אחוז ההתקדמות בחקירת המפה.
     * 
     * @return אחוז בין 0.0 ל-1.0
     */
    public double getExplorationProgress() {
        if (locations.isEmpty()) {
            return 0.0;
        }
        int visited = 0;
        for (GameLocation loc : locations.values()) {
            if (loc.isVisited()) {
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
