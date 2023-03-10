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

## Features

## Known issues

## Change logs

## What did you struggle with?
