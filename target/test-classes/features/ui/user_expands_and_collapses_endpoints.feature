Feature: Pet store swagger

  Scenario Outline: Validating pet store swagger ui
    Given user navigates to "https://petstore.swagger.io"
    When user clicks on endpoint with text "<endpoint>"
    Then user validates endpoint has "Parameters" and "Responses" details
    And user collapses the "<endpoint>"

    Examples:
      | endpoint                         |

      | post ​/pet​/{petId}​/uploadImage |
      | post ​/pet                       |
      | put ​/pet                        |
      | get ​/pet​/findByStatus          |
      | get ​/pet​/findByTags            |
      | get ​/pet​/{petId}               |
      | post ​/pet​/{petId}              |
      | delete ​/pet​/{petId}            |
      | get ​/store​/inventory           |
      | post ​/store​/order              |
      | get ​/store​/order​/{orderId}    |
      | delete ​/store​/order​/{orderId} |
      | post ​/user​/createWithList      |
      | get ​/user​/{username}           |
      | put ​/user​/{username}           |
      | delete ​/user​/{username}        |
      | get ​/user​/login                |
      | get ​/user​/logout               |
      | post ​/user​/createWithArray     |
      | post ​/user                      |


