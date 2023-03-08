Feature: Register and Update Guide (g6)
As a guide, I want to register and update my guide account so that I can work on snowshoe tours

Background:
Given the following SnowShoeTour system exists (g6)
| startDate  | nrWeeks | priceOfGuidePerWeek |
| 2024-03-13 |      10 |                 100 |
Given the following guides exist in the system (g6)
| email          | password | name | emergencyContact |
| jeff@email.com | pass1    | Jeff | (555)555-5555    |
| john@email.com | pass2    | John | (444)444-4444    |
Given the following participants exist in the system (g6)
| email           | password | name  | emergencyContact | nrWeeks | weeksAvailableFrom | weeksAvailableUntil | lodgeRequired |
| peter@email.com | pass1    | Peter | (666)555-5555    |       1 |                  1 |                   2 | true          |
| tyler@email.com | pass2    | Tyler | (777)444-4444    |       2 |                  2 |                   5 | false         |

Scenario Outline: Register a guide successfully
When a new guide attempts to register with email "<email>", password "<password>", name "<name>", and emergency contact "<emergencyContact>" (g6)
Then a guide account shall exist with email "<email>", password "<password>", name "<name>", and emergency contact "<emergencyContact>" (g6)
Then the number of guides shall be "3" (g6)

Examples:
| email          | password | name | emergencyContact |
| lisa@email.com | pass1    | Lisa | (666)666-6666    |
| liam@email.com | pass2    | Liam | (777)777-7777    |

Scenario Outline: Register a guide unsuccessfully
When a new guide attempts to register with email "<email>", password "<password>", name "<name>", and emergency contact "<emergencyContact>" (g6)
Then a guide account shall not exist with email "<email>", password "<password>", name "<name>", and emergency contact "<emergencyContact>" (g6)
Then the number of guides shall be "2" (g6)
Then the system shall raise the error "<error>" (g6)

Examples:
| email            | password | name  | emergencyContact | error                                         |
| manager@btp.com  | pass1    | Paul  | (111)111-1111    | Email cannot be manager@btp.com               |
| jeff@email.com   | pass2    | Jeff  | (111)777-7777    | Email already linked to a guide account       |
| peter@email.com  | pass3    | Peter | (555)555-5555    | Email already linked to a participant account |
| bart @ email.com | pass3    | Bart  | (444)666-6666    | Email must not contain any spaces             |
| don@email@y.com  | pass4    | Dony  | (777)555-7777    | Invalid email                                 |
| kyle@email.      | pass5    | Kyle  | (666)777-6666    | Invalid email                                 |
| greg.email@com   | pass6    | Greg  | (777)888-7777    | Invalid email                                 |
| @email.com       | pass7    | Otto  | (111)777-6666    | Invalid email                                 |
| karl@.com        | pass8    | Karl  | (111)777-6661    | Invalid email                                 |
|                  | pass9    | Vino  | (777)888-5555    | Email cannot be empty                         |
| luke@email.com   |          | Luke  | (999)888-5555    | Password cannot be empty                      |
| owen@email.com   | pass10   |       | (888)888-5555    | Name cannot be empty                          |
| noah@email.com   | pass11   | Noah  |                  | Emergency contact cannot be empty             |

Scenario Outline: Delete a guide successfully
When the manager attempts to delete the guide with email "<email>" (g6)
Then a guide account shall not exist with email "<email>" (g6)
Then the number of guides shall be "<numberOfGuides>" (g6)

Examples:
| email          | numberOfGuides |
| jeff@email.com |              1 |
| john@email.com |              1 |
| kyle@email.com |              2 |
| paul@email.com |              2 |

Scenario Outline: Successfully delete a guide that does not exist but participant exists
When the manager attempts to delete the guide with email "<email>" (g6)
Then a participant account shall exist with email "<email>" (g6)
Then the number of guides shall be "2" (g6)
Then the number of participants shall be "2" (g6)

Examples:
| email           |
| peter@email.com |
| tyler@email.com |

Scenario Outline: Successfully delete a guide that does not exist but manager exists
When the manager attempts to delete the guide with email "manager" (g6)
Then a manager account shall exist with email "manager" (g6)
Then the number of guides shall be "2" (g6)
Then the number of managers shall be "1" (g6)
