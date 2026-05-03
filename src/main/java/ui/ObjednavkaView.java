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

        Label titleLabel = new Label("UC01 - Prijatie objednávky");
        titleLabel.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");

        // Table selection
        Label tableLabel = new Label("Vyberte stôl:");
        ObservableList<Stol> tables = FXCollections.observableArrayList(
                repository.getAllStoly());
        tableCombo = new ComboBox<>(tables);
        tableCombo.setPrefWidth(300);
        tableCombo.setCellFactory(lv -> new ListCell<Stol>() {
            @Override
            protected void updateItem(Stol item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : "Stôl " + item.getId() + " (" + item.getStav() + ")");
            }
        });
        tableCombo.setButtonCell(new ListCell<Stol>() {
            @Override
            protected void updateItem(Stol item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : "Stôl " + item.getId() + " (" + item.getStav() + ")");
            }
        });

        // Menu selection
        Label menuLabel = new Label("Vyberte položku:");
        ObservableList<Menu> menuItems = FXCollections.observableArrayList(
                repository.getAllMenu());
        menuCombo = new ComboBox<>(menuItems);
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

    private void handleAddItem() {
        if (tableCombo.getValue() == null) {
            showAlert("Vyberte stôl!");
            return;
        }

        if (currentOrder == null) {
            currentOrder = objednavkaService.createObjednavka(tableCombo.getValue());
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

        // Save to database
        int orderId = repository.createObjednavka(currentOrder);
        for (ObjednaneJedlo jedlo : currentOrder.getPolozky()) {
            repository.addObjedlaneJedlo(orderId, jedlo);
        }

        objednavkaService.confirmObjednavka(currentOrder);
        repository.updateObjednavkaStav(orderId, 1); // potvrdena

        showAlert("Objednávka bola úspešne potvrdená a odoslaná do kuchyne!");
        reset();
    }

    private void handleCancel() {
        reset();
        showAlert("Objednávka bola zrušená");
    }

    private void updateOrderSummary() {
        StringBuilder sb = new StringBuilder();
        if (currentOrder != null) {
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
        menuCombo.setValue(null);
        quantitySpinner.getValueFactory().setValue(1);
        orderSummary.clear();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informácia");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
