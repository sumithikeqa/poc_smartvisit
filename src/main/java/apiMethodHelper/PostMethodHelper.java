package apiMethodHelper;

import static io.restassured.RestAssured.given;

import java.util.Map;

import io.restassured.response.Response;
import utils.FileandEnv;

public class PostMethodHelper {
    private static final String contentType = "application/json";
    private static final String accept = "*/*";
    static String url = FileandEnv.envAndFile().get("ServerUrl");

    /**
     * Sends a POST request to the specified endpoint with the given request body.
     * @param endPoint The endpoint to send the POST request to.
     * @param requestBody The body of the POST request.
     * @return The response of the POST request.
     */
    public static Response postRequest(String endPoint, Object requestBody) {
        String urlwithpath = url + endPoint;
        Response response = given()
                .baseUri(urlwithpath)
                .body(requestBody)
                .header("Content-Type", "application/json")
                .header("apiKey", "qymzoXH/IIYdIyNp/+Sx8OmT6Kxq5EuBbdQEd0NR8+k=")
                .log().all()
                .when()
                .post()
                .andReturn();
        response.then();
        return response;
    }

    /**
     * Sends a POST request to the specified endpoint with the given request body and headers.
     * @param endPoint The endpoint to send the POST request to.
     * @param requestBody The body of the POST request.
     * @param headers The headers to include in the request.
     * @return The response of the POST request.
     */
    public static Response postRequestWithHeaders(String endPoint, String requestBody, Map<String, String> headers) {
        String urlwithpath = url + endPoint;
        Response response = given()
                .baseUri(urlwithpath)
                .body(requestBody)
                .headers(headers)
                .contentType(contentType)
                .accept(accept)
                .log().all()
                .when()
                .post()
                .andReturn();
        response.then().log().all();
        return response;
    }

    /**
     * Sends a POST request to the specified endpoint with the given request body and query parameters.
     * @param endPoint The endpoint to send the POST request to.
     * @param requestBody The body of the POST request.
     * @param queryParams The query parameters to include in the request.
     * @return The response of the POST request.
     */
    public static Response postRequestWithQueryParams(String endPoint, String requestBody, Map<String, String> queryParams) {
        String urlwithpath = url + endPoint;
        Response response = given()
                .baseUri(urlwithpath)
                .body(requestBody)
                .queryParams(queryParams)
                .contentType(contentType)
                .accept(accept)
                .log().all()
                .when()
                .post()
                .andReturn();
        response.then().log().all();
        return response;
    }

    /**
     * Sends a POST request with a payload object and Bearer token as a query parameter and header.
     * @param requestPayload The payload object to include in the POST request.
     * @param token The Bearer token to include in the Authorization header and as a query parameter.
     * @param apiEndPoint The endpoint to send the POST request to.
     * @return The response of the POST request.
     */
    public static Response postQuery1(Object requestPayload, String token, String apiEndPoint) {
        String urlwithpath = url + apiEndPoint;
        Response response = given()
                .queryParam("access_token", token)
                .baseUri(urlwithpath)
                .header("Authorization", "Bearer " + token)
                .body(requestPayload)
                .contentType(contentType)
                .accept(accept)
                .when().log().all()
                .post()
                .andReturn();
        response.then();
        return response;
    }
}
