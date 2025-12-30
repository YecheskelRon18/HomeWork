package game;

import model.characters.Character;
import model.exceptions.InsufficientGoldException;
import model.exceptions.InventoryFullException;
import model.exceptions.ItemNotFoundException;
import model.items.Armor;
import model.items.Item;
import model.items.Potion;
import model.items.Weapon;

import java.util.ArrayList;
import java.util.HashMap;

public class Shop {

    private String name;
    private ArrayList<Item> inventory;
    private HashMap<String, Integer> stock;

    public Shop(String name) {
        this.name = name;
        this.inventory = new ArrayList<>();
        this.stock = new HashMap<>();
    }


    public void addItemToShop(Item item, int quantity) {
        boolean exists = false;

        for (Item i : inventory) {
            if (i.getName().equals(item.getName())) {
                exists = true;
                break;
            }
        }

        if (!exists) {
            inventory.add(item);
        }

        int current = stock.getOrDefault(item.getName(), 0);
        stock.put(item.getName(), current + quantity);
    }

    public ArrayList<Item> getAvailableItems() {
        ArrayList<Item> result = new ArrayList<>();

        for (Item item : inventory) {
            if (stock.getOrDefault(item.getName(), 0) > 0) {
                result.add(item);
            }
        }

        return result;
    }

    public ArrayList<Item> getItemsByCategory(String category) {
        ArrayList<Item> result = new ArrayList<>();
        String c = category.toLowerCase();

        for (Item item : inventory) {
            if (c.equals("weapon") && item instanceof Weapon) {
                result.add(item);
            } else if (c.equals("armor") && item instanceof Armor) {
                result.add(item);
            } else if (c.equals("potion") && item instanceof Potion) {
                result.add(item);
            }
        }

        return result;
    }


    public Item buyItem(Character customer, String itemName)
            throws ItemNotFoundException, InsufficientGoldException,
            InventoryFullException {

        Item found = null;

        for (Item item : inventory) {
            if (item.getName().equals(itemName)) {
                found = item;
                break;
            }
        }

        if (found == null || stock.getOrDefault(itemName, 0) <= 0) {
            throw new ItemNotFoundException("Item not found in shop: " + itemName);
        }

        int price = found.getBuyPrice();

        if (!customer.spendGold(price)) {
            throw new InsufficientGoldException(price, 0);
        }

        customer.addItem(found);
        stock.put(itemName, stock.get(itemName) - 1);

        return found;
    }

    public int sellItem(Character seller, String itemName)
            throws ItemNotFoundException {

        Item item = seller.removeItem(itemName);

        if (!item.isSellable()) {
            throw new ItemNotFoundException("Item not sellable: " + itemName);
        }

        addItemToShop(item, 1);
        int gold = item.getSellPrice();
        seller.addGold(gold);

        return gold;
    }

    public int getItemStock(String itemName) {
        return stock.getOrDefault(itemName, 0);
    }

    public int getTotalValue() {
        int total = 0;

        for (Item item : inventory) {
            int qty = stock.getOrDefault(item.getName(), 0);
            total += item.getBuyPrice() * qty;
        }

        return total;
    }



    public HashMap<String, String> getInventoryReport() {
        HashMap<String, String> report = new HashMap<>();

        for (Item item : inventory) {
            int qty = stock.getOrDefault(item.getName(), 0);
            report.put(
                    item.getName(),
                    "Price: " + item.getBuyPrice() + ", Stock: " + qty
            );
        }

        return report;
    }

    public HashMap<String, int[]> getPriceComparison() {
        HashMap<String, int[]> map = new HashMap<>();

        for (Item item : inventory) {
            map.put(
                    item.getName(),
                    new int[]{item.getBuyPrice(), item.getSellPrice()}
            );
        }

        return map;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getUniqueItemCount() {
        return inventory.size();
    }

    public int getTotalItemCount() {
        int total = 0;
        for (int count : stock.values()) {
            total += count;
        }
        return total;
    }

    @Override
    public String toString() {
        return String.format("Shop: " + name + " items: " + getUniqueItemCount() + "  unique, " + getTotalItemCount() + " total");
    }
}
