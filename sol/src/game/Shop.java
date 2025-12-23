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
     * מוסיף פריט לחנות עם כמות מסוימת.
     * 
     * @param item הפריט להוספה
     * @param quantity הכמות
     */
    public void addItemToShop(Item item, int quantity) {
        // Check if item already exists
        boolean found = false;
        for (Item existingItem : inventory) {
            if (existingItem.getName().equals(item.getName())) {
                found = true;
                break;
            }
        }
        
        if (!found) {
            inventory.add(item);
        }
        
        // Update stock
        int currentStock = stock.getOrDefault(item.getName(), 0);
        stock.put(item.getName(), currentStock + quantity);
    }
    
    /**
     * מחזיר רשימה של כל הפריטים הזמינים (שיש מהם במלאי).
     * 
     * @return רשימת פריטים זמינים
     */
    public ArrayList<Item> getAvailableItems() {
        ArrayList<Item> available = new ArrayList<>();
        for (Item item : inventory) {
            if (stock.getOrDefault(item.getName(), 0) > 0) {
                available.add(item);
            }
        }
        return available;
    }
    
    /**
     * מחזיר פריטים לפי קטגוריה (Weapon, Armor, Potion).
     * 
     * @param category שם הקטגוריה ("weapon", "armor", "potion")
     * @return רשימת פריטים מהקטגוריה
     */
    public ArrayList<Item> getItemsByCategory(String category) {
        ArrayList<Item> result = new ArrayList<>();
        for (Item item : getAvailableItems()) {
            switch (category.toLowerCase()) {
                case "weapon":
                    if (item instanceof Weapon) result.add(item);
                    break;
                case "armor":
                    if (item instanceof Armor) result.add(item);
                    break;
                case "potion":
                    if (item instanceof Potion) result.add(item);
                    break;
            }
        }
        return result;
    }
    
    // ============================================================
    // קנייה ומכירה
    // ============================================================
    
    /**
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
        // Find the item
        Item itemToBuy = null;
        for (Item item : inventory) {
            if (item.getName().equals(itemName)) {
                itemToBuy = item;
                break;
            }
        }
        
        if (itemToBuy == null || stock.getOrDefault(itemName, 0) <= 0) {
            throw new ItemNotFoundException(itemName);
        }
        
        int price = itemToBuy.getBuyPrice();
        if (customer.getGold() < price) {
            throw new InsufficientGoldException(price, customer.getGold());
        }
        
        // Perform transaction
        customer.spendGold(price);
        customer.addItem(itemToBuy);
        stock.put(itemName, stock.get(itemName) - 1);
        
        return itemToBuy;
    }
    
    /**
     * השחקן מוכר פריט לחנות.
     * 
     * @param seller השחקן המוכר
     * @param itemName שם הפריט למכירה
     * @return כמות הזהב שהתקבלה
     * @throws ItemNotFoundException אם הפריט לא נמצא במלאי השחקן
     */
    public int sellItem(Character seller, String itemName) 
            throws ItemNotFoundException {
        // Find and remove item from seller
        Item itemToSell = seller.removeItem(itemName);
        
        if (!itemToSell.isSellable()) {
            // Return item to seller and throw
            try {
                seller.addItem(itemToSell);
            } catch (InventoryFullException e) {
                // Should not happen
            }
            throw new ItemNotFoundException(itemName);
        }
        
        int sellPrice = itemToSell.getSellPrice();
        seller.addGold(sellPrice);
        
        // Add to shop inventory
        addItemToShop(itemToSell, 1);
        
        return sellPrice;
    }
    
    /**
     * מחזיר את כמות המלאי של פריט מסוים.
     * 
     * @param itemName שם הפריט
     * @return הכמות במלאי, או 0 אם לא קיים
     */
    public int getItemStock(String itemName) {
        return stock.getOrDefault(itemName, 0);
    }
    
    /**
     * מחשב את הערך הכולל של כל הפריטים בחנות.
     * 
     * @return הערך הכולל
     */
    public int getTotalValue() {
        int total = 0;
        for (Item item : inventory) {
            int quantity = stock.getOrDefault(item.getName(), 0);
            total += item.getBuyPrice() * quantity;
        }
        return total;
    }
    
    // ============================================================
    // דוחות (שימוש ב-HashMap)
    // ============================================================
    
    /**
     * מחזיר HashMap עם דוח מלאי: שם פריט -> מידע (מחיר וכמות).
     * 
     * @return HashMap של (String -> String) בפורמט "Price: X, Stock: Y"
     */
    public HashMap<String, String> getInventoryReport() {
        HashMap<String, String> report = new HashMap<>();
        for (Item item : inventory) {
            int quantity = stock.getOrDefault(item.getName(), 0);
            report.put(item.getName(), 
                String.format("Price: %d, Stock: %d", item.getBuyPrice(), quantity));
        }
        return report;
    }
    
    /**
     * משווה מחירים בין קנייה למכירה.
     * 
     * @return HashMap של (String -> int[]) כאשר [0]=buyPrice, [1]=sellPrice
     */
    public HashMap<String, int[]> getPriceComparison() {
        HashMap<String, int[]> comparison = new HashMap<>();
        for (Item item : inventory) {
            int[] prices = new int[] { item.getBuyPrice(), item.getSellPrice() };
            comparison.put(item.getName(), prices);
        }
        return comparison;
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
