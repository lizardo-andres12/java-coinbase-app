package cs1302.api;

import java.util.Arrays;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import javafx.scene.layout.VBox;

/** Coin menu custom JavaFX component. */
public class CoinInterfaceComponent extends VBox {

    /** Nested components. */
    private ComboBox<APIService.Coin> coinMenu;
    private Label lastCoinChecked;
    private Label currentPriceUsd;

    /** Constructor. */
    public CoinInterfaceComponent() {
        super();

        /** Variable construction. */
        this.coinMenu = new ComboBox<>();
        this.lastCoinChecked = new Label("--");
        this.currentPriceUsd = new Label("Click convert to get coin Price");

        /** Initialzation. */
        this.coinMenu.getItems().addAll(Arrays.asList(APIService.Coin.values()));
        this.getChildren().addAll(this.lastCoinChecked, this.currentPriceUsd, this.coinMenu);
    }

    /**
     * Updates price label with the coin value.
     *
     * @param data the result of the API call as an object.
     */
    public void setPriceText(APIService.CoinResult data) {
        String newText = String.format(
            "%s: $%s USD\nGlobal market cap rank: %d",
            coinMenu.getValue().getTicker(),
            data.priceUsd,
            data.rank
        );
        this.currentPriceUsd.setText(newText);
    }

    /**
     * Updates last checked coin label.
     *
     * @param coinName the name of the last coin viewed.
     */
    public void setLastCoin(String coinName) {
        this.lastCoinChecked.setText(coinName);
    }

    /**
     * Returns enum of current selection.
     *
     * @return enum of current selection.
     */
    public APIService.Coin getSelection() {
        return this.coinMenu.getValue();
    }
}
