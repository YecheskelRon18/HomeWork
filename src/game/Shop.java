package game;

import model.characters.Character;
import model.items.Item;
import model.items.Weapon;
import model.items.Armor;
import model.items.Potion;
import model.exceptions.InventoryFullException;
import model.exceptions.ItemNotFoundException;
import model.exceptions.InsufficientGoldException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * מחלקה המייצגת חנות במשחק.
 * מאפשרת קנייה ומכירה של פריטים.
 */
public class Shop {
    
    private String name;
    private ArrayList<Item> inventory;
    private HashMap<String, Integer> stock; // מיפוי שם פריט לכמות במלאי
    
    public Shop(String name) {
        this.name = name;
        this.inventory = new ArrayList<>();
        this.stock = new HashMap<>();
    }
    
    // ============================================================
    // TODO: ניהול מלאי החנות
    // ============================================================
    
    /**
     * TODO: מימוש addItemToShop
     * מוסיף פריט לחנות עם כמות מסוימת.
     * 
     * @param item הפריט להוספה
     * @param quantity הכמות
     */
    public void addItemToShop(Item item, int quantity) {
        // TODO: Implement this method
        // 1. הוסף את הפריט ל-inventory (אם לא קיים)
        // 2. עדכן את הכמות ב-stock (הוסף לכמות הקיימת או צור חדש)
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    /**
     * TODO: מימוש getAvailableItems
     * מחזיר רשימה של כל הפריטים הזמינים (שיש מהם במלאי).
     * 
     * @return רשימת פריטים זמינים
     */
    public ArrayList<Item> getAvailableItems() {
        // TODO: Implement this method
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    /**
     * TODO: מימוש getItemsByCategory
     * מחזיר פריטים לפי קטגוריה (Weapon, Armor, Potion).
     * השתמש ב-instanceof.
     * 
     * @param category שם הקטגוריה ("weapon", "armor", "potion")
     * @return רשימת פריטים מהקטגוריה
     */
    public ArrayList<Item> getItemsByCategory(String category) {
        // TODO: Implement this method
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    // ============================================================
    // TODO: קנייה ומכירה
    // ============================================================
    
    /**
     * TODO: מימוש buyItem
     * השחקן קונה פריט מהחנות.
     * 
     * @param customer השחקן הקונה
     * @param itemName שם הפריט לקנייה
     * @return הפריט שנקנה
     * @throws ItemNotFoundException אם הפריט לא קיים בחנות
     * @throws InsufficientGoldException אם אין מספיק זהב
     * @throws InventoryFullException אם המלאי של השחקן מלא
     */
    public Item buyItem(Character customer, String itemName) 
            throws ItemNotFoundException, InsufficientGoldException, 
                   InventoryFullException {
        // TODO: Implement this method
        // 1. חפש את הפריט ב-inventory לפי שם
        // 2. בדוק שיש מלאי (stock > 0)
        // 3. בדוק שיש לשחקן מספיק זהב
        // 4. בדוק שיש מקום במלאי של השחקן
        // 5. בצע את העסקה: הורד זהב, הוסף פריט לשחקן, הפחת מלאי
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    /**
     * TODO: מימוש sellItem
     * השחקן מוכר פריט לחנות.
     * 
     * @param seller השחקן המוכר
     * @param itemName שם הפריט למכירה
     * @return כמות הזהב שהתקבלה
     * @throws ItemNotFoundException אם הפריט לא נמצא במלאי השחקן
     * @throws InvalidActionException אם הפריט לא ניתן למכירה
     */
    public int sellItem(Character seller, String itemName) 
            throws ItemNotFoundException {
        // TODO: Implement this method
        // 1. חפש את הפריט במלאי השחקן
        // 2. בדוק שהפריט ניתן למכירה (isSellable)
        // 3. הסר מהשחקן והוסף לחנות
        // 4. תן לשחקן את הזהב (getSellPrice)
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    /**
     * TODO: מימוש getItemStock
     * מחזיר את כמות המלאי של פריט מסוים.
     * 
     * @param itemName שם הפריט
     * @return הכמות במלאי, או 0 אם לא קיים
     */
    public int getItemStock(String itemName) {
        // TODO: Implement this method
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    /**
     * TODO: מימוש getTotalValue
     * מחשב את הערך הכולל של כל הפריטים בחנות.
     * 
     * @return הערך הכולל
     */
    public int getTotalValue() {
        // TODO: Implement this method
        // סכום של (מחיר קנייה * כמות) לכל פריט
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    // ============================================================
    // TODO: דוחות (שימוש ב-HashMap)
    // ============================================================
    
    /**
     * TODO: מימוש getInventoryReport
     * מחזיר HashMap עם דוח מלאי: שם פריט -> מידע (מחיר וכמות).
     * 
     * @return HashMap של (String -> String) בפורמט "Price: X, Stock: Y"
     */
    public HashMap<String, String> getInventoryReport() {
        // TODO: Implement this method
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    /**
     * TODO: מימוש getPriceComparison
     * משווה מחירים בין קנייה למכירה.
     * 
     * @return HashMap של (String -> int[]) כאשר [0]=buyPrice, [1]=sellPrice
     */
    public HashMap<String, int[]> getPriceComparison() {
        // TODO: Implement this method
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    // Getters
    public String getName() {
        return name;
    }
    
    public int getUniqueItemCount() {
        return inventory.size();
    }
    
    public int getTotalItemCount() {
        int total = 0;
        for (int count : stock.values()) {
            total += count;
        }
        return total;
    }
    
    @Override
    public String toString() {
        return String.format("Shop: %s | Items: %d unique, %d total",
            name, getUniqueItemCount(), getTotalItemCount());
    }
}
