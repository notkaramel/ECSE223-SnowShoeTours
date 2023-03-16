Feature: Register and Delete Participant (g1)
As a participant, I want to register and update my participant account so that I can take a snowshoe tours

  Background: 
    Given the following SnowShoeTour system exists (g1)
      | startDate  | nrWeeks | priceOfGuidePerWeek |
      | 2024-03-13 |      10 |                 100 |
    Given the following pieces of gear exist in the system (g1)
      | name     | pricePerWeek |
      | snowshoe |           25 |
      | pole     |          150 |
      | backpack |           19 |
    Given the following combos exist in the system (g1)
      | name        | discount | items                  | quantity |
      | small combo |       10 | pole,snowshoe          |      1,2 |
      | large combo |       25 | pole,snowshoe,backpack |    1,2,2 |
    Given the following guides exist in the system (g1)
      | email          | password | name | emergencyContact |
      | jeff@email.com | pass1    | Jeff | (555)555-5555    |
      | john@email.com | pass2    | John | (444)444-4444    |
    Given the following participants exist in the system (g1)
      | email           | password | name  | emergencyContact | nrWeeks | weeksAvailableFrom | weeksAvailableUntil | lodgeRequired |
      | peter@email.com | pass1    | Peter | (666)555-5555    |       1 |                  1 |                   2 | true          |
      | tyler@email.com | pass2    | Tyler | (777)444-4444    |       2 |                  2 |                   5 | false         |
      | mary@email.com  | pass3    | Mary  | (555)666-6666    |       1 |                  1 |                   2 | false         |

  Scenario Outline: Register a participant successfully
    When a new participant attempts to register with email "<email>", password "<password>", name "<name>", emergency contact "<emergencyContact>", number of weeks "<nrWeeks>", week available from "<weeksAvailableFrom>", week available until "<weeksAvailableUntil>", and lodge required "<lodgeRequired>" (g1)
    Then a participant account shall exist with email "<email>", password "<password>", name "<name>", emergency contact "<emergencyContact>", number of weeks "<nrWeeks>", week available from "<weeksAvailableFrom>", week available until "<weeksAvailableUntil>", and lodge required "<lodgeRequired>" (g1)
    Then the number of participants shall be "4" (g1)

    Examples: 
      | email         | password  | name  | emergencyContact | nrWeeks | weeksAvailableFrom | weeksAvailableUntil | lodgeRequired |
      | user3@mail.ca | password3 | User3 | (333)333-3333    |       3 |                  3 |                  10 | true          |
      | user4@mail.ca | password4 | User4 | (444)444-4444    |       4 |                  4 |                   7 | true          |

  Scenario Outline: Register a participant unsuccessfully
    When a new participant attempts to register with email "<email>", password "<password>", name "<name>", emergency contact "<emergencyContact>", number of weeks "<nrWeeks>", week available from "<weeksAvailableFrom>", week available until "<weeksAvailableUntil>", and lodge required "<lodgeRequired>" (g1)
    Then a participant account shall not exist with email "<email>", password "<password>", name "<name>", emergency contact "<emergencyContact>", number of weeks "<nrWeeks>", week available from "<weeksAvailableFrom>", week available until "<weeksAvailableUntil>", and lodge required "<lodgeRequired>" (g1)
    Then the number of participants shall be "3" (g1)
    Then the system shall raise the error "<error>" (g1)

    Examples: 
      | email           | password | name | emergencyContact | nrWeeks | weeksAvailableFrom | weeksAvailableUntil | lodgeRequired | error                                                                                                |
      | manager@btp.com | password | User | (555)555-5555    |       5 |                  2 |                   6 | true          | Email cannot be manager@btp.com                                                                      |
      | john@email.com  | password | User | (111)111-1111    |       1 |                  1 |                  10 | true          | Email already linked to a guide account                                                              |
      | peter@email.com | password | User | (111)111-1111    |       1 |                  1 |                  10 | true          | Email already linked to a participant account                                                        |
      | user@ mail.ca   | password | User | (111)222-333     |       1 |                  8 |                   9 | true          | Email must not contain any spaces                                                                    |
      | user@mail@y.ca  | password | User | (111)222-333     |       1 |                  3 |                  10 | true          | Invalid email                                                                                        |
      | kyle@email.     | password | User | (111)222-333     |       1 |                  3 |                  10 | true          | Invalid email                                                                                        |
      | user@mail       | password | User | (111)222-333     |       1 |                  3 |                  10 | true          | Invalid email                                                                                        |
      | @mail.ca        | password | User | (111)222-333     |       1 |                  3 |                  10 | true          | Invalid email                                                                                        |
      | user@.ca        | password | User | (111)222-333     |       1 |                  3 |                  10 | true          | Invalid email                                                                                        |
      |                 | password | User | (111)222-333     |       5 |                  2 |                  10 | false         | Email cannot be empty                                                                                |
      | user@mail.ca    |          | User | (555)555-5555    |       5 |                  2 |                  10 | false         | Password cannot be empty                                                                             |
      | user@mail.ca    | password |      | (555)555-5555    |       5 |                  2 |                  10 | false         | Name cannot be empty                                                                                 |
      | user@mail.ca    | password | User |                  |       5 |                  2 |                  10 | false         | Emergency contact cannot be empty                                                                    |
      | user@mail.ca    | password | User | (555)555-5555    |       0 |                  5 |                   8 | true          | Number of weeks must be greater than zero                                                            |
      | user@mail.ca    | password | User | (555)555-5555    |      11 |                  5 |                   8 | true          | Number of weeks must be less than or equal to the number of biking weeks in the biking season        |
      | user@mail.ca    | password | User | (555)555-5555    |       3 |                  5 |                   6 | true          | Number of weeks must be less than or equal to the number of available weeks                          |
      | user@mail.ca    | password | User | (555)555-5555    |       3 |                  6 |                   5 | true          | Week from which one is available must be less than or equal to the week until which one is available |
      | user@mail.ca    | password | User | (555)555-5555    |       3 |                  0 |                   6 | true          | Available weeks must be within weeks of biking season (1-10)                                         |
      | user@mail.ca    | password | User | (555)555-5555    |       3 |                 11 |                   6 | true          | Available weeks must be within weeks of biking season (1-10)                                         |
      | user@mail.ca    | password | User | (555)555-5555    |       3 |                 -1 |                   0 | true          | Available weeks must be within weeks of biking season (1-10)                                         |
      | user@mail.ca    | password | User | (555)555-5555    |       3 |                  6 |                  11 | true          | Available weeks must be within weeks of biking season (1-10)                                         |

  Scenario Outline: Delete a participant successfully
    When the manager attempts to delete the participant with email "<email>" (g1)
    Then a participant account shall not exist with email "<email>" (g1)
    Then the number of participants shall be "<numberOfParticipants>" (g1)

    Examples: 
      | email                | numberOfParticipants |
      | peter@email.com      |                    2 |
      | tyler@email.com      |                    2 |
      | usernotfound@mail.ca |                    3 |

  Scenario Outline: Successfully delete a participant that does not exist but guide exists
    When the manager attempts to delete the participant with email "<email>" (g1)
    Then a guide account shall exist with email "<email>" (g1)
    Then the number of guides shall be "2" (g1)
    Then the number of participants shall be "3" (g1)

    Examples: 
      | email          |
      | jeff@email.com |
      | john@email.com |

  Scenario: Successfully delete a participant that does not exist but manager exists
    When the manager attempts to delete the participant with email "manager" (g1)
    Then a manager account shall exist with email "manager" (g1)
    Then the number of guides shall be "2" (g1)
    Then the number of managers shall be "1" (g1)
    Then the number of participants shall be "3" (g1)

  Scenario Outline: Successfully delete a participant that has requested a piece of gear or combo
    Given the following participants request the following pieces of gear (g1)
      | email           | gear     | quantity |
      | peter@email.com | snowshoe |        1 |
      | peter@email.com | backpack |        2 |
    Given the following participants request the following combos (g1)
      | email           | gear        | quantity |
      | peter@email.com | small combo |        1 |
      | tyler@email.com | large combo |        2 |
      | mary@email.com  | large combo |        1 |
    When the manager attempts to delete the participant with email "<email>" (g1)
    Then a participant account shall not exist with email "<email>" (g1)
    Then the number of participants shall be "2" (g1)
    Then the piece of gear with name "<gearName>" shall be requested by "<numberOfParticipantsForGear>" (g1)
    Then the combo with name "<comboName>" shall be requested by "<numberOfParticipantsForCombo>" (g1)

    Examples: 
      | email           | gearName | numberOfParticipantsForGear | comboName   | numberOfParticipantsForCombo |
      | peter@email.com | snowshoe |                           0 | small combo |                            0 |
      | peter@email.com | backpack |                           0 | large combo |                            2 |
      | tyler@email.com | backpack |                           1 | large combo |                            1 |
      | mary@email.com  | snowshoe |                           1 | large combo |                            1 |
