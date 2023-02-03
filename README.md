# SOLID Principle of OOD

* S - Single Responsibility Principle
* O - Open Closed Principle
* L - Liskove Substitution Principle
* I - Interface Segregation Principle
* D - Dependency Inversion Principle

## Single Responsibility Principle
* Every class should have a single responsibility.
* There should never be more than one reason for a class to change.
* Your classes should be small. No more than a screen full of code
* Avoid `god` classes.
* Split big classes into smaller classes.

## Open Closed Principle
* Your classes should be open for extension
* But closed for modification
* You should be able to extend a classes behavior, without modifying it.
* Use private variables with getters and setters - Only when you need them
* Use abstract base classes

## Liskove Substitution Principle
* Objects in a program would be replaceable with instances of their subtypes WITHOUT altering the correctness of the program.
* Violations will often fail the "Is a" test
* A Square "Is a" Rectangle
* However, a Rectangle "Is Not" a Square

## Interface Segregation Principle
* Make fine grained interfaces that are client specific
* Many client specific interfaces are better than one "general purpose" interface
* Keep your components focused and minimize dependencies between them
* Notice relationship to the Single Responsibility Principle?
* avoid `god` interfaces

## Dependency Inversion Principle
* Abstraction should not depend upon details
* Details should depend upon abstractions
* Important that higher level and lower level objects depend on the same abstract interaction
* This is not the same as Dependency Injection - which is how objects obtain dependent objects


## Spring DATA JPA
JPA is using CRUDRepository  
Here in this project we can mimic how JPA implement those methods like save(), findByID(), and so on.

## Spring Configuration Options

* XML Based Configuration
    * introduced in Spring Framework 2.0
    * common in legacy Spring Application
    * Newer Spring Application use Java configuration options
    * Still supported in Spring Framework 5.x

* Annotation Based Configuration -- Annotation introduced in Java 5
    * Introduced in Spring Framework 3  
	* Spring beans found via `Component Scans`  
        * A scan of packages for annotated components  
    * Spring Beans are found via class level annotations  
        * @Controller, @Service, @Component, @Repository  
            * More on these later


* Java Based Configuration  
    * Introduced in Spring Framework 3.0  
    * Uses Java classes to define Spring Beans  
    * Configuration classes are defined with @Configuration annotation  
    * Methods returning Spring Beans declared with @Bean annotation  

* Groovy Bean Definition DSL (Domain Specific Language)  
    * Introduced in Spring Framework 4.0  
    * Allows you to declare beans via a Groovy DSL  
        * Borrowed from Grails  

## Spring Framework Stereotypes  
* **Stereotype** - A fixed general image or set of characteristics which represent a particular type of person or thing  
* Spring Stereotypes are class level annotation used to define Spring Beans  
    * When classes annotated with Spring Stereotypes are detected via the component scan, an instance of the class will be added to the Spring context  
* Available Spring Stereotypes - @Component, @Controller, @RestController, @Repository, @Service  


@Component -> {@Controller, @Repository, @Service} (All these three annotations will bring classes to beans, functionally no differences)  
@Controller -> @RestController(Convenience annotation representing @Controller and @ResponseBody)  


|Annotation|Description|
|---|---|
|@Component|Indicates that an annotated class is a "component" and it will be created as a bean|  
|@Controller|indicates that an annotated class has the role of a Spring MVC "Controller"|  
|@RestController|Convenience Annotation which extends @Controller, and adds @ResponseBody|  
|@Repository|Indicates class is a "Repository", originally defined by Domain-Driven Design as "a mechanism for encapsulating storage, retrieval, and search behavior which emulates a collection of objects"|  
|@Service|Indicates that an annotated class is a "Service", originally defined by Domain-Driven Design as "an operation offered as an interface that stands alone in the model, with no encapsulated state."|  


## Spring Component Scan  

* Spring Beans defined with Spring Stereotypes are detected with Spring component scan  
* On startup Spring is told to scan packages for classes with String Stereotype annotations  
* This configuration is Spring Framework specific, NOT Spring Boot  
* Spring Boot's autoconfiguration will tell Spring to perform a component scan of the package of the main class  
    * This includes all sub packages of the main class package  
* When using Spring Boot, if class is outside the main class package tree, you must declare the component scan  