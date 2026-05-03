package services;

import models.Objednavka;
import models.ObjednaneJedlo;
import models.Menu;
import models.Stol;
import java.time.LocalDateTime;

public class ObjednavkaService {

    // UC01 - Prijatie a zaevidovanie objednávky zákazníka
    public Objednavka createObjednavka(Stol stol) {
        Objednavka objednavka = new Objednavka();
        objednavka.setStol(stol);
        objednavka.setCas(LocalDateTime.now());
        objednavka.setStav(0); // vytvarana
        return objednavka;
    }

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

    public void confirmObjednavka(Objednavka objednavka) {
        objednavka.setStav(1); // potvrdena
        System.out.println("Objednávka potvrdená a odoslaná do kuchyne");
    }

    public void removeItemFromObjednavka(Objednavka objednavka, ObjednaneJedlo jedlo) {
        objednavka.removePolozka(jedlo);
        System.out.println("Položka odstránená z objednávky");
    }

    // UC05 - Príprava a doručenie objednávky
    public void markAsInProgress(Objednavka objednavka) {
        objednavka.setStav(2); // v spracovani
        System.out.println("Objednávka sa pripravuje");
    }

    public void markAsDone(Objednavka objednavka) {
        objednavka.setStav(3); // vybavena
        System.out.println("Objednávka je pripravená");
    }
}
