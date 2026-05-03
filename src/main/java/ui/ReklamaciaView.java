package ui;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import models.Reklamacia;
import models.Objednavka;
import models.ObjednaneJedlo;
import services.ReklamaciaService;
import database.Database;
import database.Repository;
import java.time.LocalDateTime;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class ReklamaciaView extends VBox {

    private ComboBox<Objednavka> objednavkaCombo;
    private ListView<ObjednaneJedlo> polozkyList;
    private Map<Integer, Spinner<Integer>> quantitySpinners = new HashMap<>();
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
        objednavkaCombo.setOnAction(e -> handleOrderSelected());

        // Items selection
        Label polozkyLabel = new Label("Vyberte položky na reklamáciu (viacnásobný výber pomocou Ctrl/Cmd):");
        polozkyList = new ListView<>();
        polozkyList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        polozkyList.setPrefHeight(150);
        polozkyList.setCellFactory(lv -> new ListCell<ObjednaneJedlo>() {
            @Override
            protected void updateItem(ObjednaneJedlo item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                    setText(null);
                } else {
                    HBox hbox = new HBox(10);
                    hbox.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
                    Label label = new Label(item.getMenu().getNazov() + " (max " + item.getPocet() + "ks) - " + item.getCena() + "€/ks");
                    
                    Spinner<Integer> spinner = quantitySpinners.get(item.getId());
                    if (spinner == null) {
                        spinner = new Spinner<>(1, item.getPocet(), 1);
                        spinner.setPrefWidth(70);
                        spinner.setEditable(true);
                        quantitySpinners.put(item.getId(), spinner);
                    }
                    
                    hbox.getChildren().addAll(label, spinner);
                    setGraphic(hbox);
                    setText(null);
                }
            }
        });

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
                polozkyLabel, polozkyList,
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
        objednavkaCombo.getItems().addAll(repository.getObjednavkyByStatus(3)); // Only vybavena
    }

    private void handleOrderSelected() {
        Objednavka selected = objednavkaCombo.getValue();
        polozkyList.getItems().clear();
        quantitySpinners.clear();
        if (selected != null) {
            polozkyList.getItems().addAll(selected.getPolozky());
        }
    }

    private void handleCreateReklamacia() {
        Objednavka selected = objednavkaCombo.getValue();
        List<ObjednaneJedlo> selectedPolozky = new ArrayList<>(polozkyList.getSelectionModel().getSelectedItems());
        String problem = problemArea.getText();

        if (selected == null || selectedPolozky.isEmpty() || problem.isEmpty()) {
            showAlert("Vyberte objednávku, aspoň jednu položku a opíšte problém!");
            return;
        }

        StringBuilder fullProblem = new StringBuilder();
        fullProblem.append("Položky: ");
        for (ObjednaneJedlo oj : selectedPolozky) {
            int reclaimQty = quantitySpinners.get(oj.getId()).getValue();
            fullProblem.append(oj.getMenu().getNazov()).append(" (").append(reclaimQty).append("ks z ").append(oj.getPocet()).append("ks), ");
        }
        fullProblem.append("\nProblém: ").append(problem);

        currentReklamacia = reklamaciaService.createReklamacia(selected, 1, fullProblem.toString());
        repository.createReklamacia(currentReklamacia);

        // Reset inputs
        problemArea.clear();
        polozkyList.getSelectionModel().clearSelection();

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

        // If refund, update order items quantity
        if (!customerWaits) {
            List<ObjednaneJedlo> selectedPolozky = new ArrayList<>(polozkyList.getSelectionModel().getSelectedItems());
            for (ObjednaneJedlo oj : selectedPolozky) {
                int reclaimQty = quantitySpinners.get(oj.getId()).getValue();
                int newQty = oj.getPocet() - reclaimQty;
                repository.updateObjednaneJedloPocet(oj.getId(), newQty);
                oj.setPocet(newQty); // Update local model too
            }
        }

        StringBuilder result = new StringBuilder();
        result.append("Reklamácia schválená!\n");
        if (customerWaits) {
            result.append("Zákazník počká na opravu jedla\n");
            result.append("25% zľava na ďalšiu objednávku");
        } else {
            result.append("Zákazníkovi budú vrátené peniaze (odpočítané z účtu)\n");
            result.append("25% zľava na ďalšiu objednávku");
        }

        resultArea.setText(result.toString());
        statusLabel.setText("Reklamácia spracovaná úspešne");
        statusLabel.setStyle("-fx-text-fill: green;");
        approveButton.setDisable(true);
        rejectButton.setDisable(true);
        
        // Refresh items list to show new quantities
        polozkyList.refresh();
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
