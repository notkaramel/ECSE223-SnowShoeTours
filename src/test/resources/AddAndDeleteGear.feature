Feature: Add and Update Gear (g5)
As a manager, I want to add and update pieces of gear so that participants can rent them

Background:
Given the following SnowShoeTour system exists (g5)
| startDate  | nrWeeks | priceOfGuidePerWeek |
| 2024-03-13 |      10 |                 100 |
Given the following pieces of gear exist in the system (g5)
| name       | pricePerWeek |
| snowshoe   |           25 |
| pole       |          150 |
| backpack   |           19 |
| flashlight |           15 |
Given the following combos exist in the system (g5)
| name        | discount | items                  | quantity |
| small combo |       10 | pole,snowshoe          |      1,2 |
| large combo |       25 | pole,snowshoe,backpack |    1,2,2 |

Scenario Outline: Add a piece of gear successfully
When the manager attempts to add a new piece of gear with name "<name>" and price per week "<pricePerWeek>" (g5)
Then a piece of gear shall exist with name "<name>" and price per week "<pricePerWeek>" (g5)
Then the number of pieces of gear shall be "4" (g5)

Examples:
| name       | pricePerWeek |
| glove      |          100 |
| flashlight |           15 |

Scenario Outline: Add a piece of gear unsuccessfully
When the manager attempts to add a new piece of gear with name "<name>" and price per week "<pricePerWeek>" (g5)
Then a piece of gear shall not exist with name "<name>" and price per week "<pricePerWeek>" (g5)
Then the number of pieces of gear in the system shall be "3" (g5)
Then the system shall raise the error "<error>" (g5)

Examples:
| name        | pricePerWeek | error                                                 |
| compass     |          -35 | The price per week must be greater than or equal to 0 |
|             |           35 | The name must not be empty                            |
| snowshoe    |           35 | A piece of gear with the same name already exists     |
| small combo |           30 | A combo with the same name already exists             |

Scenario: Delete a piece of gear successfully
When the manager attempts to delete the piece of gear with name "<name>" (g5)
Then a piece of gear shall not exist with name "<name>" (g5)
Then the number of pieces of gear shall be "<numberOfGear>" (g5)

Examples:
| name     | numberOfGear |
| snowshoe |            3 |
| backpack |            3 |
| glove    |            4 |
| boot     |            4 |

Scenario: Successfully delete a piece of gear that has been requested by a participant
Given the following participants exist in the system (g5)
| email           | password | name  | emergencyContact | nrWeeks | weeksAvailableFrom | weeksAvailableUntil | lodgeRequired |
| peter@email.com | pass1    | Peter | (666)555-5555    |       1 |                  1 |                   2 | true          |
Given the following participants request the following pieces of gear (g5)
| email           | gear     | quantity |
| peter@email.com | snowshoe |        1 |
| peter@email.com | backpack |        2 |
When the manager attempts to delete the piece of gear with name "<name>" (g5)
Then a piece of gear shall not exist with name "<name>" (g5)
Then the number of pieces of gear shall be "3" (g5)
Then the participant with email "<email>" shall have a piece of gear with name "<requestedGearName>" and quantity "<quantity>" (g5)
Then the number of pieces of gear for the participant with email "<email>" shall be "<numberOfRequestedGear>" (g5)

Examples:
| name     | email           | requestedGearName | quantity | numberOfRequestedGear |
| snowshoe | peter@email.com | backpack          |        2 |                     1 |
| backpack | peter@email.com | snowshoe          |        1 |                     1 |

Scenario: Unsuccessfully delete a piece of gear that is in an existing combo
Given the following combos exist in the system (g5)
| name        | discount | items         | quantity |
| small combo |       10 | pole,snowshoe |      1,2 |
When the manager attempts to delete the piece of gear with name "<name>" (g5)
Then a piece of gear shall exist with name "<name>" and price per week "<pricePerWeek>" (g5)
Then the number of pieces of gear shall be "4" (g5)
Then the combo with name "<comboName>" shall have a piece of gear with name "<name>" and quantity "<quantity>" (g5)
Then the number of pieces of gear for the combo with name "<comboName>" shall be "<numberOfGearInCombo>" (g5)
Then the system shall raise the error "The piece of gear is in a combo and cannot be deleted" (g5)

Examples:
| name     | pricePerWeek | comboName   | quantity | numberOfGearInCombo |
| snowshoe |           25 | small combo |        2 |                   2 |
| pole     |          150 | small combo |        1 |                   2 |
