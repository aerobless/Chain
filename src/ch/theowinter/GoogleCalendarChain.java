package ch.theowinter;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class GoogleCalendarChain {


    public Calendar setUp() throws IOException, GeneralSecurityException {
        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        JacksonFactory jsonFactory = JacksonFactory.getDefaultInstance();

        Secrets secrets = new Secrets();

        // The clientId and clientSecret can be found in Google Developers Console
        String clientId = secrets.clientId;
        String clientSecret = secrets.clientSecret;

        // Or your redirect URL for web based applications.
        String redirectUrl = secrets.redirectUrl;
        String scope = "https://www.googleapis.com/auth/calendar";

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow(
                httpTransport, jsonFactory, clientId, clientSecret, Collections.singleton(scope));
        // Step 1: Authorize
        String authorizationUrl = flow.newAuthorizationUrl().setRedirectUri(redirectUrl).build();

        // Point or redirect your user to the authorizationUrl.
        System.out.println("Go to the following link in your browser:");
        System.out.println(authorizationUrl);

        // Read the authorization code from the standard input stream.
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("What is the authorization code?");
        String code = in.readLine();
        // End of Step 1

        // Step 2: Exchange
        GoogleTokenResponse response = flow.newTokenRequest(code).setRedirectUri(redirectUrl)
                .execute();
        // End of Step 2

        Credential credential = new GoogleCredential.Builder()
                .setTransport(httpTransport)
                .setJsonFactory(jsonFactory)
                .setClientSecrets(clientId, clientSecret)
                .build().setFromTokenResponse(response);

        Calendar service = new Calendar.Builder(httpTransport, jsonFactory, credential)
                .setApplicationName("Chains").build();
        return service;
    }

    public List<ChainEvent> compileChain() throws IOException, GeneralSecurityException{

        Calendar service = setUp();

        // Retrieve the calendar
        com.google.api.services.calendar.model.Calendar calendar =
                service.calendars().get("primary").execute();

        List<ChainEvent> eventList = new ArrayList<ChainEvent>();
        String pageToken = null;
        do {
            Events events = service.events().list("primary").setPageToken(pageToken).execute();
            List<Event> items = events.getItems();
            for (Event event : items) {
                //System.out.println(event.getSummary()+" "+event.getStart().getDate()+" "+event.getStart().getDateTime());
                if(event.getStart()!= null && event.getStart().getDateTime() != null){
                    eventList.add(new ChainEvent(event.getSummary(), new Date(event.getStart().getDateTime().getValue())));
                } else if (event.getStart()!= null && event.getStart().getDate() != null) {
                    eventList.add(new ChainEvent(event.getSummary(), new Date(event.getStart().getDate().getValue())));
                }
            }
            pageToken = events.getNextPageToken();
        } while (pageToken != null);

        return eventList;
    }
}
