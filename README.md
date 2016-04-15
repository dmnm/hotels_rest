# Hotels Rest API Example

## How to run
run with Maven (requires Maven and Java8)  
`mvn package spring-boot:run`

or (requires Java8 only)  
`java -jar target/hotels-rest-0.0.1-SNAPSHOT.jar`

## How to use
`localhost:8080` - Web applicatin main page  
`localhost:8080/admin.html` - admin application, FireFox and Edge show needed credentials.  
`localhost:8080/api/hotels/` - shows all available hotels, JSON format  
`localhost:8080/api/hotels/{id}` - shows selected hotel  
`localhost:8080/api/hotels/{id}/rooms/` - all available rooms in selected hotel  
`localhost:8080/api/hotels/{hotelId}/rooms/{roomId}` - shows selected room info  
`localhost:8080/api/hotels/{hotelId}/rooms/{roomId}/reserve` - reserve selected room, POST method only  

## Known issues
Is not possible to authorize 'admin' user and JavaScript application at the same time in FF, in Chrome works well.
