# weedapi

![433375681-0a52c643-6471-49fd-85bf-e2e7e3cf46e4](https://github.com/user-attachments/assets/38325ee4-ebb4-410c-a6d9-44e351437a6e)

This repository is a SpringBoot and PostgreSQL program that hosts a set of Application Program Interfaces (APIs) for providing information of 2154 strains of medical cannabis. Small and medium medical cannabis dispensaries can use this repository to build shop websites or inventory management system, providing they have front-end coding work.

# pre-requisite
- Java Runtime Environment (>=24.0)
- PostgreSQL (>=17.0)
- pgAdmin (>=4.0)

# configuration and deployment
- Clone and change directory into the `weedapi` directory.
- Open pgAdmin in a seperate window. Using the GUI, setup a database (maybe use a paper to record the connection url as `spring.datasource.url`). You should also note down your username (`spring.datasource.username`) and password (`spring.datasource.username`).
- There should be a `public` schema in the database you set up. Copy and paste the 3 SQL scripts in the `src\main\resources\static\script` directory to the pgAdmin SQL query window create 3 tables (`effects, flavours, strains`).
- Back to pgAdmin, right click a table and choose `Import/Export Data`. Import the corresponding CSV following pgAdmin's instruction. Repeat for the other 2 tables.
- Change the `spring.datasource.url, spring.datasource.username, spring.datasource.username` in `src\main\resources\application.properties` with the information you recorded earlier.
- Back to the `weedapi` directory. Run `mvn clean install build` in the terminal and wait for a minute or two.
- Navigate to the `target` folder. There should be a `weedapi-0.0.1-SNAPSHOT.jar`. Run that file to start the SpringBoot service.
