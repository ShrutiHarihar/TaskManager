# TaskManager
A web application to handle task creation and assignment for a Replenishment manager

Requirements
-------------
1. Spring Boot
2. Maven
3. MongoDB running at port 27017 with database taskApp
4. Angular2

How to run
----------
1. Go to folder task_manager_backend and run command 
    - mvn verify spring-boot:run

2. Go to folder task_manager_frontend and run the following commands
	- npm install
	- ng serve -o
	
3. The Task manager opens at location http://localhost:4200/login. 
4. Start by registering an employee in the system for both the roles "manager" and "employee".(In  
   future can be changed for only admin to have ability to do registration).
   
Assumptions
------------
1. The rank of a task can be added while creating tasks. Also, the rank increases as the time of the  
   task undone increases.
2. The rank of task increases in following cases
    - The elapsed time is more than the task recurring time and still, the task is undone.
    - The elapsed time is more than 6 hours since the last update of rank.
3. The recurring assignments are in following categories
    - Every one hour
    - Every three hours
    - Every six hours
    - Every twelve hours
	
	
