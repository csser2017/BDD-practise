

@logon
Feature: logon
As a user 163 mailbox user ,i can logon the mail successfully

@163logon
Scenario: verify user can logon successfully with valid password and username
Given I have 163 mailbox account 
|username  |password  |
|XXXXX@qq.com|*******|
When I logon successfully with the account
|username  |password  |
|XXXXX@qq.com|*******|
Then I should see below welcome message on the mailbox 
|msg|
|110107415@qq.com|

