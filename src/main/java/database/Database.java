package database;

import models.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private static Database instance;
    private List<Zakaznik> zakaznici;
    private List<Casnik> casnici;
    private List<Kuchar> kuchari;
    private List<Hosteska> hosteski;
    private List<Stol> stoly;
    private List<Objednavka> objednavky;
    private List<Rezervacia> rezervacie;
    private List<Platba> platby;
    private List<Reklamacia> reklamacie;
    private Sistem sistem;

    private Database() {
        this.zakaznici = new ArrayList<>();
        this.casnici = new ArrayList<>();
        this.kuchari = new ArrayList<>();
        this.hosteski = new ArrayList<>();
        this.stoly = new ArrayList<>();
        this.objednavky = new ArrayList<>();
        this.rezervacie = new ArrayList<>();
        this.platby = new ArrayList<>();
        this.reklamacie = new ArrayList<>();
        this.sistem = new Sistem();
        initializeData();
    }

    // Singleton Pattern
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    private void initializeData() {
        // Initialize staff
        casnici.add(new Casnik(1, "Peter", false));
        casnici.add(new Casnik(2, "Mária", false));

        kuchari.add(new Kuchar(1, "Ján", "dostupny"));
        kuchari.add(new Kuchar(2, "Anna", "dostupny"));

        hosteski.add(new Hosteska(1, "Helena", false));
        hosteski.add(new Hosteska(2, "Eva", false));

        // Initialize tables
        for (int i = 1; i <= 10; i++) {
            stoly.add(new Stol(i, "volny", 4));
        }
    }

    // Zakaznik methods
    public void addZakaznik(Zakaznik zakaznik) {
        zakaznici.add(zakaznik);
    }

    public List<Zakaznik> getAllZakaznici() {
        return zakaznici;
    }

    // Casnik methods
    public void addCasnik(Casnik casnik) {
        casnici.add(casnik);
    }

    public List<Casnik> getAllCasnici() {
        return casnici;
    }

    // Stol methods
    public void addStol(Stol stol) {
        stoly.add(stol);
        sistem.addStol(stol);
    }

    public List<Stol> getAllStoly() {
        return stoly;
    }

    public Stol getStolById(int id) {
        for (Stol stol : stoly) {
            if (stol.getId() == id) {
                return stol;
            }
        }
        return null;
    }

    // Objednavka methods
    public void addObjednavka(Objednavka objednavka) {
        objednavky.add(objednavka);
        sistem.addObjednavka(objednavka);
    }

    public List<Objednavka> getAllObjednavky() {
        return objednavky;
    }

    // Rezervacia methods
    public void addRezervacia(Rezervacia rezervacia) {
        rezervacie.add(rezervacia);
        sistem.addRezerbacia(rezervacia);
    }

    public List<Rezervacia> getAllRezerbacie() {
        return rezervacie;
    }

    // Platba methods
    public void addPlatba(Platba platba) {
        platby.add(platba);
    }

    public List<Platba> getAllPlatby() {
        return platby;
    }

    // Reklamacia methods
    public void addReklamacia(Reklamacia reklamacia) {
        reklamacie.add(reklamacia);
    }

    public List<Reklamacia> getAllReklamacie() {
        return reklamacie;
    }

    // Sistem methods
    public Sistem getSistem() {
        return sistem;
    }
}
