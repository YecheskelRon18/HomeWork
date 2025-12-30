package game;

import model.characters.Character;
import model.exceptions.InvalidActionException;
import model.exceptions.ItemNotFoundException;
import model.items.Item;
import model.items.Potion;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

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
    // Queue Management
    // ============================================================

    public void queueAction(BattleAction action) throws InvalidActionException {
        if (battleEnded) {
            throw new InvalidActionException("Battle already ended", "");
        }
        actionQueue.add(action);
    }

    public void queuePlayerAction(BattleAction.ActionType actionType)
            throws InvalidActionException {
        if (battleEnded) {
            throw new InvalidActionException("Battle already ended", "");
        }
        actionQueue.add(new BattleAction(player, enemy, actionType));
    }

    public void queuePlayerItemAction(String itemName)
            throws InvalidActionException {
        if (battleEnded) {
            throw new InvalidActionException("Battle already ended", "");
        }
        actionQueue.add(
                new BattleAction(player, enemy,
                        BattleAction.ActionType.USE_ITEM, itemName)
        );
    }

    public BattleAction generateEnemyAction() {
        double r = Math.random();

        if (r < 0.60) {
            return new BattleAction(enemy, player, BattleAction.ActionType.ATTACK);
        } else if (r < 0.85) {
            return new BattleAction(enemy, player, BattleAction.ActionType.SPECIAL);
        } else {
            return new BattleAction(enemy, player, BattleAction.ActionType.DEFEND);
        }
    }

    public String processNextAction() {
        if (actionQueue.isEmpty()) {
            return null;
        }

        BattleAction action = actionQueue.poll();
        String description = "";

        try {
            switch (action.getActionType()) {

                case ATTACK:
                    int damage = executeAttack(
                            action.getActor(), action.getTarget());
                    description = action.getActor().getName() +
                            " attacked for " + damage;
                    break;

                case SPECIAL:
                    boolean ok = action.getActor()
                            .useSpecialAbility(action.getTarget());
                    description = action.getActor().getName() +
                            (ok ? " used special ability"
                                    : " failed to use special ability");
                    break;

                case USE_ITEM:
                    if (executeUseItem(
                            action.getActor(), action.getItemName())) {
                        description = action.getActor().getName() +
                                " used item " + action.getItemName();
                    } else {
                        description = action.getActor().getName() +
                                " failed to use item";
                    }
                    break;

                case DEFEND:
                    description = action.getActor().getName() +
                            " is defending";
                    break;

                case FLEE:
                    if (executeFlee(action.getActor())) {
                        description = action.getActor().getName() +
                                " fled successfully";
                    } else {
                        description = action.getActor().getName() +
                                " failed to flee";
                    }
                    break;
            }
        } catch (Exception e) {
            description = e.getMessage();
        }

        checkBattleEnd();
        logMessage(description);
        return description;
    }

    public ArrayList<String> processAllActions() {
        ArrayList<String> result = new ArrayList<>();

        while (!actionQueue.isEmpty() && !battleEnded) {
            String s = processNextAction();
            if (s != null) {
                result.add(s);
            }
        }

        return result;
    }

    // ============================================================
    // Action Execution
    // ============================================================

    private int executeAttack(Character attacker, Character defender) {
        int damage = attacker.calculateAttackDamage();
        defender.takeDamage(damage);
        return damage;
    }

    private boolean executeUseItem(Character actor, String itemName)
            throws ItemNotFoundException {

        Item item = actor.removeItem(itemName);

        if (item instanceof Potion) {
            ((Potion) item).use(actor);
            actor.pushRecentlyUsed(item);
            return true;
        }

        return false;
    }

    private boolean executeFlee(Character fleeing) {
        int chance =
                30 + (player.getLevel() - enemy.getLevel()) * 5;
        int roll = (int) (Math.random() * 100);

        if (roll < chance) {
            battleEnded = true;
            winner = (fleeing == player) ? enemy : player;
            return true;
        }

        return false;
    }

    // ============================================================
    // Battle End Check
    // ============================================================

    private void checkBattleEnd() {
        if (!player.isAlive()) {
            battleEnded = true;
            winner = enemy;
        }

        if (!enemy.isAlive()) {
            battleEnded = true;
            winner = player;
        }
    }

    // ============================================================
    // Sorting & Filtering
    // ============================================================

    public void sortActionsByPriority(ArrayList<BattleAction> actions) {
        actions.sort(new Comparator<BattleAction>() {
            @Override
            public int compare(BattleAction a1, BattleAction a2) {
                return a2.getPriority() - a1.getPriority();
            }
        });
    }

    public ArrayList<BattleAction> getActionsFilteredBy(
            ArrayList<BattleAction> actions, ActionFilter filter) {

        ArrayList<BattleAction> result = new ArrayList<>();

        for (BattleAction action : actions) {
            if (filter.test(action)) {
                result.add(action);
            }
        }

        return result;
    }

    public interface ActionFilter {
        boolean test(BattleAction action);
    }

    // ============================================================
    // Utils
    // ============================================================

    private void logMessage(String message) {
        battleLog.add(message);
        System.out.println(message);
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
}
