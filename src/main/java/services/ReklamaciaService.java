package services;

import models.Reklamacia;
import models.Objednavka;
import models.ZlavovyKupon;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class ReklamaciaService {

    // UC02 - Reklamácia jedla
    public Reklamacia createReklamacia(Objednavka objednavka, int zakaznikId, String dovod) {
        Reklamacia reklamacia = new Reklamacia();
        reklamacia.setDovod(dovod);
        reklamacia.setObjednavkaId(objednavka.getId());
        reklamacia.setZakaznikId(zakaznikId);
        reklamacia.setStav("nova");
        reklamacia.setCas(LocalDateTime.now());
        return reklamacia;
    }

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

    public void rejectReklamacia(Reklamacia reklamacia) {
        reklamacia.setStav("zamietnuta");
        System.out.println("Reklamácia bola zamietnutá");
    }

    public ZlavovyKupon generateCompensationVoucher(double percentZlava) {
        ZlavovyKupon kupon = new ZlavovyKupon();
        kupon.setKod("REC-" + System.currentTimeMillis());
        kupon.setZlavaProcent(percentZlava);
        kupon.setPlatnostDo(LocalDate.now().plusDays(30));
        kupon.setPouzity(false);
        return kupon;
    }
}
