package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Listeners;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.relevantcodes.extentreports.LogStatus;

import apiMethodHelper.GetMethodHelper;
import apiVerifications.APIVerification;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

@Listeners(ExtentReportListner.class)
public class GeneralUtil extends ExtentReportListner {

    /**
     * Extracts the root node from the JSON response.
     * @param response The response object containing the JSON data.
     * @return The root JsonNode of the JSON data.
     * @throws IOException If there is an error reading the JSON data.
     */
    public static JsonNode extractnodevalue(Response response) throws IOException {
        String jsonString = response.getBody().asString();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonString);
        return jsonNode;
    }

    /**
     * Reads a JSON file and converts it to a map.
     * @param Filepath The path to the JSON file.
     * @return A map containing the JSON data.
     * @throws IOException If there is an error reading the file.
     */
    public static Map<String, Object> updatePayload1(String Filepath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> address = objectMapper.readValue(new File(Filepath), new TypeReference<Map<String, Object>>() {});
        return address;
    }

    
    /**
     * Writes the response data to a file.
     * @param responseData The response data to be written.
     */
    public static void printResponse(String responseData) {
        try (FileWriter fileWriter = new FileWriter(System.getProperty("user.dir") + "\\src\\test\\resources\\ResponseCode.txt")) {
            fileWriter.write("Response code----------------->>>>\n " + responseData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Extracts the "id" field from the JSON response.
     * @param response The response object containing the JSON data.
     * @return The value of the "id" field.
     */
    public static String getID(Response response) {
        String elementFromResponseBody = null;
        try {
            JsonPath jsonPath = response.jsonPath();
            elementFromResponseBody = jsonPath.getString("id");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return elementFromResponseBody;
    }

    /**
     * Extracts the "id" field from a nested JSON object in the response.
     * @param response The response object containing the JSON data.
     * @param tags The path to the nested JSON object.
     * @return The value of the "id" field.
     */
    public static List<String> getIDFromList(Response response, String key) {

    	List<String> ids = null;
        try {
            JsonPath jsonPath = response.jsonPath();
            ids = jsonPath.getList(key + ".id");
            test.log(LogStatus.PASS, "Successfully get the list of id " + ids + ". ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ids;
    }
    
    public static String getID(Response response, String key) {
    	String elementFromResponseBody = null;
        try {
            JsonPath jsonPath = response.jsonPath();
            elementFromResponseBody = jsonPath.getString(key + ".id");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return elementFromResponseBody;
    }

    /**
     * Validates the creation of a resource by checking if a specific key is present in the JSON response.
     * @param endPoint The endpoint to send the GET request to.
     * @param token The token for authorization.
     * @param tagsName The JSON object containing the key.
     * @param key The key to validate.
     */
    public static void validateCreation(String endPoint, String token, String tagsName, String key) {
        try {
            Response getResponse = GetMethodHelper.getQuery1(endPoint, token);
            APIVerification.responseCodeValidation(getResponse, 200);
            JSONObject json = new JSONObject(getResponse.getBody().asString());
            JSONObject project = json.getJSONObject(tagsName);
            if (project.has(key) && project.get(key) != null) {
                test.log(LogStatus.PASS, "Successfully validated value of " + key + ". It is " + project.get(key));
            } else {
                test.log(LogStatus.FAIL, "Key is not available");
            }
        } catch (Exception e) {
            test.log(LogStatus.FAIL, e.fillInStackTrace());
        }
    }

    /**
     * Validates the creation of a resource by checking if a specific key is present in the root JSON object.
     * @param endPoint The endpoint to send the GET request to.
     * @param token The token for authorization.
     * @param key The key to validate.
     */
    public static void validateCreation(String endPoint, String token, String key) {
        try {
            Response getResponse = GetMethodHelper.getQuery1(endPoint, token);
            JSONObject json = new JSONObject(getResponse.getBody().asString());
            if (json.has(key) && json.get(key) != null) {
                test.log(LogStatus.PASS, "Successfully validated creation value of " + key + ". It is " + json.get(key));
            } else {
                test.log(LogStatus.FAIL, "Key is not available");
            }
            APIVerification.responseCodeValidation(getResponse, 200);
        } catch (Exception e) {
            test.log(LogStatus.FAIL, e.fillInStackTrace());
        }
    }
}
