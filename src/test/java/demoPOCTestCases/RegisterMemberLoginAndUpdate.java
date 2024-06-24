package demoPOCTestCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import apiConfigs.ApiPath;
import apiMethodHelper.PostMethodHelper;
import apiVerifications.APIVerification;
import dataProvider.UserRegistrationTestData;
import io.restassured.response.Response;
import payload.LoginPayload;
import payload.UpdateMemberPayload;
import payload.UserRegistrationRequest;
import utils.ExtentReportListner;
import utils.GeneralUtil;

@Listeners(ExtentReportListner.class)
public class RegisterMemberLoginAndUpdate {

    // Test case to validate that a user can register with all mandatory fields
    @Test(dataProvider = "memberRegistrationData", dataProviderClass = UserRegistrationTestData.class)
    public void TC_01ValidateTheUserEnterAllMandatoryField(UserRegistrationRequest req) throws IOException {
        
        Response res = PostMethodHelper.postRequest(ApiPath.apiPath.MEMBER_REGISTRATION, req);
        APIVerification.responseCodeValidation(res, 200);
        
        Map<String, String> data = new HashMap<String, String>();
        data.put("tagsName", "requestStatus");
        data.put("key", "status");
        APIVerification.responseFieldValueValidation(res, data, "SUCCESS");
    }

    // Test case to validate that a user can log in with valid credentials
    @Test(dataProvider = "loginData", dataProviderClass = UserRegistrationTestData.class)
    public void TC_02ValidateTheUserLoginWithValidCreds(LoginPayload req) throws IOException {
        
        Response res = PostMethodHelper.postRequest(ApiPath.apiPath.LOGIN, req);
        GeneralUtil.printResponse(res.asPrettyString());
        APIVerification.responseCodeValidation(res, 200);
        
        Map<String, String> data = new HashMap<String, String>();
        data.put("tagsName", "requestStatus");
        data.put("key", "status");
        APIVerification.responseFieldValueValidation(res, data, "SUCCESS");
    }
    
    // Test case to validate that a user can update their credentials
    @Test(dataProvider = "updateMember", dataProviderClass = UserRegistrationTestData.class)
    public void TC_03ValidateTheUserUpdateTheCreads(UpdateMemberPayload req) throws IOException {
      
        Response res = PostMethodHelper.postRequest(ApiPath.apiPath.MEMBER_UPDATE, req);
        GeneralUtil.printResponse(res.asPrettyString());
        APIVerification.responseCodeValidation(res, 200);
        
        Map<String, String> data = new HashMap<String, String>();
        data.put("tagsName", "requestStatus");
        data.put("key", "status");
        APIVerification.responseFieldValueValidation(res, data, "SUCCESS");
    }
}
