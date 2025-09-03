package cs1302.api;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpRequest;
import java.net.URI;

/** Logic for interacting with crypto external API. */
public class CryptoController extends AbstractController {

    /** Constructor. */
    public CryptoController() {
        super();
    }

    /**
     * {@inheritdoc}. Only one argument is expected, and any extra arguments will be ignored. The
     * url parameters are as follows.
     *
     * {@code endpoint}?id=90 (BTC).
     * {@code endpoint}?id=80 (ETH).
     */
    @Override
    public String fetchJson(String ...args) throws IOException, InterruptedException {
        String url = String.format(
            "%s?id=%s",
            CRYPTO_ENDPOINT,
            args[0]
        );
        HttpRequest req = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .build();
        HttpResponse<String> res = HTTP_CLIENT.send(req, BodyHandlers.ofString());
        int statusCode = res.statusCode();
        if (statusCode != 200) {
            throw new IOException(String.format("Query failed, status code %d", statusCode));
        }

        /** API hardcoded to return array, so omit "[]" characters from string. */
        String json = res.body().trim();
        json = json.substring(1, json.length() - 1);
        return json;
    }
}
