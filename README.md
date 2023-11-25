# Spring Boot Database Connection
A simple project made with Spring Boot in IntelliJ designed to demonstrate connecting to a MySQL database. The program will create a messges table if one doesn't exist and ask the user to input 3 messages into the console. The program will then save the messagess to the messsages table (along with an auto-incrementing ID and timestamp) and print what was saved. 

The main files are in src/main/java/com.example.connectdatabase

## Example response:
````
messages
Type out a message to add:
Hello!
1 row updated
Type out a message to add:
Hi Friend!
1 row updated
Type out a message to add:
Looks like this is working! :D
1 row updated
id: 1, message: Hello!, time: 2023-11-25 00:24:20
id: 2, message: Hi Friend!, time: 2023-11-25 00:24:28
id: 3, message: Looks like this is working! :D, time: 2023-11-25 00:24:42
Program finished running
````