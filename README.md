## Content

The project is located inside the folder movielist. Within the project, there are two primary dividers:
1. core

    Here you can find the "backend" code for movie. This includes attributes for movie and movielist, and how these work. You can also find tests for the code here.

    Movie and MovieList class: movielist/core/src/main/java/core
    Json file: movielist/core/src/main/java/json
    Tests: movielist/core/src/test/java/core

2. ui

    ui stands for user interface. Here you can find all the code for how the app interacts with the user, such as the appcontroller or the class that launched the app. You can also find tests for the code in ui here

    App and AppController: movielist/ui/src/main/java/ui
    FXML-file: movielist/ui/src/main/resources/ui
    AppTest: movielist/ui/src/test/java/ui

Additionally, there is a folder named "docs", as well as an "assets"-folder: 
1. docs

    Here you can find all the documentation and illustrations of how the project works. It's sorted into releases

    Release 1: docs/release1
    Release 2: docs/release2
    Release 3: docs/release3

2. assets

    The "assets"-folder is where all pictures and other media are stored. This mainly includes screenshots regarding the application and its functionality. Filepaths leading to these assets are provided in the corresponding README.md-files. 

## Dependecies
Following JAVA version is needed: 16.0.1
Following Maven version is needed: Maven Apache 4.0.0
Gson: Version 2.8.8

*See POM files for more detailed information*