# Introduction
The SpringBoot-Maven-API has the main objective of working as a 'Base API' for future projects.

#Sumary
* [Getting Started]()
* [Build and Test]()
* [Package]()
* [Run Stand Alone application]()
* [Versioning]()
* [Naming pattner]()
* [Team members]()

# Getting Started
1.	Installation process
* Download from repository a zip or run clone command
* Unzip the file
* Run command ```mvn clean install```

2.	Software dependencies
* [GIT](https://git-scm.com/)
* [Java 17](https://docs.oracle.com/javase/17/docs/api/)
* [SpringBoot](https://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/)
* [Spring Data Jpa](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
* [H2 Database](http://www.h2database.com/html/main.html)
* [Maven](https://maven.apache.org/)

##### Database

* [PostgreSQL](https://www.postgresql.org/)
* [H2](https://www.h2database.com/html/main.html)
  To run Application define the environment variables bellow:

##### Environment variables
- DATASOURCE_URL: ```"jdbc:postgresql://${SERVER}:5441/${DATABASE}"```
- DATASOURCE_USER: ```< database user >```
- DATASOURCE_PASSWORD: ```< database password >```
- SERVER_PORT: ``` < port > ```

# Build and Test
To build the code and run the tests:

* To build the project run: ```mvn clean install ```
* To run project with Maven run: ``` mvn spring-boot:run ```
* To run tests run: ``` mvn test ```

## Package
* Download from repository a zip or run clone command
* Unzip the file
* Find the pom.xml file
* Run the command ```mvn clean install package```

## Run Stand Alone application
* After execute package sequence commands
* Browse to folder ``` ../target ```
* Run the command ``` java -jar costing-api.jar```

## Versioning
* Creating a new branch Team/SprintXX/<Tasks | Delivery | Bugs>/Team_SXX_T<ID_TICKET>.

  ```bash
  git checkout -b Team/Sprint01/Tasks/TEA_S01_T314875
  ```

* Add modified files.

  ```bash
  git add <file1> <file2> ...
  ```

* run commit (after Add modified files)

  ```bash
  git commit -m "<MENSAGE>"
  ```


* Sync Repository.

  ```bash
  git push origin <branch-name>
  ```

* Download updated files to local repository

    ```bash
    git fetch
    ```

* Download update files from remote repository and merge

    ```bash
    git pull
    ```

* Delete branch.

  ```bash
  git branch -D <branch-name>
  ```

## Naming pattern

The Modules must follow the naming pattern:

##### Packages and classes:

- **base package**: com.intercab
- **pattner for module**: com.intercab.<module>


##### Classes:

* Entities : ``` <DOMAIN NAME> ```
* Enums and Types : ``` <DOMAIN NAME>Enum ```
* Repository : ``` <DOMAIN NAME>Repository ```
* DTO : ``` <DOMAIN NAME>DataRequest ``` and ``` <DOMAIN NAME>DataResponse ```
* Service : ``` <DOMAIN NAME>Service ```
* Controller : ``` <DOMAIN NAME>Controller ```
* Converter : ``` <DOMAIN NAME>Mapper ```


# Team members
- Lincoln Andrade Silva
