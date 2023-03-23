Feature: Initiate creation of snowshoe tours
  As a manager, I want to initiate the creation of snowshoe tours to assign guides and tour weeks to participants

  Background: 
    Given the following SnowShoeTours system exists
      | startDate  | nrWeeks | priceOfGuidePerWeek |
      | 2024-03-13 |      10 |                 100 |
    Given the following guides exist in the system
      | email          | password | name | emergencyContact |
      | jeff@email.com | pass1    | Jeff | (555)555-5555    |
      | john@email.com | pass2    | John | (444)444-4444    |
      | bob@email.com  | psw0rd1  | Bob  | (200)5555678     |

  Scenario: Successfully initiate snowshoe tour creation in which a newer participant is assigned before an older one
    Given the following participants exist in the system
      | email              | password | name             | emergencyContact | nrWeeks | weeksAvailableFrom | weeksAvailableUntil | lodgeRequired |
      | alice@gmail.com    | pass123  | Alice Jones      | (200)5551234     |       3 |                  1 |                   3 | true          |
      | new@hotmail.ca     | newnew   | Johnny New       | (200)5559999     |       5 |                  2 |                   6 | true          |
      | charlie@hotmail.ca | charlie  | Charles Tremblay | (200)5559876     |       3 |                  1 |                   5 | false         |
      | john@hotmail.ca    | john123  | John Doe         | (200)5551234     |       3 |                  1 |                   3 | false         |
      | emily@hotmail.ca   | emily007 | Emily Green      | (200)5559876     |       2 |                  4 |                   5 | false         |
    When the administrator attempts to initiate the snowshoe tour creation process
    Then the following snowshoe tours shall exist in the system with a guide
      | id | startWeek | endWeek | participants       |
      |  1 |         1 |       3 | alice@gmail.com    |
      |  2 |         2 |       6 | new@hotmail.ca     |
      |  3 |         1 |       3 | charlie@hotmail.ca |
      |  4 |         1 |       3 | john@hotmail.ca    |
      |  5 |         4 |       5 | emily@hotmail.ca   |
    Then the participant with email "alice@gmail.com" shall be marked as "Assigned"
    Then the participant with email "new@hotmail.ca" shall be marked as "Assigned"
    Then the participant with email "charlie@hotmail.ca" shall be marked as "Assigned"
    Then the participant with email "john@hotmail.ca" shall be marked as "Assigned"
    Then the participant with email "emily@hotmail.ca" shall be marked as "Assigned"
    Then the number of snowshoe tours shall be "5"
