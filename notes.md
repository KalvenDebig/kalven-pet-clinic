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