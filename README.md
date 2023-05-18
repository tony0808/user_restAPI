# REST API with Spring Boot and H2 Database

## Introduction
This documentation is for a RESTful API service built with Spring Boot and H2 database. 
This API allows you to perform CRUD (Create, Read, Update, Delete) operations on the `Person` entity. 
The `Person` entity represents individuals with fields including username, email, firstname, and lastname.

## H2 Database
H2 is an in-memory and embedded SQL database that can be used in Spring Boot applications. 
It provides a lightweight and efficient database solution for development and testing purposes.

## Download and Setup
1. Ensure that you have your IntelliJ installed on your system.
2. Open your IDE and navigate to the workspace or the desired location where you want to clone the project.
3. Clone the GitHub repository within your IDE:
    - Select "Get from Version Control" on the welcome screen or go to `VCS -> Get from Version Control`.
    - Enter the repository URL [https://github.com/tony0808/user_restAPI](https://github.com/tony0808/user_restAPI)
    - Choose the destination folder and click "Clone" to download the project.

## Run 
1. Open the project in Intellij IDE.
2. Right-click on the pom.xml -> Maven -> reload project. This will dowload the dependencies.
3. Run the application:
    - Go to src -> main -> java -> com.examples.demo.
    - Run the DemoApplication.java.

## Access the API
Once the application is running, you can access the REST API using a tool of your choice, such as cURL, Postman, or a web browser.
The API is now accessible at `http://localhost:8080`.

## API Endpoints

The API provides the following endpoints to perform CRUD operations on the `Person` entity:

### Get All People
- URL: `/people`
- Method: GET
- Description: Retrieve a list of all the people in the database
- Response Format: JSON array
- Response Body Example:
  ```json
  [
    {
      "id": 1,
      "username": "martin123",
      "email": "martin@cinema.com",
      "firstname": "Martin",
      "lastname": "Scorcese"
    },
    {
      "id": 2,
      "username": "quentin123",
      "email": "quentin@cinema.com",
      "firstname": "Quentin",
      "lastname": "Tarantino"
    },
    ...
  ]

### Get One Person
- URL: `/person/{id}`
- Method: GET
- Description: Retrieve a person by their ID
- Path Parameter: `id` - ID of the person to retrieve
- Response Format: JSON object
- Response Body Example:
  ```json
  {
  "id": 1,
  "username": "quentin123",
  "email": "quentin@cinema.com",
  "firstname": "Quentin",
  "lastname": "Tarantino"
}

### Create Person
- URL: `/person`
- Method: POST
- Description: Create a new person
- Request Format: JSON object
- Request Body Example:
  ```json
  {
  "username": "quentin123",
  "email": "quentin@cinema.com",
  "firstname": "Quentin",
  "lastname": "Tarantino"
  }
- Response Format: JSON object
- Response Body Example:
  ```json
  {
  "id": 3,
  "username": "quentin123",
  "email": "quentin@cinema.com",
  "firstname": "Quentin",
  "lastname": "Tarantino"
  }
  
### Update Username Field
- URL: `/person/{id}/username`
- Method: PUT
- Description: Update username of a person entity
- Path Parameter: `id` - ID of the person to be updated
- Request Format: JSON object
- Request Body Example:
    ```json
    {
    "username": "newUsername",
    "email": "quentin@cinema.com",
    "firstname": "Quentin",
    "lastname": "Tarantino"
    }
- Response Format: JSON object
- Response Body Example:  
    ``` json
    {
    "id": 3,
    "username": "newUsername",
    "email": "quentin@cinema.com",
    "firstname": "Quentin",
    "lastname": "Tarantino"
    }
    
 ### Update Email Field
- URL: `/person/{id}/email`
- Method: PUT
- Description: Update email of a person entity
- Path Parameter: `id` - ID of the person to be updated
- Request Format: JSON object
- Request Body Example:
    ```json
    {
    "username": "quentin123",
    "email": "newEmail",
    "firstname": "Quentin",
    "lastname": "Tarantino"
    }
- Response Format: JSON object
- Response Body Example:  
    ``` json
    {
    "id": 3,
    "username": "quentin123",
    "email": "newEmail",
    "firstname": "Quentin",
    "lastname": "Tarantino"
    }

 ### Update Firstname Field
- URL: `/person/{id}/firstname`
- Method: PUT
- Description: Update firstname of a person entity
- Path Parameter: `id` - ID of the person to be updated
- Request Format: JSON object
- Request Body Example:
    ```json
    {
    "username": "quentin123",
    "email": "quentin@cinema.com",
    "firstname": "newfirstname",
    "lastname": "Tarantino"
    }
- Response Format: JSON object
- Response Body Example:  
    ``` json
    {
    "id": 3,
    "username": "quentin123",
    "email": "quentin@cinema.com",
    "firstname": "newfirstname",
    "lastname": "Tarantino"
    }

 ### Update Lastname Field
- URL: `/person/{id}/lastname`
- Method: PUT
- Description: Update lastname of a person entity
- Path Parameter: `id` - ID of the person to be updated
- Request Format: JSON object
- Request Body Example:
    ```json
    {
    "username": "quentin123",
    "email": "quentin@cinema.com",
    "firstname": "Quentin",
    "lastname": "newLastname"
    }
- Response Format: JSON object
- Response Body Example:  
    ``` json
    {
    "id": 3,
    "username": "quentin123",
    "email": "quentin@cinema.com",
    "firstname": "Quentin",
    "lastname": "newLastname"
    }

 ### Update All Fields
- URL: `/person/{id}`
- Method: PUT
- Description: Update all fields of a person entity
- Path Parameter: `id` - ID of the person to be updated
- Request Format: JSON object
- Request Body Example:
    ```json
    {
    "username": "newUsername",
    "email": "newEmail",
    "firstname": "newFirstname",
    "lastname": "newLastname"
    }
- Response Format: JSON object
- Response Body Example:  
    ``` json
    {
    "id": 3,
    "username": "newUsername",
    "email": "newEmail",
    "firstname": "newFirstname",
    "lastname": "newLastname"
    }
 
### Delete Person
- URL: `/person/{id}`
- Method: DELETE
- Description: Delete a person by their id
- Path Parameter: `id` - ID of the person to be deleted

## Error Handling

### Validation Errors

If a request fails validation, the API returns a `400 Bad Request` status code with detailed error messages. 
The validation is performed for the username and email fields. 
The following error handler is responsible for handling validation exceptions:

    ```java
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    Map<String, String> validationExceptionHandler(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
    
An example response for a validation error would look like: 
    ```json 
    HTTP/1.1 400 Bad Request
    Content-Type: application/json

    {
      "username": "Username is required",
      "email": "Email is required"
    }

### Person Not Found

If a request is made for a person that does not exist, the API returns a `404 Not Found` status code with an error message. 
The following exception handler is responsible for handling the `PersonNotFoundException`:

    ```java
    @ControllerAdvice
    public class PersonNotFoundAdvice {
        @ResponseBody
        @ExceptionHandler(PersonNotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        String personNotFoundHandler(PersonNotFoundException ex) {
            return ex.getMessage();
        }
    }

An example response for a person not found error would look like:

 ```json 
  HTTP/1.1 404 Not Found
  Content-Type: text/plain

  Could not find person with id: <{id}>.



