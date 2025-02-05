Feature: Contacts

  Scenario: Add Contacts
    Given API URL for add contact
    When Add contact using post request
    Then Verify the response code and response json
