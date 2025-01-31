Feature: Product Registration without any product
  
  Scenario: Successful product registration without any valid product
    Given I am on the login page
    When I enter username
    And I enter password
    And I click the login button
    Then I should be logged in successfully
    When I click on project Registration Tab
    And I am on the system information page
    And I select 'No' in the product availability dropdown
    And I fill power class, type ,product generation as 'Yes' option is selected in solar panel dropdown
    And I Select 'Yes' for ESS product and fills in racking details in System Info
    And I fill the project information details
    And I fill the purchasing information details
    And I fill the project owner information details
    Then I verify project Registration Details in Project Details Tab
    And I click the submit button
    Then I verify details of Project in project details section
    When I click on the download PDF button
    Then the PDF should contain the 'Complete System' text
    