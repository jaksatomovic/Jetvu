package canarin.lowfare.rest;

import canarin.lowfare.payload.Request;
import canarin.lowfare.payload.Response;
import canarin.lowfare.util.Constants;
import canarin.lowfare.util.Params;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

@Getter
@NoArgsConstructor
public class HTTPClient {

    protected AccessToken accessToken = new AccessToken(this);

    // GET request without parameters
    public Response get(String path) throws Exception {
        return request(Constants.GET, path, null);
    }

    // GET request with parameters
    public Response get(String path, Params params) throws Exception {
        return request(Constants.GET, path, params);
    }

    // POST request without parameters
    public Response post(String path) throws Exception {
        return request(Constants.POST, path, null);
    }

    // POST request with parameters
    public Response post(String path, Params params) throws Exception {
        return request(Constants.POST, path, params);
    }

    public Response unauthenticatedRequest(String verb, String path, Params params,
                                           String bearerToken) throws Exception {
        Request request = buildRequest(verb, path, params, bearerToken);
        return execute(request);
    }

    // A generic method for making requests of any verb.
    protected Response request(String verb, String path, Params params) throws Exception {
        return unauthenticatedRequest(verb, path, params, accessToken.getBearerToken());
    }

    // Builds a request
    protected Request buildRequest(String verb, String path, Params params, String bearerToken) {
        return new Request(verb, path, params, bearerToken);
    }

    // Executes a request and return a Response
    private Response execute(Request request) throws Exception {
        Response response = new Response(fetch(request));
        response.parse(this);
        return response;
    }


    // Tries to make an API call. Raises an error if it can't complete the call.
    private Request fetch(Request request) throws Exception {
        try {
            request.establishConnection();
            write(request);
        } catch (IOException e) {
            throw new Exception(e);
        }
        return request;
    }

    // Writes the parameters to the request.
    private void write(Request request) throws IOException {
        if (request.getVerb() == Constants.POST && request.getParams() != null) {
            OutputStream os = request.getConnection().getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(request.getParams().toQueryString());
            writer.flush();
            writer.close();
            os.close();
        }
    }
}
