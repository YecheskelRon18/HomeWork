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
        // TODO: Implement this method
        throw new UnsupportedOperationException("Not implemented yet");
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
        // TODO: Implement this method
        throw new UnsupportedOperationException("Not implemented yet");
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
        // TODO: Implement this method
        throw new UnsupportedOperationException("Not implemented yet");
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
        // TODO: Implement this method
        throw new UnsupportedOperationException("Not implemented yet");
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
        // TODO: Implement this method
        // 1. חשב כמה מאנה צריך לספוג את כל הנזק
        // 2. אם יש מספיק מאנה, ספוג הכל והחזר 0
        // 3. אם אין מספיק, ספוג כמה שאפשר והחזר את השאר
        throw new UnsupportedOperationException("Not implemented yet");
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
        // TODO: Implement this method
        throw new UnsupportedOperationException("Not implemented yet");
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
