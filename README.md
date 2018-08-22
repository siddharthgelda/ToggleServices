# ToggleServices
ToggleService

This project use for feather managerment beetween multipal services, we used two enttiy in the application

1. Services</br>
2. Toggle
  
How to Run  ->

  Some prerequisite for run</br>
  
    1 Java need your machine Java
    2 MVN for compile
    3. My Sql for database setup
    4. Google Chrome PostMan for test
    
After setUp above prerequisite

  1 Clone git repository.
  
  2. Import mysql file in your mysql
  
  3. In application folder you find all aplication code.
  
  4. properties configuration -> In application folder, Open src\main\Resource\application.properties. modify below propertis acording your configuration 
      spring.datasource.url=jdbc:mysql://localhost:3306/toggle_service
      spring.datasource.username=root
      spring.datasource.password=root
 5. Same 4 point, we need config test resource as well for Junit test src\test\Resource\application.properties src\main\Resource\application.properties     
    
