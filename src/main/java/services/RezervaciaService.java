package services;

import models.Rezervacia;
import models.Stol;
import java.time.LocalDateTime;

/**
 * Service class responsible for managing reservations (Rezervacia).
 * Handles creation, validation, and lifecycle of table reservations.
 */
public class RezervaciaService {

    /**
     * Creates a new reservation with provided customer details.
     *
     * @param zakaznikMeno customer name
     * @param zakaznikKontakt customer contact
     * @param cas reservation time
     * @param pocetOsob number of people
     * @param poznamky additional notes
     * @return created reservation
     */
    // UC03 - Rezervácia stola (UC03a - osobne, UC03b - telefonicky)
    public Rezervacia createRezervacia(String zakaznikMeno, String zakaznikKontakt,
                                       LocalDateTime cas, int pocetOsob, String poznamky) {
        Rezervacia rezervacia = new Rezervacia();
        rezervacia.setZakaznikMeno(zakaznikMeno);
        rezervacia.setZakaznikKontakt(zakaznikKontakt);
        rezervacia.setCas(cas);
        rezervacia.setPocetOsob(pocetOsob);
        rezervacia.setPoznamky(poznamky);
        rezervacia.setStav("vytvarana");
        return rezervacia;
    }

    /**
     * Checks if a table is available at a given time.
     *
     * @param stol table
     * @param cas requested time
     * @return true if table is available
     */
    public boolean checkTableAvailability(Stol stol, LocalDateTime cas) {
        // Zjednodušená kontrola - stôl musí byť voľný
        return stol.getStav().equals("volny");
    }

    /**
     * Assigns a table to a reservation and updates its status.
     *
     * @param rezervacia reservation
     * @param stol table to assign
     */
    public void assignTableToRezervacia(Rezervacia rezervacia, Stol stol) {
        rezervacia.setStolId(stol.getId());
        rezervacia.setStav("potvrdena");
        stol.setStav("rezervovany");
        System.out.println("Stôl " + stol.getId() + " je rezervovaný");
    }

    /**
     * Confirms a reservation.
     *
     * @param rezervacia reservation to confirm
     */
    public void confirmRezervacia(Rezervacia rezervacia) {
        rezervacia.setStav("rezervovana");
        System.out.println("Rezervácia potvrdená");
    }

    /**
     * Cancels a reservation.
     *
     * @param rezervacia reservation to cancel
     */
    public void cancelRezervacia(Rezervacia rezervacia) {
        rezervacia.setStav("zrusena");
        System.out.println("Rezervácia zrušená");
    }

    /**
     * Activates a reservation when the customer arrives.
     *
     * @param rezervacia reservation to activate
     */
    public void activateRezervacia(Rezervacia rezervacia) {
        rezervacia.setStav("aktivna");
        System.out.println("Zákazník je usadený - rezervácia aktivná");
    }

    /**
     * Marks a reservation as completed.
     *
     * @param rezervacia reservation to complete
     */
    public void completeRezerbacia(Rezervacia rezervacia) {
        rezervacia.setStav("dokoncena");
        System.out.println("Rezervácia dokončená");
    }
}
