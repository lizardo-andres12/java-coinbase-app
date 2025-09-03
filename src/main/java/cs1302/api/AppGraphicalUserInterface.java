package cs1302.api;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Front-end GUI logic for the crypto conversion app.
 */
public class AppGraphicalUserInterface extends Application {

    /** Scenes and boxes. */
    private Stage stage;
    private Scene scene;
    private VBox root;
    private HBox mainDisplay;
    private VBox buttonAmount;

    /** Loose components. */
    private CoinInterfaceComponent coinMenu;
    private CurrencyInterfaceComponent currencyMenu;
    private Button convert;
    private Label footer;
    private TextField amount;

    /** Member variables. */
    private APIService service;

    /** Constants for readability. */
    private static final String CONVERT = "Convert";

    /** App constructor. */
    public AppGraphicalUserInterface() {
        /** Scenes and boxes. */
        this.stage = null;
        this.scene = null;
        this.root = new VBox(8);
        this.mainDisplay = new HBox(8);
        this.buttonAmount = new VBox(8);

        /** Loose components. */
        this.coinMenu = new CoinInterfaceComponent();
        this.currencyMenu = new CurrencyInterfaceComponent();
        this.convert = new Button(CONVERT);
        this.footer = new Label("--Created by Lizardo Hernandez--");
        this.amount = new TextField();

        /** Member variables. */
        this.service = new APIService();
    }

    /** {@inheritDoc} */
    @Override
    public void init() {
        this.root.getChildren().addAll(this.mainDisplay, this.footer);
        this.mainDisplay.getChildren().addAll(
            this.coinMenu, this.buttonAmount, this.currencyMenu
        );
        this.buttonAmount.getChildren().addAll(this.amount, this.convert);

        this.convert.setOnAction((ActionEvent e) -> {
            this.convertCoinToCurrency();
        });
    }

    /** {@inheritDoc} */
    @Override
    public void start(Stage stage) {
        this.stage = stage;
        this.scene = new Scene(this.root);
        this.stage.setOnCloseRequest(event -> Platform.exit());
        this.stage.setTitle("CryptoHelper");
        this.stage.setScene(this.scene);
        this.stage.sizeToScene();
        this.stage.show();
        Platform.runLater(() -> this.stage.setResizable(false));
    }

    /** {@inheritDoc} */
    @Override
    public void stop() {
        System.out.println("Exiting...");
    }

    /** Button handler function. */
    private void convertCoinToCurrency() {
        /** Try to implement caching to eliminate identical requests. */
        try {
            APIService.Coin coin = this.coinMenu.getSelection();
            if (coin == null) {
                throw new RuntimeException("No coin selected!");
            }
            APIService.CoinResult coinValue = this.service.getCoinValue(coin);
            this.coinMenu.setLastCoin(coin.getName());
            this.coinMenu.setPriceText(coinValue);

            if (amount.getText().isEmpty()) {
                throw new RuntimeException("No amount selected!");
            }
            String totalAmount = this.getCoinPriceWithAmount(coinValue.priceUsd, amount.getText());
            APIService.Currency curr = this.currencyMenu.getSelection();
            if (curr == null) {
                throw new RuntimeException("No currency selected!");
            }
            APIService.CurrencyExchangeResult convertedValue
                = this.service.getCurrencyExchangeValue(
                curr,
                totalAmount
            );
            this.currencyMenu.setLabels(convertedValue);
        } catch (IOException | InterruptedException | RuntimeException e) {
            this.alertError(e);
        }
    }

    /**
     * Converts text field input into double and calculates new value.
     *
     * @param priceUsd the price returned by the crypto API.
     * @param amount the amount of coin entered by the user.
     * @return the result as a string.
     */
    private String getCoinPriceWithAmount(String priceUsd, String amount) {
        double numPrice = Double.parseDouble(priceUsd);
        double numAmount = Double.parseDouble(amount);
        return String.valueOf(numPrice * numAmount);
    }

    /**
     * Displays unavoidable window of error.
     *
     * @param e the exception/throwable that caused the error.
     */
    private void alertError(Throwable e) {
        Platform.runLater(() -> {
            TextArea text = new TextArea("Exception: " + e.toString());
            text.setEditable(false);
            Alert alert = new Alert(AlertType.ERROR);
            alert.getDialogPane().setContent(text);
            alert.setResizable(true);
            alert.showAndWait();
        });
    }
}
