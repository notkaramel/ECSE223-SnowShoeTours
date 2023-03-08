Feature: Update SnowShoeTour Information (g3)
As a manager, I want to update the SnowShoeTour information so that the information is accurate

Background:
Given the following SnowShoeTour system exists (g3)
| startDate  | nrWeeks | priceOfGuidePerWeek |
| 2024-03-13 |      10 |                 100 |

Scenario Outline: Update SnowShoeTour information successfully
When the manager attempts to update the SnowShoeTour information to start date "<startDate>", number of weeks "<nrWeeks>", and price of guide per week "<priceOfGuidePerWeek>" (g3)
Then the SnowShoeTour information shall be start date "<startDate>", number of weeks "<nrWeeks>", and price of guide per week "<priceOfGuidePerWeek>" (g3)

Examples:
| startDate  | nrWeeks | priceOfGuidePerWeek |
| 2024-03-14 |      10 |                 100 |
| 2024-03-13 |       0 |                 100 |
| 2024-03-13 |      10 |                   0 |

Scenario Outline: Update SnowShoeTour information unsuccessfully
When the manager attempts to update the SnowShoeTour information to start date "<startDate>", number of weeks "<nrWeeks>", and price of guide per week "<priceOfGuidePerWeek>" (g3)
Then the SnowShoeTour information shall be start date "2024-03-13", number of weeks "10", and price of guide per week "100" (g3)
Then the system shall raise the error "<error>" (g3)

Examples:
| startDate  | nrWeeks | priceOfGuidePerWeek | error                                                             |
| 2024-01-13 |      -1 |                 100 | The number of riding weeks must be greater than or equal to zero  |
| 2024-01-13 |      10 |                  -1 | The price of guide per week must be greater than or equal to zero |
| 2021-01-13 |      10 |                 100 | The start date cannot be from previous year or earlier            |
