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
        // TODO: Implement this method
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    /**
     * TODO: מימוש connectLocations
     * מחבר שני מיקומים זה לזה (דו-כיווני).
     * 
     * @param locationId1 מזהה מיקום ראשון
     * @param locationId2 מזהה מיקום שני
     * @throws InvalidActionException אם אחד המיקומים לא קיים
     */
    public void connectLocations(String locationId1, String locationId2) 
            throws InvalidActionException {
        // TODO: Implement this method
        // 1. בדוק שני המיקומים קיימים
        // 2. הוסף חיבור דו-כיווני
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    /**
     * TODO: מימוש getLocation
     * מחזיר מיקום לפי מזהה.
     * 
     * @param locationId מזהה המיקום
     * @return המיקום, או null אם לא קיים
     */
    public GameLocation getLocation(String locationId) {
        // TODO: Implement this method
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    /**
     * TODO: מימוש getCurrentLocation
     * מחזיר את המיקום הנוכחי.
     * 
     * @return המיקום הנוכחי
     */
    public GameLocation getCurrentLocation() {
        // TODO: Implement this method
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    /**
     * TODO: מימוש moveTo
     * מזיז את השחקן למיקום אחר.
     * ניתן לזוז רק למיקום מחובר!
     * 
     * @param locationId מזהה המיקום החדש
     * @throws InvalidActionException אם המיקום לא קיים או לא מחובר
     */
    public void moveTo(String locationId) throws InvalidActionException {
        // TODO: Implement this method
        // 1. בדוק שהמיקום קיים
        // 2. בדוק שהמיקום הנוכחי מחובר למיקום החדש
        // 3. עדכן את currentLocationId
        // 4. סמן את המיקום החדש כמבוקר
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    /**
     * TODO: מימוש getAccessibleLocations
     * מחזיר רשימה של כל המיקומים שניתן להגיע אליהם מהמיקום הנוכחי.
     * 
     * @return רשימת מיקומים נגישים
     */
    public ArrayList<GameLocation> getAccessibleLocations() {
        // TODO: Implement this method
        // עבור על כל ה-connectedLocationIds של המיקום הנוכחי
        // והחזר רשימה של האובייקטים המתאימים
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    /**
     * TODO: מימוש getVisitedLocations
     * מחזיר רשימה של כל המיקומים שכבר ביקרנו בהם.
     * 
     * @return רשימת מיקומים מבוקרים
     */
    public ArrayList<GameLocation> getVisitedLocations() {
        // TODO: Implement this method
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    /**
     * TODO: מימוש getUnvisitedLocations
     * מחזיר רשימה של כל המיקומים שעוד לא ביקרנו בהם.
     * 
     * @return רשימת מיקומים לא מבוקרים
     */
    public ArrayList<GameLocation> getUnvisitedLocations() {
        // TODO: Implement this method
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    /**
     * TODO: מימוש getLocationsByDangerLevel
     * מחזיר HashMap שממפה רמת סכנה לרשימת מיקומים.
     * 
     * @return HashMap של (Integer -> ArrayList של GameLocation)
     */
    public HashMap<Integer, ArrayList<GameLocation>> getLocationsByDangerLevel() {
        // TODO: Implement this method
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    /**
     * TODO: מימוש getExplorationProgress
     * מחזיר את אחוז ההתקדמות בחקירת המפה.
     * 
     * @return אחוז בין 0.0 ל-1.0
     */
    public double getExplorationProgress() {
        // TODO: Implement this method
        // מספר מיקומים מבוקרים / סך כל המיקומים
        throw new UnsupportedOperationException("Not implemented yet");
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
