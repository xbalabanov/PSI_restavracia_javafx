# Restaurant Management System - Summary

## ✅ Completed Implementation

### Database Layer
- **DBConnection.java** - PostgreSQL connection (Singleton pattern)
- **Repository.java** - Data access layer with methods for all 4 UCs
- **DatabaseSetup.sql** - SQL script to create tables and sample data

### Models (15 classes)
- Core entities: Zakaznik, Casnik, Kuchar, Hosteska
- Business objects: Objednavka, Reklamacia, Rezervacia, Platba, Ucet
- Support objects: Menu, ObjednaneJedlo, Stol, ZlavovyKupon, Kuchyna, Pokladna, Sistem

### Services (6 classes)
- **ObjednavkaService** - UC01 logic
- **ReklamaciaService** - UC02 logic
- **RezervaciaService** - UC03 logic
- **PlatbaService** - UC04 logic
- **StolService** - Table management
- **MenuService** - Menu item management

### UI Layer (5 classes + Main.java)
- **Main.java** - Tabbed application with styling
- **ObjednavkaView** - UC01 UI with table/menu selection
- **ReklamaciaView** - UC02 UI with complaint handling
- **RezervaciaView** - UC03 UI with date/time picker
- **PlatbaView** - UC04 UI with payment method selection
- **LoginView** - (for future use)

## 📊 Architecture

```
Main Application (Tabbed Interface)
│
├── UC01 Tab (Objednavka)
│   └── ObjednavkaView → ObjednavkaService → Repository → PostgreSQL
│
├── UC02 Tab (Reklamacia)
│   └── ReklamaciaView → ReklamaciaService → Repository → PostgreSQL
│
├── UC03 Tab (Rezervacia)
│   └── RezervaciaView → RezervaciaService → Repository → PostgreSQL
│
└── UC04 Tab (Platba)
    └── PlatbaView → PlatbaService → Repository → PostgreSQL
```

## 🎨 Styling Applied

- Dark header (#2c3e50) with white text
- Green buttons for main actions (#4CAF50)
- Red buttons for delete actions (#f44336)
- Separators between form sections
- Font size 12pt for consistency
- Status labels with color feedback (green=success, red=error, blue=info)

## 🗄️ Database Schema

8 main tables:
- stoly (10 sample tables with capacity 2-8)
- menu (8 sample items)
- objednavky
- objednane_jedla
- rezervacie
- reklamacie
- ucty
- platby

## 🚀 Quick Start

1. Create PostgreSQL database
2. Run DatabaseSetup.sql
3. Update connection details in DBConnection.java
4. Run the app with `./mvnw javafx:run`
5. Use tab interface to access all 4 use cases

## 📝 Use Cases Workflow

**UC01 - Order Taking:**
1. Select table → Add items from menu → Set quantity → Confirm

**UC02 - Complaint Handling:**
1. Select order → Describe problem → Approve/Reject → Generate discount

**UC03 - Reservation:**
1. Enter customer info → Select date/time → Choose persons → Confirm

**UC04 - Payment:**
1. Select order to bill → View items → Choose payment method → Process

## ✨ OOP Principles Applied

✓ **Encapsulation** - Private fields with public getters/setters
✓ **Single Responsibility** - Each class has one purpose
✓ **Inheritance** - Service hierarchy (though lightweight for simplicity)
✓ **Polymorphism** - Service methods handle different types
✓ **Abstraction** - Repository abstracts database operations
✓ **Singleton Pattern** - Database connection
✓ **Collections** - ArrayList for managing multiple entities

## 🔧 Technologies

- Java 21+
- JavaFX 21+ (UI Framework)
- PostgreSQL 12+ (Database)
- MVC Architecture (Model-View-Controller)

## 📦 Project Files

```
src/
├── main/java/
│   ├── com/example/psi_restavracia_javafx/Main.java
│   ├── models/              (15 Java files)
│   ├── services/            (6 Java files)
│   ├── database/            (3 Java files)
│   └── ui/                  (5 Java files)
└── main/resources/
    └── database/DatabaseSetup.sql

SETUP_GUIDE.md
README.md
```

## ✅ All 4 Use Cases Connected

- **UC01**: Complete with database storage
- **UC02**: Complete with complaint handling & discount vouchers
- **UC03**: Complete with table availability check
- **UC04**: Complete with payment recording

## 🎯 No Complex Logic

- Simple business rules
- Direct database operations
- Straightforward validation
- Clean, readable code
- Easy to understand and extend

---

**Status**: ✅ READY FOR USE

All 4 use cases fully implemented with PostgreSQL integration and JavaFX UI!
