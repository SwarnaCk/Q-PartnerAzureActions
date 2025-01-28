package com.aiointegration;

import java.io.File;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.utils.ConfigReader;

public class ResultUploaderToAIOTest {

    private static final String API_URL = "https://tcms.aiojiraapps.com/aio-tcms/api/v1/project/{projectKey}/testcycle/{testCycleKey}/import/results?type=Cucumber";
    private static final String PROJECT_KEY = "SCRUM";
    private static final String TEST_CYCLE_KEY = "SCRUM-CY-30";
    private static final String AIO_TOKEN = ConfigReader.loadEnv("AIO_TOKEN") != null
            ? ConfigReader.loadEnv("AIO_TOKEN")
            : System.getenv("AIO_TOKEN");
    private static final String GIT_TOKEN = ConfigReader.loadEnv("GIT_TOKEN") != null
            ? ConfigReader.loadEnv("GIT_TOKEN")
            : System.getenv("GIT_TOKEN");
    private static final String GITHUB_REPO = "saikat-ck/QPartner-POC";

    public String getGitHubActionVariable(String variableName) {
        String apiUrl = "https://api.github.com/repos/" + GITHUB_REPO + "/actions/variables";

        try {
            Response response = RestAssured
                    .given()
                    .header("Authorization", "Bearer " + GIT_TOKEN)
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
                    .header("Authorization", "AioAuth " + AIO_TOKEN)
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