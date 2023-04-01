Feature: View Snow Shoe Tour (g2)
As a manager, I want to view the snowshoe tours so that I can keep track of the participants' and guides' activities

  Background: 
    Given the following SnowShoeTour system exists (g2)
      | startDate  | nrWeeks | priceOfGuidePerWeek |
      | 2024-03-13 |      10 |                 100 |
    Given the following pieces of gear exist in the system (g2)
      | name       | pricePerWeek |
      | snowshoe   |           25 |
      | pole       |          150 |
      | backpack   |           19 |
      | flashlight |           15 |
    Given the following combos exist in the system (g2)
      | name        | discount | items                  | quantity |
      | small combo |       10 | pole,snowshoe          |      1,2 |
      | large combo |       25 | pole,snowshoe,backpack |    1,2,2 |
    Given the following guides exist in the system (g2)
      | email          | password | name | emergencyContact |
      | jeff@email.com | pass1    | Jeff | (555)555-5555    |
      | john@email.com | pass2    | John | (444)444-4444    |
    Given the following participants exist in the system (g2)
      | email           | password | name  | emergencyContact | nrWeeks | weeksAvailableFrom | weeksAvailableUntil | lodgeRequired |
      | peter@email.com | pass1    | Peter | (666)555-5555    |       1 |                  1 |                   2 | true          |
      | tyler@email.com | pass2    | Tyler | (777)444-4444    |       2 |                  2 |                   5 | false         |
      | mary@email.com  | pass3    | Mary  | (555)666-6666    |       1 |                  1 |                   2 | false         |
    Given the following participants request the following pieces of gear (g2)
      | email           | gear     | quantity |
      | peter@email.com | snowshoe |        1 |
      | peter@email.com | backpack |        2 |
    Given the following participants request the following combos (g2)
      | email           | gear        | quantity |
      | peter@email.com | small combo |        1 |
      | tyler@email.com | large combo |        2 |
      | mary@email.com  | large combo |        1 |
    Given the following snowshoe tours exist in the system (g2)
      | id | startWeek | endWeek | participants                   | guide          |
      |  1 |         1 |       1 | peter@email.com,mary@email.com | jeff@email.com |
      |  2 |         2 |       3 | tyler@email.com                | jeff@email.com |

  Scenario: Successfully view snowshoe tour 1
    When the manager attempts to view the snowshoe tour with id "1" (g2)
    Then the following snowshoe tour information shall be provided (g2)
      | id | startWeek | endWeek | guideEmail     | guideName | totalCostForGuide | participantsEmail              | participantsName | totalCostsForBookableItems | totalCostsForSnowShoeTour |
      |  1 |         1 |       1 | jeff@email.com | Jeff      |               100 | peter@email.com,mary@email.com | Peter,Mary       |                    243,238 |                   343,338 |

  Scenario: Successfully view snowshoe tour 2
    When the manager attempts to view the snowshoe tour with id "2" (g2)
    Then the following snowshoe tour information shall be provided (g2)
      | id | startWeek | endWeek | guideEmail     | guideName | totalCostForGuide | participantsEmail | participantsName | totalCostsForBookableItems | totalCostsForSnowShoeTour |
      |  2 |         2 |       3 | jeff@email.com | Jeff      |               200 | tyler@email.com   | Tyler            |                        952 |                      1152 |
