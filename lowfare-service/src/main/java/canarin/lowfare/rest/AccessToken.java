package canarin.lowfare.rest;

import canarin.lowfare.payload.Response;
import canarin.lowfare.util.Constants;
import canarin.lowfare.util.Params;
import com.google.gson.JsonObject;

public class AccessToken {

    private static final long TOKEN_BUFFER = 10000L;
    private final HTTPClient client;
    private String accessToken = null;
    private long expiresAt;

    public AccessToken(HTTPClient client) {
        this.client = client;
    }

    public String getBearerToken() throws Exception {
        lazyUpdateAccessToken();
        return String.format("Bearer %s", accessToken);
    }

    private void lazyUpdateAccessToken() throws Exception {
        if (needsRefresh()) {
            updateAccessToken();
        }
    }

    private void updateAccessToken() throws Exception {
        Response response = fetchAccessToken();
        storeAccessToken(response.getResult());
    }

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

