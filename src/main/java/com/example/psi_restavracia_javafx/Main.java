package com.example.psi_restavracia_javafx;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import ui.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Reštaurácia - Správa objednávok");
        primaryStage.setWidth(1200);
        primaryStage.setHeight(800);

        // Create main container
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #f5f5f5;");

        // Header
        VBox header = createHeader();
        root.setTop(header);

        // Tab pane with all use cases
        TabPane tabPane = createTabPane();
        root.setCenter(tabPane);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createHeader() {
        VBox header = new VBox();
        header.setPadding(new Insets(15));
        header.setStyle("-fx-background-color: #2c3e50; -fx-text-fill: white;");

        Label titleLabel = new Label("🍽️ Systém správy reštaurácie");
        titleLabel.setStyle("-fx-font-size: 24; -fx-font-weight: bold; -fx-text-fill: white;");

        Label subtitleLabel = new Label("Správa objednávok, rezervácií, reklamácií a platieb");
        subtitleLabel.setStyle("-fx-font-size: 12; -fx-text-fill: #bdc3c7;");

        header.getChildren().addAll(titleLabel, subtitleLabel);
        return header;
    }

    private TabPane createTabPane() {
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.setStyle("-fx-font-size: 12;");

        // UC01 - Objednávka
        ObjednavkaView objednavkaView = new ObjednavkaView();
        Tab tab1 = new Tab("UC01 - Objednávka", objednavkaView);
        tab1.setStyle("-fx-text: black;");

        // UC02 - Reklamácia
        ReklamaciaView reklamaciaView = new ReklamaciaView();
        Tab tab2 = new Tab("UC02 - Reklamácia", reklamaciaView);
        tab2.setStyle("-fx-text: black;");
        tab2.setOnSelectionChanged(e -> {
            if (tab2.isSelected()) {
                reklamaciaView.refreshOrderList();
            }
        });

        // UC03 - Rezervácia
        Tab tab3 = new Tab("UC03 - Rezervácia", new RezervaciaView());
        tab3.setStyle("-fx-text: black;");

        // UC04 - Platba
        PlatbaView platbaView = new PlatbaView();
        Tab tab4 = new Tab("UC04 - Platba", platbaView);
        tab4.setStyle("-fx-text: black;");
        tab4.setOnSelectionChanged(e -> {
            if (tab4.isSelected()) {
                platbaView.refreshOrderList();
            }
        });

        tabPane.getTabs().addAll(tab1, tab2, tab3, tab4);

        return tabPane;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
