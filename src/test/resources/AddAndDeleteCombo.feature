Feature: Add and Delete Combo (g4)
As a manager, I want to add and update combos so that participants can rent them

  Background: 
    Given the following SnowShoeTour system exists (g4)
      | startDate  | nrWeeks | priceOfGuidePerWeek |
      | 2024-03-13 |      10 |                 100 |
    Given the following pieces of gear exist in the system (g4)
      | name     | pricePerWeek |
      | snowshoe |           25 |
      | pole     |          150 |
      | backpack |           19 |
    Given the following combos exist in the system (g4)
      | name        | discount | items                  | quantity |
      | small combo |       10 | pole,snowshoe          |      1,2 |
      | large combo |       25 | pole,snowshoe,backpack |    1,2,2 |

  Scenario Outline: Add a combo successfully
    When the manager attempts to add a combo with name "<name>" and discount "<discount>" (g4)
    Then a combo shall exist with name "<name>" and discount "<discount>" (g4)
    Then the number of pieces of gear for the combo with name "<name>" shall be "0" (g4)
    Then the number of combos shall be "3" (g4)

    Examples: 
      | name         | discount |
      | deluxe combo |       40 |
      | combo plus   |       10 |

  Scenario Outline: Add a combo unsuccessfully
    When the manager attempts to add a combo with name "<name>" and discount "<discount>" (g4)
    Then a combo shall not exist with name "<name>" and discount "<discount>" (g4)
    Then the number of combos shall be "2" (g4)
    Then the system shall raise the error "<error>" (g4)

    Examples: 
      | name         | discount | error                                             |
      | deluxe combo |       -1 | Discount must be at least 0                       |
      | combo plus   |      101 | Discount must be no more than 100                 |
      |              |        0 | The name must not be empty                        |
      | snowshoe     |       35 | A piece of gear with the same name already exists |
      | small combo  |       30 | A combo with the same name already exists         |

  Scenario: Delete a combo successfully
    When the manager attempts to delete the combo with name "<name>" (g4)
    Then a combo shall not exist with name "<name>" (g4)
    Then the number of combos shall be "<numberOfCombos>" (g4)

    Examples: 
      | name         | numberOfCombos |
      | small combo  |              1 |
      | large combo  |              1 |
      | deluxe combo |              2 |
      | combo plus   |              2 |

  Scenario: Successfully delete a combo that has been requested by a participant
    Given the following participants exist in the system (g4)
      | email           | password | name  | emergencyContact | nrWeeks | weeksAvailableFrom | weeksAvailableUntil | lodgeRequired |
      | peter@email.com | pass1    | Peter | (666)555-5555    |       1 |                  1 |                   2 | true          |
    Given the following participants request the following combos (g4)
      | email           | combo       | quantity |
      | peter@email.com | small combo |        1 |
      | peter@email.com | large combo |        2 |
    When the manager attempts to delete the combo with name "<name>" (g4)
    Then a combo shall not exist with name "<name>" (g4)
    Then the number of combos shall be "1" (g4)
    Then the participant with email "<email>" shall have a combo with name "<requestedComboName>" and quantity "<quantity>" (g4)
    Then the number of pieces of gear for the participant with email "<email>" shall be "<numberOfRequestedCombos>" (g4)

    Examples: 
      | name        | email           | requestedComboName | quantity | numberOfRequestedCombos |
      | small combo | peter@email.com | large combo        |        2 |                       1 |
      | large combo | peter@email.com | small combo        |        1 |                       1 |
