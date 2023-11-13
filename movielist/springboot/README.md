# Springboot
## About springboot

SpringBoot is a framework that implements REST API for building Java-based web applications. REST API is a web service that uses HTTP requests to perform CRUD (Create, Read, Update, Delete) operations. 
[Source](https://folk.ntnu.no/olso/wu/rest/rest.html)
**MÅ SJEKKE OM VI KAN KILDEFØRE SLIK**

For more information on how the Movie App works, see: [Release3 README.md](https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2336/gr2336/-/blob/fc9c16f967deb79b1ca3a573d488ae8833a76ded/docs/release3/README.md) or [Overall README.md](https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2336/gr2336/-/blob/35ca9ff71abb5b937204ef06ec9f3f67d3bceb0e/README.md)

## Architecture MÅ OPPDATERES!!!!
The SpringBoot folder is responsible for handling the backend logic of the Movie App, and includes:
1. Controllers
2. Services
3. Repositoires **SLETT OM IKKE STEMMER**
4. Models

A more indepth look to these four main points can be found in _Detailed Description_.

## How to use LEGG TIL NETTADRESSER ETC.
To run the SpringBoot application there are a few tools that are needed to follow:
1. Build project: mvn clean install
2. Start SprinBoot: mvn spring-boot:run
3. When you get confirmation that the app is running, you can access the Rest API with the following URL: **LEGG TIL URL**

## Detailed Description
### Controller Classes
**ClassName:** blabla
_Filepath:_

### Service Classes


### Other OPPDATER!!
The springboot folder also contain a few other files. The *pom.xml* has specific springboot configuration, as well as specifying project dependencies, plugins and other related settings. 

## Codequality
All fields and methods have a javadoc comment explaining what the field is, or what the method does, what the parameters are and whether it returns anything.

The code adheres to the standards of sun_checks.xml, which is the built-in Sun's Style for checkstyle. This is specified in the pom in the root directory, and to test this you therefore need to be in the root directory (i.e movielist). From here you can write: mvn checkstyle:check. If it's not working, you might need to write "mvn clean" and "mvn compile" or "mvn install -DskipTests" beforehand.

The code also can be checked with spotbugs, which is sspecified in the pom.xml in ui. To check this you therefore need to be in movielist\springboot. From here you can write "mvn spotbugs: check". **SKRIV FORVENTET RESULTAT AV KJØRINGEN**
