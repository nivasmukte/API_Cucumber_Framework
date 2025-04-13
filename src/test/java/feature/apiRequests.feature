Feature: Test all the request of an API
  Scenario: Test get request for an API
    Given verify get request endpoint
    When Send the get request
    Then verify the status code
