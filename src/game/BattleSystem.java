package game;

import model.characters.Character;
import model.items.Item;
import model.items.Potion;
import model.exceptions.InvalidActionException;
import model.exceptions.ItemNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Comparator;

/**
 * מערכת הקרב של המשחק.
 * משתמשת ב-Queue לניהול תור הפעולות.
 */
public class BattleSystem {
    
    private Character player;
    private Character enemy;
    private Queue<BattleAction> actionQueue;
    private ArrayList<String> battleLog;
    private boolean battleEnded;
    private Character winner;
    
    public BattleSystem(Character player, Character enemy) {
        this.player = player;
        this.enemy = enemy;
        this.actionQueue = new LinkedList<>();
        this.battleLog = new ArrayList<>();
        this.battleEnded = false;
        this.winner = null;
        
        logMessage("Battle started: " + player.getName() + " vs " + enemy.getName());
    }
    
    // ============================================================
    // TODO: ניהול תור פעולות (Queue Management)
    // ============================================================
    
    /**
     * TODO: מימוש queueAction
     * מוסיף פעולה לתור הפעולות.
     * 
     * @param action הפעולה להוספה
     * @throws InvalidActionException אם הקרב כבר הסתיים
     */
    public void queueAction(BattleAction action) throws InvalidActionException {
        // TODO: Implement this method
        // 1. בדוק שהקרב לא הסתיים
        // 2. הוסף את הפעולה לתור
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    /**
     * TODO: מימוש queuePlayerAction
     * יוצר ומוסיף פעולה של השחקן.
     * 
     * @param actionType סוג הפעולה
     * @throws InvalidActionException אם הקרב הסתיים
     */
    public void queuePlayerAction(BattleAction.ActionType actionType) 
            throws InvalidActionException {
        // TODO: Implement this method
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    /**
     * TODO: מימוש queuePlayerItemAction
     * יוצר ומוסיף פעולה של שימוש בפריט.
     * 
     * @param itemName שם הפריט
     * @throws InvalidActionException אם הקרב הסתיים
     */
    public void queuePlayerItemAction(String itemName) throws InvalidActionException {
        // TODO: Implement this method
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    /**
     * TODO: מימוש generateEnemyAction
     * יוצר פעולה אקראית לאויב (AI פשוט).
     * - 60% התקפה רגילה
     * - 25% יכולת מיוחדת
     * - 15% הגנה
     * 
     * @return פעולת האויב
     */
    public BattleAction generateEnemyAction() {
        // TODO: Implement this method
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    /**
     * TODO: מימוש processNextAction
     * מבצע את הפעולה הבאה בתור.
     * 
     * @return תיאור מה קרה, או null אם התור ריק
     */
    public String processNextAction() {
        // TODO: Implement this method
        // 1. בדוק שהתור לא ריק
        // 2. הוצא פעולה מהתור
        // 3. בצע את הפעולה לפי הסוג שלה
        // 4. בדוק אם הקרב הסתיים
        // 5. רשום ללוג והחזר תיאור
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    /**
     * TODO: מימוש processAllActions
     * מבצע את כל הפעולות בתור עד שהוא מתרוקן או שהקרב נגמר.
     * 
     * @return רשימה של כל התיאורים של מה שקרה
     */
    public ArrayList<String> processAllActions() {
        // TODO: Implement this method
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    // ============================================================
    // TODO: ביצוע פעולות (Action Execution)
    // ============================================================
    
    /**
     * TODO: מימוש executeAttack
     * מבצע התקפה רגילה.
     * 
     * @param attacker התוקף
     * @param defender המותקף
     * @return הנזק שנגרם
     */
    private int executeAttack(Character attacker, Character defender) {
        // TODO: Implement this method
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    /**
     * TODO: מימוש executeSpecialAbility
     * מבצע יכולת מיוחדת.
     * 
     * @param actor המבצע
     * @param target היעד
     * @return true אם הצליח
     */
    private boolean executeSpecialAbility(Character actor, Character target) {
        // TODO: Implement this method
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    /**
     * TODO: מימוש executeUseItem
     * משתמש בפריט.
     * 
     * @param actor המשתמש
     * @param itemName שם הפריט
     * @return true אם הצליח
     * @throws ItemNotFoundException אם הפריט לא נמצא
     */
    private boolean executeUseItem(Character actor, String itemName) 
            throws ItemNotFoundException {
        // TODO: Implement this method
        // 1. חפש את הפריט במלאי
        // 2. אם זה Potion, השתמש בו
        // 3. הוסף לסטאק של פריטים אחרונים
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    /**
     * TODO: מימוש executeDefend
     * מבצע פעולת הגנה - מפחית נזק בתור הבא ב-50%.
     * 
     * @param defender המגן
     */
    private void executeDefend(Character defender) {
        // TODO: Implement this method
        // רשום ללוג שהדמות מגינה
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    /**
     * TODO: מימוש executeFlee
     * מנסה לברוח מהקרב.
     * סיכוי הצלחה = 30% + (רמת שחקן - רמת אויב) * 5%
     * 
     * @param fleeing הבורח
     * @return true אם ההבריחה הצליחה
     */
    private boolean executeFlee(Character fleeing) {
        // TODO: Implement this method
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    // ============================================================
    // TODO: בדיקת סיום קרב
    // ============================================================
    
    /**
     * TODO: מימוש checkBattleEnd
     * בודק אם הקרב הסתיים וקובע מנצח.
     */
    private void checkBattleEnd() {
        // TODO: Implement this method
        // בדוק אם אחד הצדדים מת
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    // ============================================================
    // TODO: מיון פעולות לפי עדיפות (שימוש במחלקה אנונימית)
    // ============================================================
    
    /**
     * TODO: מימוש sortActionsByPriority
     * ממיין רשימת פעולות לפי עדיפות (גבוה לנמוך).
     * השתמש ב-Comparator כמחלקה אנונימית!
     * 
     * @param actions רשימת הפעולות למיון
     */
    public void sortActionsByPriority(ArrayList<BattleAction> actions) {
        // TODO: Implement this method using anonymous class
        // צור Comparator<BattleAction> כמחלקה אנונימית
        // השתמש ב-actions.sort(comparator)
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    /**
     * TODO: מימוש getActionsFilteredBy
     * מסנן פעולות לפי תנאי מסוים.
     * השתמש בממשק פונקציונלי!
     * 
     * @param actions רשימת הפעולות
     * @param filter הפילטר (ממשק עם מתודת test)
     * @return רשימה מסוננת
     */
    public ArrayList<BattleAction> getActionsFilteredBy(
            ArrayList<BattleAction> actions, ActionFilter filter) {
        // TODO: Implement this method
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    /**
     * ממשק פונקציונלי לסינון פעולות.
     */
    public interface ActionFilter {
        boolean test(BattleAction action);
    }
    
    // ============================================================
    // Utility Methods
    // ============================================================
    
    private void logMessage(String message) {
        battleLog.add(message);
        System.out.println(message);
    }
    
    // Getters
    public Character getPlayer() {
        return player;
    }
    
    public Character getEnemy() {
        return enemy;
    }
    
    public boolean isBattleEnded() {
        return battleEnded;
    }
    
    public Character getWinner() {
        return winner;
    }
    
    public ArrayList<String> getBattleLog() {
        return new ArrayList<>(battleLog);
    }
    
    public int getQueueSize() {
        return actionQueue.size();
    }
    
    public boolean isQueueEmpty() {
        return actionQueue.isEmpty();
    }
}
