<div align="center">
	<code><img height="50" src="https://user-images.githubusercontent.com/25181517/192158954-f88b5814-d510-4564-b285-dff7d6400dad.png" alt="HTML" title="HTML" /></code>
	<code><img height="50" src="https://user-images.githubusercontent.com/25181517/183898674-75a4a1b1-f960-4ea9-abcb-637170a00a75.png" alt="CSS" title="CSS" /></code>
	<code><img height="50" src="https://user-images.githubusercontent.com/25181517/117447155-6a868a00-af3d-11eb-9cfe-245df15c9f3f.png" alt="JavaScript" title="JavaScript" /></code>
	<code><img height="50" src="https://user-images.githubusercontent.com/25181517/117201156-9a724800-adec-11eb-9a9d-3cd0f67da4bc.png" alt="Java" title="Java" /></code>
	<code><img height="50" src="https://user-images.githubusercontent.com/25181517/117201470-f6d56780-adec-11eb-8f7c-e70e376cfd07.png" alt="Spring" title="Spring" /></code>
	<code><img height="50" src="https://user-images.githubusercontent.com/25181517/183891303-41f257f8-6b3d-487c-aa56-c497b880d0fb.png" alt="Spring Boot" title="Spring Boot" /></code>
	<code><img height="50" src="https://user-images.githubusercontent.com/25181517/117207242-07d5a700-adf4-11eb-975e-be04e62b984b.png" alt="Maven" title="Maven" /></code>
	<code><img height="50" src="https://user-images.githubusercontent.com/25181517/117533873-484d4480-afef-11eb-9fad-67c8605e3592.png" alt="JUnit" title="JUnit" /></code>
	<code><img height="50" src="https://user-images.githubusercontent.com/25181517/183892181-ad32b69e-3603-418c-b8e7-99e976c2a784.png" alt="mocikto" title="mocikto" /></code>
	<code><img height="50" src="https://user-images.githubusercontent.com/25181517/190229463-87fa862f-ccf0-48da-8023-940d287df610.png" alt="Lombok" title="Lombok" /></code>
	<code><img height="50" src="https://user-images.githubusercontent.com/25181517/183896128-ec99105a-ec1a-4d85-b08b-1aa1620b2046.png" alt="MySQL" title="MySQL" /></code>
</div>

# Online Shop
***
### Introduce
***
This project is fully functioning web application that is an online shop. 
The main part is REST API that allows users:
- buy items
- manage sales
- managing users

### General information
***
**API is secured** - some actions are allowed only for proper user. There are three types of users:
- **user** - buy item
- **admin** - edit item information, change user account status, delete user
- **super-admin** - delete or add admin account

**MySQL** - this database is used. All tables can be generated automatically thanks to Hibernate. 
Now this option is disabled and all tables are generated by FlyWay. When the API is first run, 
the database is populated with initial data and three default users are created.

**API is responsive** - can be used on other electronic devices, not only on a computer.

**Tests** - the project include unit and integration tests. The scope of tests is to verify the operation of the Controllers 
and check that all layers together work correctly.

**Default accounts** - if you want to check all API features select one of the available default users

**SUPER ADMIN**

Username:
````
superAdminDef
````
Password:
````
Password123!
````

**ADMIN**

Username:
````
adminDef
````
Password:
````
Password123!
````

**USER**

Username:
````
userDef
````
Password:
````
Password123!
````



### Running the application
***

**1.** Clone the source code from Github:
````
https://github.com/Savilus/online-shop.git
````

**2.** Before starting the application for the first time, add an SQL database named 
`````onlineshopdb`````.

**3.** Check *application.properties* if datasource username and password are compatible with yours. 
The default setting is password and username from environment variables. 
If you don't have environment variables set, you should set it or change application.properties.

**4.** Run the API from command line or *AuctionServiceApplication.class*

**5.** Open ````http://localhost:8080```` in your browser.

**6.** Use default or register your own new user and buy what you want!


### Technologies
![Java](https://img.shields.io/badge/Java-17-blue.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.6-blue.svg)
![JUnit](https://img.shields.io/badge/JUnit-5.8.2-blue.svg)
![MySQL](https://img.shields.io/badge/MySQL-8.0.31-blue.svg)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.1.1-blue.svg)
![Project Lombok](https://img.shields.io/badge/Project%20Lombok-grey.svg)
![CSS](https://img.shields.io/badge/CSS-grey.svg)
![SCSS](https://img.shields.io/badge/SCSS-grey.svg)
![HTML](https://img.shields.io/badge/HTML-grey.svg)
![MAVEN](https://img.shields.io/badge/Maven-grey.svg)
![Mockito](https://img.shields.io/badge/Mockito-grey.svg)
***

Created by 
* Marcin Kołodziejczyk
* Michał Langner
* Jakub Łanoszka
