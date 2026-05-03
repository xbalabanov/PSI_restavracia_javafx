package database;

import models.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Repository class responsible for database access.
 * Handles CRUD operations for all main entities using JDBC.
 */
public class Repository {
    private Connection connection;
    /**
     * Initializes repository and obtains database connection.
     */
    public Repository() {
        this.connection = DBConnection.getInstance().getConnection();
    }
    /**
     * Retrieves all tables with computed occupancy status.
     *
     * @return list of tables
     */
    // === STOLY ===
    public List<Stol> getAllStoly() {
        List<Stol> stoly = new ArrayList<>();
        try {
            String sql = """
                    SELECT s.id,
                           CASE
                               WHEN EXISTS (
                                   SELECT 1
                                   FROM objednavky o
                                   LEFT JOIN ucty u ON u.objednavka_id = o.id
                                   LEFT JOIN platby p ON p.ucet_id = u.id AND p.stav = 'vybavena'
                                   WHERE o.stol_id = s.id
                                     AND o.stav >= 1
                                     AND p.id IS NULL
                               ) THEN 'obsadeny'
                               ELSE s.stav
                           END AS stav,
                           s.kapacita
                    FROM stoly s
                    ORDER BY s.id
                    """;

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Stol stol = new Stol(
                        rs.getInt("id"),
                        rs.getString("stav"),
                        rs.getInt("kapacita"));
                stoly.add(stol);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stoly;
    }
    /**
     * Updates the status of a table.
     *
     * @param stolId ID of the table
     * @param stav new status
     */
    public void updateStolStav(int stolId, String stav) {
        try {
            String sql = "UPDATE stoly SET stav = ? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, stav);
            stmt.setInt(2, stolId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // === MENU ===
    /**
     * Retrieves all menu items from the database.
     *
     * @return list of menu items
     */
    public List<Menu> getAllMenu() {
        List<Menu> menu = new ArrayList<>();
        try {
            String sql = "SELECT * FROM menu";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Menu item = new Menu(
                        rs.getInt("id"),
                        rs.getString("nazov"),
                        rs.getDouble("cena"),
                        rs.getBoolean("dostupnost"));
                menu.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menu;
    }

    // === OBJEDNAVKY (UC01) ===
    /**
     * Creates a new order.
     *
     * @return generated ID or -1 if failed
     */
    public int createObjednavka(Objednavka objednavka) {
        try {
            String sql = "INSERT INTO objednavky (stav, cas, stol_id) VALUES (?, ?, ?) RETURNING id";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, objednavka.getStav());
            stmt.setTimestamp(2, Timestamp.valueOf(objednavka.getCas()));
            stmt.setInt(3, objednavka.getStol().getId());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    /**
     * Adds an ordered item to a specific order.
     *
     * @param objednavkaId ID of the order
     * @param jedlo ordered item to add
     */
    public void addObjedlaneJedlo(int objednavkaId, ObjednaneJedlo jedlo) {
        try {
            String sql = "INSERT INTO objednane_jedla (objednavka_id, menu_id, pocet, cena) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, objednavkaId);
            stmt.setInt(2, jedlo.getMenu().getId());
            stmt.setInt(3, jedlo.getPocet());
            stmt.setDouble(4, jedlo.getCena());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Deletes all ordered items for a given order.
     *
     * @param objednavkaId ID of the order
     */
    public void deleteObjednaneJedla(int objednavkaId) {
        try {
            String sql = "DELETE FROM objednane_jedla WHERE objednavka_id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, objednavkaId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Retrieves all orders.
     *
     * @return list of all orders
     */
    public List<Objednavka> getAllObjednavky() {
        return getObjednavkyByStatus(-1);
    }
    /**
     * Retrieves orders filtered by status.
     *
     * @param status order status (-1 = all)
     * @return list of orders
     */
    public List<Objednavka> getObjednavkyByStatus(int status) {
        List<Objednavka> objednavky = new ArrayList<>();
        try {
            String sql = "SELECT * FROM objednavky";
            if (status != -1) {
                sql += " WHERE stav = ?";
            }
            PreparedStatement stmt = connection.prepareStatement(sql);
            if (status != -1) {
                stmt.setInt(1, status);
            }
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Stol stol = new Stol();
                stol.setId(rs.getInt("stol_id"));

                Objednavka objednavka = new Objednavka(
                        rs.getInt("id"),
                        rs.getInt("stav"),
                        rs.getTimestamp("cas").toLocalDateTime(),
                        stol);

                loadObjednaneJedla(objednavka);
                objednavky.add(objednavka);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objednavky;
    }
    /**
     * Loads ordered items for a given order from the database.
     *
     * @param objednavka order to populate
     */
    private void loadObjednaneJedla(Objednavka objednavka) {
        try {
            String sql = """
                    SELECT oj.id AS objednane_jedlo_id,
                           oj.pocet,
                           oj.cena AS objednane_jedlo_cena,
                           m.id AS menu_id,
                           m.nazov,
                           m.cena AS menu_cena,
                           m.dostupnost
                    FROM objednane_jedla oj
                    JOIN menu m ON oj.menu_id = m.id
                    WHERE oj.objednavka_id = ?
                    """;

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, objednavka.getId());

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Menu menu = new Menu(
                        rs.getInt("menu_id"),
                        rs.getString("nazov"),
                        rs.getDouble("menu_cena"),
                        rs.getBoolean("dostupnost"));

                ObjednaneJedlo jedlo = new ObjednaneJedlo(
                        rs.getInt("objednane_jedlo_id"),
                        rs.getInt("pocet"),
                        rs.getDouble("objednane_jedlo_cena"),
                        menu);

                objednavka.addPolozka(jedlo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Updates quantity of an ordered item or deletes it if quantity is zero or less.
     *
     * @param objednaneJedloId ID of the ordered item
     * @param novyPocet new quantity
     */
    public void updateObjednaneJedloPocet(int objednaneJedloId, int novyPocet) {
        try {
            if (novyPocet <= 0) {
                String sql = "DELETE FROM objednane_jedla WHERE id = ?";
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setInt(1, objednaneJedloId);
                stmt.executeUpdate();
            } else {
                String sql = "UPDATE objednane_jedla SET pocet = ? WHERE id = ?";
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setInt(1, novyPocet);
                stmt.setInt(2, objednaneJedloId);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Updates the status of an order.
     *
     * @param objednavkaId ID of the order
     * @param stav new status
     */
    public void updateObjednavkaStav(int objednavkaId, int stav) {
        try {
            String sql = "UPDATE objednavky SET stav = ? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, stav);
            stmt.setInt(2, objednavkaId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // === REZERVACIE (UC03) ===
    /**
     * Retrieves all unpaid orders.
     *
     * @return list of unpaid orders
     */
    public List<Objednavka> getUnpaidObjednavky() {
        return getObjednavkyWithStatusNot(4);
    }
    /**
     * Retrieves orders excluding a specific status.
     *
     * @param excludedStatus status to exclude
     * @return list of orders
     */
    public List<Objednavka> getObjednavkyWithStatusNot(int excludedStatus) {
        List<Objednavka> objednavky = new ArrayList<>();
        try {
            String sql = "SELECT * FROM objednavky WHERE stav != ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, excludedStatus);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Stol stol = new Stol();
                stol.setId(rs.getInt("stol_id"));

                Objednavka objednavka = new Objednavka(
                        rs.getInt("id"),
                        rs.getInt("stav"),
                        rs.getTimestamp("cas").toLocalDateTime(),
                        stol);

                loadObjednaneJedla(objednavka);
                objednavky.add(objednavka);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objednavky;
    }

    /**
     * Creates a new reservation.
     *
     * @param rezervacia reservation object
     * @return generated ID or -1 if failed
     */
    public int createRezervacia(Rezervacia rezervacia) {
        try {
            String sql = "INSERT INTO rezervacie (stav, cas, stol_id, zakaznik_meno, zakaznik_kontakt, pocet_osob, poznamky) "
                    +
                    "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, rezervacia.getStav());
            stmt.setTimestamp(2, Timestamp.valueOf(rezervacia.getCas()));
            stmt.setInt(3, rezervacia.getStolId());
            stmt.setString(4, rezervacia.getZakaznikMeno());
            stmt.setString(5, rezervacia.getZakaznikKontakt());
            stmt.setInt(6, rezervacia.getPocetOsob());
            stmt.setString(7, rezervacia.getPoznamky());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Retrieves all reservations.
     *
     * @return list of reservations
     */
    public List<Rezervacia> getAllRezerbacie() {
        List<Rezervacia> rezervacie = new ArrayList<>();
        try {
            String sql = "SELECT * FROM rezervacie";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Rezervacia rezervacia = new Rezervacia(
                        rs.getInt("id"),
                        rs.getString("stav"),
                        rs.getTimestamp("cas").toLocalDateTime(),
                        rs.getInt("stol_id"),
                        rs.getString("zakaznik_meno"),
                        rs.getString("zakaznik_kontakt"),
                        rs.getInt("pocet_osob"),
                        rs.getString("poznamky"));
                rezervacie.add(rezervacia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rezervacie;
    }

    /**
     * Updates reservation status.
     *
     * @param rezervaciaId reservation ID
     * @param stav new status
     */
    public void updateRezervaciaStav(int rezervaciaId, String stav) {
        try {
            String sql = "UPDATE rezervacie SET stav = ? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, stav);
            stmt.setInt(2, rezervaciaId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a reservation.
     *
     * @param rezervaciaId reservation ID
     */
    public void deleteRezervacia(int rezervaciaId) {
        try {
            String sql = "DELETE FROM rezervacie WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, rezervaciaId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // === UCTY & PLATBY (UC04) ===
    /**
     * Creates a bill (account) for an order.
     */
    public int createUcet(int objednavkaId, double suma) {
        try {
            String sql = "INSERT INTO ucty (stav, suma, cas, objednavka_id) VALUES (?, ?, ?, ?) RETURNING id";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, "vystaveny");
            stmt.setDouble(2, suma);
            stmt.setTimestamp(3, Timestamp.valueOf(java.time.LocalDateTime.now()));
            stmt.setInt(4, objednavkaId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Creates a payment record.
     *
     * @param ucetId account ID
     * @param sposob payment method
     * @param suma amount
     * @return generated ID or -1 if failed
     */
    public int createPlatba(int ucetId, String sposob, double suma) {
        try {
            String sql = "INSERT INTO platby (sposob, stav, suma, cas, ucet_id) VALUES (?, ?, ?, ?, ?) RETURNING id";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, sposob);
            stmt.setString(2, "vybavena");
            stmt.setDouble(3, suma);
            stmt.setTimestamp(4, Timestamp.valueOf(java.time.LocalDateTime.now()));
            stmt.setInt(5, ucetId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Updates bill (account) status.
     *
     * @param ucetId account ID
     * @param stav new status
     */
    public void updateUcetStav(int ucetId, String stav) {
        try {
            String sql = "UPDATE ucty SET stav = ? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, stav);
            stmt.setInt(2, ucetId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // === REKLAMACIE (UC02) ===

    /**
     * Creates a new complaint.
     *
     * @param reklamacia complaint object
     * @return generated ID or -1 if failed
     */
    public int createReklamacia(Reklamacia reklamacia) {
        try {
            String sql = "INSERT INTO reklamacie (dovod, stav, cas, objednavka_id, zakaznik_id) " +
                    "VALUES (?, ?, ?, ?, ?) RETURNING id";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, reklamacia.getDovod());
            stmt.setString(2, reklamacia.getStav());
            stmt.setTimestamp(3, Timestamp.valueOf(reklamacia.getCas()));
            stmt.setInt(4, reklamacia.getObjednavkaId());
            stmt.setInt(5, reklamacia.getZakaznikId());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }


    /**
     * Updates complaint status and result.
     *
     * @param reklamaciaId complaint ID
     * @param stav new status
     * @param vysledok result of complaint
     */
    public void updateReklamaciaStav(int reklamaciaId, String stav, String vysledok) {
        try {
            String sql = "UPDATE reklamacie SET stav = ?, vysledok = ? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, stav);
            stmt.setString(2, vysledok);
            stmt.setInt(3, reklamaciaId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
