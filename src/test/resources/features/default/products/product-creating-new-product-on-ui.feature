Feature: Product - creating new product with the use of UI

  As: A adminUser
  I can: Create new product with the use of UI

  @initSessionBefore
  Scenario: Logging to Salesforce platform as adminUser
    Given I am on the login page
    When I fill "adminUser" credential to "username" input field
    And I fill "adminUser" credential to "password" input field
    And I click login button
    Then I verify user is correctly logged in

  Scenario: Creating new product with the use of UI as adminUser
    Given I am on the product page
    When I wait for a page to load
    And I click data target selection name of type "StandardButton", "Product2" and "CreateNewProduct"
    And I wait for a page to load
    And I fill "RANDOM_PRODUCT_NAME" to "Name" product form input field
    And I fill "RANDOM_PRODUCT_CODE" to "ProductCode" product form input field
    And I fill "RANDOM_PRODUCT_DESCRIPTION" to "Product Description" product form text area
    And I toggle checkbox with "IsActive" name to "true"
    And I click "SaveEdit" button
    And I wait for a page to load
    Then I verify success toast with "productCreated" message

  @endSessionAfter
  Scenario: Verifying created data
    When I click on details tab
    Then I verify that correct data is visible in "Product Name" field
    And I verify that correct data is visible in "Product Code" field
    And I verify that correct data is visible in "Product Description" field
    And I verify that correct data is visible in "Active" field
