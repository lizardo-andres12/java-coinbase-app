package cs1302.api;

import java.io.IOException;
import java.io.FileInputStream;
import java.net.http.HttpClient;
import java.util.Properties;

/** Controller interface with common variables. */
public abstract class AbstractController {

    /** Key-value store for API keys and other private data. */
    protected static Properties props;

    /** HTTP client. */
    protected static final HttpClient HTTP_CLIENT = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_2)
        .followRedirects(HttpClient.Redirect.NORMAL)
        .build();

    /** Constants for readability. */
    protected static final String CONVERSION_ENDPOINT = "https://api.currencylayer.com/convert";
    protected static final String CRYPTO_ENDPOINT = "https://api.coinlore.net/api/ticker/";
    protected static final String PROP_PATH = "resources/config.properties";

    /** Constructor that loads api keys from PROP_PATH. */
    public AbstractController() {
        props = new Properties();
        try (FileInputStream propertyFileStream = new FileInputStream(PROP_PATH)) {
            props.load(propertyFileStream);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
    }

    /**
     * Makes request to third-party API endpoint.
     *
     * @param args the url arguments. See method documentation for ordering and expected values.
     * @throws IOException if HTTP request fails in any way.
     * @throws InterruptedException if the thread handling the HTTP request is interrupted.
     * @return the json response as a String.
     */
    public abstract String fetchJson(String ...args) throws IOException, InterruptedException;
}
