# DropParkingApp
**Software Required to run Project:**
1. Java 11
2. Maven
3. Any IDE (STS, Eclipse, Intellij)

**Steps to run Project:**
1. Import project into IDE
2. Run Project as Spring Boot Project
  i. Right Click on Project
  ii. Click on Run as
  iii. Select Run as Spring Boot App
  
**Api End Points:**
1. For /login API
    Method: POST
    URL:http://localhost:9218/parking/login
    Request Body: {
                     "password": "password",
                     "userName": "jack@mail.com"
                  }
2. For /park API
    Method: POST 
    URL: http://localhost:9218/parking/park/{licenceNumber}
    Note: change {licenceNumber} with licence number in above url
3. For /slot API
    Method: GET 
    URL: http://localhost:9218/parking/slot/{slotName}
    Note: change {slotName} with slot name in above url
4. For /unpark API
    Method: DELETE 
    URL: http://localhost:9218/parking/unpark/{licenceNumber}
    Note: change {licenceNumber} with licence number in above url

Swagger UI URL: http://localhost:9218/swagger-ui.html
