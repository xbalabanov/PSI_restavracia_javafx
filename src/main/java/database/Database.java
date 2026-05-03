package database;

import models.*;
import java.util.ArrayList;
import java.util.List;

/**
 * In-memory database implemented using the Singleton pattern.
 * Stores and manages all application data.
 */
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

    /**
     * Private constructor that initializes collections
     * and loads default sample data.
     */
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

    /**
     * Returns the single instance of the database.
     *
     * @return Database instance
     */
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    /**
     * Initializes default data (staff and tables).
     */
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

    /**
     * Adds a new customer.
     *
     * @param zakaznik customer to add
     */
    public void addZakaznik(Zakaznik zakaznik) {
        zakaznici.add(zakaznik);
    }

    /**
     * Returns all customers.
     *
     * @return list of customers
     */
    public List<Zakaznik> getAllZakaznici() {
        return zakaznici;
    }

    /**
     * Adds a new waiter.
     *
     * @param casnik waiter to add
     */
    public void addCasnik(Casnik casnik) {
        casnici.add(casnik);
    }

    /**
     * Returns all waiters.
     *
     * @return list of waiters
     */
    public List<Casnik> getAllCasnici() {
        return casnici;
    }

    /**
     * Adds a new table and registers it in the system.
     *
     * @param stol table to add
     */
    public void addStol(Stol stol) {
        stoly.add(stol);
        sistem.addStol(stol);
    }

    /**
     * Returns all tables.
     *
     * @return list of tables
     */
    public List<Stol> getAllStoly() {
        return stoly;
    }

    /**
     * Finds a table by its ID.
     *
     * @param id table ID
     * @return table or null if not found
     */
    public Stol getStolById(int id) {
        for (Stol stol : stoly) {
            if (stol.getId() == id) {
                return stol;
            }
        }
        return null;
    }

    /**
     * Adds a new order and registers it in the system.
     *
     * @param objednavka order to add
     */
    public void addObjednavka(Objednavka objednavka) {
        objednavky.add(objednavka);
        sistem.addObjednavka(objednavka);
    }

    /**
     * Returns all orders.
     *
     * @return list of orders
     */
    public List<Objednavka> getAllObjednavky() {
        return objednavky;
    }

    /**
     * Adds a new reservation and registers it in the system.
     *
     * @param rezervacia reservation to add
     */
    public void addRezervacia(Rezervacia rezervacia) {
        rezervacie.add(rezervacia);
        sistem.addRezerbacia(rezervacia);
    }

    /**
     * Returns all reservations.
     *
     * @return list of reservations
     */
    public List<Rezervacia> getAllRezerbacie() {
        return rezervacie;
    }

    /**
     * Adds a new payment.
     *
     * @param platba payment to add
     */
    public void addPlatba(Platba platba) {
        platby.add(platba);
    }

    /**
     * Returns all payments.
     *
     * @return list of payments
     */
    public List<Platba> getAllPlatby() {
        return platby;
    }

    /**
     * Adds a new complaint.
     *
     * @param reklamacia complaint to add
     */
    public void addReklamacia(Reklamacia reklamacia) {
        reklamacie.add(reklamacia);
    }

    /**
     * Returns all complaints.
     *
     * @return list of complaints
     */
    public List<Reklamacia> getAllReklamacie() {
        return reklamacie;
    }

    /**
     * Returns the system instance.
     *
     * @return system object
     */
    public Sistem getSistem() {
        return sistem;
    }
}