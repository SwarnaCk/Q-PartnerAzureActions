Feature: Project Registration with site id
  @SCRUM-TC-219
  Scenario: Successful product registration with site id
    Given I am on the login page
    When I enter username
    And I enter password
    And I click the login button
    Then I should be logged in successfully
    When I click on project Registration Tab
    And I fill the system information details
    And I fill the project information details
    And I fill the purchasing information details
    And I fill the project owner information details
    Then I verify project Registration Details in Project Details Tab
    And I click the submit button
    Then I verify details of Project in project details section
    When I click on the download PDF button 
    # Then the PDF should contain the project name
    Then the PDF should contain the 'Complete System' text
    And I fill the project information details
    
