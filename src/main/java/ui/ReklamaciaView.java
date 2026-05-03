package ui;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import models.Reklamacia;
import models.Objednavka;
import services.ReklamaciaService;
import database.Database;
import database.Repository;
import java.time.LocalDateTime;

public class ReklamaciaView extends VBox {

    private ComboBox<Objednavka> objednavkaCombo;
    private TextArea problemArea;
    private Button createButton;
    private Button approveButton;
    private Button rejectButton;
    private CheckBox customerWaitCheckbox;
    private TextArea resultArea;
    private Label statusLabel;

    private ReklamaciaService reklamaciaService;
    private Repository repository;
    private Reklamacia currentReklamacia;

    public ReklamaciaView() {
        this.setPadding(new Insets(15));
        this.setSpacing(10);
        this.setStyle("-fx-font-size: 12;");

        reklamaciaService = new ReklamaciaService();
        repository = new Repository();

        Label titleLabel = new Label("UC02 - Reklamácia jedla");
        titleLabel.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");

        // Order selection
        Label orderLabel = new Label("Vyberte objednávku:");
        objednavkaCombo = new ComboBox<>();
        objednavkaCombo.setPrefWidth(400);
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
        refreshOrderList();

        // Problem description
        Label problemLabel = new Label("Popis problému:");
        problemArea = new TextArea();
        problemArea.setPrefHeight(100);
        problemArea.setWrapText(true);
        problemArea.setPromptText("Napíšte, čo je zle s jedlom...");

        // Create complaint button
        createButton = new Button("Vytvoriť reklamáciu");
        createButton.setStyle("-fx-font-size: 12; -fx-padding: 8;");
        createButton.setOnAction(e -> handleCreateReklamacia());

        // Separator
        Separator sep = new Separator();

        // Resolution options
        Label resolutionLabel = new Label("Oprava reklamácie:");
        resolutionLabel.setStyle("-fx-font-weight: bold;");

        customerWaitCheckbox = new CheckBox("Zákazník chce čakať na opravu");

        // Approve button
        approveButton = new Button("Schváliť a opraviť");
        approveButton
                .setStyle("-fx-font-size: 12; -fx-padding: 8; -fx-text-fill: white; -fx-background-color: #4CAF50;");
        approveButton.setDisable(true);
        approveButton.setOnAction(e -> handleApprove());

        // Reject button
        rejectButton = new Button("Odmietnuť");
        rejectButton
                .setStyle("-fx-font-size: 12; -fx-padding: 8; -fx-text-fill: white; -fx-background-color: #f44336;");
        rejectButton.setDisable(true);
        rejectButton.setOnAction(e -> handleReject());

        HBox actionBox = new HBox(10);
        actionBox.getChildren().addAll(approveButton, rejectButton);

        // Result area
        Label resultLabel = new Label("Výsledok:");
        resultArea = new TextArea();
        resultArea.setPrefHeight(80);
        resultArea.setEditable(false);
        resultArea.setWrapText(true);

        // Status
        statusLabel = new Label();
        statusLabel.setStyle("-fx-text-fill: blue;");

        this.getChildren().addAll(
                titleLabel,
                new Separator(),
                orderLabel, objednavkaCombo,
                problemLabel, problemArea,
                createButton,
                sep,
                resolutionLabel,
                customerWaitCheckbox,
                actionBox,
                resultLabel, resultArea,
                statusLabel);
    }

    public void refreshOrderList() {
        objednavkaCombo.getItems().clear();
        objednavkaCombo.getItems().addAll(repository.getAllObjednavky());
    }

    private void handleCreateReklamacia() {
        Objednavka selected = objednavkaCombo.getValue();
        String problem = problemArea.getText();

        if (selected == null || problem.isEmpty()) {
            showAlert("Vyberte objednávku a opíšte problém!");
            return;
        }

        currentReklamacia = reklamaciaService.createReklamacia(selected, 1, problem);
        repository.createReklamacia(currentReklamacia);

        statusLabel.setText("Reklamácia vytvorená - Čakanie na spracovanie");
        statusLabel.setStyle("-fx-text-fill: orange;");
        approveButton.setDisable(false);
        rejectButton.setDisable(false);
    }

    private void handleApprove() {
        if (currentReklamacia == null) {
            showAlert("Najprv vytvorte reklamáciu!");
            return;
        }

        boolean customerWaits = customerWaitCheckbox.isSelected();
        reklamaciaService.approveReklamacia(currentReklamacia, customerWaits);
        repository.updateReklamaciaStav(currentReklamacia.getId(), "schvalena",
                customerWaits ? "vymena" : "vracanie_penazi");

        StringBuilder result = new StringBuilder();
        result.append("Reklamácia schválená!\n");
        if (customerWaits) {
            result.append("Zákazník počká na opravu jedla\n");
            result.append("25% zľava na ďalšiu objednávku");
        } else {
            result.append("Zákazníkovi budú vrátené peniaze\n");
            result.append("25% zľava na ďalšiu objednávku");
        }

        resultArea.setText(result.toString());
        statusLabel.setText("Reklamácia spracovaná úspešne");
        statusLabel.setStyle("-fx-text-fill: green;");
        approveButton.setDisable(true);
        rejectButton.setDisable(true);
    }

    private void handleReject() {
        if (currentReklamacia == null) {
            showAlert("Najprv vytvorte reklamáciu!");
            return;
        }

        reklamaciaService.rejectReklamacia(currentReklamacia);
        repository.updateReklamaciaStav(currentReklamacia.getId(), "zamietnuta", "odmietuta");

        resultArea.setText("Reklamácia bola zamietnutá.\nNespokojný zákazník.");
        statusLabel.setText("Reklamácia zamietnutá");
        statusLabel.setStyle("-fx-text-fill: red;");
        approveButton.setDisable(true);
        rejectButton.setDisable(true);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Upozornenie");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
