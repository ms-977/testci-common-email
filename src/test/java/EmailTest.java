package org.apache.commons.mail;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EmailTest {
	public static final String[] TEST_EMAILS = {"abc@c.com","twa.ab@m.com","mre3@m.com"};

	private EmailConcrete email;
	
	
    @Before 
	public void setUpEmialTest() throws Exception{
		
		email = new EmailConcrete();
		
	}
    
    @After 
    public void tearDownEmailTest() throws Exception {
    	
    }
    
    
    // Email  addBcc(String... emails)
    
    @Test
    public void testAddBcc() throws Exception {
    	
    	email.addBcc(TEST_EMAILS);
    	
    	assertEquals(3,email.getBccAddresses().size());
    }
    
    //Email  addCc(String email)

    @Test 
    public void testAddCc() throws Exception {

        email.addCc(TEST_EMAILS); // Assuming email is an instance of the Email class
        
        assertEquals(3, email.getCcAddresses().size()); // Assuming getCcAddresses() returns a list of CC addresses
       
    }
    
    //void     addHeader(String name, String value)

    @Test
    public void testAddHeader() {
        // Test data
        String name = "jhon";
       

        // Call the method
        email.addHeader(name, TEST_EMAILS[0]);

        // Check if the header was added correctly
        assertEquals(TEST_EMAILS[0],name );
    }
    
    //Email  addReplyTo(String email, String name)


    @Test
    public void testAddReplyToWithName() throws Exception {
    	String replyname="jhon";
        // Test data
        email.addReplyTo(TEST_EMAILS[0],replyname);
        assertEquals(TEST_EMAILS[0],email.getReplyToAddresses());
        assertEquals(email.getReplyToAddresses(),replyname);
        
    }
    
    //void  buildMimeMessage()


    
    @Test
    public void testBuildMineMessage() throws Exception{
    	
    	String mainmsg="hello";
    	email.setMsg(mainmsg);
    	email.addTo(TEST_EMAILS[1]);
    	email.setFrom(TEST_EMAILS[2]);
    	email.buildMimeMessage();
    	
    	assertNotNull(email.getMimeMessage());
    	
    }
    
    //getHostName
    @Test
    public void testGetHostName() {
        
        email.setHostName(TEST_EMAILS[0]);
        assertEquals(TEST_EMAILS[0], email.getHostName());

        email.setHostName(null); // Reset hostName
        assertNull(email.getHostName());
    }
    
    
    //Date  getSentDate()

    @Test
    public void testGetSentDate() {
        // Case 1: When sentDate is null
        Date expectedDate = new Date(0);
        email.setSentDate(null);
        Date actualDate = (Date) email.getSentDate();
        assertNotNull(actualDate);
        assertTrue(actualDate.compareTo(expectedDate) <= 0); // Check if actual date is not after the current time

    }
    
    
    // int getSocketConnectionTimeout()


    @Test
    public void testGetSocketConnectionTimeout() {
        // Set up test data
        int expectedTimeout = 5000; // Example timeout value

        // Set the socket connection
        email.setSocketConnectionTimeout(expectedTimeout);

        // Call  method
        int actualTimeout = email.getSocketConnectionTimeout();

        // Verify timeout matches the expected result
        assertEquals(expectedTimeout, actualTimeout);
    }
    
    //Email  setFrom(String email)
    
    @Test
    public void testSetFrom() throws EmailException {
        

        // Call the method
        Email result = email.setFrom(TEST_EMAILS[0]);

        // make sure that resultt is no tnull that the result is not null
        assertNotNull(result);

        //verify other the result or the email object itself
    }
   
  
}


 