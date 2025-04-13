Feature: Test all the request of an API
  Scenario: Test get request for an API
    Given verify get request endpoint
    When Send the get request
    Then verify the status code

  Scenario: Verify post endpoint of API
    Given verify post request endpoint
    When send the post request
      | title  | foo |
      | body   | bar |
      | userId | 1   |
    Then verify the status code for post request

