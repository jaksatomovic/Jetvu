package canarin.lowfare.payload;

import canarin.lowfare.rest.HTTPClient;
import canarin.lowfare.util.Constants;
import canarin.lowfare.util.Params;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Accessors(chain = true)
@ToString
@Getter
public class Request {

    private String verb;
    private String path;
    private Params params;
    private String bearerToken;
    private HashMap<String, String> headers;
    private String uri;
    private HttpURLConnection connection;

    public Request(String verb, String path, Params params, String bearerToken) {

        this.verb = verb;
        this.path = path;
        this.params = params;
        this.bearerToken = bearerToken;

        prepareUrl();
        prepareHeaders();
    }

    // Builds a HttpURLConnection using all the data for this request.
    public void establishConnection() throws IOException {
        this.connection = (HttpURLConnection) new URL(uri).openConnection();
        connection.setRequestMethod(verb);
        connection.setDoInput(true);
        if (verb == Constants.POST || verb == Constants.PUT) {
            connection.setDoOutput(true);
        }
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            connection.setRequestProperty(entry.getKey(), entry.getValue());
        }
    }

    // Prepares the full URL
    private void prepareUrl() {
        this.uri = String.format("https://test.api.amadeus.com:443%s?%s",
            path, getQueryParams());
    }

    // Prepares the headers to be sent in the request
    private void prepareHeaders() {
        this.headers = new HashMap<String, String>();
        headers.put(Constants.ACCEPT, "application/json, application/vnd.amadeus+json");
        if (bearerToken != null) {
            headers.put(Constants.AUTHORIZATION, bearerToken);
        }
    }

    // Gets the serialized params, only if this is a Get call
    private String getQueryParams() {
        if (verb == Constants.GET && params != null) {
            return params.toQueryString();
        } else {
            return "";
        }
    }
}
