package com.next_birthday_test;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Calc_User_Next_Bday{
	private final String BASE_URL = "https://lx8ssktxx9.execute-api.eu-west-1.amazonaws.com/Prod/next-birthday";
	
	
    @Test
    public void testHourUnit() throws IOException  {
        String expectedResponse ="{" + "message" + ": "+ "2616 hours left" + "}";        
        String actualResponse = executeHttpRequest("1990-08-03", "hour");
        Assert.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testDayUnit() throws IOException {
        String expectedResponse = "{" + "message" + ": "+ "109 days left" + "}";        
        String actualResponse = executeHttpRequest("1990-08-03", "day");
        Assert.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testWeekUnit() throws IOException {
        String expectedResponse = "{" + "message" + ": "+ "15 weeks left" + "}";        
        String actualResponse = executeHttpRequest("1990-07-30", "week");
        Assert.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testMonthUnit() throws IOException {
        String expectedResponse = "{" + "message" + ": " + ("3 months left") + "}" ;
        String actualResponse = executeHttpRequest("1990-07-16", "month");
        Assert.assertEquals(expectedResponse, actualResponse);
    }
	

    private String executeHttpRequest(String dateOfBirth, String unit) throws IOException {
       	URL url = new URL(BASE_URL + "?dateofbirth=" + dateOfBirth + "&unit=" + unit);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        int responseCode = conn.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("HTTP response code: " + responseCode);
        }

        Scanner scanner = new Scanner(conn.getInputStream());
        StringBuilder sb = new StringBuilder();
        while (scanner.hasNextLine()) {
            sb.append(scanner.nextLine());
        }
        scanner.close();

        return sb.toString();
    }

}



