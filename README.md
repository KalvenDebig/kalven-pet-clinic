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

## Spring Bean Scopes  

* Singleton - (default) Only one instance of the bean is created in the IoC container.  
* Prototype - A new instance is created each time the bean is requested  
* Request - A single instance per http request. Only valid in the context of a web-aware Spring ApplicationContext  
* Session - A single instance per http session. Only valid if the context of a web-aware Spring ApplicationContext  
* Global-Session - A single instance per global session. Typically Only used in a Portlet context. Only valid in the context of a web-aware Spring ApplicationContext
* Application - bean is scoped to the lifecycle of a ServletContext. Only valid in the context of a web aware.  
* Websocket - Scopes a single bean definition to the lifecycle of a WebSocket. Only valid in the context of a web-aware Spring ApplicationContext  
* Custom Scope - Spring Scopes are extensible, and you can define your own scope by implementing Spring's `Scope` interface.  
* See Spring's Java docs for details  
* You cannot override the built in Singleton and Prototype Scopes  


## Declaring Bean Scope  

* No declaration needed for singleton scope.  
* In Java configuration use @Scope annotation  
* In XML configuration scope is an XML attribute of the `bean` tag  
* 99% of the time singleton scope is fine!  


## External Properties  

* Why use external properties?  
    * Hard coding values which can change is considered a bad practice  
    * Makes your application rigid and hard to change  
    * You want your application to be portable  
        * Deployment artifact (jar/war) should be deployable to different environments  
    * What to change?  
        * Usernames, passwords, urls, API keys, paths, queue names, etc  

## Setting External Properties  

* How do you set external properties?  
    * Command Line Args  
    * SPRING_APPLICATION_JSON - JSON object via command line or environment variable  
    * JNDI  
    * OS Environment variables  
    * Property files (property file or YAML variants)  


## Property Hierarchy  

* Review Section 24 - Externalized Configuration of Spring Boot for specifics  
* Properties can be overridden depending on how they are defined  
    * Gives you a lot of flexibility for deployments  
* Lowest are properties defined in JAR or war properties or YAML files  
* Next are external property files to JAR via file system  
* Higher are profile specific properties files (in jar, then external)  
* OS Environment variables  

* Java system properties  
* JNDI  
* SPRING_APPLICATION_JSON
* Command Line Args  
* Test Properties (for texting)  


