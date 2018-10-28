Practical Task:
Practical task is a spring boot application availing crud cars api.

To build the application use the command : mvn clean install in same path as pom.xml
Before building the application you have to set the database url,username,password in application.properties and run the below scripts:
	1.User creation script from  Evolvice\Dockers_Final\OracleDatabase\SingleInstance\12.2.0.1\scripts
	2.Using flyway to run table and sequence creation under Evolvice\Dockers_Final\flyway


To run the application use the command : java -jar ./target/evolvice-0.0.1-SNAPSHOT.jar

You will find the rest documentation under \Evolvice\evolvice\demo\target\generated-docs

API authentication:
User:admin
Password:adminPassword

To run application on docker you have to apply the below steps:

	1.Create network in docker use the below command we need it for all containers to connect to each other :
			docker network create --subnet=172.18.0.0/16 customnetwork

	2.Build of oracle database from path Evolvice\Dockers_Final\OracleDatabase\SingleInstance\12.2.0.1 using command:
			docker build -t oracledb .

	3.Run oracle docker image using command :
			Note: You have to change path D:\kashif\Work\Evolvice\Dockers_Final\OracleDatabase\SingleInstance\12.2.0.1\scripts to the folder path of scripts on the machine as relative path didnt work with this command in windows
			
			docker run -d -it -p 1522:1521 --net customnetwork --ip 172.18.0.15 -v D:\kashif\Work\Evolvice\Dockers_Final\OracleDatabase\SingleInstance\12.2.0.1\scripts:/opt/oracle/scripts/setup --name=oraclecustomernetwork oracledb

	4.Run flyway image to apply database migration:
			Note: You have to change path D:\kashif\Work\Evolvice\Dockers_Final\flyway to the folder path of flyway folder on the machine as relative path didnt work with this command in windows
			
			docker run  --net customnetwork --rm -v D:\kashif\Work\Evolvice\Dockers_Final\flyway/sqldir:/flyway/sql -v D:\kashif\Work\Evolvice\Dockers_Final\flyway/confdir:/flyway/conf -v D:\kashif\Work\Evolvice\Dockers_Final\flyway/driverdir:/flyway/drivers boxfuse/flyway migrate

	
	5.Build of practical task application image from path Evolvice\evolvice\demo using command:
			docker build -t evolvicetask .

	6.docker image run :
			docker run -d -it -p 8081:8080 --net customnetwork --name=evolvicecontainer evolvicetask