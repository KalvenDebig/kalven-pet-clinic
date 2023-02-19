[![CircleCI](https://dl.circleci.com/status-badge/img/gh/KalvenDebig/kalven-pet-clinic/tree/main.svg?style=svg&circle-token=d8e5ca085f4e53f5e9aeffc80f02a9c60e3ccc53)](https://dl.circleci.com/status-badge/redirect/gh/KalvenDebig/kalven-pet-clinic/tree/main)

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


## Database Initialization with Spring  

## Hibernate DDL Auto  

* DDL = Data Definition Language  
    * DML = Data Manipulation Language  
* Hibernate property is set by the Spring property `spring.jpa.hibernate.ddl-auto`  
* Options are: none, validate, update, create, create-drop  
* Spring Boot will use create-drop for embeded databases (hsql, h2, derby) or none  


## Initialize with Hibernate  

* Data can be loaded from import.sql  
    * Hibernate feature (not Spring specific)  
    * Must be on root of class path  
    * Only executed if Hibernate's ddl-auto property is set to create or create-drop  

## Spring JDBC  

* Spring's DataSource initializer via Spring Boot will by default loead schema.sql and data.sql from the root of the classpath  
* Spring Boot will also load from schema-${platform}.sql and data-${platform}.sql  
    * Must set spring.datasource.platform  
* May conflict with Hibernate's DDL Auto property  
    * Should use setting of 'none' or 'validate'  


### Flash cards about JPA  

* What are the 4 types of entity relationships?  
    * @OneToOne, @OneToMany, @ManyToOne, @ManyToMany  
* Which JPA Relationship will require the use of a join table?  
    * @ManyToMany
* What is the difference between a unidirectional relationship and a bidirectional relationship?  
    * In unidirectional, the mapping is done one-way, meaning one side of the relationship will not know about the other. While bidirectional both sides will know about the relationship  
* Which type of relationship is generally recommended, unidirectional or bidirectional?  
    * bidirectional - This allows you to naviage the object graph from either direction  
* What two fetch types can be used with JPA?  
    * Lazy and Eager  
* What do JPA Cascade Types do?  
    * Cascade Types control how changes are cascaded from parent objects to child objects  
* In JPA 2.1, what is the default Cascade Type?  
    * None - JPA does not have a default Cascade Type.  
* What are the 6 available Cascade Types in JPA 2.1?  
    * PERSIST, MERGE, REFRESH, REMOVE, DETACH, ALL  
* Can you embed a type in JPA for reuse?  
    * Yes, JPA supports embeddable types, A good example is a Address type.  
* Does JPA support object inheritance  
    * Yes, in JPA classes can inherit from a super class.  
* What is Hibernate's default persistance strategy for inheritance?  
    * Single Table - One table is created for the super class and inheriting subclasses.  
* What is a disadvantage of the Single Table strategy for inheritance?  
    * Single Table can lead to a lot of unused database columns  
* What is a disadvantage of the Join Table strategy for inheritance?  
    * Fetching subclasses entities require a join to the table of the superclass  
* Can you have JPA automatically update timestamp properties for audit purposes?  
    * Yes, using @PrePersist or @PreUpdate within JPA. Or Hibernate specific @CreationTimestamp or @UpdateTimestamp  
* What does Hibernate's ddl-auto property control?  
    * The ddl-auto property controls what is any DDL operations Hibernate will perform on startup.  
* What are the 5 valid options for Hibernate's ddl-auto property?  
    * none, validate, update, create, create-drop  
* When using an embedded database, which ddl-auto setting will Spring Boot set by default?  
    * create-drop
* When using an NON-embedded database, which ddl-auto setting will Spring Boot set by default?  
    * none  
* What two files will Spinrg Boot use to initialize the database?  
    * SpringBoot will look on the root classpath for the files schema.sql and data.sql  
    

## Project Lombok  

* Hooks in via the Annotation processor API  
* The AST (raw source code) is passed to Lombok for code generation before the Java compiler continues  
* Thus, produces properly compiled Java code in conjunction with the Java compiler  
* Under the 'target/classes' you can view the compiledc class files  
* IntelliJ will decompile to show you the source code  

## Lombok Features  

* **val** - local variables declared final  
* **var** - mutable loval variables  
* @NonNull - Null check, will throw NPE if parameter is null 
* @Cleanup - will call close() on resource in finally block  
* @Getter - Creates getter methods for all properties  
* @Setter - Creates setter for all non-final properties  
* @ToString  
    * Generates String of classname, and each field separated by commas  
    * Optional parameter to include field names  
    * Optional parameter to include call to the super toString method 
* @EqualsAnsHashCode  
    * Generates implementations of equals(Object other) and hashCode()  
    * by default will use all non-static, non-transient properties  
    * Can optionally exclude specific properties  
* @NoArgsConstructor  
    * Generates no args constructor  
    * Will cause compiler error if there are final fields  
    * Can optionally force, which will initialize final fields with 0/false/null  
* @RequiredArgsConstructor  
    * Generates a constructor for all fields that are final or marked @NonNull  
    * Constructor will throw a NullPointerException if any @NonNull fields are null  
* @AllArgsConstructor  
    * Generates a constructor for all properties of class  
    * Any @NotNull properties will have null check  
* @Data  
    * Generates typical boilerplate code for POJOS  
    * Combines - @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArdsConstructor  
    * No constructor is generated if constructors have been explicitly declared  
* @Value  
    * The immutable varient of @Data  
    * All fields are made of private and finaly by default  
* @NonNull  
    * Set on parameters of methods or constructor and a NullPointerException will be thrown if parameter is null 
* @Builder
    * Impelement the `builder` pattern for object creation  
    * Person.builder().name("Adam Savage").city("SF").job("rapper").job("Unchained Reaction").build();  
* @SneakyThrows  
    * Throw checked exception without declaring in calling method's throw clause  
* @Syncronized  
    * A safer implementation of Java's synchronized  
* @Getter(lazy = true) - for expensive getters  
    * Will calculate value first time and cache  
    * Additional gets will read from cache  
* @Log - Creates a Java util logger - Java util loggers are awful  
* @Slf4j - Creates a SLF4J logger  
* Recommended - SLF4J is a generic logging cache  
* Spring Boot's default logger is LogBack  

## Spring Framework Testing Terminology  

* **Code Under Test** - This is the code (or application) you are testing  
* **Test Fixture** - "A test fixture is a fixed state of a set of objects used as a baseline for running tests. The purpose of a test fixture is to ensure that there is a well known and fixed environment in which tests are run so that results are repeatable." - Junit Doc  
    * Includes: input data, mock objects, loading database with known data, etc.  

* **Unit Tests/Unit Testing** - Code written to test code under test  
    * Designed to test specific sections of code  
    * Percentage of lines of code tested is code coverage  
        * Ideal coverage is in the 70-80% range  
    * Should be 'unit' and execute very fast  
    * Should have no external dependencies  
        * ie. no database, no Spring context, etc  

* **Integration Tests** - Designed to test behaviors between objects and parts of the overall system  
    * Much larger scope  
    * Can include the Spring Context, database and message brokers  
    * Will run much slower than unit tests  

* **Functional Tests** - Typically means you are testing the running application  
    * Application is live, likely depolyed in a known environment  
    * Functional touch points are tested  
        * i.e. Using a web driver, calling web services, sending/receiving messages, etc  

* **TDD** - Test Driven Developement - Write tests first, which will fail, then code to 'fix' test.  
* **BDD** - Behavior Driven Development - Builds on TDD and specifies that tests of any unit of software should be specified in terms of desired behavior of the unit.  
    * Often implemented with DSLs to create natural language tests  
    * JBehave, Cucumber, Spock  
    * example: given, when, then  

* **Mock** - A fake implementation of a class used for testing. Like a test double.  
* **Spy** - A partial mock, allowing you to override select methods of a real class  

## Testing Goals  

* Generally, you will want the majority of your tests to be unit tests  
* Bringing up the Spring Context makes your tests exponentially slower  
* Try to test specific buisness logic in unit tests  
* Use Integeration Tests to test interactions  
* Think of a pyramid. Base is unit test, middle is integration tests, top is functional test  

## Test Scope Dependencies  

* Using spring-boot-starter-test (default from Spring Initializr will load the following dependencies:)  
    * JUnit - The de-facto standard for unit testing Java applications  
    * Spring Test and Spring Boot Test - Utilities and integration test support for Spring Boot applications  
    * AssertJ - A fluent assertion library  
    * Hamcrest - A library of matcher objects  
    * Mockito - A java mocking framework  
    * JSONassert - An assertion library for JSON  
    * JSONPath - XPath for JSON  


## JUnit 4  

* JUnit 4 is the most popular testing framework in the Spring community  
* Originally written by Erich Gamma and Kent Beck (Creator of extreme programming)  

## JUnit 4 Annotations  

|Annotation| Description|
|---|---|  
|@Test|Identifies a method as a test method|  
|@Before|Executed before each test. It is used to prepare the test environment (e.g., read input data, initialize the class).|  
|@After| Executed after each test. It is used to cleanup the test environment. It can also save memory by cleaning up expensive memory structres.|
|@BeforeClass| Executed once. before the start of all test. Methods marked with this annotation need to be defined as static to work with JUnit|  
|@Ignore|Marks that the test should be disabled|  
|@Test(expected = Exception.class)|Fails if the methods does not throw the named exception|  
|@Test(timeout = 10)|Fails if the methods takes longer than 100 milseconds|  
|@RunWith(SpringRunner.class)|Run test with Spring Context|  
|@SpringBootTest| Search for Spring Boot Application for configuration|  
|@TestConfiguration| Specify a String configuration for your test|  
|@MockBean|Injects Mockito Mock|  
|@SpyBean|Injects Mockto Spy|  
|@JsonTest|Creates a Jackson or Gson object mapper via Spring Boot|  
|@WebMVCTest|Used to test web context without a full http server|  
|@DataJpaTest|Used to test data layer with embedded database|  
|@JdbcTest| Like @DataJpaTest, but does not configure entity manager|  
|@DataMongoTest|Configures an embedded MongoDB for testing|  
|@RestClientTest|Creates a mock server for testing rest clients|  
|@AutoConfigureRestDocks|Allows you to use Spring Rest Docs in tests, creating API documentation|  
|@BootStrapWith|Used to configure how the TestContext is bootstrapped|  
|@ContextConfiguration|Used to direct Spring how to configure the context for the test.|  
|@ContextHierarchy|Allows you to create a context hierarchy with @ContextConfiguration+  
|@ActiveProfiles|Set which Spring Profiles are active for the test|  
|@TestPropertySouce|Configure the property sources for the test.|  
|@DirtiesContext|Resets the Spring Context afte the test(expensive to do)|  
|@WebAppConfiguration|Indicates Spring should use a Web Application context|  
|@TestExecutionListeners|Allows you to specify listeners for testing events|  
|@Transactional|Run test in transaction, rollback when complete by default|  
|@BeforeTransaction|Action to run before starting a transaction|  
|@AfterTransaction|Action to run after a transaction|  
|@Commit|Specifies the transaction should be comitted after the test|  
|@Rollback|Transaction should be rolled back after test.(Default action)|  
|@Sql|Specify SQL scripts to run before|  
|@SqlConfig|Define meta data for scripts|  
|@SqlGroup|Group of @Sql annotations|  
|@Repeat|Repeat test x number of times|  
|@Timed|Similar to JUnit's timeout, but will wait for test to complete, unlike JUnit.|  
|@IfProfileValue|Indiactes test is enabled for a specific testing environment|  
|@ProfileValueSourceConfiguration|Specify a profile value source|  


## Why JUnit 5?  

* JUnit 5 leverages new features of Java 8  
    * Lambda Expressions  
    * Streams  
    * Java 8 or Higher is required  
* Designed for better integration and extensibility  

## JUnit Vintage 

* Optional JUint 5 Library  
* Provides a test runner for JUnit 3 and 4 tests using JUnit 5  
    * Allows easy migration to JUnit 5  

## Key Difference from JUnit 4  

|JUnit 4|JUnit 5|  
|---|---|  
|@Test(expected = Foo.class)|Assertions.assertThrows(Foo.class)|  
|@Test(timeout = 1) | Assertions.assertTimeout(Duration...)|  
|@RunWith(SpringJUnit4ClassRunner.class|@ExtendWith(SpringExtension.class|  

## New Annotations  
|JUnit 4|JUnit 5|
|---|---|  
|@Before|@BeforeEach|  
|@After|@AfterEach|  
|@BeforeClass|@BeforeAll|  
|@AfterClass|@AfterAll|  
|@Ignored|@Disabled|
|@Category|@Tag|  


## Data Binding in Spring  

* Command Objects (aka Backing Beans)  
    * Are used to transfer data to and from web forms 
    * Spring will automatically bind data of form posts  
    * Binding done by property name (less 'get'/'set')  

## Data Binding Examples  

* Example of a 'PersonBean'  
    * 'firstName' would bind to property firstName  
    * 'address.addressLine1' would bind to the addressLine1 one of the address property of the PersonBean  
    * email[0]/email[1] would bind to index zero and one of the email List or Set property of Person  

## HTTP Status Code  

* HTTP 5xx Server Error  
    * HTTP 500 - Internal Server Error  
    * Generally, any unhandled exception  
* Other 500 errors are generally not used with Spring MVC  

* HTTP 4xx Client Errors - Generally Checked Exceptions  
    * 400 Bad Request - Cannot process due to client error  
    * 401 Unauthorized - Authentication required  
    * 404 Not Found - Resource Not Found  
    * 405 Method Not Allowed - HTTP method not allowed  
    * 409 - Conflict - Possible with simulataneous updates  
    * 417 Expectation Failed - Sometimes used with RESTful interfaces  

## @ResponseStatus  

* Allows you to annotate custom exception classes to indicate to the framework the HTTPS status you want returned when that exception is thrown.  
* Global to the application  

## @ExceptionHandler  

* @Exceptionhandler works at the controller level  
* Allows youto define custom exception handling  
    * Can be used with @ResponseStatus for just returning a http status  
    * Can be used to return a specific view  
    * Also can take total control and work with the Model and View  
        * 'Model' cannot be a parameter of an ExceptionHandler method  

## HandlerExceptionResolver  

* HandlerExceptionResolver is an interface you can implement for custom exception handling  
* Used Internally by Spring MVC  
* Note Model is not passed  

```java
public interface HandlerExceptionResolver {
    @Nullable  
    ModelAndView resolveException(
        HttpServletRequest request, HttpServletResponse response, @Nullable Object handler, Exception ex);
}
```

## Internal Spring MVC Exception Handlers  

* Spring MVC has 3 implementations of HandlerExceptionResolver  
* ExceptionHandlerExceptionResolver - Matches uncaught exceptions to @ExceptionHandler  
* ResponseStatusExceptionResolver - Looks for uncaught exceptions matching @ResponseStatus  
* DefaultHandlerExceptionResolver - Converts standard Spring Exceptions to HTTP status codes (Interval to Spring MVC)  

## Custom HandlerExceptionResolver  

* You can provide your own implementations of HandlerExceptionResolver  
* Typically implemented with Spring's Ordered interface to define order the handlers will run in  
* Custom implementations are uncommon due to Spring robust exception handling  

## SimpleMappingExceptionResolver  

* A Spring Bean you can define to map exceptions to specific views  
* You only define the exception class name (no package) and the view name  
* You can optionally define a default error page  

## Which to Use When?  

* Depends on your specific needs  
    * If just setting the HTTP status - use @ResponseStatus  
    * If redirection to a view, use SimpleMappingExceptionResolver
    * If both, consider @ExceptionHandler on the controller  

## Built In Constraint Definitions  

* @Null - checks value is null  
* @NotNull - checks value is not null  
* @AssertTrue - Value is true  
* @AssertFalse - Value is false  
* @Min - Number is equal or higher  
* @Max - Number is equal or less  
* @DecimalMin - Value is larger  
* @DecimalMax - Value is less than  
* @Negative - Value is less than zero, Zero is valid  
* @NegativeOrZero - Value is zero or less than zero  
* @Positive - Value is greater than zero. Zero is valid  
* @PositiveOrZero - Value is zero or greater than zero.  
* @Size - Check if string or collection is between a min and max  
* @Digits - check for integer digits and fraction digits.  
* @Past - check if date is past  
* @PastOrPresent - check is date is in past or present  
* @Future - checks if date is in future  
* @FutureOrPresent - Checks if date is present or in future  
* @Pattern - checks against RegEx pattern  
* @Not Empty - checks if value is not null nor empty (whitespace characters or empty collection)  
* @NonBlank - checks string is not null or not whitespace characters  
* @Email - checks if string value is an email address  

## Hibernate Validator Constraints  

* @ScriptAssert - Class level annotation, checks class against script  
* @CreditCardNumber - Verifies value is a credit number  
* @Currency - Valid currency amount  
* @DurationMax - Duration less than given value  
* @DurationMin - Duration greater than given value  
* @EAN - Valid EAN barcode  
* @ISBN - Valid ISBN value  
* @Length - String length between given min and max  
* @CodePointLength - Validates that code point length of the annotated character sequence is between min and max included  
* @LuhnCheck - Luhn check sum  
* @Mod10Check - Mod 10 check sum  
* @Mod11Check - Mod 11 check sum  
* @Range - checks if number is between given min and max(inclusive)  
* @SafeHtml - checks for safe HTML  
* @UniqueElements - checks if collection has unique elements  
* @Url - checks for valid URL  

## Hibernate Validator Country Constraints  

* @CNPJ - Brazilian Corporate Tax Payer Registry Number  
* @CPF - Brazilian Individual Tax Payer registry Number  
* @TituloEleitoral - Brazilian vote ID  
* @NIP - Poish VAR ID  
* @PESEL - Polish National Validation Number  
* @REGON - Polish Tax Payer ID  

## I18N Locale Detection  

* Default behavior is to use Accept-Language header  
* Can be configured to use system, a cookie, or a custom parameter.  
    * Custom Parameter is useful to allow user to select language.  

## Locale Resolvers  

* AcceptHeaderLocaleResolver is the Spring Boot Default  
* Optionally, can use FixedLocaleResolver  
    * Uses the locale of the JVM  
* Available: CookieLocaleResolver, SessionLocaleResolver  

## Changing Locale  

* Browsers are typically tied to the Locale of the operating system  
* Locale changing plugins are available  
* Spring MVC provides as LocaleChangeInterceptor to allow you to configure a custom parameter to use to change the locale  

## Resource Bundles  

* Resource bundles (aka messages.properties) are selected on highest match order  
* First selected will be on language region 
    * ie en-US would match messages_en_US.properties  
* If no exact match is found, just the language code is used  
    * en-GB would match messages_en_GB.properties  
    * OR if no file found, would match messages_en.properties  
    * Finally would match messages.properties  

    
## What is Docker?  

* Docker is a standard for Linux containers.  
* A "Container" is an isolated runtime inside of Linux
* A "Container" provides a private machine like space under Linux.  
* Containers will run under any modern Linux kernal

## Containers can  

* HJave their own process space.  
* Their own network interface  
* 'Run' processes as root (inside the container)  
* Have their own disk space 
    can share with host too  


## Docker Terminology  

* Docker Image - The representation of a Docker Container. Kind of like a Jar ow War file in java.  
* Docker Container - The standard runtime of Docker. Effectively a deployed and running Docker Image. Like a Spring Boot executable Jar.  
* Docker Engine - The code which manages Docker stuff. Creates and runs Docker containers  

 
## What is a Docker Image?  

* An Image defines a Docker Container  
    * Similar in concept to a snapshot of a VM.  
    * Or a class vs an instance of the class. 
* Images are immutable  
    * Once built, the files making up an image do not change.  


## Image Layers  

* Images are built in layers  
* Each layer is an immutable file, but is a collection of files and directories.  
* Layers receive an ID, calculated via a SHA 256 hash of the layer contents.  
    * Thus, if the layer contents change, the SHA 256 hash changes as well  

## Images Ids  

* Image Ids are a SHA 256 hash derived from the layers  
    * Thus if the layers of the image changes. the SHA 256 hash changes  
* The Image ID listed by docker commands `docker images` is the first 12 characters of the hash.  

## Image Tag Names  

* The hash values of images are referred to by 'tag' names  
    * This concept is very confusing at first  

* The format of the full tag name is: `[REGISTRYHOST/][USERNAME/]NMAE[:TAG]`  
* For Registry Host 'registry.hub.docker.com' is inferred  
* For ':TAG' - 'latest' is default, and inferred.  
* Full tag example: registry.hub.docker.com/mongo:latest
* REPOSITORY: mongo, TAG: latest


## Cleaning Up After Docker  

* With Development Use Docker can leave behind a lot of files  
* These files will grow and consume lots of disk space  
* This is less of an issue on production systems where containers aren't being built and restarted all the time  
* There are 3 key areas of house keeping:  
    * Containers  
    * Images  
    * Volumes  

## Cleaning Up Containers  

* Kill all Running Docker Containers  
    * `docker kill $(docker ps -q)`
* Delete all Stopped Docker Containers  
    * `docker rm $(docker ps -a -q)`  

## Cleaning Up Images  

* Remove a Docker Image  
    * `docker rmi <image name>`    
* Delete Untagged (dangling) Images  
    * `docker rmi $(docker images -q -f dangling=true)`  
* Delete All Images  
    * `docker rmi $(docker images -q)`  

## Cleaning Up Volumes  

* Once a volume is no longer associated with a container, it is considered 'dangling'  
* Remove all dangling volumes  
    * `docker volume rm $(docker volume ls -f dangling=true -q)`  
* NOTE: Dost not remove files from host system in shared volumes  

[cheat sheet](https://springframework.guru/docker-cheat-sheet-for-spring-developers)    

## RDBMS Deployment Architectures  

* RDBMS's can be deployed in a number of different ways.  
* Typically is driven by needs of scalability and availability.  
* Can be done on a single non-dedicated server, or many dedicated servers  
* Communication is typically over a network socket.  
* The client will need software called a 'driver' to talk to the databse over the network socket  

## Simple Non-Dedicated Server  

* Single Server (computer)  
* RDBMS is installed  
* User logs in and accesses database from command line  
* Simplest configuration  
* Often we talk over localhost and a network socket on localhost  

## LAMP Stack  

* LAMP = Linux, Apache, MySQL, PHP  
* Very popular  
* Most websites will run off a single server  
* Downside is database and Apache compete for the limited server resource  

## Client Server  

* "Client Server" was the buzz in the 90s  
* Concept of moving application code to the client and different hardware, while using dedicated harware for the database server.  
* Offloads the application load from the database server  
 
## Scaling Client Server  

* Scalability is achieved by doing processing on application servers  
* Database Server is dedicated.  
* Often companies will increase the size of the database server to grow further  
* Data storage is off loaded to dedicated hardware  

## Scaling Even Higher  

* Multiple Servers are used for the database  
* Example - Oracle Real Application Cluster  
* Improves Scalability over a single database server   
* Improves Reliability since a node can be lost, and the database cluster will continue  
* "Mainframe" like performance  

## Scaling Even Higher?  
* Cloud Scale, Amazon Google Facebook  
* Distributed computing - load is spread to many many servers.  
* Often cheap commodity servers are used.  
* Large mainframe like systems are avoided.  
* Significantly different paradigm than Client Server  
* RDBMS's are typically not used due to scalability limitations.  

## MySQL Types of Connections  

* Local Connection  
    * You're connecting to MySQL from the command line on the machine running MySQL  
    * Protocol depends on your Operating System  
* Remote/Client Connection  
    * You're using some type of client software on the same machine running MySQL  
    * Or - Connecting to the MySQL Server from a different machine over the network  

## Client Protocols  

* TCP/IP - Most Common  
* Socket - Unix/OSX/Linux Only  
* PIPE - Windows Only  
* MEMORY - Windows Only  

## TCP/IP  

* TCP/IP - Transmission Control Protocol/Internet Protocol  
* DNS - Domain Name Service - Associates an IP address with a human readable name  
* "localhost" is the computer you are on. IP Address: 127.0.0.1  
* A "port" is a logical connection endpoint of an IP Address  
* Ports range from 0 to 65535  
* MySQL be default will connect on port 3306  

## MySQL Command Line/MySQL Workbench  
* From the operating system command line, we can use the program "mysql" to connect to the MySQL database  
* This is a client program for the command line which gives you a shell to interact with the database  
* MySQL Workbench is a GUI for working with the MYSQL database  


## MongoDB (document oriented database)  

* MongoDB documents are stored in BSON  
    * Binary JSON  
* MongoDB is a NoSql database  
* MongoDB is great for high insert systems  
    * Such as sensor readings, social media systems, advertising systems.  
* Good when you need schema flexibility  
* Can also support a high number of read per second  

## Why Avoid MongoDB?  

* MongoDB has no concept of transactions  
    * No ACID  
    * No locking for transactional support, hence faster inserts  
* Not good for concurrent updates  
* Losing data  

## MongoDB Terminology  

|RDMS|MongoDB|  
|---|---|  
|Database|Database|  
|Table|Collection|  
|Row|Document|  
|Column|Field|  
|Table Join|Embedded Documents|  
|Primary Key|Primary Key|  
|Aggregation|Aggregation Pipeline|  

## Reactive Manifesto  

* Reactive Systems - Architecture and Design  
    * Cloud Native  
* Reactive Programming  
    * Generally Event Based  
* Functional Reactive Programming (FRP)  
    * Often confused with Reactive Programming  

## Reactive Manifesto - Responsive  

* The system responds in a timely manner.  
* Responsiveness is the cornerstone of usability and utility  
* Responsiveness also means problems may be detected quickly and dealt with effectively.  
* Responsive systems provide rapid and consistent response times.  
* Consistent behavior simplifies error handling, builds end user confidence, and encourages further interaction.  

## Reactive Manifesto - Resilient  

* System stays responsive in the face of failure.  
* Resilience is achieved by replication, containment, isolation, and delegation.  
* Failures are contained within each component.  
* Parts of the system can fail, without compromising the system as a whole  
* Recovery of each component is delegated to another  
* High-availability is ensured by replication where necessary.  

## Reactive Manifesto - Elastic  

* The system stays responsive under varying workload  
* Reactive Systems can react to changes in the input rate by increasing or decreasing resources allocated to service inputs.  
* Reactive Systems achieve elasticity in a cost effective way on commodity hardware and software platforms  


## Reactive Manifesto - Message  Driven  

* Reactive Systems rely on asynchronous message passing to establish a boundary between components  
    * This ensures loose coupling, isolation, and location transparency.  
* Message passing enables load management, elasticity, and flow control.  
* Location transparent messaging makes management of failures possible  
* Non-blocking communication allows recipients to only consume resources while active, leading to less system overhead.  

## Reactive Programming with Reactive System  

* Reactive programming is a useful implementation technique  
* Reactive Programming focuses on non-blocking, asynchronous execution - a key characteristic of Reactive Systems.  
* Reactive Programming is just on tool in building Reactive Systems.    

## Reactive Programming  

* Reactive Programming is an asynchronous programming paradigm focused on streams of data. 

## Common Use Cases  

* External Service Calls  
* Highly Concurrent Message Consumers  
* Spreadsheets  
* Abstraction Over Asynchronous Processing  
    * Abstract whether or not your program is synchronous or asynchronous  

## Features of Reactive Programming  

* Data Streams  
* Asynchronous  
* Non-blocking  
* Backpressure  
* Failure as Messages  

## Data Streams  

* Data Streams can be just about anything.  
* Mouse clicks, or other user interactions  
* JMS Messages, RESTful Service calls, Twitter feed, Stock Trades, list of data from a database  
* A Stream is a sequence of events ordered in time.  
* Events you want to listen to.  

## Asynchronous  

* Events are captured asynchronously.  
* A function is defined to execute when an event is emitted.  
* Another function is defined if an error is emitted.  
* Another function is defined when complete is emitted.  

## Non-Blocking  

* The concept of using non-blocking is important.  
* In blocking, the code will stop and wait for more data (ie reading from disk, network, etc)  
* Non-blocking in contrast, will process available data, ask to be notified when more is available, then continue,  


## Back pressure  

* The ability of the subscriber to throttle data  

## Failures as Messages  

* Exceptions are not thrown in a traditional sense.  
    * Would break processing of stream  
* Exception are processed by a handler function  

## Key Take Away  

* Reactive Programming focuses on processing streams of data  
* Traditional CRUD applications are still alive and well.  


## Reactive Streams API  

* Goal is to create a standard for asynchronous stream processing with non-blocking back pressure.  
* Reactive Streams is a set of 4 interfaces which define the API  

**Publisher**
```java
/**
 * A {@link Publisher} is a provider of a potentially unbounded number of sequenced elements, publishing them according to the demand received from its {@link Subscriber}(s).
 * 
 * A {@link Publisher} can serve multiple {@link Subscriber}s subscribed {@link #subscribe(Subscriber)} dynamically at various points in time.  
 * 
 * @param <T> the type of element signaled.  
 */ 
public interface Publisher<T> {
    /**
     * Request {@link Publisher} to start streaming data.  
     * This is a "factory method" and can be called multiple times, each time starting a new {@link Subscription}.  
     * 
     * Each {@link Subscription} will work for only a single {@link Subscriber}.  
     * 
     * A {@link Subscriber} should only subscribe once to a single {@link Publisher}.  
     * 
     * If the {@link Publisher} rejects the subscription attempt or otherwise fails it will singal the error via {@link Subscriber#onError}.
     * 
     * @param s the {@link Subscriber} that will consume signals from this {@link Publisher})
     */ 
    public void subscribe(Subscriber<? super T> s);
}
```  

**Subscriber**   
```java
public interface Subscriber<T> {
    public void onSubscribe(Subscription s);

    public void onNext(T t);

    public void onError(Throwable t);

    public void onComplete();
}
```

**Subscription**  
```java
public interface Subscription {
    public void request(long n);
    public void cancel();
}
```

**Processor** 
```java
public interface Processor<T, R> extends Subscriber<T>, Publisher<P> {
}
```

## Spring MVC & Spring WebFlux  
|@Controller / @RequestMapping|Router Functions|  
|---|---|  
|Spring-webmvc|spring-webflux|  
|Servlet API|HTTP/Reactive Streams|  
|Servlet Container|Tomcat, Jetty, Netty, Undertow|  

## Spring Reactive Types  

* Two new ractive types are introduced with Spring Framework 5  
* `Mono` is a publisher with zero or one elements in data stream  
* `Flux` is a publisher with zero or MANY elements in the data stream  
* Both types implement the Reactive Stream Publisher interface  

## Guide To REST  

* Becuase of the simplicity and versatility, RESTful web services have become the de facto standard for web services.  

* REST - Representational State Transfer  
    * Representation - Typically JSON or XML  
    * State Transfer - Typically via HTTP  

## RESTful Terminology  

* Verbs - HTTP methods: GET, PUT, POST, DELETE  
* Messaages - the payload of the action (JSON/XML)  
* URI - Uniform Resource Identifier  
    * A unique string identifying a resource  
* URL - Uniform Resource Locator  
    * A URI with network information [link](http://www.example.com)  
* Stateless - Service does not maintain any client state  
* HATEOAS - Hypermedia as the engine of application state  

## HTTP - GET  

* Use: to read data from resource  
* Read only  
* Idempotent  
* Safe operation - does not change state of resource  

## HTTP - PUT  

* Use: to insert (if not found) or update (if found)  
* Idempotent - Multiple PUTs will not change result.  
    * Like saving a file multiple times  
* Not safe operation - does change state of resource  

## HTTP - POST  

* Use: to create new object (insert)  
* Non-Idempotent - Multiple POSTs is expected to create multiple objects  
* Not Safe operation - does change state of resource  
* Only Non-Idempotent, Non-safe HTTP verb  

## HTTP - DELETE  

* Use: to delete an object (resource)  
* Idempotent - Multiple DELETEs will have same effect / behavior  
* Not Safe operation - does change state of resource  

## Richardson Maturity Model (RMM)  

* A model used to describe the maturity of RESTful services  
* Unlike SOAP, there is no formal specification for REST  
* RMM is used to describe the quality of the RESTful service  

## RMM levels  

* Level 3: Hypermedia controls  
* Level 2: HTTP Verbs  
* Level 1: Resources  
* Level 0: The Swap of POX  

## Level 0: Swamp of POX  

* POX - Plain old xml  
* Uses implementing protocol as a transport protocol   
* Typically uses one URI and one kind of method  
* Example - RPC, SOAP, XML-RPC  

## Level 1: Resources  

* Uses Multiple URIs to identify specific resources  
* Still uses a single method (ie GET)  

## Level 2: HTTP Verbs  

* HTTP Verbs are used with URIs for desired actions  
    * GET /products/1234 - to return data for product 1234  
    * PUT /products/1234 (with XML body) to update data for product 1234  
    * DELETE /products/1234 to delete product 1234  
* MOST common in practical use  

## Level 3: Hypermedia controls  

* Representation now contains URIs which may be useful to consumers  
* Helps client developers explore the resource  
* Spring provides an implementation of HATEOS  

## Summary  

* Level 1 - breaks large service into distinct URIs  
* Level 2 - Introduces Verbs to implement actions  
* Level 3 - provides discoverability, making the API more self documenting.  


## Introduction to MapStruct  

* MapStruct is a code generator for Java bean mapping.  
    * Helps reduce coding for type conversions.  
    * When dealing with REST services, a common use case is to expose API data using DTOs  
        * DTO - Data Transfer Object  
* Like Project Lombok, MapStruct is annotation based processor plugged into the Java compiler  
* From interfaces you declare, MapStruct will generate code at build time.  


## MapStruct - Example  

```java
public class Car {
    private String make;
    private int numberOfSeats;
    private CarType type;
    // constructor, getters, setters etc.  
}
```

```java
public class CarDto {
    private String make;
    private int seatCount;
    private String type;
    // constructor, getters, setters etc.  
}
```

```java
@Mapper 
public interface CarMapper {
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    @Mapping(source = "numberOfSeats", target = "seatCount")
    CarDto carToCarDto(Car car); 
}
```


```java
@IllegalThreadStateException
public void shouldMapCarToDto() {
    // given
    Car car = new Car("Morris", 5, CarType.SEDAN);

    // when 
    CarDto carDto = CarMapper.INSTANCE.carToCarDto(car);

    // then  
    assertThat(carDto).isNotNull();
    assertThat(carDto.getMake()).isEqualTo("Morris");
    assertThat(carDto.getSeatCount()).isEqualTo(5);
    assertThat(carDto.getType()).isEqualTo("SEDAN");
}
```

## FAQ MapStruct  

* Can MapStruct be used with Project Lombok?  
    * Yes  
* Are Java 8 Streams Supported?     
    * Yes  


## Swagger  

* An open source software framework backed bu a large ecosystem of tools that helps developers design, build, document, and consume RESTful Web services.  
* The goal of Swagger is to define a standard, language agnostic interface to REST APIs which allows both humans and computers to discover and understand the capabilities of the service without access to source code, documentation, or through network traffic inspection

* Swagger is more than Documentation  
* Swagger is a Specification  
* JSON for Metadata  
* JSON for API Definition  
* JSON for Schema for the Model Specification  

* The Swagger Specification is Machine Readable  
* Also is Language Agnostic  
* Swagger is supported for most popular server side and client side languages.  


## REST vs SOAP  

* Prior to RESTful APIs, SOAP web services were vey popular  
* A cornerstone of SOAP is the WSDL  
    * Web Service Description Language  
* WSDL is an XML document, describing a web service  
* Can also be thought of as a 'contract'  
* There is not formal WSDL for RESTful APIs. Hence Swagger  


## The Swagger Eco System  

* Swagger UI - HTML, Javascript and CSS componentes to dynamically generate documentation from a Swagger Compliant API.  
* Swagger Editor - Edit API Specifications in YAML and preview documentation real time  
* Swagger Codegen - Create clinet libraries and server stubs from a Swagger definition  

## The Swagger Specification  

* The Swagger Specification can be JSON or YAML  
* Field names are case sensitive  
* Data types are based on JSON-Schema  
* Models are described using Schema Object, of JSON Schema  
* Swagger 2 uses JSON Schema Draft 4  

```swagger
swagger: '2.0'
info:
    version: 1.0.0
    title: Simple example API  
    description: An API to illustrate Swagger  
path:
    /list:
      get:
        description: Returns a list of stuff
        responses:
            200:
                description: Successful Response  
```

## OpenAPI Specification  

* Swagger Specification aka:  
    * Swagger RESTful API Documentation Specification
* With Swagger 2.0, the Swagger RESTful API Documentation Specification Became Known as:  
    * OpenAPI Specification  
