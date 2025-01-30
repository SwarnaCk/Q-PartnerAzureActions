package com.aiointegration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ResultUploaderToAIOTest {

    private static final String API_URL = "https://tcms.aiojiraapps.com/aio-tcms/api/v1/project/{projectKey}/testcycle/{testCycleKey}/import/results?type=Cucumber";
    private static final String PROJECT_KEY = "SCRUM";
    private static final String TEST_CYCLE_KEY = "SCRUM-CY-30";
    private static final String GITHUB_REPO = "saikat-ck/QPartner-POC";

    private final String aioToken;
    private final String gitToken;

    public ResultUploaderToAIOTest(String aioToken, String gitToken) {
        this.aioToken = aioToken;
        this.gitToken = gitToken;
    }

    public String getGitHubActionVariable(String variableName) {
        String apiUrl = "https://api.github.com/repos/" + GITHUB_REPO + "/actions/variables";

        try {
            Response response = RestAssured
                    .given()
                    .header("Authorization", "Bearer " + this.gitToken)
                    .when()
                    .get(apiUrl)
                    .then()
                    .extract().response();

            int statusCode = response.getStatusCode();
            String responseBody = response.getBody().asString();

            if (statusCode == 200) {
                JSONParser parser = new JSONParser();
                JSONObject jsonResponse = (JSONObject) parser.parse(responseBody);
                JSONArray variables = (JSONArray) jsonResponse.get("variables");

                for (Object obj : variables) {
                    JSONObject variable = (JSONObject) obj;
                    if (variable.get("name").equals(variableName)) {
                        return (String) variable.get("value");
                    }
                }
                System.err.println("Variable " + variableName + " not found.");
                return null;
            } else {
                System.err.println("Failed to fetch GitHub Action Variables. Response Code: " + statusCode);
                System.err.println("Response Body: " + responseBody);
                return null;
            }
        } catch (ParseException e) {
            System.err.println("Error parsing JSON response: " + e.getMessage());
            return null;
        }
    }

    public void uploadTestResults(File cucumberJsonFile) throws IOException {
        String apiUrl = API_URL
                .replace("{projectKey}", PROJECT_KEY)
                .replace("{testCycleKey}", TEST_CYCLE_KEY);

        try (FileInputStream fis = new FileInputStream(cucumberJsonFile)) {
            Response response = RestAssured
                    .given()
                    .header("Authorization", "AioAuth " + this.aioToken)
                    .multiPart("file", cucumberJsonFile, "application/json")
                    .formParam("createNewRun", "true")
                    .formParam("createCase", "true")
                    .formParam("addCaseToCycle", "true")
                    .formParam("bddForceUpdateCase", "true")
                    .formParam("forceUpdateCase", "true")
                    .formParam("updateDatasets", "true")
                    .formParam("type", "Cucumber")
                    .when()
                    .post(apiUrl)
                    .then()
                    .extract().response();
 

            int statusCode = response.getStatusCode();
            String responseBody = response.getBody().asString();

            if (statusCode == 200 || statusCode == 201) {
                System.out.println("Test results uploaded successfully!");
                // System.out.println("Response Body: " + responseBody);
            } else {
                System.err.println("Failed to upload test results. Response Code: " + statusCode);
                System.err.println("Response Body: " + responseBody);

                System.err.println("Request URL: " + apiUrl);
                System.err.println("File exists: " + cucumberJsonFile.exists());
                System.err.println("File size: " + cucumberJsonFile.length());
            }
        } catch (IOException e) {
            System.err.println("Error uploading test results: " + e.getMessage());
            e.printStackTrace();
        }
    }
}