
This project is a simple spring boot application applying some retail site business conditions which as the following

1. If the user is an employee of the store, he gets a 30% discount
2. If the user is an affiliate of the store, he gets a 10% discount
3. If the user has been a customer for over 2 years, he gets a 5% discount.
4. For every $100 on the bill, there would be a $ 5 discount (e.g. for $ 990, you get $ 45
as a discount).
5. The percentage based discounts do not apply on groceries.
6. A user can get only one of the percentage based discounts on a bill.

## UML Digram

- [UML Digram] (C:\Users\shire\Documents\workspace-spring-tool-suite-4-4.23.1.RELEASE\retail-shop-discounts\classdiagrams.umlc)

## Requirements

For building and running the application you need:

- JDK 21
- [Maven 4.0.0](https://maven.apache.org)

## Running the application locally Successfully

There are several ways to run a Spring Boot application on your local machine. 
1. execute the `main` method in the `RetailShopDiscountsApplication` class from your IDE.
  - Right click on Project
  - Select Run As
  - Select Java Application (JRE - Java 21)
  - Select Main Class `RetailShopDiscountsApplication` and click on Run.
  
2. Run through command line 
  - cmd
  - Go to path where our main class file exists `RetailShopDiscountsApplication`
  - execute this command -> mvn spring-boot:run

2. Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

shell
mvn spring-boot:run

### Build an executable Runnable JAR (
Note :
===================
    1. `application using JDK 21. Need to be installed JDK 21 as enviroment variables and system variable PATH)`
    2. Update Maven Dependency which supports for JDK 21.
    
1. Build executable jar file by Right clicking on project -> Export -> Runnable Jar file -> select Package required libraries into generated jar file option -> and select below path to keep your executable jar file
C:\Users\shire\Documents\workspace-spring-tool-suite-4-4.23.1.RELEASE\retail-shop-discounts\target\RetailShopDiscounts.jar

   - Above .jar file can run from eclipse as Run As 
           OR
   - Above .jar file can run as below via command line but we need to have JDK 21 installed in system and path variables need to be set and related maven dependencies
        1. Cmd
        2. Go to path where RetailShopDiscounts.jar file present
        3. You can run the application from the command line using:
            java -jar RetailShopDiscounts.jar

## To Run Application Junit Test cases
1. Right click on project from eclipse STS
2. Select `Run As` option
3. Select `Junit Test`
4. Results can be stored in target/surefire-reports/TEST-com.retail.shop.discounts.assignment.RetailShopDiscountsApplicationTests.xml file

## License