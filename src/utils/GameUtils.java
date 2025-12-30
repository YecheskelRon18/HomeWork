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
        items.sort(new Comparator<Item>() {
            @Override
            public int compare(Item a, Item b) {
                return Integer.compare(a.getBuyPrice(), b.getBuyPrice());
            }
        });
    }
    
    /**
     * TODO: מימוש sortItemsByPriceDescending
     * ממיין רשימת פריטים לפי מחיר (מהיקר לזול).
     * השתמש ב-Comparator כמחלקה אנונימית!
     * 
     * @param items רשימת הפריטים למיון
     */
    public static void sortItemsByPriceDescending(ArrayList<Item> items) {
        items.sort(new Comparator<Item>() {
            @Override
            public int compare(Item a, Item b) {
                return Integer.compare(b.getBuyPrice(), a.getBuyPrice());
            }
        });
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
        items.sort(new Comparator<Item>() {
            @Override
            public int compare(Item a, Item b) {
                return Integer.compare((int) (a.getRarity().getPriceMultiplier() * 100), (int) (b.getRarity().getPriceMultiplier() * 100));
            }
        });
    }
    
    /**
     * TODO: מימוש sortItemsByName
     * ממיין רשימת פריטים לפי שם (אלפביתי).
     * 
     * @param items רשימת הפריטים למיון
     */
    public static void sortItemsByName(ArrayList<Item> items) {
        items.sort(new Comparator<Item>() {
            @Override
            public int compare(Item a, Item b) {
                return a.getName().compareTo(b.getName());
            }
        });
    }


    public static void sortItemsByWeight(ArrayList<Item> items) {
        items.sort(new Comparator<Item>() {
            @Override
            public int compare(Item a, Item b) {
                return Integer.compare(a.getWeight(), b.getWeight());
            }
        });
    }

    public static void sortCharactersByHealth(ArrayList<Character> characters) {
        characters.sort(new Comparator<Character>() {
            @Override
            public int compare(Character a, Character b) {
                return Integer.compare(a.getCurrentHealth(), b.getCurrentHealth());
            }
        });
    }

    public static void sortCharactersByLevel(ArrayList<Character> characters) {
        characters.sort(new Comparator<Character>() {
            @Override
            public int compare(Character a, Character b) {
                return Integer.compare(b.getLevel(), a.getLevel());
            }
        });
    }

    public static ArrayList<Item> filterAffordableItems(ArrayList<Item> items, int playerGold) {
        ArrayList<Item> result = new ArrayList<>();
        for (Item item : items) {
            if (item.getBuyPrice() <= playerGold) {
                result.add(item);
            }
        }
        return result;
    }

    public static ArrayList<Item> filterByRarity(ArrayList<Item> items, Item.ItemRarity minRarity) {
        ArrayList<Item> result = new ArrayList<>();
        for (Item item : items) {
            if (item.getRarity().ordinal() >= minRarity.ordinal()) {
                result.add(item);
            }
        }
        return result;
    }

    public static ArrayList<Item> filterLightItems(ArrayList<Item> items, int maxWeight) {
        ArrayList<Item> result = new ArrayList<>();
        for (Item item : items) {
            if (item.getWeight() <= maxWeight) {
                result.add(item);
            }
        }
        return result;
    }

    public static Item findBestItem(ArrayList<Item> items, Comparator<Item> comparator) {
        if (items == null || items.isEmpty()) {
            return null;
        }
        Item best = items.get(0);
        for (int i = 1; i < items.size(); i++) {
            if (comparator.compare(items.get(i), best) > 0) {
                best = items.get(i);
            }
        }
        return best;
    }

    public static int calculateTotalWeight(ArrayList<Item> items) {
        int total = 0;
        for (Item item : items) {
            total += item.getWeight();
        }
        return total;
    }

    public static int calculateTotalValue(ArrayList<Item> items) {
        int total = 0;
        for (Item item : items) {
            total += item.getSellPrice();
        }
        return total;
    }

}
