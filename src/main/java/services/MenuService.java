package services;

import models.Menu;
import java.util.ArrayList;
import java.util.List;

public class MenuService {

    private List<Menu> menu;

    public MenuService() {
        this.menu = new ArrayList<>();
        initializeMenu();
    }

    public void initializeMenu() {
        menu.add(new Menu(1, "Pizza Margarita", 8.50, true));
        menu.add(new Menu(2, "Pizza Pepperoni", 9.50, true));
        menu.add(new Menu(3, "Kofola 0.5L", 1.50, true));
        menu.add(new Menu(4, "Voda 0.5L", 1.00, true));
        menu.add(new Menu(5, "Pasta Carbonara", 10.00, true));
        menu.add(new Menu(6, "Cesnak chlieb", 3.00, true));
    }

    public List<Menu> getAllMenuItems() {
        return menu;
    }

    public Menu getMenuItemById(int id) {
        for (Menu item : menu) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public void updateMenuItemAvailability(int id, boolean dostupnost) {
        Menu item = getMenuItemById(id);
        if (item != null) {
            item.setDostupnost(dostupnost);
            System.out.println("Dostupnosť položky " + item.getNazov() + " zmenená na: " + dostupnost);
        }
    }

    public void addMenuItem(Menu item) {
        menu.add(item);
    }

    public void removeMenuItem(int id) {
        Menu item = getMenuItemById(id);
        if (item != null) {
            menu.remove(item);
        }
    }
}
