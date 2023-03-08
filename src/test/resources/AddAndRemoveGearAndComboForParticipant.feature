Feature: Add and Remove Gear And Combo for Participant (g7)
As a participant, I want to add and remove gear and combos so that I can use them during my snowshoe tour

Background:
Given the following SnowShoeTour system exists (g7)
| startDate  | nrWeeks | priceOfGuidePerWeek |
| 2024-03-13 |      10 |                 100 |
Given the following pieces of gear exist in the system (g7)
| name     | pricePerWeek |
| snowshoe |           25 |
| pole     |          150 |
| backpack |           19 |
Given the following combos exist in the system (g7)
| name        | discount | items                  | quantity |
| small combo |       10 | pole,snowshoe          |      1,2 |
| large combo |       25 | pole,snowshoe,backpack |    1,2,2 |
Given the following guides exist in the system (g7)
| email          | password | name | emergencyContact |
| jeff@email.com | pass1    | Jeff | (555)555-5555    |
| john@email.com | pass2    | John | (444)444-4444    |
Given the following participants exist in the system (g7)
| email           | password | name  | emergencyContact | nrWeeks | weeksAvailableFrom | weeksAvailableUntil | lodgeRequired |
| peter@email.com | pass1    | Peter | (666)555-5555    |       1 |                  1 |                   2 | true          |
| tyler@email.com | pass2    | Tyler | (777)444-4444    |       2 |                  2 |                   5 | false         |
| mary@email.com  | pass3    | Mary  | (555)666-6666    |       1 |                  1 |                   2 | false         |
Given the following participants request the following pieces of gear (g7)
| email           | gear     | quantity |
| peter@email.com | snowshoe |        1 |
| peter@email.com | backpack |        2 |
Given the following participants request the following combos (g7)
| email           | gear        | quantity |
| peter@email.com | small combo |        1 |
| tyler@email.com | large combo |        2 |
| mary@email.com  | large combo |        1 |

Scenario Outline: Add a piece of gear or combo to a participant successfully
When the manager attempts to add a piece of gear or combo with name "<name>" to the participant with email "<email>" (g7)
Then a piece of gear or combo shall exist with name "<name>" and quantity "<quantity>" for the participant with email "<email>" (g7)
Then the number of pieces of gear or combos for the participant with email "<email>" shall be "<numberOfItemsForParticipant>" (g7)
Then the number of participants shall be "3" (g7)

Examples:
| name        | quantity | email           | numberOfItemsForParticipant |
| backpack    |        3 | peter@email.com |                           3 |
| backpack    |        1 | tyler@email.com |                           2 |
| pole        |        1 | peter@email.com |                           4 |
| pole        |        1 | tyler@email.com |                           2 |
| snowshoe    |        2 | peter@email.com |                           3 |
| snowshoe    |        1 | tyler@email.com |                           2 |
| small combo |        2 | peter@email.com |                           3 |
| small combo |        1 | tyler@email.com |                           2 |
| large combo |        1 | peter@email.com |                           4 |
| large combo |        3 | tyler@email.com |                           1 |

Scenario Outline: Unsuccessfully add a piece of gear or combo that does not exist to a participant
When the manager attempts to add a piece of gear or combo with name "<nonExistingName>" to the participant with email "<email>" (g7)
Then a piece of gear or combo shall exist with name "<name>" and quantity "<quantity>" for the participant with email "<email>" (g7)
Then the number of pieces of gear or combos for the participant with email "<email>" shall be "<numberOfItemsForParticipant>" (g7)
Then the number of participants shall be "3" (g7)
Then the system shall raise the error "<error>" (g7)

Examples:
| nonExistingName | name        | quantity | email           | numberOfItemsForParticipant | error                                     |
| boot            | backpack    |        2 | peter@email.com |                           3 | The piece of gear or combo does not exist |
| sunglasses      | snowshoe    |        1 | peter@email.com |                           3 | The piece of gear or combo does not exist |
| boot            | small combo |        1 | peter@email.com |                           3 | The piece of gear or combo does not exist |
| combo plus      | large combo |        2 | tyler@email.com |                           1 | The piece of gear or combo does not exist |
| deluxe combo    | large combo |        1 | mary@email.com  |                           1 | The piece of gear or combo does not exist |

Scenario Outline: Unsuccessfully add a piece of gear or combo to a participant that does not exist
When the manager attempts to add a piece of gear or combo with name "<name>" to the participant with email "<email>" (g7)
Then the number of participants shall be "3" (g7)
Then the system shall raise the error "<error>" (g7)

Examples:
| name        | email          | error                          |
| pole        | john@email.com | The participant does not exist |
| snowshoe    | john@email.com | The participant does not exist |
| pole        | joe@email.com  | The participant does not exist |
| small combo | joe@email.com  | The participant does not exist |
| large combo | joe@email.com  | The participant does not exist |

Scenario Outline: Remove a piece of gear or combo from a participant successfully
When the manager attempts to remove a piece of gear or combo with name "<name>" from the participant with email "<email>" (g7)
Then a piece of gear or combo shall exist with name "<name>" and quantity "<quantity>" for the participant with email "<email>" (g7)
Then the number of pieces of gear or combos for the participant with email "<email>" shall be "<numberOfItemsForParticipant>" (g7)
Then the number of participants shall be "3" (g7)

Examples:
| name        | quantity | email           | numberOfItemsForParticipant |
| backpack    |        1 | peter@email.com |                           3 |
| large combo |        1 | tyler@email.com |                           1 |

Scenario Outline: Remove the last item of a piece of gear or combo from a participant successfully
When the manager attempts to remove a piece of gear or combo with name "<name>" from the participant with email "<email>" (g7)
Then a piece of gear or combo shall not exist with name "<name>" for the participant with email "<email>" (g7)
Then the number of pieces of gear or combos for the participant with email "<email>" shall be "<numberOfItemsForParticipant>" (g7)
Then the number of participants shall be "3" (g7)

Examples:
| name        | email           | numberOfItemsForParticipant |
| pole        | peter@email.com |                           3 |
| pole        | tyler@email.com |                           1 |
| snowshoe    | peter@email.com |                           2 |
| snowshoe    | tyler@email.com |                           1 |
| small combo | peter@email.com |                           2 |
| small combo | tyler@email.com |                           1 |
| large combo | peter@email.com |                           3 |
| large combo | mary@email.com  |                           0 |

Scenario Outline: Unsuccessfully remove a piece of gear or combo from a participant that does not exist
When the manager attempts to remove a piece of gear or combo with name "<name>" from the participant with email "<email>" (g7)
Then the number of participants shall be "3" (g7)
Then the system shall raise the error "<error>" (g7)

Examples:
| name        | email          | error                          |
| pole        | john@email.com | The participant does not exist |
| snowshoe    | john@email.com | The participant does not exist |
| pole        | joe@email.com  | The participant does not exist |
| small combo | joe@email.com  | The participant does not exist |
| large combo | joe@email.com  | The participant does not exist |
