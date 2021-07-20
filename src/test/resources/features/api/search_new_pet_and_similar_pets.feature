Feature: Pet Store

  Scenario Outline: Search for new pet and similar pets
    Given pet has category details
      | id   | <categoryId>   |
      | name | <categoryName> |
    And pet has tag details
      | id   | <tagId>   |
      | name | <tagName> |
    And pet has following information
      | id      | name      | photoUrls  | status   |
      | <petId> | <petName> | <photoUrl> | <status> |
    When the pet is created "/pet"
    Then search for new pet "/pet/{petId}"
    And search for similar pets "/findByStatus"

    Examples:
      | petId | categoryId | categoryName | petName | photoUrl | tagId  | tagName | status    |
      | 12347 | 12345      | terrier      | Woodie  | url      | 123456 | WD      | available |
      | 12348 | 12346      | husky        | Doodie  | url      | 123457 | DD      | pending   |