package model.characters;

/**
 * מחלקה המייצגת קשת במשחק.
 * יורשת מ-Character.
 * הקשת מתמחה בהתקפות מרחוק ובסיכוי לפגיעה קריטית.
 */
public class Archer extends Character {
    
    private double criticalChance;
    private double criticalMultiplier;
    private int arrows;
    private static final int MAX_ARROWS = 30;
    private static final int MULTISHOT_ARROW_COST = 3;
    
    public Archer(String name) {
        // קשת: חיים בינוניים, מאנה בינונית, כוח בינוני, הגנה נמוכה
        super(name, 100, 80, 12, 5);
        this.criticalChance = 0.15; // 15% סיכוי לקריטי
        this.criticalMultiplier = 2.0; // נזק כפול בקריטי
        this.arrows = MAX_ARROWS;
    }
    
    // ============================================================
    // TODO: מימוש מתודות אבסטרקטיות
    // ============================================================
    
    /**
     * כאשר קשת עולה רמה
     */
    @Override
    protected void onLevelUp() {
        maxHealth += 12;
        maxMana += 10;
        baseStrength += 2;
        baseDefense += 1;
        criticalChance = Math.min(0.5, criticalChance + 0.02);
        arrows = MAX_ARROWS;
        currentHealth = maxHealth;
        currentMana = maxMana;
    }
    
    /**
     * נזק קשת = baseStrength + נזק נשק (אם יש)
     * יש סיכוי של criticalChance לפגיעה קריטית
     * 
     * @return נזק ההתקפה (עם או בלי קריטי)
     */
    @Override
    public int calculateAttackDamage() {
        int weaponDamage = (equippedWeapon != null) ? equippedWeapon.calculateDamage() : 0;
        int baseDamage = baseStrength + weaponDamage;
        
        if (Math.random() < criticalChance) {
            return (int) (baseDamage * criticalMultiplier);
        }
        return baseDamage;
    }
    
    /**
     * יכולת מיוחדת: ירי מרובה
     * 
     * @param target היריב
     * @return true אם היכולת בוצעה
     */
    @Override
    public boolean useSpecialAbility(Character target) {
        if (arrows < MULTISHOT_ARROW_COST) {
            return false;
        }
        arrows -= MULTISHOT_ARROW_COST;
        
        // Shoot 3 arrows, each doing 70% damage
        for (int i = 0; i < 3; i++) {
            int weaponDamage = (equippedWeapon != null) ? equippedWeapon.calculateDamage() : 0;
            int baseDamage = (int) ((baseStrength + weaponDamage) * 0.7);
            
            if (Math.random() < criticalChance) {
                baseDamage = (int) (baseDamage * criticalMultiplier);
            }
            target.takeDamage(baseDamage);
        }
        return true;
    }
    
    // ============================================================
    // TODO: מתודות ייחודיות לקשת
    // ============================================================
    
    /**
     * יורה חץ בודד ביריב.
     * 
     * @param target היריב
     * @return הנזק שנגרם, או -1 אם אין חיצים
     */
    public int shootArrow(Character target) {
        if (arrows <= 0) {
            return -1;
        }
        arrows--;
        int damage = calculateAttackDamage();
        target.takeDamage(damage);
        return damage;
    }
    
    /**
     * ממלא חיצים בחזרה למקסימום.
     * עולה 5 זהב לכל חץ שחסר.
     * 
     * @return true אם המילוי הצליח, false אם אין מספיק זהב
     */
    public boolean refillArrows() {
        int missingArrows = MAX_ARROWS - arrows;
        int cost = missingArrows * 5;
        
        if (spendGold(cost)) {
            arrows = MAX_ARROWS;
            return true;
        }
        return false;
    }
    
    /**
     * תמרון התחמקות - סיכוי להתחמק מהתקפה.
     * סיכוי ההתחמקות = criticalChance * 1.5
     * עולה 15 מאנה
     * 
     * @return true אם ההתחמקות הצליחה
     */
    public boolean evasiveManeuver() {
        if (!useMana(15)) {
            return false;
        }
        return Math.random() < (criticalChance * 1.5);
    }
    
    // Getters
    public double getCriticalChance() {
        return criticalChance;
    }
    
    public double getCriticalMultiplier() {
        return criticalMultiplier;
    }
    
    public int getArrows() {
        return arrows;
    }
    
    public int getMaxArrows() {
        return MAX_ARROWS;
    }
    
    @Override
    public String toString() {
        return "Archer: " + super.toString() + 
               String.format(" | Arrows: %d/%d | Crit: %.0f%%", 
                   arrows, MAX_ARROWS, criticalChance * 100);
    }
}
