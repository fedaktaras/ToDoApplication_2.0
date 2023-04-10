# ToDoApplication_2.0
***ToDoApplication_2.0*** is a Spring REST API App that functions like a Trello board.
It has two entities, two rest controllers, and two repositories.
The application allows users to create lists (ListWithItems) and add cards (ListItems) to them. 
Users can drag and drop to change the order of the lists and cards, and they can also move cards from one list to another.

![How it works.](C:\Users\taras\IdeaProjects\ToDoApplication\ToDoApplication_2.0.png)

## Entities
The application has two entities: ListWithItems and ListItem.

+ **ListWithItems**: Represents a list in the Trello board.
    It has a title and a list of ListItems.
+ **ListItem**: Represents a card in the Trello board.
  It has a title, description, a link to the previous and next cards, and a reference to the ListWithItems it belongs to.

## Rest Controllers
The application has two REST controllers: *ListWithItemsController* and *ListItemController.*

## Technologies Used
+ Java
+ Spring Boot
+ Spring Data JPA
+ MySQL

## Setup
+ Clone the repository.
+ Open the project in an IDE.
+ Configure the MySQL database settings in the application.properties file. (Create-drop and initializer by default)
+ Build and run the application.


## Postman Configuration
You can import the ToDoApplication_2.0.postman_collection.json file into Postman to quickly test the endpoints.

## Update Endpoints (coming soon)
You can add additional update endpoints to the application by creating new methods in the REST controllers and updating the corresponding services and repositories. Few more endpoint needed.

## Tests (coming soon)
You can create tests to test the application by creating new test classes in the src/test/java directory and using tools like JUnit and Mockito to write unit and integration tests.