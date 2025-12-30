# Dungeon Adventure - תרגיל מסכם בג'אווה

## תיאור הפרויקט
פרויקט משחק הרפתקה טקסטואלי הכולל דמויות, פריטים, קרבות ומפת מבוך.

## נושאים נלמדים
הפרויקט מתרגל את הנושאים הבאים:
- **ArrayList** - ניהול מלאי, רשימות פריטים
- **HashMap** - מיפוי מיקומים במפה, ציוד לפי slot, פריטים לפי נדירות
- **Queue** - תור פעולות במערכת הקרב
- **Stack** - מעקב אחר פריטים אחרונים שנעשה בהם שימוש
- **ירושה (Inheritance)** - Character ← Warrior/Mage/Archer, Item ← Weapon/Armor/Potion
- **ממשקים (Interfaces)** - Attackable, Usable, Tradeable
- **מחלקה אנונימית** - Comparator למיון, ActionFilter לסינון
- **Exceptions** - InventoryFullException, InvalidActionException, ItemNotFoundException

## מבנה הפרויקט
```
DungeonAdventure/
├── src/
│   ├── model/
│   │   ├── characters/
│   │   │   ├── Attackable.java        # ממשק ליצורים שניתן לתקוף
│   │   │   ├── Character.java         # מחלקה בסיסית לדמויות
│   │   │   ├── Warrior.java           # לוחם
│   │   │   ├── Mage.java              # קוסם
│   │   │   └── Archer.java            # קשת
│   │   ├── items/
│   │   │   ├── Tradeable.java         # ממשק לפריטים שניתן לסחור
│   │   │   ├── Usable.java            # ממשק לפריטים שניתן להשתמש
│   │   │   ├── Item.java              # מחלקה בסיסית לפריטים
│   │   │   ├── Weapon.java            # נשק
│   │   │   ├── Armor.java             # שריון
│   │   │   └── Potion.java            # שיקוי
│   │   └── exceptions/
│   │       ├── InventoryFullException.java
│   │       ├── InvalidActionException.java
│   │       ├── ItemNotFoundException.java
│   │       └── InsufficientGoldException.java
│   ├── game/
│   │   ├── Game.java                  # המחלקה הראשית
│   │   ├── GameLocation.java          # מיקום במפה
│   │   ├── DungeonMap.java            # מפת המבוך
│   │   ├── BattleAction.java          # פעולת קרב
│   │   ├── BattleSystem.java          # מערכת הקרב
│   │   └── Shop.java                  # חנות
│   ├── utils/
│   │   └── GameUtils.java             # פונקציות עזר (מיון, סינון)
│   └── TestProject.java               # בדיקות
```

## הוראות הידור והרצה

### הידור
```bash
# יצירת תיקיית פלט
mkdir -p out

# הידור כל הקבצים
javac -d out src/model/exceptions/*.java src/model/items/*.java src/model/characters/*.java src/game/*.java src/utils/*.java src/TestProject.java
```

### הרצת הבדיקות
```bash
java -cp out TestProject
```

### הרצת המשחק
```bash
java -cp out game.Game
```

## מה צריך להשלים (TODO)

כל המקומות שמסומנים ב-TODO צריכים להיות מושלמים על ידי הסטודנט.

### שלב 1: פריטים בסיסיים (Item.java)
- [ ] `getBuyPrice()` - חישוב מחיר קנייה עם מכפיל נדירות
- [ ] `getSellPrice()` - חישוב מחיר מכירה (50% מקנייה)
- [ ] `isSellable()` - בדיקה אם פריט ניתן למכירה

### שלב 2: נשק ושריון (Weapon.java, Armor.java)
- [ ] `calculateDamage()` - נזק אקראי בין min ל-max
- [ ] `getAverageDamage()` - ממוצע נזק
- [ ] `calculateDamageReduction()` - חישוב הפחתת נזק
- [ ] `reduceDamage()` - יישום הפחתת נזק

