Feature: Add and Remove Gear for Combo (g8)
As a manager, I want to add and remove gear for combos so that participants can rent them

  Background: 
    Given the following SnowShoeTour system exists (g8)
      | startDate  | nrWeeks | priceOfGuidePerWeek |
      | 2024-03-13 |      10 |                 100 |
    Given the following pieces of gear exist in the system (g8)
      | name       | pricePerWeek |
      | snowshoe   |           25 |
      | pole       |          150 |
      | backpack   |           19 |
      | flashlight |           15 |
    Given the following combos exist in the system (g8)
      | name        | discount | items                  | quantity |
      | small combo |       10 | pole,snowshoe          |      1,2 |
      | large combo |       25 | pole,snowshoe,backpack |    1,2,2 |

  Scenario Outline: Add a piece of gear to a combo successfully
    When the manager attempts to add a piece of gear with name "<gearName>" to the combo with name "<comboName>" (g8)
    Then a piece of gear shall exist with name "<gearName>" and quantity "<quantity>" for the combo with name "<comboName>" (g8)
    Then the number of pieces of gear for the combo with name "<comboName>" shall be "<numberOfGearInCombo>" (g8)
    Then the number of combos shall be "2" (g8)

    Examples: 
      | gearName   | quantity | comboName   | numberOfGearInCombo |
      | backpack   |        1 | small combo |                   3 |
      | backpack   |        3 | large combo |                   3 |
      | pole       |        2 | small combo |                   2 |
      | pole       |        2 | large combo |                   3 |
      | flashlight |        1 | small combo |                   3 |
      | flashlight |        1 | large combo |                   4 |

  Scenario Outline: Unsuccessfully add a piece of gear that does not exist to a combo
    When the manager attempts to add a piece of gear with name "<nonExistingGearName>" to the combo with name "<comboName>" (g8)
    Then a piece of gear shall exist with name "<gearName>" and quantity "<quantity>" for the combo with name "<comboName>" (g8)
    Then the number of pieces of gear for the combo with name "<comboName>" shall be "<numberOfGearInCombo>" (g8)
    Then the number of combos shall be "2" (g8)
    Then the system shall raise the error "<error>" (g8)

    Examples: 
      | nonExistingGearName | gearName | quantity | comboName   | numberOfGearInCombo | error                            |
      | boot                | pole     |        1 | small combo |                   2 | The piece of gear does not exist |
      | sunglasses          | snowshoe |        2 | small combo |                   2 | The piece of gear does not exist |
      | boot                | pole     |        1 | large combo |                   3 | The piece of gear does not exist |
      | sunglasses          | snowshoe |        2 | large combo |                   3 | The piece of gear does not exist |
      | sunglasses          | backpack |        2 | large combo |                   3 | The piece of gear does not exist |

  Scenario Outline: Unsuccessfully add a piece of gear to a combo that does not exist
    When the manager attempts to add a piece of gear with name "<gearName>" to the combo with name "<comboName>" (g8)
    Then the number of combos shall be "2" (g8)
    Then the system shall raise the error "<error>" (g8)

    Examples: 
      | gearName | comboName    | error                    |
      | pole     | deluxe combo | The combo does not exist |
      | snowshoe | deluxe combo | The combo does not exist |
      | pole     | combo plus   | The combo does not exist |
      | snowshoe | combo plus   | The combo does not exist |
      | backpack | combo plus   | The combo does not exist |

  Scenario Outline: Remove a piece of gear from a combo successfully
    When the manager attempts to remove a piece of gear with name "<gearName>" from the combo with name "<comboName>" (g8)
    Then a piece of gear shall exist with name "<gearName>" and quantity "<quantity>" for the combo with name "<comboName>" (g8)
    Then the number of pieces of gear for the combo with name "<comboName>" shall be "<numberOfGearInCombo>" (g8)
    Then the number of combos shall be "2" (g8)

    Examples: 
      | gearName | quantity | comboName   | numberOfGearInCombo |
      | snowshoe |        1 | small combo |                   2 |
      | snowshoe |        1 | large combo |                   3 |
      | backpack |        1 | large combo |                   3 |

  Scenario Outline: Remove the last item of a piece of gear from a combo successfully
    When the manager attempts to remove a piece of gear with name "<gearName>" from the combo with name "<comboName>" (g8)
    Then a piece of gear shall not exist with name "<gearName>" for the combo with name "<comboName>" (g8)
    Then the number of pieces of gear for the combo with name "<comboName>" shall be "<numberOfGearInCombo>" (g8)
    Then the number of combos shall be "2" (g8)

    Examples: 
      | gearName | comboName   | numberOfGearInCombo |
      | pole     | large combo |                   2 |
      | boot     | small combo |                   2 |
      | boot     | large combo |                   3 |

  Scenario Outline: Remove a piece of gear from a combo unsuccessfully
    When the manager attempts to remove a piece of gear with name "<gearName>" from the combo with name "<comboName>" (g8)
    Then a piece of gear shall exist with name "<gearName>" and quantity "<quantity>" for the combo with name "<comboName>" (g8)
    Then the number of pieces of gear for the combo with name "<comboName>" shall be "<numberOfGearInCombo>" (g8)
    Then the number of combos shall be "2" (g8)
    Then the system shall raise the error "<error>" (g8)

    Examples: 
      | gearName | quantity | comboName   | numberOfGearInCombo | error                                         |
      | pole     |        1 | small combo |                   2 | A combo must have at least two pieces of gear |

  Scenario Outline: Unsuccessfully remove a piece of gear from a combo that does not exist
    When the manager attempts to remove a piece of gear with name "<gearName>" from the combo with name "<comboName>" (g8)
    Then the number of combos shall be "2" (g8)
    Then the system shall raise the error "<error>" (g8)

    Examples: 
      | gearName | comboName    | error                    |
      | pole     | deluxe combo | The combo does not exist |
      | snowshoe | deluxe combo | The combo does not exist |
      | pole     | combo plus   | The combo does not exist |
      | snowshoe | combo plus   | The combo does not exist |
      | backpack | combo plus   | The combo does not exist |
