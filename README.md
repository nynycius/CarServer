# CarServer
CarServer Management with Java

This project is part of the module Concurrent Development from the course BSCH Computing Science at Griffith College 

Build a CarServer using:

-Sockets,
-Locks,
-Semaphores,
-Threads

Requirements: 

A car sales company requires a server to store all its car data and share it 
between its sales personnel. For the purposes of this assignment each car has the 
following attributes: registration, make, price, mileage and forSale (use a boolean- true for forSale and false for sold). Clients can do the following: 

-A sales person can add a new car to the system. 
-Sell a car 
-Request information from the system. 
Sample requests would be cars for sale, cars of a given make, total value of all sales. 

All cars added to the system should be stored in a shared data structure on the 
server. Your task is to build a working model of this system. 
Notes 
1. No log on/off required for users. 
2. For this assignment you should add approximately 15 cars to the system. 
3. Server should use thread pool and semaphores to limit users to 50 
4. All requests to server must be objects 
5. Cars added on server must be thread safe as many clients have conflicting requests 



######################################

TODO: Future improvements 

- Implement th project with a SQL data base
- Create login and log off