### שלב 3: שיקויים - ממשק Usable (Potion.java)
- [ ] `use()` - שימוש בשיקוי על דמות
- [ ] `canUse()` - בדיקה אם ניתן להשתמש
- [ ] `isSellable()` - דריסה - שיקוי משומש לא ניתן למכירה

### שלב 4: דמות בסיסית - ממשק Attackable (Character.java)
- [ ] `takeDamage()` - קבלת נזק עם הפחתת שריון
- [ ] `isAlive()` - בדיקה אם הדמות חיה
- [ ] `addItem()` - הוספה למלאי עם Exception
- [ ] `removeItem()` - הסרה מהמלאי עם Exception
- [ ] `findItemsByType()` - חיפוש פריטים לפי סוג (instanceof)
- [ ] `getItemsByRarity()` - מיפוי פריטים לפי נדירות (HashMap)
- [ ] `equipWeapon()` - ציוד נשק
- [ ] `equipArmor()` - ציוד שריון (HashMap לפי slot)
- [ ] `getTotalDefense()` - סכום הגנה מכל השריונים
- [ ] `heal()` - ריפוי
- [ ] `restoreMana()` - שחזור מאנה
- [ ] `useMana()` - שימוש במאנה
- [ ] `gainExperience()` - עלייה ברמה

### שלב 5: דמויות ספציפיות (Warrior.java, Mage.java, Archer.java)
- [ ] `onLevelUp()` - התנהגות כשעולים רמה
- [ ] `calculateAttackDamage()` - חישוב נזק ייחודי
- [ ] `useSpecialAbility()` - יכולת מיוחדת (Berserk, Fireball, Multishot)
- [ ] מתודות ייחודיות לכל דמות

### שלב 6: מפת המבוך (GameLocation.java, DungeonMap.java)
- [ ] `addConnection()` - חיבור מיקומים
- [ ] `isConnectedTo()` - בדיקת חיבור
- [ ] `addLocation()` - הוספת מיקום ל-HashMap
- [ ] `connectLocations()` - חיבור דו-כיווני
- [ ] `moveTo()` - מעבר למיקום (עם בדיקת חיבור)
- [ ] `getAccessibleLocations()` - מיקומים נגישים
- [ ] `getVisitedLocations()` - מיקומים מבוקרים
- [ ] `getLocationsByDangerLevel()` - מיפוי לפי סכנה

### שלב 7: מערכת קרב עם Queue (BattleSystem.java)
- [ ] `queueAction()` - הוספה לתור
- [ ] `queuePlayerAction()` - יצירת פעולת שחקן
- [ ] `generateEnemyAction()` - AI פשוט לאויב
- [ ] `processNextAction()` - ביצוע פעולה מהתור
- [ ] `processAllActions()` - ביצוע כל הפעולות
- [ ] `executeAttack()` - ביצוע התקפה
- [ ] `executeSpecialAbility()` - ביצוע יכולת מיוחדת
- [ ] `executeFlee()` - ניסיון בריחה
- [ ] `sortActionsByPriority()` - **מיון עם מחלקה אנונימית!**
- [ ] `getActionsFilteredBy()` - סינון עם ActionFilter

### שלב 8: חנות (Shop.java)
- [ ] `addItemToShop()` - הוספת פריט עם כמות
- [ ] `getAvailableItems()` - פריטים זמינים
- [ ] `getItemsByCategory()` - סינון לפי קטגוריה (instanceof)
- [ ] `buyItem()` - קנייה עם Exceptions
- [ ] `sellItem()` - מכירה עם Exceptions
- [ ] `getItemStock()` - כמות במלאי
- [ ] `getInventoryReport()` - דוח HashMap

