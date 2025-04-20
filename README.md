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

# use
We use Postman to demonstrate the typical use cases.

- Find a random strain:
![image](https://github.com/user-attachments/assets/500c7aa9-8506-43f8-ad6c-856457381a75)

- Get a list of possible effects:
![image](https://github.com/user-attachments/assets/b34db801-7120-4eca-bf9e-eda8209886b3)

- Get a list of possible flavours:
![image](https://github.com/user-attachments/assets/c7ef026e-71e0-4b88-8f45-e48f57051d0d)

- Find a specific strain by name:
![image](https://github.com/user-attachments/assets/c75448ca-58ff-4ece-82aa-de04921e0dae)

- If there isn't anything you need in the database, the program will return an empty JSON:
![image](https://github.com/user-attachments/assets/ca620d57-a5f5-4af7-85db-c1a195593841)

- You can find a list of strains with a non-negative rating not larger than 5.0 (higher means better, generally speaking):
![image](https://github.com/user-attachments/assets/f481c1a7-b97d-4db4-acde-c3e8eb73099f)

- You can also cross-over search parameters, for example:
![image](https://github.com/user-attachments/assets/b49c5cac-9497-454f-a4a4-2c905c4a149a)
The above query returns a list of strains with 4.7 marks and with Berry OR Tar flavours.

- Another cross-over example:
![image](https://github.com/user-attachments/assets/12b6fc86-dcc3-4f4a-9ccb-4711f3c6a049)
The above query returns a list of strains with Rose OR Berry flavours AND will make people Sleepy OR Happy. 








