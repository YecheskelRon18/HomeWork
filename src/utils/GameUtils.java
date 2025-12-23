package utils;

import model.characters.Character;
import model.items.Item;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * מחלקת עזר עם פונקציות שימושיות למשחק.
 * כאן נתרגל שימוש במחלקות אנונימיות ו-Comparator.
 */
public class GameUtils {
    
    // ============================================================
    // TODO: מיון פריטים (שימוש במחלקות אנונימיות)
    // ============================================================
    
    /**
     * TODO: מימוש sortItemsByPrice
     * ממיין רשימת פריטים לפי מחיר (מהזול ליקר).
     * השתמש ב-Comparator כמחלקה אנונימית!
     * 
     * @param items רשימת הפריטים למיון
     */
    public static void sortItemsByPrice(ArrayList<Item> items) {
        // TODO: Implement this method using anonymous Comparator
        // items.sort(new Comparator<Item>() { ... });
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    /**
     * TODO: מימוש sortItemsByPriceDescending
     * ממיין רשימת פריטים לפי מחיר (מהיקר לזול).
     * השתמש ב-Comparator כמחלקה אנונימית!
     * 
     * @param items רשימת הפריטים למיון
     */
    public static void sortItemsByPriceDescending(ArrayList<Item> items) {
        // TODO: Implement this method using anonymous Comparator
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    /**
     * TODO: מימוש sortItemsByRarity
     * ממיין רשימת פריטים לפי נדירות (מהנפוץ לנדיר ביותר).
     * סדר הנדירות: COMMON < UNCOMMON < RARE < EPIC < LEGENDARY
     * השתמש ב-ordinal() של ה-enum!
     * 
     * @param items רשימת הפריטים למיון
     */
    public static void sortItemsByRarity(ArrayList<Item> items) {
        // TODO: Implement this method using anonymous Comparator
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    /**
     * TODO: מימוש sortItemsByName
     * ממיין רשימת פריטים לפי שם (אלפביתי).
     * 
     * @param items רשימת הפריטים למיון
     */
    public static void sortItemsByName(ArrayList<Item> items) {
        // TODO: Implement this method using anonymous Comparator
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    /**
     * TODO: מימוש sortItemsByWeight
     * ממיין רשימת פריטים לפי משקל (מהקל לכבד).
     * 
     * @param items רשימת הפריטים למיון
     */
    public static void sortItemsByWeight(ArrayList<Item> items) {
        // TODO: Implement this method using anonymous Comparator
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    // ============================================================
    // TODO: מיון דמויות
    // ============================================================
    
    /**
     * TODO: מימוש sortCharactersByHealth
     * ממיין רשימת דמויות לפי בריאות נוכחית (מהנמוך לגבוה).
     * 
     * @param characters רשימת הדמויות למיון
     */
    public static void sortCharactersByHealth(ArrayList<Character> characters) {
        // TODO: Implement this method using anonymous Comparator
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    /**
     * TODO: מימוש sortCharactersByLevel
     * ממיין רשימת דמויות לפי רמה (מהגבוה לנמוך).
     * 
     * @param characters רשימת הדמויות למיון
     */
    public static void sortCharactersByLevel(ArrayList<Character> characters) {
        // TODO: Implement this method using anonymous Comparator
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    // ============================================================
    // TODO: סינון (Filtering)
    // ============================================================
    
    /**
     * ממשק פונקציונלי לסינון פריטים.
     */
    public interface ItemFilter {
        boolean accept(Item item);
    }
    
    /**
     * TODO: מימוש filterItems
     * מסנן רשימת פריטים לפי תנאי מסוים.
     * 
     * @param items רשימת הפריטים
     * @param filter הפילטר
     * @return רשימה חדשה עם הפריטים שעברו את הסינון
     */
    public static ArrayList<Item> filterItems(ArrayList<Item> items, ItemFilter filter) {
        // TODO: Implement this method
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    /**
     * TODO: מימוש filterAffordableItems
     * מסנן פריטים שהשחקן יכול לקנות.
     * השתמש ב-filterItems עם מחלקה אנונימית!
     * 
     * @param items רשימת הפריטים
     * @param playerGold כמות הזהב של השחקן
     * @return רשימת פריטים שניתן לקנות
     */
    public static ArrayList<Item> filterAffordableItems(ArrayList<Item> items, int playerGold) {
        // TODO: Implement this method using anonymous ItemFilter
        // return filterItems(items, new ItemFilter() { ... });
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    /**
     * TODO: מימוש filterByRarity
     * מסנן פריטים לפי נדירות מינימלית.
     * 
     * @param items רשימת הפריטים
     * @param minRarity הנדירות המינימלית
     * @return רשימת פריטים בנדירות המבוקשת או יותר
     */
    public static ArrayList<Item> filterByRarity(ArrayList<Item> items, 
                                                  Item.ItemRarity minRarity) {
        // TODO: Implement this method using anonymous ItemFilter
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    /**
     * TODO: מימוש filterLightItems
     * מסנן פריטים קלים (עד משקל מסוים).
     * 
     * @param items רשימת הפריטים
     * @param maxWeight המשקל המקסימלי
     * @return רשימת פריטים קלים
     */
    public static ArrayList<Item> filterLightItems(ArrayList<Item> items, int maxWeight) {
        // TODO: Implement this method using anonymous ItemFilter
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    // ============================================================
    // TODO: פונקציות עזר נוספות
    // ============================================================
    
    /**
     * TODO: מימוש findBestItem
     * מוצא את הפריט ה"טוב ביותר" לפי קריטריון מסוים.
     * 
     * @param items רשימת הפריטים
     * @param comparator הקריטריון להשוואה
     * @return הפריט הטוב ביותר, או null אם הרשימה ריקה
     */
    public static Item findBestItem(ArrayList<Item> items, Comparator<Item> comparator) {
        // TODO: Implement this method
        // עבור על כל הפריטים ומצא את ה"גדול" ביותר לפי ה-comparator
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    /**
     * TODO: מימוש calculateTotalWeight
     * מחשב את המשקל הכולל של כל הפריטים ברשימה.
     * 
     * @param items רשימת הפריטים
     * @return המשקל הכולל
     */
    public static int calculateTotalWeight(ArrayList<Item> items) {
        // TODO: Implement this method
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    /**
     * TODO: מימוש calculateTotalValue
     * מחשב את הערך הכולל של כל הפריטים (לפי מחיר מכירה).
     * 
     * @param items רשימת הפריטים
     * @return הערך הכולל
     */
    public static int calculateTotalValue(ArrayList<Item> items) {
        // TODO: Implement this method
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
