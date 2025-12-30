package model.characters;

/**
 * מחלקה המייצגת קוסם במשחק.
 * יורשת מ-Character.
 * הקוסם מתמחה בכישופים ובשימוש במאנה.
 */
public class Mage extends Character {
    
    private int spellPower;
    private static final int FIREBALL_MANA_COST = 25;
    private static final int HEAL_MANA_COST = 30;
    
    public Mage(String name) {
        // קוסם: מעט חיים, הרבה מאנה, כוח נמוך, הגנה נמוכה
        super(name, 80, 150, 5, 3);
        this.spellPower = 20;
    }
    
    // ============================================================
    // TODO: מימוש מתודות אבסטרקטיות
    // ============================================================
    
    /**
     * TODO: מימוש onLevelUp
     * כאשר קוסם עולה רמה:
     * - maxHealth עולה ב-8
     * - maxMana עולה ב-25
     * - baseStrength עולה ב-1
     * - baseDefense עולה ב-1
     * - spellPower עולה ב-5
     * - currentHealth ו-currentMana מתמלאים למקסימום
     */
    @Override
    protected void onLevelUp() {
        this.maxHealth += 8;
        this.maxMana += 25;
        this.baseStrength += 1;
        this.baseDefense += 1;
        this.spellPower += 5;

        this.currentHealth = this.maxHealth;
        this.currentMana = this.maxMana;
    }


    /**
     * TODO: מימוש calculateAttackDamage
     * נזק קוסם בסיסי = baseStrength + נזק נשק (אם יש)
     * הקוסם מעדיף להשתמש בכישופים אז הנזק הפיזי שלו נמוך
     * 
     * @return נזק ההתקפה הפיזית
     */
    @Override
    public int calculateAttackDamage() {
        int weaponDamage = 0;

        if (this.equippedWeapon != null) {
            weaponDamage = (int) this.equippedWeapon.getAverageDamage();
        }

        return this.baseStrength + weaponDamage;
    }


    /**
     * TODO: מימוש useSpecialAbility - Fireball
     * יכולת מיוחדת: כדור אש
     * - עולה FIREBALL_MANA_COST מאנה
     * - גורם נזק של spellPower * 1.5 (עגל כלפי מעלה)
     * - מחזיר true אם הצליח, false אם אין מספיק מאנה
     * 
     * @param target היריב
     * @return true אם הכישוף בוצע
     */
    @Override
    public boolean useSpecialAbility(Character target) {
        if (this.currentMana < FIREBALL_MANA_COST) {
            return false;
        }
        this.currentMana -= FIREBALL_MANA_COST;
        int damage = calculateSpellDamage(1.5);
        target.takeDamage(damage);
        return true;
    }


    // ============================================================
    // TODO: מתודות ייחודיות לקוסם
    // ============================================================
    
    /**
     * TODO: מימוש castHeal
     * כישוף ריפוי עצמי.
     * - עולה HEAL_MANA_COST מאנה
     * - מרפא כמות של spellPower נקודות
     * 
     * @return true אם הכישוף הצליח
     */
    public boolean castHeal() {
        if (this.currentMana < HEAL_MANA_COST) {
            return false;
        }
        this.currentMana -= HEAL_MANA_COST;
        this.currentHealth += this.spellPower;
        if (this.currentHealth > this.maxHealth) {
            this.currentHealth = this.maxHealth;
        }
        return true;
    }


    /**
     * TODO: מימוש castManaShield
     * מגן מאנה - משתמש במאנה במקום בחיים לספיגת נזק.
     * כל נקודת מאנה סופגת 2 נקודות נזק.
     * 
     * @param incomingDamage הנזק הנכנס
     * @return הנזק שנותר אחרי ספיגת המגן (אם המאנה לא הספיקה)
     */
    public int castManaShield(int incomingDamage) {
        int maxAbsorb = this.currentMana * 2;

        if (maxAbsorb >= incomingDamage) {
            int manaUsed = (int) Math.ceil(incomingDamage / 2.0);
            this.currentMana -= manaUsed;
            return 0;
        } else {
            // אין מספיק מאנה
            this.currentMana = 0;
            return incomingDamage - maxAbsorb;
        }
    }


    /**
     * TODO: מימוש calculateSpellDamage
     * מחשב נזק כישוף לפי מכפיל.
     * נזק = spellPower * multiplier
     * 
     * @param multiplier מכפיל הנזק
     * @return נזק הכישוף (מספר שלם, עגל כלפי מעלה)
     */
    public int calculateSpellDamage(double multiplier) {
        return (int) Math.ceil(this.spellPower * multiplier);
    }


    // Getters
    public int getSpellPower() {
        return spellPower;
    }
    
    @Override
    public String toString() {
        return "Mage: " + super.toString() + 
               String.format(" | Spell Power: %d", spellPower);
    }
}
