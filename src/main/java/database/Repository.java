package database;

import models.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    private Connection connection;

    public Repository() {
        this.connection = DBConnection.getInstance().getConnection();
    }

    // === STOLY ===
    public List<Stol> getAllStoly() {
        List<Stol> stoly = new ArrayList<>();
        try {
            String sql = "SELECT * FROM stoly";
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

    public List<Objednavka> getAllObjednavky() {
        List<Objednavka> objednavky = new ArrayList<>();
        try {
            String sql = "SELECT * FROM objednavky";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

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

    // === UCTY & PLATBY (UC04) ===
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
