package apiMethodHelper;

import static io.restassured.RestAssured.given;

import java.util.Map;

import io.restassured.response.Response;
import utils.FileandEnv;

public class GetMethodHelper {

    private static final String contentType = "application/json";
    private static final String accept = "*/*";
    static String url = FileandEnv.envAndFile().get("ServerUrl");

    /**
     * Sends a GET request to the specified endpoint with a Bearer token in the Authorization header.
     * @param endPoint The endpoint to send the GET request to.
     * @param token The Bearer token to include in the Authorization header.
     * @return The response of the GET request.
     */
    public static Response getQuery1(String endPoint, String token) {
        Response response = given()
        		.queryParam("access_token", token)
            .baseUri(url + endPoint)
            .header("Authorization", "Bearer " + token)
            .contentType(contentType)
            .when()
            .get()
            .andReturn();
        response.then();
        return response;
    }

    /**
     * Sends a basic GET request to the specified endpoint.
     * @param endPoint The endpoint to send the GET request to.
     * @return The response of the GET request.
     */
    public static Response getRequest(String endPoint) {
        Response response = given()
            .baseUri(url + endPoint)
            .contentType(contentType)
            .log().all()
            .when()
            .get()
            .andReturn();
        response.then().log().all();
        return response;
    }

    /**
     * Sends a GET request to the specified endpoint with custom headers.
     * @param endPoint The endpoint to send the GET request to.
     * @param headers The headers to include in the request.
     * @return The response of the GET request.
     */
    public static Response getRequestWithHeaders(String endPoint, Map<String, String> headers) {
        Response response = given()
            .baseUri(url + endPoint)
            .headers(headers)
            .contentType(contentType)
            .log().all()
            .when()
            .get()
            .andReturn();
        response.then().log().all();
        return response;
    }

    /**
     * Sends a GET request to the specified endpoint with multiple query parameters.
     * @param endPoint The endpoint to send the GET request to.
     * @param queryParams The query parameters to include in the request.
     * @return The response of the GET request.
     */
    public static Response getRequestWithMultipleQueryParams(String endPoint, Map<String, String> queryParams) {
        Response response = given()
            .baseUri(url + endPoint)
            .queryParams(queryParams)
            .contentType(contentType)
            .log().all()
            .when()
            .get()
            .andReturn();
        response.then().log().all();
        return response;
    }
}
