# CourseFinderApp

This project was generated with Angular CLI 9.1.15 and Apache Maven 3.6.3.

The aim of this project is to help students find Undergraduate Courses based on their hobbies/interests.

The backend Database was created using PostgreSql. You will need to create a dummy database to test this project.
The ER and UML diagrams are attached as image files in the root directory.

## Major Dependencies

Dependencies are as follows:

1. Java 12
1. Node v14.15.4
1. Npm v6.14.10
1. Angular CLI v9.1.15
1. Maven v3.6.3

## Running the Project

There are two parts of this Project:

1. Running the backend:
    1. On importing or pulling the repo, you will need to simply run `maven clean install` in the Java terminal. 
    This will automatically install all the dependencies present in the pom.xml file. This might take a while.
    1. After the above step is completed, simply run the main application `src\main\java\com\integradev\unicoursefinder\UniCourseFinderApplication.java`
1. Hosting the User Interface:
    1. Run `npm install`, which will install all the front end dependencies like Bootstrap, etc.
    1. Once the application is up and running, run `ng serve` in the terminal which will host the Web Service on a local server.
    1. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

