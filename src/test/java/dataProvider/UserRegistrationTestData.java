package dataProvider;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.testng.annotations.DataProvider;

import payload.LoginPayload;
import payload.UpdateMemberPayload;
import payload.UserRegistrationRequest;

public class UserRegistrationTestData {
	
	/**
	 * Data provider method to generate test data for creating a project.
	 * This method generates a unique project name using the current date and time.
	 * It returns a two-dimensional array containing a map with project data.
	 * The map includes the project name, a tag name, and a key.
	 * 
	 * @return A two-dimensional array containing a map with project data.
	 */
	
	static String email;
	
	@DataProvider(name="memberRegistrationData")
	public Object[][] memberRegistration() {
		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		 email = "Sumit" + currentDateTime.format(formatter);
		UserRegistrationRequest payload= new UserRegistrationRequest();
		payload.setRequestDateTime("25-04-2024 10:20:00");
		payload.setProgramId(22181);
		payload.setPostCode("3255");
		payload.setPassword("password@123");
		payload.setEmailAddress("testuser"+email+"@gmail.com");
		payload.setGivenName("Test");
		payload.setFamilyName("AU");
		payload.setCountryCode("AU");
		
		return new Object[][] {{payload}};
	}
	
	@DataProvider(name="loginData")
	public Object[][] login() {
		
		LoginPayload payload= new LoginPayload();
		payload.setRequestDateTime("25-04-2024 10:20:00");
		payload.setPassword("password@123");
		payload.setEmailAddress("testuser"+email+"@gmail.com");
				
		return new Object[][] {{payload}};
	}
	
	@DataProvider(name="updateMember")
	public Object[][] updateMember() {
		
	
				
		return new Object[][] {
			
			{new UpdateMemberPayload("14-06-2024 10:20:00","22181","R04004328","Sample","Member","6987")}
			
		};
	}
	
}
