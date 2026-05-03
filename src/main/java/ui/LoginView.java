package ui;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import models.Casnik;
import database.Database;

/**
 * UI component representing login form for waiters (Casnik).
 * Handles user input, basic authentication, and login state.
 */
public class LoginView extends VBox {

    private TextField usernameField;
    private PasswordField passwordField;
    private Button loginButton;
    private Label statusLabel;
    private Casnik loggedInCasnik;

    /**
     * Initializes login form UI components and layout.
     */
    public LoginView() {
        this.setPadding(new Insets(20));
        this.setSpacing(15);

        Label titleLabel = new Label("Prihlásenie čašníka");
        titleLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");

        Label usernameLabel = new Label("Meno:");
        usernameField = new TextField();
        usernameField.setPromptText("Zadajte vaše meno");

        Label passwordLabel = new Label("Heslo:");
        passwordField = new PasswordField();
        passwordField.setPromptText("Zadajte heslo");

        loginButton = new Button("Prihlásiť sa");
        loginButton.setPrefWidth(200);
        loginButton.setStyle("-fx-font-size: 14;");

        loginButton.setOnAction(e -> handleLogin());

        statusLabel = new Label();
        statusLabel.setStyle("-fx-text-fill: red;");

        this.getChildren().addAll(
                titleLabel,
                usernameLabel,
                usernameField,
                passwordLabel,
                passwordField,
                loginButton,
                statusLabel);
    }

    /**
     * Handles login button action.
     * Performs simple validation and simulated authentication.
     */
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Zjednodušená autentifikácia
        if (username.isEmpty() || password.isEmpty()) {
            statusLabel.setText("Vyplňte všetky polia!");
            return;
        }

        // Simulovaná správa používateľov
        if (username.equals("peter") && password.equals("1234")) {
            loggedInCasnik = new Casnik(1, "Peter", true);
            statusLabel.setStyle("-fx-text-fill: green;");
            statusLabel.setText("Prihlásenie úspešné!");
        } else {
            statusLabel.setText("Nesprávne meno alebo heslo!");
        }
    }

    /**
     * Returns currently logged-in waiter.
     *
     * @return logged-in Casnik or null if not logged in
     */
    public Casnik getLoggedInCasnik() {
        return loggedInCasnik;
    }
}
