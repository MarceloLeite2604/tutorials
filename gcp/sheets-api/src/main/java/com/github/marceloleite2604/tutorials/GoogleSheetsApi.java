package com.github.marceloleite2604.tutorials;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.util.*;

public class GoogleSheetsApi {

    private static final String APPLICATION_NAME = "Tutorials - Google Sheets API";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    private static final String USER_ENTERED = "USER_ENTERED";
    private static final String AUTHORIZATION_CODE_FLOW_ACCESS_TYPE = "offline";
    private static final String AUTHORIZATION_CODE_INSTALLED_APP_USER_ID = "user";

    private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS);
    private static final String CREDENTIALS_FILE_PATH = "src/main/resources/google-cloud-platform/tutorials-credentials.json";

    private static final String DEFAULT_SPREADSHEET_ID = "1VZAD6fk7Jyg1rTx3RMHbW38w37EhyULqjy098q2DOB8";
    private static final String SPREADSHEET_TITLE = "Tutorials - Automated spreadsheet";
    private static final String SPREADSHEET_RANGE = "A:B";

    private static String spreadsheetId = DEFAULT_SPREADSHEET_ID;

    private static GoogleClientSecrets retrieveGoogleClientSecrets() throws IOException {
        try (InputStream inputStream = Files.newInputStream(Paths.get(CREDENTIALS_FILE_PATH)); InputStreamReader inputStreamReader = new InputStreamReader(inputStream)) {
            return GoogleClientSecrets.load(JSON_FACTORY, inputStreamReader);
        }
    }

    private static Credential getCredentials(NetHttpTransport netHttpTransport) throws IOException {

        GoogleClientSecrets googleClientSecrets = retrieveGoogleClientSecrets();

        GoogleAuthorizationCodeFlow googleAuthorizationCodeFlow = new GoogleAuthorizationCodeFlow.Builder(
                netHttpTransport, JSON_FACTORY, googleClientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType(AUTHORIZATION_CODE_FLOW_ACCESS_TYPE)
                .build();

        LocalServerReceiver localServerReceiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(googleAuthorizationCodeFlow, localServerReceiver).authorize(AUTHORIZATION_CODE_INSTALLED_APP_USER_ID);
    }

    private static Sheets retrieveSheets() throws IOException, GeneralSecurityException {
        final NetHttpTransport netHttpTransport = GoogleNetHttpTransport.newTrustedTransport();

        Credential credentials = getCredentials(netHttpTransport);

        return new Sheets.Builder(netHttpTransport, JSON_FACTORY, credentials)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    private static Spreadsheet retrieveOrCreateSpreadsheet(Sheets sheets) throws IOException {
        Spreadsheet spreadsheet;
        try {
            spreadsheet = sheets.spreadsheets().get(DEFAULT_SPREADSHEET_ID).execute();
            System.out.println("Spreadsheet found.");
        } catch (GoogleJsonResponseException exception) {
            System.err.println(exception.getDetails().getMessage());
            System.out.println("Spreadsheet not found. Creating a new one.");
            spreadsheet = new Spreadsheet()
                    .setProperties(new SpreadsheetProperties()
                            .setTitle(SPREADSHEET_TITLE));
            spreadsheet = sheets.spreadsheets().create(spreadsheet)
                    .execute();
            System.out.println("New spreadsheet \""+spreadsheet.getProperties().getTitle()+"\" created. ID is " + spreadsheet.getSpreadsheetId());
        }

        return spreadsheet;
    }

    private static ValueRange retrieveContent(Sheets sheets) throws IOException {
        return sheets.spreadsheets().values().get(spreadsheetId, SPREADSHEET_RANGE).execute();
    }

    private static ValueRange createNewRecordValues() {
        List<Object> values = Arrays.asList(Integer.toString(new Random().nextInt()), LocalDateTime.now().toString());
        return new ValueRange()
                .setValues(Collections.singletonList(values));
    }

    private static void appendContent(Sheets sheets, ValueRange valueRange) throws IOException {
        AppendValuesResponse appendValuesResponse =
                sheets.spreadsheets().values().append(spreadsheetId, SPREADSHEET_RANGE, valueRange)
                        .setValueInputOption(USER_ENTERED)
                        .execute();
        System.out.println("Rows written: " + appendValuesResponse.getUpdates().getUpdatedRows());
    }

    public static void main(String[] args) throws IOException, GeneralSecurityException {

        Sheets sheets = retrieveSheets();

        Spreadsheet spreadsheet = retrieveOrCreateSpreadsheet(sheets);
        spreadsheetId = spreadsheet.getSpreadsheetId();

        ValueRange valueRange = retrieveContent(sheets);

        int rows = Optional.ofNullable(valueRange.getValues())
                .map(List::size)
                .orElse(0);
        System.out.println("Rows found: " + rows);

        ValueRange newRecordValues = createNewRecordValues();

        appendContent(sheets, newRecordValues);
    }
}

