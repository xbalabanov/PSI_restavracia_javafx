-- PostgreSQL Setup Script for Restaurant Management System
-- Run this script in PostgreSQL to create all necessary tables

CREATE DATABASE restauracia;

\c restauracia

-- Stoly (Tables)
CREATE TABLE stoly (
    id SERIAL PRIMARY KEY,
    stav VARCHAR(20) NOT NULL,
    kapacita INTEGER NOT NULL
);

-- Menu
CREATE TABLE menu (
    id SERIAL PRIMARY KEY,
    nazov VARCHAR(100) NOT NULL,
    cena DECIMAL(10, 2) NOT NULL,
    dostupnost BOOLEAN NOT NULL
);

-- Objednavky (Orders)
CREATE TABLE objednavky (
    id SERIAL PRIMARY KEY,
    stav INTEGER NOT NULL,
    cas TIMESTAMP NOT NULL,
    stol_id INTEGER NOT NULL,
    FOREIGN KEY (stol_id) REFERENCES stoly(id)
);

-- ObjednaneJedlo (Ordered Items)
CREATE TABLE objednane_jedla (
    id SERIAL PRIMARY KEY,
    objednavka_id INTEGER NOT NULL,
    menu_id INTEGER NOT NULL,
    pocet INTEGER NOT NULL,
    cena DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (objednavka_id) REFERENCES objednavky(id),
    FOREIGN KEY (menu_id) REFERENCES menu(id)
);

-- Rezervacie (Reservations)
CREATE TABLE rezervacie (
    id SERIAL PRIMARY KEY,
    stav VARCHAR(20) NOT NULL,
    cas TIMESTAMP NOT NULL,
    stol_id INTEGER NOT NULL,
    zakaznik_meno VARCHAR(100) NOT NULL,
    zakaznik_kontakt VARCHAR(20) NOT NULL,
    pocet_osob INTEGER NOT NULL,
    poznamky TEXT,
    FOREIGN KEY (stol_id) REFERENCES stoly(id)
);

-- Reklamacie (Complaints)
CREATE TABLE reklamacie (
    id SERIAL PRIMARY KEY,
    dovod VARCHAR(255) NOT NULL,
    stav VARCHAR(20) NOT NULL,
    cas TIMESTAMP NOT NULL,
    objednavka_id INTEGER NOT NULL,
    zakaznik_id INTEGER,
    vysledok VARCHAR(50),
    FOREIGN KEY (objednavka_id) REFERENCES objednavky(id)
);

-- Ucty (Invoices)
CREATE TABLE ucty (
    id SERIAL PRIMARY KEY,
    stav VARCHAR(20) NOT NULL,
    suma DECIMAL(10, 2) NOT NULL,
    zlava DECIMAL(10, 2) DEFAULT 0,
    cas TIMESTAMP NOT NULL,
    objednavka_id INTEGER NOT NULL,
    FOREIGN KEY (objednavka_id) REFERENCES objednavky(id)
);

-- Platby (Payments)
CREATE TABLE platby (
    id SERIAL PRIMARY KEY,
    sposob VARCHAR(20) NOT NULL,
    stav VARCHAR(20) NOT NULL,
    suma DECIMAL(10, 2) NOT NULL,
    cas TIMESTAMP NOT NULL,
    ucet_id INTEGER NOT NULL,
    FOREIGN KEY (ucet_id) REFERENCES ucty(id)
);

-- ZlavoveKupony (Discount Vouchers)
CREATE TABLE zlavove_kupony (
    kod VARCHAR(50) PRIMARY KEY,
    zlava_percent DECIMAL(5, 2) NOT NULL,
    platnost_do DATE NOT NULL,
    pouzity BOOLEAN NOT NULL
);

-- Insert sample data
INSERT INTO stoly (stav, kapacita) VALUES
('volny', 4),
('volny', 4),
('volny', 4),
('volny', 6),
('volny', 6),
('volny', 2),
('volny', 2),
('volny', 8),
('volny', 4),
('volny', 4);

INSERT INTO menu (nazov, cena, dostupnost) VALUES
('Pizza Margarita', 8.50, true),
('Pizza Pepperoni', 9.50, true),
('Pasta Carbonara', 10.00, true),
('Cesnak chlieb', 3.00, true),
('Kofola 0.5L', 1.50, true),
('Voda 0.5L', 1.00, true),
('Rizoto s hubami', 11.00, true),
('Kuracica s rydzou', 12.00, true);
