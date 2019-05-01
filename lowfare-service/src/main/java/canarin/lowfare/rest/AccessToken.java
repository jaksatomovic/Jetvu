package canarin.lowfare.rest;

import canarin.lowfare.payload.Response;
import canarin.lowfare.util.Constants;
import canarin.lowfare.util.Params;
import com.google.gson.JsonObject;

public class AccessToken {

    // Renew the token 10 seconds earlier than required,
    // just to account for system lag
    private static final long TOKEN_BUFFER = 10000L;
    // An instance of the API client
    private final HTTPClient client;
    // The access token value
    private String accessToken = null;
    // The (UNIX) expiry time of this token
    private long expiresAt;

    /**
     * Constructor.
     * @hides as only used internally
     */
    public AccessToken(HTTPClient client) {
        this.client = client;
    }

    /**
     * Creates a Bearer header using the cached Access Token.
     * @hides as only used internally
     */
    public String getBearerToken() throws Exception {
        lazyUpdateAccessToken();
        return String.format("Bearer %s", accessToken);
    }

    // Loads the access token if it's still null
    // or has expired.
    private void lazyUpdateAccessToken() throws Exception {
        if (needsRefresh()) {
            updateAccessToken();
        }
    }

    // Fetches the access token and then parses the resulting values.
    private void updateAccessToken() throws Exception {
        Response response = fetchAccessToken();
        storeAccessToken(response.getResult());
    }

    // Checks if this access token needs a refresh.
    private boolean needsRefresh() {
        boolean isNull = accessToken == null;
        boolean expired = (System.currentTimeMillis() + TOKEN_BUFFER) > expiresAt;
        return isNull || expired;
    }

    // Fetches a new Access Token using the credentials from the client
    private Response fetchAccessToken() throws Exception {
        return client.unauthenticatedRequest(
            Constants.POST,
            Constants.AUTH_URL,
            Params.with(Constants.GRANT_TYPE, Constants.CLIENT_CREDENTIALS)
                .and(Constants.CLIENT_ID, "y95AI1tVg2jROCFP6hNdwsx3AwvMPnxm")
                .and(Constants.CLIENT_SECRET, "UpU09vbyhBrhjymm"),
            null
        );
    }

    // Store the fetched access token and expiry date
    private void storeAccessToken(JsonObject result) {
        this.accessToken = result.get(Constants.ACCESS_TOKEN).getAsString();
        int expiresIn = result.get(Constants.EXPIRES_IN).getAsInt();
        this.expiresAt = System.currentTimeMillis() + expiresIn * 1000L;
    }
}

