Feature: Project Registration

  Scenario: Successful project registration
    Given I am on the login page
    When I enter username
    And I enter password
    And I click the login button
    Then I should be logged in successfully
    When I click on project Registration Tab
    And I select 'No' option in dropdown in system information page
    And I fill the project information details
    And I fill the purchasing information details
    And I fill the project owner information details