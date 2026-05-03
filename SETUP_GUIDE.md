# Restaurant Management System - Setup Guide

## Prerequisites
- Java 21 or higher
- PostgreSQL 12 or higher
- JavaFX SDK 21+ (only needed for direct IDE or source-run setups)

## PostgreSQL Setup

### 1. Create Database
Run the following SQL script in PostgreSQL:

```bash
psql -U postgres -f src/main/resources/database/DatabaseSetup.sql
```

Or manually run the SQL commands from `src/main/resources/database/DatabaseSetup.sql`

### 2. Connection Configuration
Update database connection in `src/main/java/database/DBConnection.java`:

```java
private static final String URL = "jdbc:postgresql://localhost:5432/restauracia";
private static final String USER = "postgres";
private static final String PASSWORD = "postgres";
```

### 3. Add PostgreSQL JDBC Driver
Add to your project classpath:
```
postgresql-42.x.x.jar
```

## Project Structure

```
PSI_restavracia_javafx/
├── src/
│   ├── main/java/
│   │   ├── com/example/psi_restavracia_javafx/Main.java  # Entry point
│   │   ├── models/                   # Domain objects
│   │   │   ├── Zakaznik.java
│   │   │   ├── Casnik.java
│   │   │   ├── Objednavka.java      # UC01
│   │   │   ├── Reklamacia.java      # UC02
│   │   │   ├── Rezervacia.java      # UC03
│   │   │   ├── Platba.java          # UC04
│   │   │   └── ... (other models)
│   │   ├── services/                 # Business logic
│   │   │   ├── ObjednavkaService.java
│   │   │   ├── ReklamaciaService.java
│   │   │   ├── RezervaciaService.java
│   │   │   ├── PlatbaService.java
│   │   │   └── ...
│   │   ├── database/                 # Database layer
│   │   │   ├── DBConnection.java
│   │   │   ├── Repository.java
│   │   │   └── Database.java
│   │   └── ui/                       # JavaFX Views
│   │       ├── LoginView.java
│   │       ├── ObjednavkaView.java   # UC01 UI
│   │       ├── ReklamaciaView.java   # UC02 UI
│   │       ├── RezervaciaView.java   # UC03 UI
│   │       └── PlatbaView.java       # UC04 UI
│   └── main/resources/
│       └── database/DatabaseSetup.sql
```

## Use Cases Implemented

### UC01 - Prijatie objednávky (Order Taking)
- Select table
- Add menu items with quantity
- Confirm and send to kitchen

### UC02 - Reklamácia jedla (Food Complaint)
- Create complaint for prepared food
- Approve (refund or repair) or reject
- Generate discount voucher

### UC03 - Rezervácia stola (Table Reservation)
- Enter customer details and reservation time
- Check table availability
- Confirm reservation

### UC04 - Realizácia platby (Payment Processing)
- Select order to pay
- View bill summary
- Process payment by cash or card

## Running the Application

1. Ensure PostgreSQL is running
2. Database is created and initialized
3. Compile and run with Maven:
```bash
./mvnw javafx:run
```

Or use your IDE (IntelliJ IDEA, Eclipse, VS Code) to run Launcher.java or Main.java

## Database Tables

- **stoly** - Restaurant tables
- **menu** - Menu items
- **objednavky** - Orders
- **objednane_jedla** - Items in orders
- **rezervacie** - Table reservations
- **reklamacie** - Customer complaints
- **ucty** - Invoices
- **platby** - Payments
- **zlavove_kupony** - Discount vouchers

## Features

✓ PostgreSQL database integration
✓ Simple & clean UI with JavaFX
✓ OOP principles applied
✓ Service layer for business logic
✓ Repository pattern for database access
✓ Tabbed interface for all 4 use cases
✓ Basic styling and color scheme

## Troubleshooting

**Cannot connect to PostgreSQL:**
- Check if PostgreSQL service is running
- Verify connection string in DBConnection.java
- Ensure database user has correct permissions

**UI not showing tables:**
- Verify database is initialized
- Check PostgreSQL connection
- Run DatabaseSetup.sql again

**Buttons not working:**
- Ensure all views are properly imported in Main.java
- Check console for errors

## Notes

- All data is persisted in PostgreSQL
- Simple validation for user inputs
- Status messages show operation results
- Discount vouchers are implemented but not fully connected in UI
