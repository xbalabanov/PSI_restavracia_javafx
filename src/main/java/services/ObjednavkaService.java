package services;

import models.Objednavka;
import models.ObjednaneJedlo;
import models.Menu;
import models.Stol;
import java.time.LocalDateTime;

/**
 * Service class responsible for managing orders (Objednavka).
 * Handles creation, modification, and status transitions of orders.
 */

public class ObjednavkaService {

    // UC01 - Prijatie a zaevidovanie objednávky zákazníka
    /**
     * Creates a new order for a given table.
     *
     * @param stol table where the order is created
     * @return newly created order
     */
    public Objednavka createObjednavka(Stol stol) {
        Objednavka objednavka = new Objednavka();
        objednavka.setStol(stol);
        objednavka.setCas(LocalDateTime.now());
        objednavka.setStav(0); // vytvarana
        return objednavka;
    }

    /**
     * Adds a menu item to an order.
     *
     * @param objednavka target order
     * @param menu menu item
     * @param pocet quantity
     */
    public void addItemToObjednavka(Objednavka objednavka, Menu menu, int pocet) {
        if (!menu.isDostupnost()) {
            System.out.println("Položka nie je dostupná!");
            return;
        }

        ObjednaneJedlo jedlo = new ObjednaneJedlo();
        jedlo.setMenu(menu);
        jedlo.setPocet(pocet);
        jedlo.setCena(menu.getCena());

        objednavka.addPolozka(jedlo);
    }

    /**
     * Confirms the order and sends it to the kitchen.
     *
     * @param objednavka order to confirm
     */
    public void confirmObjednavka(Objednavka objednavka) {
        objednavka.setStav(1); // potvrdena
        System.out.println("Objednávka potvrdená a odoslaná do kuchyne");
    }

    /**
     * Removes an item from an order.
     *
     * @param objednavka target order
     * @param jedlo item to remove
     */
    public void removeItemFromObjednavka(Objednavka objednavka, ObjednaneJedlo jedlo) {
        objednavka.removePolozka(jedlo);
        System.out.println("Položka odstránená z objednávky");
    }

    // UC05 - Príprava a doručenie objednávky
    /**
     * Marks the order as being prepared.
     *
     * @param objednavka order to update
     */
    public void markAsInProgress(Objednavka objednavka) {
        objednavka.setStav(2); // v spracovani
        System.out.println("Objednávka sa pripravuje");
    }

    /**
     * Marks the order as completed.
     *
     * @param objednavka order to update
     */
    public void markAsDone(Objednavka objednavka) {
        objednavka.setStav(3); // vybavena
        System.out.println("Objednávka je pripravená");
    }
}
