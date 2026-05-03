package services;

import models.Reklamacia;
import models.Objednavka;
import models.ZlavovyKupon;
import java.time.LocalDateTime;
import java.time.LocalDate;

/**
 * Service class responsible for handling complaints (Reklamacia).
 * Manages creation, approval, rejection, and compensation logic.
 */
public class ReklamaciaService {

    // UC02 - Reklamácia jedla
    /**
     * Creates a new complaint for a given order.
     *
     * @param objednavka related order
     * @param zakaznikId customer ID
     * @param dovod reason for complaint
     * @return created complaint
     */
    public Reklamacia createReklamacia(Objednavka objednavka, int zakaznikId, String dovod) {
        Reklamacia reklamacia = new Reklamacia();
        reklamacia.setDovod(dovod);
        reklamacia.setObjednavkaId(objednavka.getId());
        reklamacia.setZakaznikId(zakaznikId);
        reklamacia.setStav("nova");
        reklamacia.setCas(LocalDateTime.now());
        return reklamacia;
    }

    /**
     * Approves a complaint and determines resolution.
     *
     * @param reklamacia complaint to approve
     * @param zakaznikChce_pockat whether the customer wants to wait for replacement
     */
    public void approveReklamacia(Reklamacia reklamacia, boolean zakaznikChce_pockat) {
        if (zakaznikChce_pockat) {
            reklamacia.setVysledok("vymena");
            System.out.println("Zákazník čaká na opravu jedla");
        } else {
            reklamacia.setVysledok("vracanie_penazi");
            System.out.println("Zákazník dostane vrátené peniaze");
        }
        reklamacia.setStav("schvalena");
    }

    /**
     * Rejects a complaint.
     *
     * @param reklamacia complaint to reject
     */
    public void rejectReklamacia(Reklamacia reklamacia) {
        reklamacia.setStav("zamietnuta");
        System.out.println("Reklamácia bola zamietnutá");
    }

    /**
     * Generates a compensation voucher for the customer.
     *
     * @param percentZlava discount percentage
     * @return generated voucher
     */
    public ZlavovyKupon generateCompensationVoucher(double percentZlava) {
        ZlavovyKupon kupon = new ZlavovyKupon();
        kupon.setKod("REC-" + System.currentTimeMillis());
        kupon.setZlavaProcent(percentZlava);
        kupon.setPlatnostDo(LocalDate.now().plusDays(30));
        kupon.setPouzity(false);
        return kupon;
    }
}
