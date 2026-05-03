package ui;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Objednavka;
import models.Menu;
import models.Stol;
import models.ObjednaneJedlo;
import services.ObjednavkaService;
import services.MenuService;
import database.Repository;
import java.time.LocalDateTime;

public class ObjednavkaView extends VBox {

    private ComboBox<Stol> tableCombo;
    private ComboBox<Objednavka> existingOrderCombo;
    private ComboBox<Menu> menuCombo;
    private Spinner<Integer> quantitySpinner;
    private TextArea orderSummary;
    private Button addItemButton;
    private Button confirmButton;
    private Button cancelButton;

    private ObjednavkaService objednavkaService;
    private MenuService menuService;
    private Repository repository;
    private Objednavka currentOrder;

    public ObjednavkaView() {
        this.setPadding(new Insets(15));
        this.setSpacing(10);
        this.setStyle("-fx-font-size: 12;");

        objednavkaService = new ObjednavkaService();
        menuService = new MenuService();
        repository = new Repository();
        currentOrder = null;

        Label titleLabel = new Label("UC01 - Prijatie a zmena objednávky");
        titleLabel.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");

        // Existing order selection (for modification)
        Label existingOrderLabel = new Label("Zmeniť existujúcu objednávku:");
        existingOrderCombo = new ComboBox<>();
        refreshExistingOrders();
        existingOrderCombo.setPrefWidth(300);
        existingOrderCombo.setCellFactory(lv -> new ListCell<Objednavka>() {
            @Override
            protected void updateItem(Objednavka item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText("Nová objednávka");
                } else {
                    setText("Objednávka #" + item.getId() + " - Stôl " + item.getStol().getId() + " - " + item.getPolozky().size() + " položiek");
                }
            }
        });
        existingOrderCombo.setButtonCell(new ListCell<Objednavka>() {
            @Override
            protected void updateItem(Objednavka item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText("Nová objednávka");
                } else {
                    setText("Objednávka #" + item.getId() + " - Stôl " + item.getStol().getId());
                }
            }
        });
        existingOrderCombo.setOnAction(e -> handleLoadOrder());

        // Table selection
        Label tableLabel = new Label("Vyberte stôl:");
        tableCombo = new ComboBox<>();
        refreshTableList();
        tableCombo.setPrefWidth(300);
        tableCombo.setCellFactory(lv -> new ListCell<Stol>() {
            @Override
            protected void updateItem(Stol item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText("");
                    setStyle("");
                } else {
                    boolean available = item.getStav().equals("volny") || item.getStav().equals("rezervovany");
                    setText("Stôl " + item.getId()
                            + " (" + item.getStav() + ", kapacita " + item.getKapacita() + ")"
                            + (available ? "" : " - OBSADENÝ"));
                    setStyle(available ? "" : "-fx-text-fill: #999999;");
                }
            }
        });
        tableCombo.setButtonCell(new ListCell<Stol>() {
            @Override
            protected void updateItem(Stol item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText("");
                    setStyle("");
                } else {
                    boolean available = item.getStav().equals("volny") || item.getStav().equals("rezervovany");
                    setText("Stôl " + item.getId()
                            + " (" + item.getStav() + ", kapacita " + item.getKapacita() + ")"
                            + (available ? "" : " - OBSADENÝ"));
                    setStyle(available ? "" : "-fx-text-fill: #999999;");
                }
            }
        });

        // Menu selection
        Label menuLabel = new Label("Vyberte položku:");
        menuCombo = new ComboBox<>();
        menuCombo.getItems().addAll(repository.getAllMenu());
        menuCombo.setPrefWidth(300);

        // Quantity
        Label quantityLabel = new Label("Počet:");
        quantitySpinner = new Spinner<>(1, 10, 1);

        // Add to order button
        addItemButton = new Button("Pridať položku");
        addItemButton.setStyle("-fx-font-size: 12; -fx-padding: 8;");
        addItemButton.setOnAction(e -> handleAddItem());

        // Order summary
        Label summaryLabel = new Label("Zhrnutie objednávky:");
        orderSummary = new TextArea();
        orderSummary.setPrefHeight(200);
        orderSummary.setEditable(false);
        orderSummary.setWrapText(true);
        orderSummary.setStyle("-fx-font-size: 11;");

        // Buttons
        confirmButton = new Button("Potvrdiť objednávku");
        confirmButton
                .setStyle("-fx-font-size: 12; -fx-padding: 8; -fx-text-fill: white; -fx-background-color: #4CAF50;");
        confirmButton.setOnAction(e -> handleConfirm());

        cancelButton = new Button("Zrušiť");
        cancelButton.setStyle("-fx-font-size: 12; -fx-padding: 8;");
        cancelButton.setOnAction(e -> handleCancel());

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(confirmButton, cancelButton);

        this.getChildren().addAll(
                titleLabel,
                new Separator(),
                existingOrderLabel,
                existingOrderCombo,
                new Separator(),
                tableLabel,
                tableCombo,
                menuLabel,
                menuCombo,
                quantityLabel,
                quantitySpinner,
                addItemButton,
                new Separator(),
                summaryLabel,
                orderSummary,
                buttonBox);
    }

    public void refreshTableList() {
        tableCombo.getItems().clear();
        tableCombo.getItems().addAll(repository.getAllStoly());
    }

    public void refreshExistingOrders() {
        existingOrderCombo.getItems().clear();
        existingOrderCombo.getItems().add(null); // Option for new order
        existingOrderCombo.getItems().addAll(repository.getObjednavkyByStatus(3)); // Only confirmed/delivered (not paid)
    }

    private void handleLoadOrder() {
        Objednavka selected = existingOrderCombo.getValue();
        if (selected != null) {
            currentOrder = selected;
            // Find and select the correct table in tableCombo
            for (Stol s : tableCombo.getItems()) {
                if (s.getId() == currentOrder.getStol().getId()) {
                    tableCombo.setValue(s);
                    break;
                }
            }
            updateOrderSummary();
            confirmButton.setText("Uložiť zmeny");
        } else {
            reset();
        }
    }

    private void handleAddItem() {
        if (tableCombo.getValue() == null) {
            showAlert("Vyberte stôl!");
            return;
        }

        // Only check table availability if it's a new order or table is being changed
        if (currentOrder == null || currentOrder.getId() <= 0 || currentOrder.getStol().getId() != tableCombo.getValue().getId()) {
            if (!tableCombo.getValue().getStav().equals("volny") && !tableCombo.getValue().getStav().equals("rezervovany")) {
                showAlert("Tento stôl nie je dostupný, pretože je obsadený alebo má nezaplatenú objednávku!");
                return;
            }
        }

        if (currentOrder == null) {
            currentOrder = objednavkaService.createObjednavka(tableCombo.getValue());
        } else if (currentOrder.getStol().getId() != tableCombo.getValue().getId()) {
            currentOrder.setStol(tableCombo.getValue());
        }

        Menu selectedMenu = menuCombo.getValue();
        int quantity = quantitySpinner.getValue();

        if (selectedMenu != null) {
            objednavkaService.addItemToObjednavka(currentOrder, selectedMenu, quantity);
            updateOrderSummary();
        }
    }

    private void handleConfirm() {
        if (currentOrder == null || currentOrder.getPolozky().isEmpty()) {
            showAlert("Objednávka je prázdna!");
            return;
        }

        int orderId;
        if (currentOrder.getId() > 0) {
            // Updating existing order
            orderId = currentOrder.getId();
            repository.updateObjednavkaStav(orderId, 3); // Directly to status 3
            repository.deleteObjednaneJedla(orderId);
            for (ObjednaneJedlo jedlo : currentOrder.getPolozky()) {
                repository.addObjedlaneJedlo(orderId, jedlo);
            }
            showAlert("Objednávka bola úspešne zmenená!");
        } else {
            // New order
            orderId = repository.createObjednavka(currentOrder);
            for (ObjednaneJedlo jedlo : currentOrder.getPolozky()) {
                repository.addObjedlaneJedlo(orderId, jedlo);
            }
            objednavkaService.confirmObjednavka(currentOrder);
            repository.updateObjednavkaStav(orderId, 3); // Directly to status 3
            repository.updateStolStav(currentOrder.getStol().getId(), "obsadeny");
            showAlert("Objednávka bola úspešne potvrdená a doručená!");
        }

        reset();
        refreshTableList();
        refreshExistingOrders();
    }

    private void handleCancel() {
        reset();
        showAlert("Zmeny boli zrušené");
    }

    private void updateOrderSummary() {
        StringBuilder sb = new StringBuilder();
        if (currentOrder != null) {
            if (currentOrder.getId() > 0) {
                sb.append("Objednávka #").append(currentOrder.getId()).append("\n");
            }
            sb.append("Stôl: ").append(currentOrder.getStol().getId()).append("\n");
            sb.append("─────────────────────\n");
            sb.append("Položky:\n");
            for (var item : currentOrder.getPolozky()) {
                sb.append("• ").append(item.getMenu().getNazov())
                        .append(" x").append(item.getPocet())
                        .append(" = ").append(String.format("%.2f", item.getTotalCena())).append("€\n");
            }
            sb.append("─────────────────────\n");
            sb.append("Celkom: ").append(String.format("%.2f", currentOrder.getTotalCena())).append("€");
        }
        orderSummary.setText(sb.toString());
    }

    private void reset() {
        currentOrder = null;
        tableCombo.setValue(null);
        existingOrderCombo.setValue(null);
        menuCombo.setValue(null);
        quantitySpinner.getValueFactory().setValue(1);
        orderSummary.clear();
        confirmButton.setText("Potvrdiť objednávku");
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informácia");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
