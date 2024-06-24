package apiVerifications;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Listeners;

import com.relevantcodes.extentreports.LogStatus;

import io.restassured.response.Response;
import utils.ExtentReportListner;

@Listeners(ExtentReportListner.class)
public class APIVerification extends ExtentReportListner {

    /**
     * Validates the status code of the response.
     * @param response The response object containing the status code.
     * @param statusCode The expected status code.
     */
    public static void responseCodeValidation(Response response, int statusCode) {
    	
    	int actual = response.getStatusCode();
        Assert.assertEquals(actual, statusCode);
        test.log(LogStatus.PASS, "Successfully validated status code, status code is :: " + actual);
    }

    /**
     * Validates the presence of a key in each JSON object within a JSON array.
     * @param response The response object containing the JSON array.
     * @param key The key to validate.
     */
    public static void responseKeyValidationFromArray(Response response, String key) {
        try {
            JSONArray array = new JSONArray(response.getBody().asString());
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                test.log(LogStatus.PASS, "Validated value is " + obj.get(key));
            }
        } catch (Exception e) {
            test.log(LogStatus.FAIL, e.fillInStackTrace());
        }
    }
    
    public static void responseKeyAndValueValidationFromArray(Response response, String key, Object expectedValue) {
        try {
            JSONArray array = new JSONArray(response.getBody().asString());
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                if (obj.has(key) && obj.get(key).equals(expectedValue)) {
                    test.log(LogStatus.PASS, "Validated key: " + key + " with value: " + obj.get(key));
                } else {
                    test.log(LogStatus.FAIL, "Validation failed for key: " + key + ". Expected value: " + expectedValue + ", but found: " + (obj.has(key) ? obj.get(key) : "key not found"));
                }
            }
        } catch (Exception e) {
            test.log(LogStatus.FAIL, e.fillInStackTrace());
        }
    }


    public static void responseKeyAndValueValidationFromArray(Response response, String arrayKey, String key, List<String> expectedValues) {
        try {
            JSONObject responseObject = new JSONObject(response.getBody().asString());
            if (!responseObject.has(arrayKey)) {
                test.log(LogStatus.FAIL, "Array key: " + arrayKey + " not found in the response.");
                return;
            }

            JSONArray dataObjectsArray = responseObject.getJSONArray(arrayKey);
            for (String expectedValue : expectedValues) {
                boolean keyFoundAndValid = false;

                for (int i = 0; i < dataObjectsArray.length(); i++) {
                    JSONObject obj = dataObjectsArray.getJSONObject(i);
                    if (obj.has(key) && obj.get(key).equals(expectedValue)) {
                        test.log(LogStatus.PASS, "Validated key: " + key + " with value: " + obj.get(key));
                        keyFoundAndValid = true;
                        break;  // Exit the inner loop if we found the key and value
                    }
                }

                if (!keyFoundAndValid) {
                    test.log(LogStatus.FAIL, "Validation failed for key: " + key + ". Expected value: " + expectedValue + " not found in any dataObjects.");
                }
            }

        } catch (Exception e) {
            test.log(LogStatus.FAIL, e.fillInStackTrace());
        }
    }
    
    
    /**
     * Validates the presence and value of a key in a JSON object.
     * @param response The response object containing the JSON data.
     * @param key The key to validate.
     */
    public static void responseKeyValidationFromJsonObject(Response response, String key) {
        try {
            JSONObject json = new JSONObject(response.getBody().asString());
            if (json.has(key) && json.get(key) != null) {
                test.log(LogStatus.PASS, "Successfully validated value of " + key + ". It is " + json.get(key));
            } else {
                test.log(LogStatus.FAIL, "Key is not available");
            }
        } catch (Exception e) {
            test.log(LogStatus.FAIL, e.fillInStackTrace());
        }
    }

    
    public static void responseKeyAndValueValidationFromJsonObject(Response response, String key, Object expectedValue) {
        try {
            JSONObject json = new JSONObject(response.getBody().asString());
            if (json.has(key) && json.get(key) != null) {
            	Assert.assertEquals(expectedValue, json.get(key));
                test.log(LogStatus.PASS, "Successfully validated value of " + key + ". It is " + json.get(key));
            } else {
                test.log(LogStatus.FAIL, "Key is not available");
            }
        } catch (Exception e) {
            test.log(LogStatus.FAIL, e.fillInStackTrace());
        }
    }
    
    /**
     * Logs the response time of the API request.
     * @param response The response object containing the timing information.
     */
    public static void responseTimeValidation(Response response) {
        try {
            long time = response.time();
            test.log(LogStatus.INFO, "API response time is :: " + time + " ms");
        } catch (Exception e) {
            test.log(LogStatus.FAIL, e.fillInStackTrace());
        }
    }

    /**
     * Validates the value of a specific header in the response.
     * @param response The response object containing the headers.
     * @param headerName The name of the header to validate.
     * @param expectedValue The expected value of the header.
     */
    public static void responseHeaderValidation(Response response, String headerName, String expectedValue) {
        try {
            String actualValue = response.getHeader(headerName);
            Assert.assertEquals(actualValue, expectedValue);
            test.log(LogStatus.PASS, "Successfully validated header " + headerName + ". Value is :: " + actualValue);
        } catch (Exception e) {
            test.log(LogStatus.FAIL, e.fillInStackTrace());
        }
    }

    /**
     * Validates the content type of the response.
     * @param response The response object containing the content type.
     * @param expectedContentType The expected content type.
     */
    public static void responseContentTypeValidation(Response response, String expectedContentType) {
        try {
            String actualContentType = response.getContentType();
            Assert.assertEquals(actualContentType, expectedContentType);
            test.log(LogStatus.PASS, "Successfully validated content type. It is :: " + actualContentType);
        } catch (Exception e) {
            test.log(LogStatus.FAIL, e.fillInStackTrace());
        }
    }

    
    /**
     * Validates the value of a field in a nested JSON object.
     * @param response The response object containing the JSON data.
     * @param tagsNameAndKey A map containing the tags name and key.
     * @param expectedValue The expected value of the field.
     */
    public static void responseFieldValueValidation(Response response, Map<String, String> tagsNameAndKey, Object expectedValue) {
    	try {
            JSONObject json = new JSONObject(response.getBody().asString());
            JSONObject project = json.getJSONObject(tagsNameAndKey.get("tagsName"));
            Object actualValue;
            if (expectedValue instanceof String) {
                actualValue = project.getString(tagsNameAndKey.get("key"));
            } else if (expectedValue instanceof Integer) {
                actualValue = project.getInt(tagsNameAndKey.get("key"));
            }else if(expectedValue instanceof Boolean){
            	  actualValue = project.getBoolean(tagsNameAndKey.get("key"));
            }
            else {
                throw new IllegalArgumentException("Unsupported expected value type: " + expectedValue.getClass().getName());
            }

            Assert.assertEquals(actualValue, expectedValue);
            test.log(LogStatus.PASS, "Successfully validated field value. " + tagsNameAndKey.get("key") + " = " + actualValue);
        } catch (Exception e) {
            test.log(LogStatus.FAIL, e.fillInStackTrace());
        }
    }
  
    
    
    public static void responseLengthValidation(int expectedLength, int actualLength) {
    	try {
           
            Assert.assertEquals(expectedLength, actualLength);
            test.log(LogStatus.PASS, "Successfully validated length It is :: " + actualLength);
        } catch (Exception e) {
            test.log(LogStatus.FAIL, e.fillInStackTrace());
        }
    	
    }
    
    public static void validateTheListOfElement(List<String> expectedList, List<String> actualList) {
    	try {
           
            Assert.assertEquals(expectedList, actualList);
            test.log(LogStatus.PASS, "Successfully validated length It is :: " + actualList);
        } catch (Exception e) {
            test.log(LogStatus.FAIL, e.fillInStackTrace());
        }
    	
    }
}
