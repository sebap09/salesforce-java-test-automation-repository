Feature: Account - editing existing account with the use of UI

  As: A adminUser
  I can: Edit existing account with the use of UI

  @initSessionBefore
  Scenario: Logging to Salesforce platform as adminUser
    Given I am on the login page
    When I fill "adminUser" credential to "username" input field
    And I fill "adminUser" credential to "password" input field
    And I click login button
    Then I verify user is correctly logged in

  Scenario: Editing existing account with the use of UI as adminUser
    Given I generate authorization token
    When I fetch Id from "Account" by "Test Automation Account" Name
    And I open details page of object by fetched id
    And I wait for a page to load
    And I click on details tab
    And I click on menu button
    And I click data target selection name of type "StandardButton", "Account" and "Edit"
    And I fill "RANDOM_DESCRIPTION" to "Description" account form text area
    And I click "SaveEdit" button
    Then I verify success toast with "accountSaved" message

  @endSessionAfter
  Scenario: Verifying edited data
    When I click on details tab
    Then I verify that correct data is visible in "Description" field
