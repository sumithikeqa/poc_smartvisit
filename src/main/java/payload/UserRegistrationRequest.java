package payload;



	public class UserRegistrationRequest {
	   
		private String requestDateTime;
	    private int programId;
	    private String givenName;
	    private String familyName;
	    private String emailAddress;
	    private String password;
	    private String postCode;
	    private String countryCode;

	    // Getters and Setters
	    public String getRequestDateTime() {
	        return requestDateTime;
	    }

	    public void setRequestDateTime(String requestDateTime) {
	        this.requestDateTime = requestDateTime;
	    }

	    public int getProgramId() {
	        return programId;
	    }

	    public void setProgramId(int programId) {
	        this.programId = programId;
	    }

	    public String getGivenName() {
	        return givenName;
	    }

	    public void setGivenName(String givenName) {
	        this.givenName = givenName;
	    }

	    public String getFamilyName() {
	        return familyName;
	    }

	    public void setFamilyName(String familyName) {
	        this.familyName = familyName;
	    }

	    public String getEmailAddress() {
	        return emailAddress;
	    }

	    public void setEmailAddress(String emailAddress) {
	        this.emailAddress = emailAddress;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public String getPostCode() {
	        return postCode;
	    }

	    public void setPostCode(String postCode) {
	        this.postCode = postCode;
	    }

	    public String getCountryCode() {
	        return countryCode;
	    }

	    public void setCountryCode(String countryCode) {
	        this.countryCode = countryCode;
	    }

	}    
   