## John's Pragmatic Guide  
[Spring External Properties Documentation](https://docs.spring.io/spring-boot/docs/2.4.3/reference/html/spring-boot-features.html#boot-features-external-config)
* Favor using application.properties or application.yml in packaged JAR (or WAR)  
* Use profile specific properties or YAML files for profile specific properties  
* For deployments, override propertie that change with environment variables  
    * Typically 70-80% of values do not change, only override what is needed  
    * Environment variables offer a secure way of setting sensitive values such as a passwords  


## Spring Cloud Config  

* Spring Cloud Config allows Spring applications to obtain configuration from a configuration server  
* Application starts up and obtains configuration values from a configuration server  
* Bootstrap process runs before normal Spring startup  
* Spring Cloud Config is out of scope of this course  
    * Covered in depth in Spring Boot Microservices course  


## Thymeleaf Features  

* Thymeleaf is a Java template engine producing XML, XHTML, and HTML5  
* Thymeleaf is a replacement for JSPs (Java Server Pages)  
* Thymeleaf if a 'Natural' Template Engine.  
* Is not tied to web environment. (ie can be used for producing HTML for emails)  
* Not a web framework  


## Thymeleaf vs JSP  

* Thymeleaf templates are valid HTML documents you can view in the browser  
* JSP files are not valid HTML, and look awful in the browser  
* The natural templating ability allows you to perform rapid development, without the need to run a container to parse the template/JSP to view the product in a browser  
    * Speeds development time  


## Thymeleaf Performance  

* Thymeleaf typically does benchmark slower than other template engines such as JSP, Freemarker, and Velocity  
    * Note: Spring 5 no longer supports Velocity  
* Thymeleaf 3.0 did bring significant performance improvements  
    * Beware of old benchmark data!  

    
## HTTP Request Methods  

* Request methods, also known as verbs, are used to indicate the desired action to be performed  
* GET - is a request for a resource (html file, javascript file, image, etc)  
* GET - is used when you visit a website  
* HEAD - is like GET, but only asks for meta information without the body  
* POST - is used to post data to the server  
* Typical use case for POST is to post form data to the server (like a checkout form)  
* PUT - is a request for the enclosed entity be stored at the supplied URL. If the entity exists, it is expected to be updated.  
* POST is a create request  
* PUT is a create OR update request  
* DELETE - Is a request to delete the specified resource  
* TRACE - Will echo the received request. Can be used to see if request was altered by intermediate servers  
* OPTIONS - Returns the HTTP methods supported by the server for the specified URL  
* CONNECT - Converts the request to a transparent TCP/IP tunnel, typically for HTTPS through an unencrypted HTTP proxy  
* PATCH - Applies partial modifications to the specified resource  


## Safe Methods  
* Safe Methods are considered safe to use because they only fetch information and do not cause changes on the server  
* The Safe Methods are: GET, HEAD, OPTIONS, and TRACE  

## Idempotent Methods  

* Idempotence - A quality of an action such that repetitions of the action have no further effect on the outcome  
* PUT and DELETE are Idempotent Methods  
* Safe Methods(GET, HEAD, TRACE, OPTIONS) are also Idempotent  
* Being truly Idempotent is not enforced by the protocol  

## Non-Idempotent Methods  

* POST is NOT Idempotent  
* Multiple Post are likely to create multiple resources  

|Method|Request Body| Response Body| Safe |Idempotent|Cachable| 
|---|---|---|---|---|---|  
|GET|NO|YES|YES|YES|YES|  
|HEAD|NO|NO|YES|YES|YES|  
|POST|YES|YES|NO|NO|YES|  
|PUT|YES|YES|NO|YES|NO|  
|DELETE|NO|YES|NO|YES|NO|  
|CONNECT|YES|YES|NO|NO|NO|  
|OPTIONS|OPTIONAL|YES|YES|YES|NO|  
|TRACE|NO|YES|YES|YES|NO|  
|PATCH|YES|YES|NO|NO|YES|  


## HTTP Status Codes  

* 100 series are informational in nature  
* 200 series are indicate successful request  
* 300 series are redirections  
* 400 series are client erros  
* 500 series are service side erros  

## Common HTTP Status Codes  

* 200 Okay; 201 Created; 204 Accepted  
* 301 Moved Permanently  
* 400 Bad Request; 401 Not Authorized; 404 Not Found  
* 500 Internal Server Error; 503 Service Unavailable  

## Spring Boot Developer Tools

* Added to Project via artifact 'spring-boot-devtools'  
* Developer Tools are automatically disabled when running a packaged application (ie java -jar)  
* By default not included in repackaged archives  


## Developer Tools Features  

* Automatic Restart  
    * Triggers a restart of the Spring Context when classes change  
    * Uses two classloaders. One for your application, one for project jar dependencies  
    * Restarts are very fast, since only your project classes are being loaded  
* IntelliJ:  
    * By default you need to select 'Build/Make Project'  
    * There is an advanced setting you can change to make this more seamless  

## Template Caching  

* By default templates are cached for performance  
* But caching will require a container restart to refresh the cache  
* Developer Tools will disable tempalte caching so the restart is not required to see changes  

## LiveReload  

* Live Reload is a technology to automatically trigger a browser refresh when resources are changed  
* Spring Boot Developer Tools includes a Live Reload server  

## Type of Relationships  

* One to One - @OneToOne  
    * One entity is related to one other entity  
* One to Many - @OneToMany  
    * One entity is related to many entities (List, Set, Map, SortedSet, SortedMap)  
* Many to One - @ManyToOne  
    * The inverse relationship of One to Many  
* Many to Many - @ManyToMany 
    * Many entities are related to many entities  
    * Each has a List or Set referencec to the other  
    * A join table is used to define the relationships  

## Unidirectional vs Bidirectional  

* Unidirectional is one-way  
    * Mapping is only done one way. One side of the relationship will not know about the other  
* Bidirectional is two way  
    * Both sides know about each other  
    * Generally recommended to use Bidirectional, since you can navigate the object graph in either direction  

## "Owning Side"  

* The Owning side in the relationship will hould the foreign key in the database  
* One to One is the side where the foreign key is specified  
* OneToMany and ManyToOne is the "Many" side  
* 'mappedBy' is used to define the filed with "owns" the reference of the relationship  

## Fetch Type  

* Lazy Fetch Type - Data is not queried until referenced  
* Eager Fetch Type - Data is queried up front  
* Hibernate 5 Supports the JPA 2.1 Fetch Type Defaults  
* JPA 2.1 Fetch Type Defaults:  
    * OneToMany - Lazy  
    * ManyToOne - Eager  
    * ManyToMany - Lazy
    * OneToOne - Eager  


## JPA Cascade Types  

* JPA Cascade Types Control how state changes are cascaded from parent objects to child objects.  
* JPA Cascade Types:  
    * PERSIST - Save operations will cascade to related entities  
    * MERGE - related entities are merged when the owning entity is merged  
    * REFRESH - related entities are refreshed when the owning entity is refreshed  

## JPA Cascade Types - Cont  

* JPA Cascade Types(continued):  
    * REMOVE - Removes all related entites when the owning entity is deleted  
    * DETACH - detaches all related entites if a manual detach occurs  
    * ALL - Applies all the above cascade options  
* By default, no operations are cascaded  


## Embeddable Types  

* JPA/Hibernate support embeddable types  
* These are used to define a common set of properties  
* For example, an order might have a billing address, and a shipping address  
* An embeddable type could be used for the address properties  


## Inheritance  

* MappedSuperclass - Entities inherit from a super class. A database table IS NOT created for the super class  
* Single Table - (Hibernate Default) - One Table is used for all subclasses  
* Joined Table - Base class and subclasses have their own tables. Fetching subclass entities require a join to the parent table  
* Table Per Class - Each subclass has its own table  


## Create and Update Timestamps  

* Often a best practice to use create and update timestamps on your entities for audit purposes  
* JPA supports @PrePersist and @PreUpdate which can be used to support audit timestamps via JPA lifecycle callbacks  
* Hibernate provides @CreationTimestamp and @UpdateTimestamp  

