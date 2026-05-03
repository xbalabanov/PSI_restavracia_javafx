package ui;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import models.Rezervacia;
import models.Stol;
import services.RezervaciaService;
import database.Repository;
import java.time.LocalDateTime;

public class RezervaciaView extends VBox {

    private TextField nameField;
    private TextField contactField;
    private DatePicker dateField;
    private Spinner<Integer> hourSpinner;
    private Spinner<Integer> minuteSpinner;
    private Spinner<Integer> personCountSpinner;
    private TextArea notesArea;
    private Button createButton;
    private Button cancelButton;
    private Label statusLabel;

    private RezervaciaService rezervaciaService;
    private Repository repository;

    public RezervaciaView() {
        this.setPadding(new Insets(15));
        this.setSpacing(10);
        this.setStyle("-fx-font-size: 12;");

        rezervaciaService = new RezervaciaService();
        repository = new Repository();

        Label titleLabel = new Label("UC03 - Rezervácia stola");
        titleLabel.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");

        // Customer name
        Label nameLabel = new Label("Meno zákazníka:");
        nameField = new TextField();
        nameField.setPromptText("Zadajte meno");

        // Contact
        Label contactLabel = new Label("Kontakt (telefón):");
        contactField = new TextField();
        contactField.setPromptText("Zadajte telefón");

        // Date
        Label dateLabel = new Label("Dátum rezervácie:");
        dateField = new DatePicker();

        // Time
        Label timeLabel = new Label("Čas:");
        hourSpinner = new Spinner<>(0, 23, 12);
        minuteSpinner = new Spinner<>(0, 59, 0);
        HBox timeBox = new HBox(5);
        timeBox.getChildren().addAll(
                new Label("Hodina:"), hourSpinner,
                new Label("Minúta:"), minuteSpinner);

        // Person count
        Label personLabel = new Label("Počet osôb:");
        personCountSpinner = new Spinner<>(1, 20, 2);

        // Notes
        Label notesLabel = new Label("Poznámky:");
        notesArea = new TextArea();
        notesArea.setPrefHeight(80);
        notesArea.setWrapText(true);

        // Status
        statusLabel = new Label();
        statusLabel.setStyle("-fx-text-fill: blue;");

        // Buttons
        createButton = new Button("Vytvoriť rezerváciu");
        createButton.setStyle("-fx-font-size: 12; -fx-padding: 8;");
        createButton.setOnAction(e -> handleCreateRezervacia());

        cancelButton = new Button("Zrušiť");
        cancelButton.setStyle("-fx-font-size: 12; -fx-padding: 8;");
        cancelButton.setOnAction(e -> handleCancel());

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(createButton, cancelButton);

        this.getChildren().addAll(
                titleLabel,
                nameLabel, nameField,
                contactLabel, contactField,
                dateLabel, dateField,
                timeLabel, timeBox,
                personLabel, personCountSpinner,
                notesLabel, notesArea,
                buttonBox,
                statusLabel);
    }

    private void handleCreateRezervacia() {
        if (nameField.getText().isEmpty() || contactField.getText().isEmpty() || dateField.getValue() == null) {
            showAlert("Vyplňte všetky povinné polia!");
            return;
        }

        LocalDateTime reservationTime = LocalDateTime.of(
                dateField.getValue().getYear(),
                dateField.getValue().getMonth(),
                dateField.getValue().getDayOfMonth(),
                hourSpinner.getValue(),
                minuteSpinner.getValue());

        Rezervacia rezervacia = rezervaciaService.createRezervacia(
                nameField.getText(),
                contactField.getText(),
                reservationTime,
                personCountSpinner.getValue(),
                notesArea.getText());

        // Find available table
        Stol availableTable = null;
        for (Stol stol : repository.getAllStoly()) {
            if (stol.getStav().equals("volny") && stol.getKapacita() >= personCountSpinner.getValue()) {
                availableTable = stol;
                break;
            }
        }

        if (availableTable != null) {
            rezervacia.setStolId(availableTable.getId());
            repository.createRezervacia(rezervacia);
            repository.updateStolStav(availableTable.getId(), "rezervovany");

            statusLabel.setText("✓ Rezervácia vytvorená - Stôl č. " + availableTable.getId());
            statusLabel.setStyle("-fx-text-fill: green; -fx-font-size: 12;");
            showAlert("Rezervácia úspešne vytvorená!\nStôl č. " + availableTable.getId());
            clearForm();
        } else {
            statusLabel.setText("✗ Nie je dostupný vhodný stôl!");
            statusLabel.setStyle("-fx-text-fill: red; -fx-font-size: 12;");
            showAlert("Ľutujeme, nie je dostupný stôl vyhovujúcej veľkosti v požadovanom čase.");
        }
    }

    private void handleCancel() {
        clearForm();
    }

    private void clearForm() {
        nameField.clear();
        contactField.clear();
        dateField.setValue(null);
        hourSpinner.getValueFactory().setValue(12);
        minuteSpinner.getValueFactory().setValue(0);
        personCountSpinner.getValueFactory().setValue(2);
        notesArea.clear();
        statusLabel.setText("");
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Upozornenie");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
