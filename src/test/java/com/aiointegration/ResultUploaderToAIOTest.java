package com.aiointegration;

import java.io.File;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.io.FileInputStream;
import java.io.IOException;


public class ResultUploaderToAIOTest {

    private static final String API_URL = "https://tcms.aiojiraapps.com/aio-tcms/api/v1/project/{projectKey}/testcycle/{testCycleKey}/import/results?type=Cucumber";
    private static final String PROJECT_KEY = "SCRUM";
    private static final String TEST_CYCLE_KEY = "SCRUM-CY-27";
    private static final String AUTH_TOKEN = "AioAuth N2YxMDM1NTItOWRkNy0zMDYyLWIyNjYtMDQxYWY5MzhlYjc1LjU3MWMyMTgzLTM3MGEtNGEzMy1iZGEzLTUyZTNmMTAzZjEyZA==";

    public void uploadTestResults(File cucumberJsonFile) {
        String apiUrl = API_URL
                .replace("{projectKey}", PROJECT_KEY)
                .replace("{testCycleKey}", TEST_CYCLE_KEY);

        try (FileInputStream fis = new FileInputStream(cucumberJsonFile)) {
            Response response = RestAssured
                    .given()
                    .header("Authorization", AUTH_TOKEN)
                    .multiPart("file", cucumberJsonFile, "application/json")
                    .formParam("createNewRun", "true")
                    .formParam("bddForceUpdateCase", "true")
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




