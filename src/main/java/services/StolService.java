package services;

import models.Stol;

public class StolService {

    // UC06 - Vyčistenie stola
    public void cleanTable(Stol stol) {
        if (stol.getStav().equals("obsadeny")) {
            stol.setStav("volny");
            System.out.println("Stôl " + stol.getId() + " bol vyčistený a je voľný");
        }
    }

    public void markTableAsOccupied(Stol stol) {
        stol.setStav("obsadeny");
        System.out.println("Stôl " + stol.getId() + " je obsadený");
    }

    public void markTableAsReserved(Stol stol) {
        stol.setStav("rezervovany");
        System.out.println("Stôl " + stol.getId() + " je rezervovaný");
    }

    public void markTableAsFree(Stol stol) {
        stol.setStav("volny");
        System.out.println("Stôl " + stol.getId() + " je voľný");
    }
}
