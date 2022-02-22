Feature: Checking working class heroes tax relief 
@DispenseNow

Scenario: To check the Displense Now button availability and the functionality

Given User is on The Oppenheimer Project Landing page
When User Check the Dispense Now button display and Select
Then Validate the Cash dispensed message 

@valid
Scenario: To check the CSV fileupload function with a valid data and validate the records are added
Given User is on The Oppenheimer Project Landing page
When User select the Browse button to upload the "valid" file
Then Validate "24" records are added

@invalid
Scenario: To check the CSV fileupload function with an invalid data and validate the record is added
Given User is on The Oppenheimer Project Landing page
When User select the Browse button to upload the "invalid" file
Then Validate "0" records are added

@valid
Scenario: To check the natid field is masked from the 5th character onwards
Given User is on The Oppenheimer Project Landing page
When User select the Browse button to upload the "valid" file
Then Verify the natid is masked from the 5th cahracter


@TaxCalculation
Scenario Outline: To chek the taxrelief amount verification for the records
Given User is on The Oppenheimer Project Landing page
When User details <Natid> <salary> <tax> <gender> <DOB>

Examples:
|Natid|salary|tax|gender|DOB|
|123111|10000|3000|M|11012005|
|124111|10000|3000|M|11012004|
|125111|10000|3000|M|11012003|
|126111|10000|3000|M|11011988|
|127111|10000|3000|M|11011987|
|128111|10000|3000|M|11011986|
|129111|10000|3000|M|11011973|
|130111|10000|3000|M|11011972|
|131111|10000|3000|M|11011971|
|132111|10000|3000|M|11011948|
|133111|10000|3000|M|11011947|
|134111|10000|3000|M|11011946|
|223111|10000|3000|F|11012005|
|224111|10000|3000|F|11012004|
|225111|10000|3000|F|11012003|
|226111|10000|3000|F|11011988|
|227111|10000|3000|F|11011987|
|228111|10000|3000|F|11011986|
|229111|10000|3000|F|11011973|
|230111|10000|3000|F|11011972|
|231111|10000|3000|F|11011971|
|232111|10000|3000|F|11011948|
|233111|10000|3000|F|11011947|
|234111|10000|3000|F|11011946|

@TaxDisplayChecking
Scenario: To check the Tax amount displayed is correct
Given User is on The Oppenheimer Project Landing page
When Find the taotal tax relief amount
