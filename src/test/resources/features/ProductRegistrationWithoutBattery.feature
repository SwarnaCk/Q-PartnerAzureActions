Feature: Project Registration without battery
  @SCRUM-TC-222
  Scenario: Successful product registration without battery
    Given I am on the login page
    When I enter username
    And I enter password
    And I click the login button
    Then I should be logged in successfully
    When I click on project Registration Tab
    And I am on the system information page
    And I enter Site Id as 'Yes' option is seelected in product dropdown
    And I fill model, powerclass, type ,product generation as 'Yes' option is selected in solar panel dropdown
    And I fill registration no as'Yes' option is selected in ESS product dropdown
    And I cannot verify the battery status
    And I fill the project information details
    And I fill the purchasing information details
    And I fill the project owner information details
    Then I verify project Registration Details in Project Details Tab
    And I click the submit button
    Then I verify details of Project in project details section
    When I click on the download PDF button
    Then the PDF should contain the 'Complete System' text
   