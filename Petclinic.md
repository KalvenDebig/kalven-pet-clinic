# Spring Petclinic

* Sample application designed to show how the Spring application frameworks can be used to build simple, but powerful database-oriented applications.  

* Demonstrate the use of Spring's core functionality:  
  * JavaBeans based application configuration using inversion of Control (IoC)
  * Model View Controller (MVC) web Presentation Layer
  * Practical database access through JDBC, Java Persistence API (JPA) or Spring Data JPA  
  * Application monitoring based on JMX
  * Declarative Transaction Management using AOP 
  * Data Validation that supports but is not dependent on the Presentation Layer  

## Data Access

VisitRepository

JdbcVisit Repositorylmpl  

JpaVisit Repositorylmpl

SpringData VisitRepository 

In order to select which implementation should be used:  
1. select the appropriate bean profile inside PetclinicInitializer (jdbc, jpa or spring-data-jpa)  
2. or use the -Dspring.profiles.active=jdbc VM option

## Database 

* Supports HSQLDB(default), MySQL, PostgreSQL 
* Connections parameters and drivers are declared into `Maven` profiles  
* DDL and DML SQL scripts for each database vendors:  

Properties that control the population of schema and data for a new data source  
jdbc.initLocation=classpath:db/${db.scipt}/initDB.sql  
jdbc.dataLocation=classpath:db/${db.scipt}/populateDB.sql  

data-access.properties  

* How to start Spring Petclinic with a MySQL database?  

docker run --name mysql-petclinic -e MYSQL_ROOT_PASSWORD=petclinic -e MYSQL_DATABASE=petclinic -p 3306:3305 mysql:5.7.8  

mvn tomcat7:run-war -P MySQL

## Bean profiles  

business-config.xml (3profiles)  
1. default(JPA)
2. jdbc
3. Spring Data JPA  

```
<beans profile="jpa,spring-data-jpa">
	<bean id="entityManagerFactory" ...>
</beans>
```  

Inside Junit tests  
```java
@ContextConfiguration(locations = ...) 
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("jpa")
public class ClinicServiceJpaTests ... {
	// ...
}
```


Inside PetclinicInitializer.java
```java
XmlWebApplicationContext context = new XmlWebApplicationContext();
context.setConfigLocations(...);
context.getEnvironment().setDefaultProfiles("jpa");
```

No configuration needed incase you with to use the default profile (jpa)  

## Maven Release Plugin
Uses this package to release a version of your application