### שלב 9: פונקציות עזר - מחלקות אנונימיות (GameUtils.java)
- [ ] `sortItemsByPrice()` - **מיון עם Comparator אנונימי**
- [ ] `sortItemsByRarity()` - **מיון עם Comparator אנונימי**
- [ ] `sortItemsByName()` - **מיון עם Comparator אנונימי**
- [ ] `sortCharactersByHealth()` - **מיון עם Comparator אנונימי**
- [ ] `filterItems()` - סינון עם ItemFilter
- [ ] `filterAffordableItems()` - **סינון עם מחלקה אנונימית**
- [ ] `filterByRarity()` - **סינון עם מחלקה אנונימית**
- [ ] `findBestItem()` - מציאת הטוב ביותר

### שלב 10: המשחק הראשי (Game.java)
- [ ] `createCharacter()` - יצירת דמות
- [ ] `initializeMap()` - אתחול מפה
- [ ] `initializeShop()` - אתחול חנות
- [ ] השלמת כל ה-TODO בתפריטים

## דוגמאות קוד

### דוגמה 1: שימוש ב-ArrayList
```java
ArrayList<Item> inventory = new ArrayList<>();
inventory.add(new Weapon(...));
inventory.add(new Potion(...));

for (Item item : inventory) {
    System.out.println(item.getName());
}
```

### דוגמה 2: שימוש ב-HashMap
```java
HashMap<String, GameLocation> locations = new HashMap<>();
locations.put("entrance", new GameLocation(...));
locations.put("hall", new GameLocation(...));

GameLocation loc = locations.get("entrance");
```

### דוגמה 3: שימוש ב-Queue
```java
Queue<BattleAction> actionQueue = new LinkedList<>();
actionQueue.add(new BattleAction(...));  // הוספה לסוף

BattleAction next = actionQueue.poll();  // הוצאה מההתחלה
```

### דוגמה 4: שימוש ב-Stack
```java
Stack<Item> recentlyUsed = new Stack<>();
recentlyUsed.push(item);  // הוספה לראש

Item last = recentlyUsed.pop();  // הוצאה מהראש
Item peek = recentlyUsed.peek(); // הצצה בלי הוצאה
```

### דוגמה 5: מחלקה אנונימית עם Comparator
```java
ArrayList<Item> items = ...;

// מיון לפי מחיר - מהזול ליקר
items.sort(new Comparator<Item>() {
    @Override
    public int compare(Item i1, Item i2) {
        return i1.getBuyPrice() - i2.getBuyPrice();
    }
});

// מיון לפי שם - אלפביתי
items.sort(new Comparator<Item>() {
    @Override
    public int compare(Item i1, Item i2) {
        return i1.getName().compareTo(i2.getName());
    }
});
```

### דוגמה 6: ממשק וירושה
```java
// ממשק
public interface Usable {
    boolean use(Character target);
}

// מחלקה שמממשת
public class Potion extends Item implements Usable {
    @Override
    public boolean use(Character target) {
        // מימוש
    }
}
```

### דוגמה 7: זריקה ותפיסת Exceptions
```java
// זריקה
public void addItem(Item item) throws InventoryFullException {
    if (inventory.size() >= maxSize) {
        throw new InventoryFullException(item.getName(), maxSize);
    }
    inventory.add(item);
}

// תפיסה
try {
    character.addItem(newItem);
    System.out.println("Item added!");
} catch (InventoryFullException e) {
    System.out.println("Error: " + e.getMessage());
}
```

## טיפים
1. התחילו מהמחלקות הפשוטות (Item, Weapon) ועברו לבסיסיות (Character)
2. הריצו את הבדיקות אחרי כל שלב
3. קראו את ה-JavaDoc של כל מתודה לפני המימוש
4. שימו לב לטיפול ב-Exceptions
5. השתמשו ב-instanceof לזיהוי סוגי פריטים

## הערכה
- **40%** - מימוש נכון של כל ה-TODOs
- **20%** - שימוש נכון בירושה וממשקים
- **20%** - טיפול נכון ב-Exceptions
- **10%** - שימוש במחלקות אנונימיות
- **10%** - קוד נקי ותיעוד

בהצלחה! 🎮
#   P r o j e c t J a v a  
 