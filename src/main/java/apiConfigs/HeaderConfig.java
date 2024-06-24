package apiConfigs;

import java.util.HashMap;
import java.util.Map;

public class HeaderConfig {

    public HeaderConfig() {
    }

    /**
     * Provides default headers with Content-Type set to application/json.
     * @return A map of default headers.
     */
    public Map<String, String> defaultHeaders() {
        Map<String, String> defaultHeaders = new HashMap<>();
        defaultHeaders.put("Content-Type", "application/json");
        return defaultHeaders;
    }

    /**
     * Provides headers with a custom Bearer token for authorization.
     * @param token The Bearer token to include in the headers.
     * @return A map of headers with the Authorization Bearer token.
     */
    public Map<String, String> headersWithCustomToken(String token) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer " + token);
        return headers;
    }

    /**
     * Provides default headers combined with additional custom headers.
     * @param customHeaders The custom headers to include.
     * @return A map of headers combining default and custom headers.
     */
    public Map<String, String> headersWithAdditionalCustomHeaders(Map<String, String> customHeaders) {
        Map<String, String> headers = defaultHeaders();
        headers.putAll(customHeaders);
        return headers;
    }

    /**
     * Provides headers for multipart/form-data content type.
     * @return A map of headers with Content-Type set to multipart/form-data.
     */
    public Map<String, String> headersForMultipartFormData() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "multipart/form-data");
        return headers;
    }
}
