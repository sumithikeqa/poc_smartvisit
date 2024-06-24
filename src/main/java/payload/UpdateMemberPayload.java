package payload;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UpdateMemberPayload {

	 private String requestDateTime;
	    private String programId;
	    private String memberId;
	    private String givenName;
	    private String familyName;
	    private String memberPin;

	    public UpdateMemberPayload(String requestDateTime, String programId,String memberId, String givenName, String familyName, String memberPin) {
	      
	        this.requestDateTime = requestDateTime;
	        this.programId = programId;
	        this.memberId=memberId;
	        this.givenName = givenName;
	        this.familyName = familyName;
	        this.memberPin = memberPin;
	    }

	    // Getters and Setters
	    public String getRequestDateTime() {
	        return requestDateTime;
	    }

	    public void setRequestDateTime(String requestDateTime) {
	        
	        this.requestDateTime = requestDateTime;
	    }

	    public String getProgramId() {
	        return programId;
	    }

	    public void setProgramId(String programId) {
	        this.programId = programId;
	    }

	    public String getMemberId() {
	        return memberId;
	    }

	    public void setMemberId(String memberId) {
	        this.memberId = memberId;
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

	    public String getMemberPin() {
	        return memberPin;
	    }

	    public void setMemberPin(String memberPin) {
	        this.memberPin = memberPin;
	    }

	  	
}
