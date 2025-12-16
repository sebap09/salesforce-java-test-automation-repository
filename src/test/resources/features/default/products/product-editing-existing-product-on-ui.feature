Feature: Product - editing existing product with the use of UI

  As: A adminUser
  I can: Edit existing account product the use of UI

  @initSessionBefore
  Scenario: Logging to Salesforce platform as adminUser
    Given I authorize as adminUser with SOAP API and fetch session id
    And I login to Salesforce frontdoor with fetched session id
    Then I verify user is correctly logged in

  Scenario: Editing existing account with the use of UI as adminUser
    Given I generate authorization token
    When I fetch Id from "Product2" by "Test Automation Product" Name
    And I open details page of object by fetched id
    And I wait for a page to load
    And I click on "Details" tab
    And I click on menu button
    And I click data target selection name of type "StandardButton", "Product2" and "Edit"
    And I fill "RANDOM_PRODUCT_DESCRIPTION" to "Product Description" product form text area
    And I click "SaveEdit" button
    Then I verify success toast with "productSaved" message

  @endSessionAfter
  Scenario: Verifying edited data
    When I click on "Details" tab
    Then I verify that correct data is visible in "Product Description" field