# Hotels Rest API Example

## How to run
run with Maven (requires Maven and Java8)  
`mvn package spring-boot:run`

or (requires Java8 only)  
`java -jar target/hotels-rest-0.0.1-SNAPSHOT.jar`

## How to use
### Web UI
`localhost:8080` - Web applicatin main page  
`localhost:8080/admin.html` - admin application, FireFox and Edge show needed credentials.

### Rest API  
`localhost:8080/api/hotels/` - shows all available hotels, JSON format  
`localhost:8080/api/hotels/{id}` - shows selected hotel  
`localhost:8080/api/hotels/{id}/rooms/` - all available rooms in selected hotel  
`localhost:8080/api/hotels/{id}/plans/` - get Arrival Plans<sup>tm</sup> for selected hotel  
`localhost:8080/api/hotels/{hotelId}/rooms/{roomId}` - shows selected room info  
`localhost:8080/api/hotels/{hotelId}/rooms/{roomId}/reserve` - reserve selected room, POST method only  
`localhost:8080/api/search/` - search for available rooms, GET only  

#### Admin part (needs admin privileges)
`localhost:8080/api/hotels/` - add a brand new hotel, POST method  
`localhost:8080/api/hotels/{id}` - update selected hotel, PUT method  
`localhost:8080/api/hotels/{id}` - delete selected hotel, DELETE method  
`localhost:8080/api/hotels/{id}/rooms/` - add a brand new room to selected hotel, POST method  
`localhost:8080/api/hotels/{hotelId}/rooms/{roomId}` - update selected room, PUT method  
`localhost:8080/api/hotels/{hotelId}/rooms/{roomId}` - delete selected room, DELETE method  

## TODO
- Add pagination especialy for Search page results  
- Search page: handle 'Doesn't matter' value for bollean values (checkboxes)  
- Improve navigation between pages  

## Known issues
Is not possible to authorize 'admin' user and JavaScript application at the same time in FF, in Chrome works well.
