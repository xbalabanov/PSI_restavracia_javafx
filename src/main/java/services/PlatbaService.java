package services;

import models.Ucet;
import models.Platba;
import models.ObjednaneJedlo;
import models.ZlavovyKupon;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Service class responsible for handling payments (Platba).
 * Manages bill creation, voucher application, and payment processing.
 */

public class PlatbaService {

    // UC04 - Realizácia platby a uvoľnenie stola
    /**
     * Creates a bill (Ucet) from a list of ordered items.
     *
     * @param polozky list of ordered items
     * @return created bill
     */
    public Ucet createUcet(List<ObjednaneJedlo> polozky) {
        Ucet ucet = new Ucet();
        double suma = 0;

        for (ObjednaneJedlo jedlo : polozky) {
            ucet.addPolozka(jedlo);
            suma += jedlo.getTotalCena();
        }

        ucet.setSuma(suma);
        ucet.setStav("vystaveny");
        ucet.setCas(LocalDateTime.now());
        return ucet;
    }

    /**
     * Applies a discount voucher to a bill.
     *
     * @param ucet bill
     * @param kupon discount coupon
     */
    public void applyVoucher(Ucet ucet, ZlavovyKupon kupon) {
        if (!kupon.isValid()) {
            System.out.println("Kupón nie je platný!");
            return;
        }

        double zlavaAmount = ucet.getSuma() * (kupon.getZlavaProcent() / 100);
        ucet.setZlava(zlavaAmount);
        kupon.setPouzity(true);
        System.out.println("Kupón aplikovaný - zľava: " + zlavaAmount + "€");
    }

    /**
     * Processes a payment for a given bill.
     *
     * @param ucet bill to be paid
     * @param sposob payment method (e.g., hotovost, karta)
     * @return processed payment
     */
    public Platba processPlatba(Ucet ucet, String sposob) {
        Platba platba = new Platba();
        platba.setSposob(sposob);
        platba.setSuma(ucet.getFinalniSuma());
        platba.setCas(LocalDateTime.now());
        platba.setUcetId(ucet.getId());

        // Zjednodušene predpokladáme úspešnú platbu
        platba.setStav("vybavena");
        ucet.setStav("zaplateny");

        System.out.println("Platba úspešne spracovaná - " + sposob);
        return platba;
    }

    /**
     * Marks a payment as rejected.
     *
     * @param platba payment to reject
     */
    public void rejectPlatba(Platba platba) {
        platba.setStav("odmietuta");
        System.out.println("Platba bola odmietnutá");
    }

    /**
     * Calculates the final price of a bill after discount.
     *
     * @param ucet bill
     * @return final price
     */
    public double calculateFinalPrice(Ucet ucet) {
        return ucet.getFinalniSuma();
    }
}
