package apiMethodHelper;

import static io.restassured.RestAssured.given;

import java.util.Map;

import io.restassured.response.Response;
import utils.FileandEnv;

public class PutMethodHelper {
    private static final String contentType = "application/json";
    private static final String accept = "*/*";
    static String url = FileandEnv.envAndFile().get("ServerUrl");

    /**
     * Sends a PUT request to the specified endpoint with the given request body.
     * @param endPoint The endpoint to send the PUT request to.
     * @param requestBody The body of the PUT request.
     * @return The response of the PUT request.
     */
    public static Response putRequest(String endPoint, String requestBody) {
        String urlwithpath = url + endPoint;
        Response response = given()
                .baseUri(urlwithpath)
                .body(requestBody)
                .contentType(contentType)
                .accept(accept)
                .log().all()
                .when()
                .put()
                .andReturn();
        response.then().log().all();
        return response;
    }

    /**
     * Sends a PUT request to the specified endpoint with the given request body and headers.
     * @param endPoint The endpoint to send the PUT request to.
     * @param requestBody The body of the PUT request.
     * @param headers The headers to include in the request.
     * @return The response of the PUT request.
     */
    public static Response putRequestWithHeaders(String endPoint, String requestBody, Map<String, String> headers) {
        String urlwithpath = url + endPoint;
        Response response = given()
                .baseUri(urlwithpath)
                .body(requestBody)
                .headers(headers)
                .contentType(contentType)
                .accept(accept)
                .log().all()
                .when()
                .put()
                .andReturn();
        response.then().log().all();
        return response;
    }

    /**
     * Sends a PUT request to the specified endpoint with the given request body and query parameters.
     * @param endPoint The endpoint to send the PUT request to.
     * @param requestBody The body of the PUT request.
     * @param queryParams The query parameters to include in the request.
     * @return The response of the PUT request.
     */
    public static Response putRequestWithQueryParams(String endPoint, String requestBody, Map<String, String> queryParams) {
        String urlwithpath = url + endPoint;
        Response response = given()
                .baseUri(urlwithpath)
                .body(requestBody)
                .queryParams(queryParams)
                .contentType(contentType)
                .accept(accept)
                .log().all()
                .when()
                .put()
                .andReturn();
        response.then().log().all();
        return response;
    }
}
