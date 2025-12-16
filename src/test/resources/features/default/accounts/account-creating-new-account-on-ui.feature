Feature: Account - creating new account with the use of UI

  As: A adminUser
  I can: Create new account with the use of UI

  @initSessionBefore
  Scenario: Logging to Salesforce platform as adminUser
    Given I authorize as adminUser with SOAP API and fetch session id
    And I login to Salesforce frontdoor with fetched session id
    Then I verify user is correctly logged in

  Scenario: Creating new account with the use of UI as adminUser
    Given I am on the account page
    When I wait for a page to load
    And I click data target selection name of type "StandardButton", "Account" and "New"
    And I wait for a page to load
    And I fill "RANDOM_ACCOUNT_NAME" to "Name" account form input field
    And I fill "RANDOM_PHONE_NUMBER" to "Phone" account form input field
    And I fill "RANDOM_STREET" to "Billing Street" account form text area
    And I fill "RANDOM_POSTAL_CODE" to "postalCode" account form input field
    And I fill "RANDOM_CITY" to "city" account form input field
    And I fill "RANDOM_PROVINCE" to "province" account form input field
    And I fill "RANDOM_COUNTRY" to "country" account form input field
    And I select "Technology Partner" from "Type" object form dropdown
    And I select "Banking" from "Industry" object form dropdown
    And I click "SaveEdit" button
    Then I verify success toast with "accountCreated" message

  @endSessionAfter
  Scenario: Verifying created data
    When I click on "Details" tab custom layout
    Then I verify that correct data is visible in "Account Name" field custom layout
    And I verify that correct data is visible in "Phone" field custom layout
    And I verify that correct data is visible in "Type" field custom layout
    And I verify that correct data is visible in "Industry" field custom layout
    And I verify that correct data is visible in "Billing Address" field custom layout