package cs1302.api;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpRequest;
import java.net.URI;

/** Controller implementation for the currency exchange external API. */
public class ForexController extends AbstractController {

    /** Constructor. */
    public ForexController() {
        super();
    }

    /**
     * {@inheritdoc}. Only two arguments are expected, and any extra arguments will be ignored. The
     * url parameters are as follows.
     *
     * {@code endpoint}?{@code hardcoded arguments}&to=GBP&amount=10
     * {@code endpoint}?{@code hardcoded arguments}&to=EUR&amount=7.45
     */
    @Override
    public String fetchJson(String ...args) throws IOException, InterruptedException {
        String url = String.format(
            "%s?access_key=%s&from=%s&to=%s&amount=%s",
            CONVERSION_ENDPOINT,
            props.getProperty("conversionapi.key"),
            "USD",
            args[0],
            args[1]
        );
        HttpRequest req = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .build();
        HttpResponse<String> res = HTTP_CLIENT.send(req, BodyHandlers.ofString());
        int statusCode = res.statusCode();
        if (statusCode != 200) {
            throw new IOException(String.format("Query failed, status code %d", statusCode));
        }

        String json = res.body().trim();
        return json;
    }

}
