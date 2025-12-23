package game;

import model.characters.*;
import model.characters.Character;
import model.items.*;
import model.exceptions.*;
import java.util.Scanner;

/**
 * המחלקה הראשית של המשחק.
 * מנהלת את זרימת המשחק והאינטראקציה עם השחקן.
 */
public class Game {
    
    private Character player;
    private DungeonMap map;
    private Shop shop;
    private Scanner scanner;
    private boolean gameRunning;
    
    public Game() {
        this.scanner = new Scanner(System.in);
        this.gameRunning = false;
    }
    
    /**
     * מתחיל את המשחק.
     */
    public void start() {
        System.out.println("=================================");
        System.out.println("  Welcome to Dungeon Adventure!");
        System.out.println("=================================\n");
        
        createCharacter();
        initializeMap();
        initializeShop();
        
        gameRunning = true;
        gameLoop();
    }
    
    /**
     * TODO: מימוש createCharacter
     * מאפשר לשחקן לבחור סוג דמות וליצור אותה.
     */
    private void createCharacter() {
        // TODO: Implement this method
        // 1. בקש שם מהשחקן
        // 2. הצג אפשרויות: 1. Warrior, 2. Mage, 3. Archer
        // 3. צור את הדמות המתאימה
        System.out.println("Character creation - TODO");
        
        // Placeholder - create a Warrior by default
        player = new Warrior("Hero");
    }
    
    /**
     * TODO: מימוש initializeMap
     * יוצר את מפת המבוך עם כמה מיקומים.
     */
    private void initializeMap() {
        // TODO: Implement this method
        // 1. צור DungeonMap חדש
        // 2. הוסף לפחות 5 מיקומים
        // 3. חבר ביניהם
        // 4. הגדר נקודת התחלה ומיקום הבוס
        System.out.println("Map initialization - TODO");
        
        map = new DungeonMap();
    }
    
    /**
     * TODO: מימוש initializeShop
     * יוצר את החנות עם פריטים התחלתיים.
     */
    private void initializeShop() {
        // TODO: Implement this method
        // 1. צור Shop חדש
        // 2. הוסף כמה נשקים, שריונים ושיקויים
        System.out.println("Shop initialization - TODO");
        
        shop = new Shop("Village Shop");
    }
    
    /**
     * לולאת המשחק הראשית.
     */
    private void gameLoop() {
        while (gameRunning) {
            displayMenu();
            int choice = getPlayerChoice();
            handleChoice(choice);
        }
        
        System.out.println("\nThanks for playing Dungeon Adventure!");
    }
    
    /**
     * מציג את התפריט הראשי.
     */
    private void displayMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. View Character");
        System.out.println("2. View Inventory");
        System.out.println("3. Move to Location");
        System.out.println("4. Visit Shop");
        System.out.println("5. Battle");
        System.out.println("6. Save & Quit");
        System.out.print("Choose: ");
    }
    
    /**
     * קורא בחירה מהשחקן.
     */
    private int getPlayerChoice() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    /**
     * TODO: מימוש handleChoice
     * מטפל בבחירת השחקן.
     */
    private void handleChoice(int choice) {
        // TODO: Implement this method
        // switch על choice וקרא למתודות המתאימות
        switch (choice) {
            case 1:
                viewCharacter();
                break;
            case 2:
                viewInventory();
                break;
            case 3:
                moveToLocation();
                break;
            case 4:
                visitShop();
                break;
            case 5:
                startBattle();
                break;
            case 6:
                gameRunning = false;
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }
    
    /**
     * TODO: מימוש viewCharacter
     * מציג מידע על הדמות.
     */
    private void viewCharacter() {
        // TODO: Implement this method
        System.out.println("\n" + player.toString());
    }
    
    /**
     * TODO: מימוש viewInventory
     * מציג את המלאי של השחקן.
     */
    private void viewInventory() {
        // TODO: Implement this method
        // הצג את כל הפריטים במלאי
        System.out.println("\nInventory display - TODO");
    }
    
    /**
     * TODO: מימוש moveToLocation
     * מאפשר לשחקן לזוז למיקום אחר.
     */
    private void moveToLocation() {
        // TODO: Implement this method
        // 1. הצג מיקומים נגישים
        // 2. בקש בחירה מהשחקן
        // 3. הזז את השחקן
        System.out.println("\nMove to location - TODO");
    }
    
    /**
     * TODO: מימוש visitShop
     * מאפשר לשחקן לקנות ולמכור בחנות.
     */
    private void visitShop() {
        // TODO: Implement this method
        System.out.println("\nShop visit - TODO");
    }
    
    /**
     * TODO: מימוש startBattle
     * מתחיל קרב עם אויב.
     */
    private void startBattle() {
        // TODO: Implement this method
        // 1. צור אויב (דמות אקראית)
        // 2. צור BattleSystem
        // 3. הרץ את הקרב
        System.out.println("\nBattle - TODO");
    }
    
    /**
     * נקודת הכניסה למשחק.
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
