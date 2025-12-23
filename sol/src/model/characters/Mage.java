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
     * כאשר קוסם עולה רמה
     */
    @Override
    protected void onLevelUp() {
        maxHealth += 8;
        maxMana += 25;
        baseStrength += 1;
        baseDefense += 1;
        spellPower += 5;
        currentHealth = maxHealth;
        currentMana = maxMana;
    }
    
    /**
     * נזק קוסם בסיסי = baseStrength + נזק נשק (אם יש)
     * 
     * @return נזק ההתקפה הפיזית
     */
    @Override
    public int calculateAttackDamage() {
        int weaponDamage = (equippedWeapon != null) ? equippedWeapon.calculateDamage() : 0;
        return baseStrength + weaponDamage;
    }
    
    /**
     * יכולת מיוחדת: כדור אש
     * 
     * @param target היריב
     * @return true אם הכישוף בוצע
     */
    @Override
    public boolean useSpecialAbility(Character target) {
        if (!useMana(FIREBALL_MANA_COST)) {
            return false;
        }
        int damage = (int) Math.ceil(spellPower * 1.5);
        target.takeDamage(damage);
        return true;
    }
    
    // ============================================================
    // TODO: מתודות ייחודיות לקוסם
    // ============================================================
    
    /**
     * כישוף ריפוי עצמי.
     * 
     * @return true אם הכישוף הצליח
     */
    public boolean castHeal() {
        if (!useMana(HEAL_MANA_COST)) {
            return false;
        }
        heal(spellPower);
        return true;
    }
    
    /**
     * מגן מאנה - משתמש במאנה במקום בחיים לספיגת נזק.
     * כל נקודת מאנה סופגת 2 נקודות נזק.
     * 
     * @param incomingDamage הנזק הנכנס
     * @return הנזק שנותר אחרי ספיגת המגן (אם המאנה לא הספיקה)
     */
    public int castManaShield(int incomingDamage) {
        int manaNeeded = (int) Math.ceil(incomingDamage / 2.0);
        
        if (currentMana >= manaNeeded) {
            currentMana -= manaNeeded;
            return 0;
        } else {
            int damageAbsorbed = currentMana * 2;
            currentMana = 0;
            return incomingDamage - damageAbsorbed;
        }
    }
    
    /**
     * מחשב נזק כישוף לפי מכפיל.
     * 
     * @param multiplier מכפיל הנזק
     * @return נזק הכישוף (מספר שלם, עגל כלפי מעלה)
     */
    public int calculateSpellDamage(double multiplier) {
        return (int) Math.ceil(spellPower * multiplier);
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
