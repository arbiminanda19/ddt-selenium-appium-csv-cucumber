Feature: Login functionality

  Scenario Outline: Ensure login functionality
    Given user is on KasirAja login page
    When user input <email> as email
    And user input <password> as password
    And user click submit
    Then user verify <status> login result

    Examples:
      | email                   | password      | status  |
      | tdd-selenium@gmail.com  | tdd-selenium  | success |
      | failed-login@gmail.com  | failed-login  | failed  |