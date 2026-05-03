package services;

import models.Stol;

/**
 * Service class responsible for managing table states (Stol).
 * Handles operations such as cleaning and updating table status.
 */
public class StolService {

    // UC06 - Vyčistenie stola
    /**
     * Cleans a table if it is currently occupied and marks it as free.
     *
     * @param stol table to clean
     */
    public void cleanTable(Stol stol) {
        if (stol.getStav().equals("obsadeny")) {
            stol.setStav("volny");
            System.out.println("Stôl " + stol.getId() + " bol vyčistený a je voľný");
        }
    }

    /**
     * Marks a table as occupied.
     *
     * @param stol table to update
     */
    public void markTableAsOccupied(Stol stol) {
        stol.setStav("obsadeny");
        System.out.println("Stôl " + stol.getId() + " je obsadený");
    }

    /**
     * Marks a table as reserved.
     *
     * @param stol table to update
     */
    public void markTableAsReserved(Stol stol) {
        stol.setStav("rezervovany");
        System.out.println("Stôl " + stol.getId() + " je rezervovaný");
    }

    /**
     * Marks a table as free.
     *
     * @param stol table to update
     */
    public void markTableAsFree(Stol stol) {
        stol.setStav("volny");
        System.out.println("Stôl " + stol.getId() + " je voľný");
    }
}
