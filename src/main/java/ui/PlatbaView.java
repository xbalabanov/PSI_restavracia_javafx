package ui;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import models.Ucet;
import models.Platba;
import models.Objednavka;
import services.PlatbaService;
import database.Repository;

public class PlatbaView extends VBox {

    private ComboBox<Objednavka> objednavkaCombo;
    private TextArea accountSummary;
    private ComboBox<String> paymentMethodCombo;
    private TextField discountCodeField;
    private Label totalLabel;
    private Button applyDiscountButton;
    private Button processPaymentButton;
    private Button cancelButton;
    private Label statusLabel;

    private PlatbaService platbaService;
    private Repository repository;
    private Ucet currentUcet;
    private Objednavka selectedOrder;

    public PlatbaView() {
        this.setPadding(new Insets(15));
        this.setSpacing(10);
        this.setStyle("-fx-font-size: 12;");

        platbaService = new PlatbaService();
        repository = new Repository();

        Label titleLabel = new Label("UC04 - Realizácia platby");
        titleLabel.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");

        // Order selection
        Label orderLabel = new Label("Vyberte objednávku:");
        objednavkaCombo = new ComboBox<>();
        refreshOrderList();
        objednavkaCombo.setPrefWidth(300);
        objednavkaCombo.setCellFactory(lv -> new ListCell<Objednavka>() {
            @Override
            protected void updateItem(Objednavka item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : "Objednávka #" + item.getId() + " - Stôl " + item.getStol().getId() + " - Stav " + item.getStav());
            }
        });
        objednavkaCombo.setButtonCell(new ListCell<Objednavka>() {
            @Override
            protected void updateItem(Objednavka item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : "Objednávka #" + item.getId() + " - Stôl " + item.getStol().getId() + " - Stav " + item.getStav());
            }
        });
        objednavkaCombo.setOnAction(e -> handleOrderSelected());

        // Account summary
        Label summaryLabel = new Label("Zhrnutie účtu:");
        accountSummary = new TextArea();
        accountSummary.setPrefHeight(150);
        accountSummary.setEditable(false);
        accountSummary.setWrapText(true);

        // Total price
        totalLabel = new Label("Celkom: 0.00€");
        totalLabel.setStyle("-fx-font-size: 14; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        // Discount code
        Label discountLabel = new Label("Zľavový kupón:");
        HBox discountBox = new HBox(5);
        discountCodeField = new TextField();
        discountCodeField.setPromptText("Zadajte kód kupónu");
        applyDiscountButton = new Button("Aplikovať");
        applyDiscountButton.setOnAction(e -> handleApplyDiscount());
        discountBox.getChildren().addAll(discountCodeField, applyDiscountButton);

        // Payment method
        Label methodLabel = new Label("Spôsob platby:");
        paymentMethodCombo = new ComboBox<>();
        paymentMethodCombo.getItems().addAll("Hotovosť", "Karta");
        paymentMethodCombo.setValue("Hotovosť");

        // Buttons
        processPaymentButton = new Button("Spracovať platbu");
        processPaymentButton
                .setStyle("-fx-font-size: 12; -fx-padding: 8; -fx-text-fill: white; -fx-background-color: #4CAF50;");
        processPaymentButton.setOnAction(e -> handleProcessPayment());

        cancelButton = new Button("Zrušiť");
        cancelButton.setStyle("-fx-font-size: 12; -fx-padding: 8;");
        cancelButton.setOnAction(e -> handleCancel());

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(processPaymentButton, cancelButton);

        // Status
        statusLabel = new Label();

        this.getChildren().addAll(
                titleLabel,
                new Separator(),
                orderLabel, objednavkaCombo,
                summaryLabel, accountSummary,
                totalLabel,
                discountLabel, discountBox,
                methodLabel, paymentMethodCombo,
                buttonBox,
                statusLabel);
    }

    public void refreshOrderList() {
        objednavkaCombo.getItems().clear();
        objednavkaCombo.getItems().addAll(repository.getAllObjednavky());
    }

    private void handleOrderSelected() {
        Objednavka selected = objednavkaCombo.getValue();
        if (selected != null && selected.getStav() >= 1) {
            selectedOrder = selected;
            currentUcet = platbaService.createUcet(selected.getPolozky());
            updateAccountSummary();
        }
    }

    private void updateAccountSummary() {
        if (currentUcet != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Položky:\n");

            for (var item : currentUcet.getPolozky()) {
                sb.append("- ").append(item.getMenu().getNazov())
                        .append(" x").append(item.getPocet())
                        .append(" = ").append(String.format("%.2f", item.getTotalCena()))
                        .append("€\n");
            }

            sb.append("\nCena bez zľavy: ")
                    .append(String.format("%.2f", currentUcet.getSuma()))
                    .append("€");

            if (currentUcet.getZlava() > 0) {
                sb.append("\nZľava: -")
                        .append(String.format("%.2f", currentUcet.getZlava()))
                        .append("€");
            }

            accountSummary.setText(sb.toString());
            totalLabel.setText(String.format("Celkom: %.2f€", currentUcet.getFinalniSuma()));
        }
    }

    private void handleApplyDiscount() {
        showAlert("Funkcia na aplikovanie kupónu - v budúcnosti");
    }

    private void handleProcessPayment() {
        if (currentUcet == null || selectedOrder == null) {
            showAlert("Vyberte objednávku!");
            return;
        }

        String method = paymentMethodCombo.getValue();
        Platba platba = platbaService.processPlatba(currentUcet, method);

        if (platba.getStav().equals("vybavena")) {
            int ucetId = repository.createUcet(selectedOrder.getId(), currentUcet.getFinalniSuma());

            statusLabel.setText("✓ Platba úspešne spracovaná!");
            statusLabel.setStyle("-fx-text-fill: green;");
            repository.createPlatba(ucetId, method, currentUcet.getFinalniSuma());
            showAlert("Platba bola spracovaná. Ďakujeme!");
            handleCancel();
        }
    }

    private void handleCancel() {
        currentUcet = null;
        selectedOrder = null;
        objednavkaCombo.setValue(null);
        accountSummary.clear();
        totalLabel.setText("Celkom: 0.00€");
        statusLabel.setText("");
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informácia");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
