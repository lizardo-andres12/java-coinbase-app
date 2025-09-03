package cs1302.api;

import java.util.Arrays;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/** Currency meny custon JavaFX Component. */
public class CurrencyInterfaceComponent extends VBox {

    /** Nested components. */
    private ComboBox<APIService.Currency> currencyMenu;
    private Label currencyName;
    private Label exchangeRate;

    /** Constructor. */
    public CurrencyInterfaceComponent() {
        super();

        /** Variable construction. */
        this.currencyMenu = new ComboBox<>();
        this.currencyName = new Label("Click convert to select currency");
        this.exchangeRate = new Label("Result: --\n--x");

        /** Initialzation. */
        this.currencyMenu.getItems().addAll(Arrays.asList(APIService.Currency.values()));
        this.getChildren().addAll(this.currencyName, this.exchangeRate, this.currencyMenu);
    }

    /**
     * Updates labels based on the result of API call.
     *
     * @param data the result of the API call.
     */
    public void setLabels(APIService.CurrencyExchangeResult data) {
        String newText = String.format(
            "Result: %.2f\n%fx",
            data.result,
            data.info.quote
        );
        this.exchangeRate.setText(newText);
        this.currencyName.setText(this.getSelection().getName());
    }

    /**
     * Returns the selected enum value.
     *
     * @return the enum value selected.
     */
    public APIService.Currency getSelection() {
        return this.currencyMenu.getValue();
    }
}
