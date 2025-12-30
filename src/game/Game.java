package game;

import model.characters.Archer;
import model.characters.Character;
import model.characters.Mage;
import model.characters.Warrior;
import model.items.Item;
import model.items.Weapon;

import java.util.ArrayList;
import java.util.Scanner;

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

    private void createCharacter() {
        System.out.print("Enter character name: ");
        String name = scanner.nextLine();

        System.out.println("Choose class:");
        System.out.println("1. Warrior");
        System.out.println("2. Mage");
        System.out.println("3. Archer");
        System.out.print("Choice: ");

        int choice = getPlayerChoice();

        switch (choice) {
            case 1:
                player = new Warrior(name);
                break;
            case 2:
                player = new Mage(name);
                break;
            case 3:
                player = new Archer(name);
                break;
            default:
                player = new Warrior(name);
        }
    }

    private void initializeMap() {
        map = new DungeonMap();

        map.addLocation(new GameLocation("moshe", "moshe", "moshes house", 1));
        map.addLocation(new GameLocation("beit shemesh", "beit shemesh", "periferia", 2));
        map.addLocation(new GameLocation("school", "school", "school", 3));
        map.addLocation(new GameLocation("moshe2", "moshe2", "moshes second house", 4));

        try {
            map.connectLocations("moshe", "beit shemesh");
            map.connectLocations("beit shemesh", "school");
            map.connectLocations("school", "moshe2");
        } catch (Exception e) {
        }

        map.setStartLocation("moshe");
        map.setBossLocation("moshe2");
    }


    private void initializeShop() {
        shop = new Shop("store of abu ahmad");

        shop.addItemToShop(new Weapon("glock19", "glock nice n cheap", 2, 150, Item.ItemRarity.COMMON, 5, 8, Weapon.WeaponType.DAGGER), 5);
        shop.addItemToShop(new Weapon("mac11", "high fire rate smg", 4, 300, Item.ItemRarity.UNCOMMON, 8, 12, Weapon.WeaponType.DAGGER), 3);
        shop.addItemToShop(new Weapon("ak47", "gun more reliable than a toyota", 7, 600, Item.ItemRarity.RARE, 15, 22, Weapon.WeaponType.AXE), 2);
        shop.addItemToShop(new Weapon("sword", "mid", 6, 900, Item.ItemRarity.EPIC, 20, 30, Weapon.WeaponType.SWORD), 1);
        shop.addItemToShop(new Weapon("tank merkava 4", "israeli tank", 5000, 2500, Item.ItemRarity.LEGENDARY, 1000, 10020, Weapon.WeaponType.AXE), 1);

    }


    private void gameLoop() {
        while (gameRunning) {
            displayMenu();
            int choice = getPlayerChoice();
            handleChoice(choice);
        }

        System.out.println("\nThanks for playing Dungeon Adventure!");
    }

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

    private int getPlayerChoice() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void handleChoice(int choice) {
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

    private void viewCharacter() {
        System.out.println(player);
    }

    private void viewInventory() {
        ArrayList<Item> inventory = player.getInventory();

        System.out.println("\n--- Inventory ---");

        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        int index = 1;
        for (Item item : inventory) {
            System.out.println(index + ". " + item);
            index++;
        }
    }

    private void moveToLocation() {
        ArrayList<GameLocation> accessible = map.getAccessibleLocations();

        if (accessible.isEmpty()) {
            System.out.println("no accessible locations");
            return;
        }

        System.out.println("you can move to");

        for (int i = 0; i < accessible.size(); i++) {
            GameLocation loc = accessible.get(i);
            System.out.println(
                    (i + 1) + " " + loc.getId() + " danger " + loc.getDangerLevel());
        }

        System.out.print("choose location ");
        int choice = getPlayerChoice();

        if (choice < 1 || choice > accessible.size()) {
            System.out.println("invalid choice");
            return;
        }

        GameLocation target = accessible.get(choice - 1);

        try {
            map.moveTo(target.getId());
            System.out.println("moved to " + target.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private void visitShop() {
        boolean inShop = true;

        while (inShop) {
            System.out.println(shop.getName());
            System.out.println("gold: " + player.getGold());
            System.out.println("1 buy item");
            System.out.println("2 sell item");
            System.out.println("3 view shop inventory");
            System.out.println("4 exit Shop");
            System.out.print("choose: ");

            int choice = getPlayerChoice();

            switch (choice) {
                case 1:
                    System.out.println("\nAvailable Items:");
                    for (Item item : shop.getAvailableItems()) {
                        System.out.println(
                                item.getName() +
                                        "  buy: " + item.getBuyPrice() +
                                        "  stock: " + shop.getItemStock(item.getName())
                        );
                    }

                    System.out.print("enter item name to buy: ");
                    String buyName = scanner.nextLine();

                    try {
                        shop.buyItem(player, buyName);
                        System.out.println("item bought successfully.");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("\nyour Inventory:");
                    for (Item item : player.getInventory()) {
                        System.out.println(
                                item.getName() + " Sell: " + item.getSellPrice()
                        );
                    }

                    System.out.print("enter item name to sell: ");
                    String sellName = scanner.nextLine();

                    try {
                        int gold = shop.sellItem(player, sellName);
                        System.out.println("item sold for " + gold + " gold");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println("\nshop inventory:");
                    System.out.println(shop);
                    break;

                case 4:
                    inShop = false;
                    break;

                default:
                    System.out.println("Invalid ");
            }
        }
    }


    private void startBattle() {
        Character enemy = new Warrior("enemy");

        BattleSystem battle = new BattleSystem(player, enemy);

        System.out.println("battle started");

        while (!battle.isBattleEnded()) {

            System.out.println("choose action");
            System.out.println("1 attack");
            System.out.println("2 special");
            System.out.println("3 use item");
            System.out.println("4 defend");
            System.out.println("5 flee");

            int choice = getPlayerChoice();

            try {
                switch (choice) {
                    case 1:
                        battle.queuePlayerAction(BattleAction.ActionType.ATTACK);
                        break;
                    case 2:
                        battle.queuePlayerAction(BattleAction.ActionType.SPECIAL);
                        break;
                    case 3:
                        System.out.print("item name ");
                        String itemName = scanner.nextLine();
                        battle.queuePlayerItemAction(itemName);
                        break;
                    case 4:
                        battle.queuePlayerAction(BattleAction.ActionType.DEFEND);
                        break;
                    case 5:
                        battle.queuePlayerAction(BattleAction.ActionType.FLEE);
                        break;
                    default:
                        System.out.println("invalid choice");
                        continue;
                }

                battle.queueAction(battle.generateEnemyAction());
                battle.processAllActions();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        Character winner = battle.getWinner();

        if (winner == player) {
            System.out.println("you won the battle");
        } else {
            System.out.println("you lost the battle");
        }
    }


    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}

