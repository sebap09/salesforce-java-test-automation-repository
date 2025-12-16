Feature: Account - editing existing account with the use of UI

  As: A adminUser
  I can: Edit existing account with the use of UI

  @initSessionBefore
  Scenario: Logging to Salesforce platform as adminUser
    Given I authorize as adminUser with SOAP API and fetch session id
    And I login to Salesforce frontdoor with fetched session id
    Then I verify user is correctly logged in

  Scenario: Editing existing account with the use of UI as adminUser
    Given I generate authorization token
    When I fetch Id from "Account" by "Test Automation Account" Name
    And I open details page of object by fetched id
    And I wait for a page to load
    And I click on "Details" tab custom layout
    And I click on menu button
    And I click data target selection name of type "StandardButton", "Account" and "Edit"
    And I fill "RANDOM_DESCRIPTION" to "Description" account form text area
    And I click "SaveEdit" button
    Then I verify success toast with "accountSaved" message

  @endSessionAfter
  Scenario: Verifying edited data
    When I click on "More Information" tab custom layout
    Then I verify that correct data is visible in "Description" field custom layout
