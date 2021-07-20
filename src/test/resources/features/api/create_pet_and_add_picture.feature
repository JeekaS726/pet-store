Feature: Pet Store

  Scenario Outline: Create a pet and add a picture for <petName>
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
    Then pet image will be uploaded "/pet/{petId}/uploadImage"
      | fileName | <fileName> |

    Examples:
      | petId | categoryId | categoryName | petName | photoUrl | tagId  | tagName | status    | fileName    |
      | 12345 | 12345      | terrier      | Woodie  | url      | 123456 | WD      | available | terrier.png |
      | 12346 | 12346      | husky        | Doodie  | url      | 123457 | DD      | pending   | husky.png   |