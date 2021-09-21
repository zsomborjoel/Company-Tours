# Company Tours

## How to run the application

1.	Please go into the package folder „company-tours”
2.	Run build: mvn clean package spring-boot:repackage
3.	Run application: java -jar target/vmtours-0.0.1-SNAPSHOT.jar
      How to run tests

1.	Please go into the package folder „company-tours”
2.	Run tests: mvn test
      Additional curl requests while running the app:
      
### Refresh:
      
#### refresh all
```
curl --request POST \
--url http://localhost:8081/tours/refresh \
--user 'admin:admin12' \
--header 'Content-Type: application/json' \
--header 'cache-control: no-cache' \
--data '{"filter": null}'
```

#### refresh filtered
```
curl --request POST \
--url http://localhost:8081/tours/refresh \
--user 'admin:admin12' \
--header 'Content-Type: application/json' \
--header 'cache-control: no-cache' \
--data '{"filter": "history"}'
```

### Query:

#### query all
```
curl --request GET \
-u john:john12 \
http://localhost:8081/tours
```

#### query filtered
```
curl --request GET \
-u john:john12 \
http://localhost:8081/tours?filter=Cheers%20in%20the%20Liget
```

## How to run code coverage mesausrement

1.	After building the app
2.	Go into target folder under app
3.	open target/site/jacoco/index.html in the browser
