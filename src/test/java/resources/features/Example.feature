
@tag
Feature: logon
As a user 163 mailbox user ,i can logon the mail successfully

@logon
Scenario: verify user can logon successfully with valid password and username
Given I have 163 mailbox account 
|username  |password  |
|11111@qq.com|1234|
When I logon successfully with the account
|username  |password  |
|11111@qq.com|1233|
Then I should see below welcome message on the mailbox 
|msg|
|11111@qq.com|

