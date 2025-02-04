Feature: Project Registration with Battery Only
  @SCRUM-TC-270
  Scenario: Successful product registration with only a battery
    Given I am on the login page
    When I enter username
    And I enter password
    And I click the login button
    Then I should be logged in successfully
    When I click on project Registration Tab
    And I am on the system information page
    And I select 'No' in the product availability dropdown
    And I select 'No' in the solar panel dropdown
    And I select 'No' for ESS product and enable the battery option
    And I fill the project information details
    And I fill the purchasing information details
    And I fill the project owner information details
    Then I verify project Registration Details in Project Details Tab
    And I click the submit button
    Then I verify details of Project in project details section