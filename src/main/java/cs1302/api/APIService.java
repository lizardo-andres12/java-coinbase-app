package cs1302.api;

import java.io.IOException;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/** Service layer for external APIs. */
public class APIService {

    /** API controllers. */
    private AbstractController cryptoController;
    private AbstractController forexController;

    /** Google {@code Gson} object for parsing JSON-formatted strings. */
    private static Gson GSON = new GsonBuilder()
        .setPrettyPrinting()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create();

    /** POD class for crypto response parsing. */
    public static class CoinResult {
        int rank;
        String priceUsd;
    }

    /** POD class for forex response parsing. */
    public static class CurrencyExchangeInfo {
        double quote;
    }

    /** POD class for forex response parsing. */
    public static class CurrencyExchangeResult {
        CurrencyExchangeInfo info;
        double result;
    }

    /** Enumeration of coins available to search. */
    enum Coin {
        BTC("90", "BTC", "Bitcoin"),
        ETH("80", "ETH", "Ethereum"),
        USDT("518", "USDT", "Tether"),
        XRP("58", "XRP", "XRP"),
        SOL("48543", "SOL", "Solana"),
        DOGE("2", "DOGE", "Dogecoin");

        /** Member variables. */
        private String id;
        private String ticker;
        private String name;

        /**
         * Returns the enum's id.
         *
         * @return the id of the coin.
         */
        public String getId() {
            return this.id;
        }

        /**
         * Returns the enum's ticker.
         *
         * @return the ticker of the coin.
         */
        public String getTicker() {
            return this.ticker;
        }

        /**
         * Returns the enum's name.
         *
         * @return the full name of the coin.
         */
        public String getName() {
            return this.name;
        }

        /**
         * Private constructor for enum.
         *
         * @param id the id corresponding to the coin in the external API.
         * @param ticker the ticker of the coin.
         * @param name the full name of the coin.
         */
        private Coin(String id, String ticker, String name) {
            this.id = id;
            this.ticker = ticker;
            this.name = name;
        }
    }

    /** Enumeration of currencies available to convert to. */
    enum Currency {
        GBP("GBP", "British Pound"),
        EUR("EUR", "Euro"),
        AUD("AUD", "Australian Dollar"),
        BRL("BRL", "Brazilian Real"),
        CNY("CNY", "Chinese Yuan");

        /** Member variables. */
        private String ticker;
        private String name;

        /**
         * Returns the enum's ticker.
         *
         * @return the ticker of the currency.
         */
        public String getTicker() {
            return this.ticker;
        }

        /**
         * Returns the enum's name.
         *
         * @return the full name of the currency.
         */
        public String getName() {
            return this.name;
        }

        /**
         * Private constructor for enum.
         *
         * @param ticker the ticker symbol of the currency.
         * @param name the full name of the currency.
         */
        private Currency(String ticker, String name) {
            this.ticker = ticker;
            this.name = name;
        }
    }

    /** Constructor which initializes controller of each API. */
    public APIService() {
        this.cryptoController = new CryptoController();
        this.forexController = new ForexController();
    }

    /**
     * Queries the cryptocurrency external API to fetch the current price of the coin in USD.
     *
     * @param coin the coin selected by the user.
     * @throws IOException if the query fails in any way.
     * @throws InterruptedException if the thread executing the query is interrupted.
     * @return an object representing the parsed json result.
     */
    public CoinResult getCoinValue(Coin coin) throws IOException, InterruptedException {
        String jsonResponse = this.cryptoController.fetchJson(coin.getId());
        CoinResult res = GSON.fromJson(jsonResponse, CoinResult.class);
        return res;
    }

    /**
     * Queries the currency exchange external API to fetch the price of the coin by the amount
     * in the specifiec currency.
     *
     * @param curr the coin selected by the user.
     * @param amount the amount of the coin entered by the user.
     * @throws IOException if the query fails in any way.
     * @throws InterruptedException if the thread executing the query is interrupted.
     * @return an object representing the parsed json result.
     */
    public CurrencyExchangeResult getCurrencyExchangeValue(
        Currency curr,
        String amount
    ) throws IOException, InterruptedException {
        String jsonResponse = this.forexController.fetchJson(
            curr.getTicker(),
            amount
        );
        CurrencyExchangeResult res = GSON.fromJson(jsonResponse, CurrencyExchangeResult.class);
        return res;
    }
}
