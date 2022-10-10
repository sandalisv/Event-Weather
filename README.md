# Event-Weather
This a event management system where user can add , view , list all and delete a event.

### 1. Clone or Unzip the project.
		github clone url - https://github.com/sandalisv/Event-Weather.git

### 2. Import the project into a IDE (IntelliJ Idea steps are described).

		right click the pom.xml file -> open with -> select IntelliJ -> Click on "New Window" in popup
	
### 3. Click on refresh icon in the maven tab.
		view -> tool windows -> maven -> click on the refresh icon.


### 4. Import src/main/resources/DbCreation.sql into MySQL Workbench and run the file to create the schema and tables.


### 5. Update database credential and other configuration into application.properties available in src/main/java/resources


```
#spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/event
spring.datasource.username=root
spring.datasource.password=<YOUR PASSWORD>
#spring.datasource.tomcat.max-wait=20000
#spring.datasource.tomcat.max-active=50
#spring.datasource.tomcat.max-idle=20
#spring.datasource.tomcat.min-idle=15

spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQLDialect
spring.jpa.properties.hibernate.id.new_generator_mappings = false
spring.jpa.properties.hibernate.format_sql = true

logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE

```
### NOTE: replace <YOUR PASSWORD> with your database root password.

### 6. Import the guest details in GuestInsert.sql in src/main/resources to MySQL Workbench and run the sql file to insert guests to guest table.

### NOTE: My openweathermap key is hard coded in the code. Please replace it if needed in EventServiceImpl.java (Change APP_ID value with your key).

### 7. Right click on Application.java file and run Java Application


### 8. Once Sprint Boot Application will be started successfully then we can call following Endpoints by using POSTMAN.


### 9. To Create New Event use following url with POST Request

```
  http://localhost:8080/eventManagement/events
  
```
### set request body as raw with JSON payload

```
    {
        "event_name": "M2",
        "date": "2022-10-10",
        "start_time" : "03:30:00",
        "end_time" : "18:30:00",
        "city": "Berlin",
        "country": "DE",
        "guestSet": [
            {
                "guest_id": 4,
                "guest_name": "Harry Potter",
                "age": 17,
                "email": "harry.potter@gmail.com",
                "mobile": "0000011014"
            },
			{
                "guest_id": 5,
                "guest_name": "Ron Weasley",
                "age": 17,
                "email": "ron.w@gmail.com",
                "mobile": "000001105"
            },
            {
                "guest_id": 6,
                "guest_name": "Hermoine Granger",
                "age": 17,
                "email": "hermoine.g@gmail.com",
                "mobile": "000001106"
            }
        ]
}
```


### 10. To list All events call following endpoint with GET Request

```
  http://localhost:8080/eventManagement/events
```


### 11. To get an event by ID call following endpoint with GET Request

```
  http://localhost:8080/eventManagement/events/<id>
```


### 12. To delete an event by ID call following endpoint with DELETE Request

```
http://localhost:8080/eventManagement/events/<id>

```


### NOTE - Replace <id> with actual id 

### NOTE - For more details about assumptions please refer "EventManagement" word file in current folder.

### NOTE - You can import the postman collection "EventManagement.postman_collection.json" in current folder and edit JSON body and <id> accordingly to try the application.




