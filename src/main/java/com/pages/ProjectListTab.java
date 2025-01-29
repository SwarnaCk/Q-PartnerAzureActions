package com.pages;

import java.util.ArrayList;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;

import com.utils.ConfigReader;
import com.utils.ReusableClass;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class ProjectListTab {
    private ReusableClass reusable;

    public ProjectListTab(ReusableClass reusable) {
        this.reusable = reusable;
    }

    public By getAttributeName() {
        JSONObject jsonObject = ConfigReader.readJsonFile("projectInfo.json");
        String keyValue = (String) jsonObject.get("projectName");
        return By.xpath("//a[text()='" + keyValue + "']");
    }

    public By getDownloadBtn() {
        JSONObject jsonObject = ConfigReader.readJsonFile("projectInfo.json");
        String keyValue = (String) jsonObject.get("projectName");
        return By.xpath("//a[text()='" + keyValue + "']/../..//button[@title='Download']");
    }

    public String getProjectName() {
        return reusable.getText(getAttributeName());

    }

    public void clickDownloadPdf() {
        reusable.click(getDownloadBtn());
    }

    public void switchToPdfTab() {
        ArrayList<String> tabs = new ArrayList<>(reusable.getDriver().getWindowHandles());
        reusable.getDriver().switchTo().window(tabs.get(1)); // Switch to the new tab
    }

    public boolean validatePdfContent(String expectedText) throws IOException {

        try {

            // Extract the text from the PDF file using Apache PDFBox
            String path=System.getProperty("user.dir") + "/src/test/resources/pdfData/QPartner_Certificate_Battery.pdf";
            File pdfFilePath = new File(path);
            BufferedInputStream bf = new BufferedInputStream(new FileInputStream(pdfFilePath));
            PDDocument document = PDDocument.load(bf);
            PDFTextStripper stripper = new PDFTextStripper();
            String pdfText = stripper.getText(document);
            // Check if the expected text is present in the extracted tex
            return pdfText.contains(expectedText);
        } catch (IOException e) {
            // Handle the exception, for example by logging the error
            e.printStackTrace();
            return false; // Or return a default value
        }
    }
    public boolean validateCompleteSystemTextContent(String expectedText) throws IOException {

        try {

            // Extract the text from the PDF file using Apache PDFBox
            String path=System.getProperty("user.dir") + "/src/test/resources/pdfData/QPartner_Certificate_Complete_System.pdf";
            File pdfFilePath = new File(path);
            BufferedInputStream bf = new BufferedInputStream(new FileInputStream(pdfFilePath));
            PDDocument document = PDDocument.load(bf);
            PDFTextStripper stripper = new PDFTextStripper();
            String pdfText = stripper.getText(document);
            // Check if the expected text is present in the extracted tex
            return pdfText.contains(expectedText);
        } catch (IOException e) {
            // Handle the exception, for example by logging the error
            e.printStackTrace();
            return false; // Or return a default value
        }
    }

    public void closePdfTabAndSwitchBack() {
        reusable.getDriver().close(); // Close the current tab (PDF tab)
        ArrayList<String> tabs = new ArrayList<>(reusable.getDriver().getWindowHandles());
        reusable.getDriver().switchTo().window(tabs.get(0)); // Switch back to the original tab
    }

}
