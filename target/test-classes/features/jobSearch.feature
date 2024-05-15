Feature: NHS Job Search Functionality
    
 # Background:
   #Given I open the NHS Job Search website homepage
    #When I verify the title of the homepage
    #Then the title should be "Apply on NHS Jobs"
    #And I should see some important Links on the homepage
    #And I should see buttons presents on the homepage
    #And I should see "Search for jobs" and "Your applications" section displayed


  Scenario Outline: Job Search using Search Link (Go to search) on NHS Jobs Website
    Given I am on the NHS Jobs website homepage
    When I verify the title of the homepage
    When I click on the Go to search link
    And I enter "<JobTitle>" in the search box
    And I select "<location>" from the location dropdown
    And I click on the Search button
    Then I should see search results for jobs matching "<JobTitle>" and "<location>"

    Examples:
      | JobTitle        | location   |
      | Nurse       | Sunderland     |


  Scenario Outline: Sort NHS Job Search Results by newest Date Posted
    Given I am on the NHS Jobs website homepage
    When I click on the Go to search link
    And I enter "<JobTitle>" in the search box
    And I select "<location>" from the location dropdown
    And I click on the Search button
    When I choose to sort the results by date posted
    Then the search results should be rearranged to display the most recently posted jobs first
    Examples:
      | JobTitle        | location   |
      | Nurse       | Sunderland     |
  
    
  
  
  
  
