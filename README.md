# Toggle Service
Toggle Managerment Service

This project use for feature managerment between multiple services, we used two enttiy in the application

1. Services</br>
2. Toggle

Total 3 operation are created 
        
        A.  Create service with toggle detail
        
        B.  Update service with toggle status
        
        C.  Get Service
  
How to Run  ->

  Some prerequisite for run</br>
  
    1 Java need your machine Java
    2 MVN for compile
    3. My Sql for database setup
    4. Google Chrome PostMan for test
    
After setup above prerequisite

  1 Clone git repository.
  
  2. Import mysql file in your mysql
  
  3. In application folder you find all aplication code.
  
  4. properties configuration -> In application folder, Open src\main\Resource\application.properties. modify below propertis acording your configuration 
      spring.datasource.url=jdbc:mysql://localhost:3306/toggle_service
      spring.datasource.username=root
      spring.datasource.password=root
 5. Same 4 point, we need config test resource as well for Junit test src\test\Resource\application.properties src\main\Resource\application.properties  
 
 6. Open command prompt in your machine thengo to you clone folder path inside the application folder.
 7. run command -> 
      <br>
      a. mvn dependency:tree
     <br> 
      b. mvn spring-boot:run
      
      In output of command will be lie 
      
          2018-Aug-22 07:13:17.367 INFO  [main] o.s.b.w.e.t.TomcatWebServer - Tomcat started on port(s): 8080 (http) with context path            ''
          2018-Aug-22 07:13:17.417 INFO  [main] c.x.t.Application - Started Application in 6.037 seconds (JVM running for 23.532)
          2018-Aug-22 07:13:17.420 DEBUG [main] c.x.t.Application - --Application Started--
          
8. In port 8080 your application start successfully        

9. Open your google chrome then open postman tool.

10. Total 3 operation are created 
        
        A.  Create service with toggle detail
        
        B.  Update service with toggle status
        
        C.  Get Service
        
11. Please take guide for operation detail from document folder.
 
 
 
 
    
