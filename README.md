# Post-API

{add test badges here, all projects you build from here on out will have tests, therefore you should have github workflow badges at the top of your repositories: Github Workflow Badges}

## Demo & Snippets

## Requirements / Purpose

This project was completed with the goal of helping me improve my skills in Java and gain more confidence bulding and testing APIs with Spring.

The MVP for the project was given by nology and included the following:
Create an API in Java that allows mobile clients to retrieve and add suburb and postcode combinations.
We want you to implement:

- An API that allows mobile clients to retrieve the suburb information by postcode.
- An API that allows mobile clients to retrieve a postcode given a suburb name
- A secured API to add new suburb and postcode combinations (you'll have to work out how this should work)
- Some form of persistence (a database)
- Testing for controller / service layers

## Build Steps

To run this project on your local machine, clone the repository, into a folder of your choice using the command:

`git clone git@github.com:JoanneAlexia/postAPI.git`

The REST API will load suburb and postcode data into a local mySQL database, therefore prior to running the application you will need to create a local mySQL database called post. To do this first ensure that you have mysql installed. You can check this by going to your terminal and running `mysql --version`. The application was tested with a mysql version 8.0.31 local database.
If you do not have mysql installed you can download it using the following link:
https://downloads.mysql.com/archives/community/

Once you have mysql installed you can create a database using an IDE such as mySQL workbench or using the terminal with the command:

```
create database post
```

Run the REST API in using your prefered IDE. The Spring boot dashboad extension can be added and used to run the backend application in Visual Studio Code.

### Running applications

#### API

Run the REST API in using your prefered IDE. The Spring boot dashboad extension can be added and used to run the backend application in Visual Studio Code.

## Design Goals / Approach

The table schema for suburb contains four rows:

- ID
- Suburb name
- Suburb state
- postcode

### An API that allows mobile clients to retrieve the suburb information by postcode.

At endpoint "/suburb/postcode/:postcode" there is a GET method that will retrieve suburb and state information based on postcode. To accomplish this I needed to add a derieved query method to the SuburbRepository interface called findByPostcode which accepted a postcode as an argument.
A list is returned from this method because there are cases where more than one suburb has the same postcode.
To limit the results of the query so that only suburb name and state data would be retrieved, I used interface based projections. I created an interface SuburbNameAndStateOnly that contained a method which returned a string containing suburbName and suburbState in the format "suburbName, suburbState" for example "Stanmore, NSW". The findByPostcode method returned a list of type SuburbNameStateOnly.
If the postcode is not found in the database a Http status of 404 will be returned.

### An API that allows mobile clients to retrieve a postcode given a suburb name

At endpoint "/suburb/suburbName/:suburbName" there is a GET method that will retrieve postcode information based on suburb name alone.
At endpoint "/suburb/suburbName/:suburbName/suburbState/suburbState" there is a GET method that will retrieve apostcode information based on suburb name and state information.
To accomplish this I needed to add two more derieved query method to the SuburbRepository interface called findBySuburbName and findBySuburbNameAndState which accepts a suburb name or suburb name and state information respectively.
A list is returned from both methods because there are cases where a suburb can have more than one postcode. For example, Epping, NSW has a 1710 (post office boxes) and 2121 (delivery area) poscode.
To limit the results of the query I again used interface based projections. I created an interface PostcodeOnly that contained a method which returned the value for postcode.
If the data entered is not found in the database a Http status of 404 will be returned.

### A secured API to add new suburb and postcode combinations (you'll have to work out how this should work)

At the endpoint "/suburb", I added a POST method for creating new entries.
I created a SuburbCreateDTO which contains the content of the request body for the post request. It includes a suburb name, state and postcode.
Annotions were used for input validation to ensure that none of the values were blank or null and that state value contains 3 letters and the postcode contains 4 digits.
Before a new database entry is created, a search is performed for duplicate enteries. If a duplicate entry is found an error will be returned.

## Testing

Unit tests were written for the custom methods added to the repoisitory layer, all methods in the service layer and controller layer.
