package canarin.lowfare.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import lombok.NonNull;

public class Params extends HashMap<String, String> {
    protected String encoding = "UTF-8";

    protected Params() {}

    public static Params with(@NonNull String key, Object value) {
        return new Params().and(key, value);
    }

    public Params and(@NonNull String key, Object value) {
        put(key, String.valueOf(value));
        return this;
    }

    public String toString() {
        return toQueryString();
    }

    // Converts params into a HTTP query string.
    public String toQueryString() {
        StringBuilder query = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : entrySet()) {
            if (!first) {
                query.append("&");
            }
            first = false;
            try {
                query.append(URLEncoder.encode(entry.getKey(), encoding));
                query.append("=");
                query.append(URLEncoder.encode(entry.getValue(), encoding));
            } catch (UnsupportedEncodingException e) {
                // no need to anything
            }
        }

        return query.toString();
    }
}
