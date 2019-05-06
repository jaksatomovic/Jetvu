package canarin.lowfare.payload;

import canarin.lowfare.rest.HTTPClient;
import canarin.lowfare.util.Constants;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Getter;
import lombok.ToString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Arrays;

@Getter
@ToString
public class Response {

    private int statusCode;
    private boolean parsed;
    private JsonObject result;
    private JsonElement data;
    private String body;
    private Request request;

    public Response(Request request) {
        this.request = request;
    }

    public void parse(HTTPClient client) {
        parseStatusCode();
        parseData(client);
    }

    private void parseStatusCode() {
        try {
            this.statusCode = getRequest().getConnection().getResponseCode();
        } catch (IOException e) {
            this.statusCode = 0;
        }
    }

    // Tries to parse the data
    private void parseData(HTTPClient client) {
        this.parsed = false;
        this.body = readBody();
        this.result = parseJson(client);
        this.parsed = this.result != null;
        if (parsed && result.has("data")) {
            if (result.get("data").isJsonArray()) {
                this.data = result.get("data").getAsJsonArray();
            }
            if (result.get("data").isJsonObject()) {
                this.data = result.get("data").getAsJsonObject();
            }
        }
    }

    // Tries to read the body.
    private String readBody() {

        HttpURLConnection connection = getRequest().getConnection();

        InputStream inputStream = null;

        try {
            inputStream = connection.getInputStream();
        } catch (IOException e) {
            inputStream = connection.getErrorStream();
        }

        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer body = new StringBuffer();
            String inputLine;

            while ((inputLine = bufferedReader.readLine()) != null) {
                body.append(inputLine);
            }

            bufferedReader.close();
            return body.toString();
        } catch (IOException e) {
            return null;
        }
    }

    // Ties to parse the response body into a JSON Object
    private JsonObject parseJson(HTTPClient client) {
        if (isJson()) {
            return new JsonParser().parse(getBody()).getAsJsonObject();
        }
        return null;
    }

    // Checks if the response is likely to be JSON.
    private boolean isJson() {
        return hasJsonHeader() && hasBody();
    }

    // Checks if the response headers include a JSON mime-type.
    private boolean hasJsonHeader() {
        String contentType = getRequest().getConnection().getHeaderField(Constants.CONTENT_TYPE);
        String[] expectedContentTypes = new String[] {
            "application/json", "application/vnd.amadeus+json"
        };
        return Arrays.asList(expectedContentTypes).contains(contentType);
    }

    // Checks if the response has a body
    private boolean hasBody() {
        return !(body == null || body.isEmpty());
    }
}
