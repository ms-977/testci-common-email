package org.apache.commons.mail;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EmailTest {
    private EmailConcrete email;
    public static final String[] TEST_EMAILS = {"abc@c.com","twa.ab@m.com","mre3@m.com"};

    @Before 
    public void setUpEmailTest() throws Exception {
        email = new EmailConcrete();
    }
    
    @After 
    public void tearDownEmailTest() throws Exception {
        email = null;
    }
    
    @Test
    public void testAddBcc() throws Exception {
        email.addBcc(TEST_EMAILS);
        assertEquals(3, email.getBccAddresses().size());
    }
    
    @Test 
    public void testAddCc() throws Exception {
        for (String email : TEST_EMAILS) {
            email.addCc(email);
        }
        assertEquals(3, email.getCcAddresses().size());
    }
    
    @Test
    public void testAddHeader() {
        String name = "X-Test-Header";
        String value = "Test Value";
        email.addHeader(name, value);
        assertEquals(value, email.headers.get(name));
    }
    
    @Test
    public void testAddReplyToWithName() throws Exception {
        String replyName = "John";
        email.addReplyTo(TEST_EMAILS[0], replyName);
        assertEquals(TEST_EMAILS[0], email.getReplyToAddresses().get(0));
        assertEquals(replyName, email.getReplyToAddresses().get(1));
    }
    
    @Test
    public void testBuildMimeMessage() throws Exception {
        String mainMsg = "Hello";
        email.setMsg(mainMsg);
        email.addTo(TEST_EMAILS[1]);
        email.setFrom(TEST_EMAILS[2]);
        email.buildMimeMessage();
        assertNotNull(email.getMimeMessage());
    }
    
    @Test
    public void testGetHostName() {
        String hostName = "mail.example.com";
        email.setHostName(hostName);
        assertEquals(hostName, email.getHostName());
        email.setHostName(null);
        assertNull(email.getHostName());
    }
    
    @Test
    public void testGetSentDate() {
        assertNull(email.getSentDate());
    }
    
    @Test
    public void testGetSocketConnectionTimeout() {
        int timeout = 5000;
        email.setSocketConnectionTimeout(timeout);
        assertEquals(timeout, email.getSocketConnectionTimeout());
    }
    
    @Test
    public void testSetFrom() {
        String fromEmail = "sender@example.com";
        Email result = email.setFrom(fromEmail);
        assertNotNull(result);
        assertEquals(fromEmail, email.getFromAddress());
    }
}