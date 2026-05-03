package models;

/**
 * Model class representing a customer (Zakaznik).
 * Stores basic information such as name and contact details.
 */
public class Zakaznik {
    /**
     * Creates a customer with specified values.
     *
     * @param id customer ID
     * @param meno customer name
     * @param kontakt contact information
     */
    public Zakaznik(int id, String meno, String kontakt) {
        this.id = id;
        this.meno = meno;
        this.kontakt = kontakt;
    }

    /**
     * Default constructor initializing customer with default values.
     */
    public Zakaznik() {
        this(0, "", "");
    }

    /**
     * Getter and setter methods.
     */
    private int id;
    private String meno;
    private String kontakt;

    /**
     * Returns customer ID.
     *
     * @return ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets customer ID.
     *
     * @param id customer ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns customer name.
     *
     * @return name
     */
    public String getMeno() {
        return meno;
    }

    /**
     * Sets customer name.
     *
     * @param meno name
     */
    public void setMeno(String meno) {
        this.meno = meno;
    }

    /**
     * Returns contact information.
     *
     * @return contact
     */
    public String getKontakt() {
        return kontakt;
    }

    /**
     * Sets contact information.
     *
     * @param kontakt contact
     */
    public void setKontakt(String kontakt) {
        this.kontakt = kontakt;
    }

    /**
     * Returns string representation of the customer.
     *
     * @return string with customer details
     */
    @Override
    public String toString() {
        return "Zakaznik{" +
                "id=" + id +
                ", meno='" + meno + '\'' +
                ", kontakt='" + kontakt + '\'' +
                '}';
    }
}
