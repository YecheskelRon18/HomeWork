package model.characters;

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
        throw new UnsupportedOperationException("Not implemented yet");
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
        // TODO: Implement this method
        throw new UnsupportedOperationException("Not implemented yet");
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
        // TODO: Implement this method
        throw new UnsupportedOperationException("Not implemented yet");
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
        // 1. קרא ל-super.takeDamage(damage) (אחרי שתממש אותו)
        // 2. הוסף זעם
        throw new UnsupportedOperationException("Not implemented yet");
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
        // TODO: Implement this method
        throw new UnsupportedOperationException("Not implemented yet");
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
