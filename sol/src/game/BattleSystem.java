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
     * מוסיף פעולה לתור הפעולות.
     * 
     * @param action הפעולה להוספה
     * @throws InvalidActionException אם הקרב כבר הסתיים
     */
    public void queueAction(BattleAction action) throws InvalidActionException {
        if (battleEnded) {
            throw new InvalidActionException("queue", "Battle has already ended");
        }
        actionQueue.add(action);
    }
    
    /**
     * יוצר ומוסיף פעולה של השחקן.
     * 
     * @param actionType סוג הפעולה
     * @throws InvalidActionException אם הקרב הסתיים
     */
    public void queuePlayerAction(BattleAction.ActionType actionType) 
            throws InvalidActionException {
        BattleAction action = new BattleAction(player, enemy, actionType);
        queueAction(action);
    }
    
    /**
     * יוצר ומוסיף פעולה של שימוש בפריט.
     * 
     * @param itemName שם הפריט
     * @throws InvalidActionException אם הקרב הסתיים
     */
    public void queuePlayerItemAction(String itemName) throws InvalidActionException {
        BattleAction action = new BattleAction(player, enemy, BattleAction.ActionType.USE_ITEM, itemName);
        queueAction(action);
    }
    
    /**
     * יוצר פעולה אקראית לאויב (AI פשוט).
     * 
     * @return פעולת האויב
     */
    public BattleAction generateEnemyAction() {
        double roll = Math.random();
        BattleAction.ActionType actionType;
        
        if (roll < 0.60) {
            actionType = BattleAction.ActionType.ATTACK;
        } else if (roll < 0.85) {
            actionType = BattleAction.ActionType.SPECIAL;
        } else {
            actionType = BattleAction.ActionType.DEFEND;
        }
        
        return new BattleAction(enemy, player, actionType);
    }
    
    /**
     * מבצע את הפעולה הבאה בתור.
     * 
     * @return תיאור מה קרה, או null אם התור ריק
     */
    public String processNextAction() {
        if (actionQueue.isEmpty() || battleEnded) {
            return null;
        }
        
        BattleAction action = actionQueue.poll();
        Character actor = action.getActor();
        Character target = action.getTarget();
        String result = "";
        
        switch (action.getActionType()) {
            case ATTACK:
                int damage = executeAttack(actor, target);
                result = actor.getName() + " attacks for " + damage + " damage!";
                break;
            case SPECIAL:
                boolean success = executeSpecialAbility(actor, target);
                result = actor.getName() + (success ? " uses special ability!" : " failed to use special ability.");
                break;
            case USE_ITEM:
                try {
                    boolean used = executeUseItem(actor, action.getItemName());
                    result = actor.getName() + (used ? " uses " + action.getItemName() : " failed to use item.");
                } catch (Exception e) {
                    result = actor.getName() + " couldn't find the item.";
                }
                break;
            case DEFEND:
                executeDefend(actor);
                result = actor.getName() + " takes a defensive stance.";
                break;
            case FLEE:
                boolean fled = executeFlee(actor);
                result = actor.getName() + (fled ? " successfully fled!" : " failed to flee!");
                if (fled) {
                    battleEnded = true;
                }
                break;
        }
        
        checkBattleEnd();
        logMessage(result);
        return result;
    }
    
    /**
     * מבצע את כל הפעולות בתור עד שהוא מתרוקן או שהקרב נגמר.
     * 
     * @return רשימה של כל התיאורים של מה שקרה
     */
    public ArrayList<String> processAllActions() {
        ArrayList<String> results = new ArrayList<>();
        while (!actionQueue.isEmpty() && !battleEnded) {
            String result = processNextAction();
            if (result != null) {
                results.add(result);
            }
        }
        return results;
    }
    
    // ============================================================
    // TODO: ביצוע פעולות (Action Execution)
    // ============================================================
    
    /**
     * מבצע התקפה רגילה.
     * 
     * @param attacker התוקף
     * @param defender המותקף
     * @return הנזק שנגרם
     */
    private int executeAttack(Character attacker, Character defender) {
        int damage = attacker.calculateAttackDamage();
        defender.takeDamage(damage);
        return damage;
    }
    
    /**
     * מבצע יכולת מיוחדת.
     * 
     * @param actor המבצע
     * @param target היעד
     * @return true אם הצליח
     */
    private boolean executeSpecialAbility(Character actor, Character target) {
        return actor.useSpecialAbility(target);
    }
    
    /**
     * משתמש בפריט.
     * 
     * @param actor המשתמש
     * @param itemName שם הפריט
     * @return true אם הצליח
     * @throws ItemNotFoundException אם הפריט לא נמצא
     */
    private boolean executeUseItem(Character actor, String itemName) 
            throws ItemNotFoundException {
        ArrayList<Item> inventory = actor.getInventory();
        for (Item item : inventory) {
            if (item.getName().equals(itemName)) {
                if (item instanceof Potion) {
                    Potion potion = (Potion) item;
                    if (potion.use(actor)) {
                        actor.pushRecentlyUsed(item);
                        return true;
                    }
                }
                return false;
            }
        }
        throw new ItemNotFoundException(itemName);
    }
    
    /**
     * מבצע פעולת הגנה - מפחית נזק בתור הבא ב-50%.
     * 
     * @param defender המגן
     */
    private void executeDefend(Character defender) {
        logMessage(defender.getName() + " is defending!");
    }
    
    /**
     * מנסה לברוח מהקרב.
     * סיכוי הצלחה = 30% + (רמת שחקן - רמת אויב) * 5%
     * 
     * @param fleeing הבורח
     * @return true אם ההבריחה הצליחה
     */
    private boolean executeFlee(Character fleeing) {
        Character other = (fleeing == player) ? enemy : player;
        double chance = 0.30 + (fleeing.getLevel() - other.getLevel()) * 0.05;
        chance = Math.max(0.1, Math.min(0.9, chance)); // Clamp between 10% and 90%
        return Math.random() < chance;
    }
    
    // ============================================================
    // בדיקת סיום קרב
    // ============================================================
    
    /**
     * בודק אם הקרב הסתיים וקובע מנצח.
     */
    private void checkBattleEnd() {
        if (!player.isAlive()) {
            battleEnded = true;
            winner = enemy;
            logMessage(enemy.getName() + " wins the battle!");
        } else if (!enemy.isAlive()) {
            battleEnded = true;
            winner = player;
            logMessage(player.getName() + " wins the battle!");
        }
    }
    
    // ============================================================
    // מיון פעולות לפי עדיפות (שימוש במחלקה אנונימית)
    // ============================================================
    
    /**
     * ממיין רשימת פעולות לפי עדיפות (גבוה לנמוך).
     * 
     * @param actions רשימת הפעולות למיון
     */
    public void sortActionsByPriority(ArrayList<BattleAction> actions) {
        actions.sort(new Comparator<BattleAction>() {
            @Override
            public int compare(BattleAction a1, BattleAction a2) {
                return Integer.compare(a2.getPriority(), a1.getPriority());
            }
        });
    }
    
    /**
     * מסנן פעולות לפי תנאי מסוים.
     * 
     * @param actions רשימת הפעולות
     * @param filter הפילטר (ממשק עם מתודת test)
     * @return רשימה מסוננת
     */
    public ArrayList<BattleAction> getActionsFilteredBy(
            ArrayList<BattleAction> actions, ActionFilter filter) {
        ArrayList<BattleAction> filtered = new ArrayList<>();
        for (BattleAction action : actions) {
            if (filter.test(action)) {
                filtered.add(action);
            }
        }
        return filtered;
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
