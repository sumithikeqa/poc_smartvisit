package apiMethodHelper;

import io.restassured.response.Response;
import utils.FileandEnv;

import static io.restassured.RestAssured.given;

public class DeleteMethodHelper {
    private static final String contentType = "application/json";
    private static final String accept = "*/*";
    static String url = FileandEnv.envAndFile().get("ServerUrl");

    /**
     * Sends a DELETE request to the specified endpoint.
     * @param endPoint The endpoint to send the DELETE request to.
     * @return The response of the DELETE request.
     */
    public static Response deleteRequest(String endPoint) {
        String urlwithpath = url + endPoint;
        Response response = given()
                .baseUri(urlwithpath)
                .contentType(contentType)
                .accept(accept)
                .when()
                .delete()
                .andReturn();
        response.then();
        return response;
    }
}
