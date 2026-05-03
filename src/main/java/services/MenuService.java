package services;

import models.Menu;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class responsible for managing menu items.
 * Provides basic operations such as retrieval, update, addition, and removal of menu items.
 */
public class MenuService {

    private List<Menu> menu;

    /**
     * Initializes the menu service and loads default menu items.
     */
    public MenuService() {
        this.menu = new ArrayList<>();
        initializeMenu();
    }

    /**
     * Loads default menu items into the system.
     */
    public void initializeMenu() {
        menu.add(new Menu(1, "Pizza Margarita", 8.50, true));
        menu.add(new Menu(2, "Pizza Pepperoni", 9.50, true));
        menu.add(new Menu(3, "Kofola 0.5L", 1.50, true));
        menu.add(new Menu(4, "Voda 0.5L", 1.00, true));
        menu.add(new Menu(5, "Pasta Carbonara", 10.00, true));
        menu.add(new Menu(6, "Cesnak chlieb", 3.00, true));
    }

    /**
     * Returns all menu items.
     *
     * @return list of menu items
     */
    public List<Menu> getAllMenuItems() {
        return menu;
    }

    /**
     * Finds a menu item by its ID.
     *
     * @param id menu item ID
     * @return menu item or null if not found
     */
    public Menu getMenuItemById(int id) {
        for (Menu item : menu) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    /**
     * Updates availability of a menu item.
     *
     * @param id menu item ID
     * @param dostupnost availability status
     */
    public void updateMenuItemAvailability(int id, boolean dostupnost) {
        Menu item = getMenuItemById(id);
        if (item != null) {
            item.setDostupnost(dostupnost);
            System.out.println("Dostupnosť položky " + item.getNazov() + " zmenená na: " + dostupnost);
        }
    }

    /**
     * Adds a new menu item.
     *
     * @param item menu item to add
     */
    public void addMenuItem(Menu item) {
        menu.add(item);
    }

    /**
     * Removes a menu item by ID.
     *
     * @param id menu item ID
     */
    public void removeMenuItem(int id) {
        Menu item = getMenuItemById(id);
        if (item != null) {
            menu.remove(item);
        }
    }
}
