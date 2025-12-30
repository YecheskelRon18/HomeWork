package model.characters;

import model.items.Armor;

/**
 * מחלקה המייצגת לוחם במשחק.
 * יורשת מ-Character.
 * הלוחם מתמחה בהתקפות פיזיות חזקות ובהגנה גבוהה.
 */
public class Warrior extends Character {
    
    private int rage;
    private static final int MAX_RAGE = 100;
    private static final int RAGE_PER_HIT = 10;
    private static final int BERSERK_RAGE_COST = 50;
    
    public Warrior(String name) {
        // לוחם: הרבה חיים, מעט מאנה, כוח גבוה, הגנה גבוהה
        super(name, 150, 30, 15, 10);
        this.rage = 0;
    }
    
    // ============================================================
    // TODO: מימוש מתודות אבסטרקטיות
    // ============================================================
    
    /**
     * TODO: מימוש onLevelUp
     * כאשר לוחם עולה רמה:
     * - maxHealth עולה ב-20
     * - maxMana עולה ב-5
     * - baseStrength עולה ב-3
     * - baseDefense עולה ב-2
     * - currentHealth ו-currentMana מתמלאים למקסימום
     */
    @Override
    protected void onLevelUp() {
        // TODO: Implement this method
        this.maxHealth += 20;
        this.maxMana += 5;
        this.baseStrength += 3;
        this.baseDefense += 2;
        this.currentHealth = this.maxHealth;
        this.currentMana = this.maxMana;
    }
    
    /**
     * TODO: מימוש calculateAttackDamage
     * נזק לוחם = baseStrength + נזק נשק (אם יש) + בונוס זעם
     * בונוס זעם = rage / 10 (מספר שלם)
     * אם אין נשק, נזק הנשק הוא 0
     * 
     * @return נזק ההתקפה
     */
    @Override
    public int calculateAttackDamage() {
        int rageB = Math.ceilDiv(this.rage, 10);
        if (rageB < 1) {
            rageB = 1;
        }
        return (int) (rageB + baseStrength + this.equippedWeapon.getAverageDamage());
        // TODO: Implement this method
    }
    
    /**
     * TODO: מימוש useSpecialAbility - Berserk
     * יכולת מיוחדת: זעם ברסרק
     * - עולה BERSERK_RAGE_COST זעם
     * - גורם נזק כפול מנזק התקפה רגיל ליריב
     * - מחזיר true אם הצליח, false אם אין מספיק זעם
     * 
     * @param target היריב
     * @return true אם היכולת בוצעה
     */
    @Override
    public boolean useSpecialAbility(Character target) {
        Character p = this;
        if (this.BERSERK_RAGE_COST - this.rage < 0) {
            this.rage -= BERSERK_RAGE_COST;
            target.takeDamage(p.calculateAttackDamage());
            return true;
        }
        return false;
    }
    
    /**
     * TODO: מימוש דריסה ל-takeDamage
     * כאשר הלוחם מקבל נזק, הוא גם צובר זעם.
     * קודם קרא למימוש של המחלקה הבסיסית (כשתממש אותה)
     * ואז הוסף RAGE_PER_HIT לזעם (מקסימום MAX_RAGE)
     */
    @Override
    public void takeDamage(int damage) {
        // TODO: Implement this method
        int RedDmg = 0;
        for (Armor p : this.equippedArmor.values()) {
            RedDmg = p.reduceDamage(damage);
            if (RedDmg > 0) {
                this.currentHealth -= RedDmg;
                this.rage += RAGE_PER_HIT;
            }
        }
        if (rage > MAX_RAGE) {
            rage = MAX_RAGE;
        }
        // 1. קרא ל-super.takeDamage(damage) (אחרי שתממש אותו)
        // 2. הוסף זעם
    }
    
    // ============================================================
    // מתודות ייחודיות ללוחם
    // ============================================================
    
    /**
     * TODO: מימוש shieldBlock
     * הלוחם חוסם בעזרת מגן ומקבל רק 25% מהנזק.
     * עולה 20 מאנה.
     * 
     * @param incomingDamage הנזק הנכנס
     * @return true אם החסימה הצליחה
     */
    public boolean shieldBlock(int incomingDamage) {
        if (useMana(20)) {
            incomingDamage = (int) Math.ceil(incomingDamage * 0.75);
            takeDamage(incomingDamage);
            return true;
        }
        return false;
    }
    
    // Getters
    public int getRage() {
        return rage;
    }
    
    public int getMaxRage() {
        return MAX_RAGE;
    }
    
    @Override
    public String toString() {
        return "Warrior: " + super.toString() + 
               String.format(" | Rage: %d/%d", rage, MAX_RAGE);
    }
}
