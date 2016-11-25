# Simple web project based on Spring.

## Parking house solution.

### Requirements:
1. Java 8
2. Maven 3

### How to run application?
1. Download and unzip.
2. Open command prompt and build by running command: "mvn clean install".
3. "/target" folder will contain "parking-house-1.0-SNAPSHOT.war".
4. .war can be deployed to any application server (Tomcat, WildFly).

### What happens if application is started?
1. All data is saved in memory. By every restart application will have fresh data.
2. Mock data can be loaded by making GET request to "/api/mock".

### Implementation and decision descriptions:
1. Money are in cents.
2. Implemented REST api exception handling.

### What is missed and how it should be improved:
1. Implement logging. Slf4j will be better solution.
2. Now api returns model but should return dto objects. Provide dto object and converters (entity -> dto; dto -> entity). Converters can be written manually but "org.modelmapper" can be used also for simple conversions.
3. ee.home.parkinghouse.calculation.FeeCostCalculation.getFees(Date, Date, CustomerType) is very simplified. Provide logic to handle all cases for parking period (day and night cost difference).
4. Parking house settings (prices, times, limits) are located in .properties file. But to implement in future requirement "Today the company operates only one parking house but in the future this will grow" this settings should migrate to database. Parking house settings will be loaded by separate service (like ParkinghouseSetting.loadByName(String parkinghouseName).
5. Implement authorization and authentication.
6. [Swagger](http://www.baeldung.com/swagger-2-documentation-for-spring-rest-api) can be used to describe API calls.
7. Vendor name can be added to REST api then application can support several vendors. Related with parking house settings.
8. REST api and pages are in same module but can be separated.