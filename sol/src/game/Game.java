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
     * מאפשר לשחקן לבחור סוג דמות וליצור אותה.
     */
    private void createCharacter() {
        System.out.print("Enter your character name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            name = "Hero";
        }
        
        System.out.println("\nChoose your class:");
        System.out.println("1. Warrior - High HP and defense, uses rage");
        System.out.println("2. Mage - High mana, powerful spells");
        System.out.println("3. Archer - Critical hits, ranged attacks");
        System.out.print("Your choice: ");
        
        int choice = getPlayerChoice();
        
        switch (choice) {
            case 1:
                player = new Warrior(name);
                System.out.println("\nYou are now a Warrior!");
                break;
            case 2:
                player = new Mage(name);
                System.out.println("\nYou are now a Mage!");
                break;
            case 3:
                player = new Archer(name);
                System.out.println("\nYou are now an Archer!");
                break;
            default:
                player = new Warrior(name);
                System.out.println("\nInvalid choice. Defaulting to Warrior.");
        }
        
        player.addGold(100); // Starting gold
    }
    
    /**
     * יוצר את מפת המבוך עם כמה מיקומים.
     */
    private void initializeMap() {
        map = new DungeonMap();
        
        // Create locations
        GameLocation entrance = new GameLocation("entrance", "Dungeon Entrance", 
            "The entrance to the dark dungeon. Torches flicker on the walls.", 1);
        GameLocation hallway = new GameLocation("hallway", "Dark Hallway", 
            "A long, dark hallway with cobwebs everywhere.", 2);
        GameLocation armory = new GameLocation("armory", "Abandoned Armory", 
            "An old armory. Some weapons might still be useful.", 2);
        GameLocation treasury = new GameLocation("treasury", "Treasure Room", 
            "Glittering gold and gems catch your eye.", 3);
        GameLocation throneRoom = new GameLocation("throne", "Throne Room", 
            "The dark lord awaits on his throne.", 5);
        
        // Add locations to map
        map.addLocation(entrance);
        map.addLocation(hallway);
        map.addLocation(armory);
        map.addLocation(treasury);
        map.addLocation(throneRoom);
        
        // Connect locations
        try {
            map.connectLocations("entrance", "hallway");
            map.connectLocations("hallway", "armory");
            map.connectLocations("hallway", "treasury");
            map.connectLocations("treasury", "throne");
        } catch (InvalidActionException e) {
            System.out.println("Error connecting locations: " + e.getMessage());
        }
        
        // Set special locations
        map.setStartLocation("entrance");
        map.setBossLocation("throne");
        
        // Add some loot to locations
        armory.addLoot(new Weapon("Rusty Sword", "An old but usable sword", 5, 20, 
            Item.ItemRarity.COMMON, 5, 10, Weapon.WeaponType.SWORD));
        treasury.addLoot(new Potion("Health Potion", "Restores 30 HP", 15, 
            Item.ItemRarity.COMMON, Potion.PotionType.HEALTH, 30, 1));
        
        System.out.println("Map initialized with 5 locations.");
    }
    
    /**
     * יוצר את החנות עם פריטים התחלתיים.
     */
    private void initializeShop() {
        shop = new Shop("Village Shop");
        
        // Add weapons
        shop.addItemToShop(new Weapon("Iron Sword", "A sturdy iron sword", 6, 50, 
            Item.ItemRarity.COMMON, 8, 15, Weapon.WeaponType.SWORD), 3);
        shop.addItemToShop(new Weapon("Battle Axe", "A heavy battle axe", 10, 75, 
            Item.ItemRarity.UNCOMMON, 12, 20, Weapon.WeaponType.AXE), 2);
        shop.addItemToShop(new Weapon("Oak Staff", "A magical staff", 4, 60, 
            Item.ItemRarity.COMMON, 6, 12, Weapon.WeaponType.STAFF), 2);
        shop.addItemToShop(new Weapon("Hunting Bow", "A reliable bow", 3, 55, 
            Item.ItemRarity.COMMON, 7, 14, Weapon.WeaponType.BOW), 2);
        
        // Add armor
        shop.addItemToShop(new Armor("Leather Helmet", "Basic head protection", 2, 30, 
            Item.ItemRarity.COMMON, 5, Armor.ArmorSlot.HEAD), 3);
        shop.addItemToShop(new Armor("Chain Mail", "Decent chest protection", 8, 80, 
            Item.ItemRarity.UNCOMMON, 15, Armor.ArmorSlot.CHEST), 2);
        shop.addItemToShop(new Armor("Iron Boots", "Sturdy boots", 4, 40, 
            Item.ItemRarity.COMMON, 8, Armor.ArmorSlot.BOOTS), 3);
        
        // Add potions
        shop.addItemToShop(new Potion("Health Potion", "Restores 30 HP", 20, 
            Item.ItemRarity.COMMON, Potion.PotionType.HEALTH, 30, 1), 10);
        shop.addItemToShop(new Potion("Mana Potion", "Restores 25 MP", 25, 
            Item.ItemRarity.COMMON, Potion.PotionType.MANA, 25, 1), 8);
        shop.addItemToShop(new Potion("Greater Health Potion", "Restores 60 HP", 50, 
            Item.ItemRarity.UNCOMMON, Potion.PotionType.HEALTH, 60, 1), 5);
        
        System.out.println("Shop initialized with " + shop.getTotalItemCount() + " items.");
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
     * מציג מידע על הדמות.
     */
    private void viewCharacter() {
        System.out.println("\n=== Character Info ===");
        System.out.println(player.toString());
        System.out.println("Strength: " + player.getBaseStrength());
        System.out.println("Defense: " + player.getTotalDefense());
        if (player.getEquippedWeapon() != null) {
            System.out.println("Weapon: " + player.getEquippedWeapon().getName());
        }
    }
    
    /**
     * מציג את המלאי של השחקן.
     */
    private void viewInventory() {
        System.out.println("\n=== Inventory ===");
        System.out.println("Items: " + player.getInventorySize() + "/" + player.getMaxInventorySize());
        
        if (player.getInventory().isEmpty()) {
            System.out.println("Your inventory is empty.");
            return;
        }
        
        int i = 1;
        for (Item item : player.getInventory()) {
            System.out.println(i + ". " + item.toString());
            i++;
        }
    }
    
    /**
     * מאפשר לשחקן לזוז למיקום אחר.
     */
    private void moveToLocation() {
        System.out.println("\n=== Current Location ===");
        GameLocation current = map.getCurrentLocation();
        if (current != null) {
            System.out.println(current.toString());
        }
        
        System.out.println("\n=== Accessible Locations ===");
        java.util.ArrayList<GameLocation> accessible = map.getAccessibleLocations();
        
        if (accessible.isEmpty()) {
            System.out.println("No accessible locations from here.");
            return;
        }
        
        int i = 1;
        for (GameLocation loc : accessible) {
            System.out.println(i + ". " + loc.getName() + " (Danger: " + loc.getDangerLevel() + ")" +
                (loc.isVisited() ? " [VISITED]" : ""));
            i++;
        }
        
        System.out.print("\nChoose location (0 to cancel): ");
        int choice = getPlayerChoice();
        
        if (choice > 0 && choice <= accessible.size()) {
            GameLocation target = accessible.get(choice - 1);
            try {
                map.moveTo(target.getId());
                System.out.println("\nYou moved to " + target.getName());
                System.out.println(target.getDescription());
                
                // Check for loot
                if (target.hasLoot()) {
                    System.out.println("\nYou found some loot!");
                    for (Item item : target.collectAllLoot()) {
                        try {
                            player.addItem(item);
                            System.out.println("Picked up: " + item.getName());
                        } catch (InventoryFullException e) {
                            System.out.println("Inventory full! Couldn't pick up: " + item.getName());
                        }
                    }
                }
            } catch (InvalidActionException e) {
                System.out.println("Cannot move there: " + e.getMessage());
            }
        }
    }
    
    /**
     * מאפשר לשחקן לקנות ולמכור בחנות.
     */
    private void visitShop() {
        System.out.println("\n=== " + shop.getName() + " ===");
        System.out.println("Your gold: " + player.getGold());
        
        while (true) {
            System.out.println("\n1. Buy items");
            System.out.println("2. Sell items");
            System.out.println("3. Leave shop");
            System.out.print("Choice: ");
            
            int choice = getPlayerChoice();
            
            if (choice == 1) {
                // Buy
                System.out.println("\n=== Items for Sale ===");
                java.util.ArrayList<Item> available = shop.getAvailableItems();
                int i = 1;
                for (Item item : available) {
                    System.out.println(i + ". " + item.getName() + " - " + item.getBuyPrice() + " gold (Stock: " + shop.getItemStock(item.getName()) + ")");
                    i++;
                }
                System.out.print("Buy which item? (0 to cancel): ");
                int buyChoice = getPlayerChoice();
                
                if (buyChoice > 0 && buyChoice <= available.size()) {
                    Item itemToBuy = available.get(buyChoice - 1);
                    try {
                        shop.buyItem(player, itemToBuy.getName());
                        System.out.println("Bought " + itemToBuy.getName() + "!");
                    } catch (ItemNotFoundException e) {
                        System.out.println("Item not available.");
                    } catch (InsufficientGoldException e) {
                        System.out.println("Not enough gold!");
                    } catch (InventoryFullException e) {
                        System.out.println("Inventory full!");
                    }
                }
            } else if (choice == 2) {
                // Sell
                if (player.getInventory().isEmpty()) {
                    System.out.println("You have nothing to sell.");
                    continue;
                }
                
                System.out.println("\n=== Your Items ===");
                java.util.ArrayList<Item> inventory = player.getInventory();
                int i = 1;
                for (Item item : inventory) {
                    System.out.println(i + ". " + item.getName() + " - Sell for " + item.getSellPrice() + " gold");
                    i++;
                }
                System.out.print("Sell which item? (0 to cancel): ");
                int sellChoice = getPlayerChoice();
                
                if (sellChoice > 0 && sellChoice <= inventory.size()) {
                    Item itemToSell = inventory.get(sellChoice - 1);
                    try {
                        int gold = shop.sellItem(player, itemToSell.getName());
                        System.out.println("Sold for " + gold + " gold!");
                    } catch (ItemNotFoundException e) {
                        System.out.println("Cannot sell that item.");
                    }
                }
            } else if (choice == 3) {
                break;
            }
            
            System.out.println("Your gold: " + player.getGold());
        }
    }
    
    /**
     * מתחיל קרב עם אויב.
     */
    private void startBattle() {
        // Create a random enemy based on current location danger
        GameLocation current = map.getCurrentLocation();
        int danger = (current != null) ? current.getDangerLevel() : 1;
        
        Character enemy;
        double roll = Math.random();
        if (roll < 0.33) {
            enemy = new Warrior("Goblin Warrior");
        } else if (roll < 0.66) {
            enemy = new Mage("Dark Mage");
        } else {
            enemy = new Archer("Skeleton Archer");
        }
        
        // Give enemy experience to level up based on danger
        for (int i = 1; i < danger; i++) {
            enemy.gainExperience(100);
        }
        
        System.out.println("\n=== BATTLE ===");
        System.out.println("A " + enemy.getName() + " (Level " + enemy.getLevel() + ") appears!");
        
        BattleSystem battle = new BattleSystem(player, enemy);
        
        while (!battle.isBattleEnded()) {
            System.out.println("\n" + player.getName() + ": HP " + player.getCurrentHealth() + "/" + player.getMaxHealth());
            System.out.println(enemy.getName() + ": HP " + enemy.getCurrentHealth() + "/" + enemy.getMaxHealth());
            
            System.out.println("\n1. Attack");
            System.out.println("2. Special Ability");
            System.out.println("3. Defend");
            System.out.println("4. Flee");
            System.out.print("Action: ");
            
            int choice = getPlayerChoice();
            BattleAction.ActionType playerAction;
            
            switch (choice) {
                case 1:
                    playerAction = BattleAction.ActionType.ATTACK;
                    break;
                case 2:
                    playerAction = BattleAction.ActionType.SPECIAL;
                    break;
                case 3:
                    playerAction = BattleAction.ActionType.DEFEND;
                    break;
                case 4:
                    playerAction = BattleAction.ActionType.FLEE;
                    break;
                default:
                    playerAction = BattleAction.ActionType.ATTACK;
            }
            
            try {
                battle.queuePlayerAction(playerAction);
                battle.queueAction(battle.generateEnemyAction());
                battle.processAllActions();
            } catch (InvalidActionException e) {
                System.out.println("Invalid action: " + e.getMessage());
            }
        }
        
        if (battle.getWinner() == player) {
            int expGained = danger * 25;
            int goldGained = danger * 10;
            System.out.println("\nVictory! You gained " + expGained + " experience and " + goldGained + " gold!");
            player.gainExperience(expGained);
            player.addGold(goldGained);
        } else {
            System.out.println("\nDefeat...");
        }
    }
    
    /**
     * נקודת הכניסה למשחק.
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
