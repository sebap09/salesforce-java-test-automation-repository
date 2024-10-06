Feature: Asset - creating new asset and corresponding account, product and contact with the use of UI

  As: A adminUser
  I can: Create new asset and corresponding account, product and contact with the use of UI

  @initSessionBefore
  Scenario: Logging to Salesforce platform as adminUser
    Given I am on the login page
    When I fill "adminUser" credential to "username" input field
    And I fill "adminUser" credential to "password" input field
    And I click login button
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
    And I fill "RANDOM_DESCRIPTION" to "Description" account form text area
    And I select "Technology Partner" from "Type" object form dropdown
    And I select "Banking" from "Industry" object form dropdown
    And I click "SaveEdit" button
    Then I verify success toast with "accountCreated" message

  Scenario: Verifying created account data
    When I click on details tab
    Then I verify that correct data is visible in "Account Name" field
    And I verify that correct data is visible in "Phone" field
    And I verify that correct data is visible in "Type" field
    And I verify that correct data is visible in "Industry" field
    And I verify that correct data is visible in "Billing Address" field
    And I verify that correct data is visible in "Description" field

  Scenario: Creating new contact with the use of UI as adminUser
    Given I am on the contact page
    When I wait for a page to load
    And I click data target selection name of type "StandardButton", "Contact" and "NewContact"
    And I wait for a page to load
    And I fill "RANDOM_FIRST_NAME" to "firstName" contact form input field
    And I fill "RANDOM_LAST_NAME" to "lastName" contact form input field
    And I select "ACCOUNT_NAME" from "Account Name" form combo box
    And I fill "RANDOM_EMAIL" to "Email" contact form input field
    And I fill "RANDOM_MOBILE" to "MobilePhone" contact form input field
    And I click "SaveEdit" button
    And I map First Name and Last Name to Full Name field
    Then I verify success toast with "contactCreated" message

  Scenario: Verifying created contact data
    When I click on details tab
    Then I verify that correct data is visible in "Name" field
    And I verify that correct data is visible in "Mobile" field
    And I verify that correct data is visible in "Email" field
    And I verify that correct data is visible in "Account Name" field

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

  Scenario: Verifying created product data
    When I click on details tab
    Then I verify that correct data is visible in "Product Name" field
    And I verify that correct data is visible in "Product Code" field
    And I verify that correct data is visible in "Product Description" field
    And I verify that correct data is visible in "Active" field

  Scenario: Creating new asset with the use of UI as adminUser
    Given I am on the asset page
    When I wait for a page to load
    And I click data target selection name of type "StandardButton", "Asset" and "New"
    And I wait for a page to load
    And I fill "RANDOM_ASSET_NAME" to "Name" asset form input field
    And I fill "RANDOM_SERIAL_NUMBER" to "SerialNumber" asset form input field
    And I fill "RANDOM_DESCRIPTION" to "Description" asset form text area
    And I toggle checkbox with "IsCompetitorProduct" name to "true"
    And I select "ACCOUNT_NAME" from "Account" form combo box
    And I select "PRODUCT_NAME" from "Product" form combo box
    And I select "CONTACT_NAME" from "Contact" form combo box
    And I click "SaveEdit" button
    And I wait for a page to load
    Then I verify success toast with "assetCreated" message

  @endSessionAfter
  Scenario: Verifying created asset data
    When I click on details tab
    Then I verify that correct data is visible in "Asset Name" field
    And I verify that correct data is visible in "Serial Number" field
    And I verify that correct data is visible in "Description" field
    And I verify that correct data is visible in "Competitor Asset" field
    And I verify that correct data is visible in "Account" field
    And I verify that correct data is visible in "Contact" field
    And I verify that correct data is visible in "Product" field