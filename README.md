## Content

The project is located inside the folder movielist. Within the project, there are two primary dividers:

1. core

    Here you can find the "backend" code for movie. This includes attributes for movie and movielist, and how these work. The movieListHandler handles reading and writing to the json file. You can also find tests for the code here.

    Movie and MovieList class: movielist/core/src/main/java/core
    Filehandler-class: movielist/core/src/main/java/filehandler
    Tests: movielist/core/src/test/java/core

2. ui

    ui stands for user interface. Here you can find all the code controlling how the app interacts with the user, such as the appcontroller and the App-class that launches the application. The json-file that is used for file storage can also be located within ui. Tests for the code in ui are here as well. Lastly, ui consists of the data-access classes that are used to interact with the REST-API that was implemented in release 3.


    Application and its controllers: movielist/ui/src/main/java/ui
    FXML-files: movielist/ui/src/main/resources/ui
    Json-file: movielist/ui/src/main/java/json
    AppTest: movielist/ui/src/test/java/ui
    Data-access classes for REST-API:  movielist/ui/src/main/java/dataaccess


The third folder within movielist is springboot:

3. springboot

    The REST-API of this application was built using springboot. This folder therefore includes all the classes necessary to communicate the core logic of the application with the REST-API. Movielist can be run remotely or locally, which the classes within this folder makes sure of. More info regarding REST-API and springboot can be found within the README.md-file in this folder.

    Application, service, rest-controller and more: movielist/springboot/src/main/java/movielist/springboot

Outside of the movielist-folder, information regarding documentation can be found. You can find both a folder named "docs", as well as an "assets"-folder: 

1. docs

    Here you can find all the documentation and illustrations of how the project works. It's sorted into releases.

    Release 1: docs/release1
    Release 2: docs/release2
    Release 3: docs/release3

2. assets

    The "assets"-folder is where all pictures and other media are stored. This mainly includes screenshots regarding the application and its functionality. Filepaths leading to these assets are provided in the corresponding README.md-files. Diagrams exported as png are also included in the assets-folder, sorted within their respective release-folder.

    Release 1: assets/release1
    Release 2: assets/release2
    Release 3: assets/release3

## Eclipse Che
This project is Eclipse Che ready. 

You can find the environment through the link: 
https://che.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2336/gr2336

If meeting any trouble running the project, please "mvn clean install" inside the movielist folder. Then run "mvn javafx:run", "mvn test" and/or other prefered maven commands within the gr2336/movielist/ui folder.

## Dependecies
Following JAVA version is needed: 16.0.1
Following Maven version is needed: Maven Apache 4.0.0
Gson: Version 2.8.8

*See POM files for more detailed information*