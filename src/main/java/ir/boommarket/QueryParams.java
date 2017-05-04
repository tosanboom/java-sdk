package ir.boommarket;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Utility class to generate a query string with an easy to use API.
 *
 * <p>I chose to <strong>not</strong> make this class thread-safe (Otherwise, we
 * would use a {@linkplain java.util.concurrent.ConcurrentMap} instead of a plain {@linkplain Map}). So,
 * use this class in a thread confined context, e.g. body of a method.
 *
 * @author Ali Dehghani
 */
public class QueryParams {
    private static final String EMPTY_QUERY_PARAM = "";
    private final Map<String, Object> keyValuePairs = new LinkedHashMap<>();
    
    private QueryParams() {}

    /**
     * Create a new instance of {@linkplain QueryParams}
     */
    public static QueryParams newQuery() {
        return new QueryParams();
    }

    /**
     * Adds one key-value pair to the list of existing ones. If the given {@code value} was
     * {@code null}, this method will ignore the passed key-value.
     *
     * @param key The key of the query param
     * @param value The value of the query param
     * @return The current {@linkplain QueryParams} instance. This way you can chain multiple
     *         calls together
     */
    public QueryParams with(String key, Object value) {
        if (value != null) keyValuePairs.put(key, value);
        return this;
    }

    /**
     * Generate a query string from accumulated key-value pairs in the following format:
     * <pre>
     *     ?key1=value1&key2=value2&...
     * </pre>
     *
     * @return The generated query string
     */
    public String toString() {
        if (keyValuePairs.isEmpty()) return EMPTY_QUERY_PARAM;

        StringBuilder builder = new StringBuilder();
        builder.append("?");
        int counter = 0;
        for (Map.Entry<String, Object> entry : keyValuePairs.entrySet()) {
            if (counter++ != 0) builder.append("&");

            builder.append(getUrlEncoded(entry.getKey()));
            builder.append("=");
            builder.append(getUrlEncoded(entry.getValue()));
        }

        return builder.toString();
    }

    private String getUrlEncoded(Object value) {
        try {
            return URLEncoder.encode(value.toString(), "UTF-8");
        } catch (UnsupportedEncodingException ignore) {
            return null;
        }
    }
}