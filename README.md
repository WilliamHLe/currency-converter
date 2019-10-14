Group members;

*  Malin Holte
*  Sivert Hognes
*  William H. Le
*  Stefan Fongen

Mappene som utgjør største delen av kodingsprosjektet er modulene "core", "fxui", "restapi" og "restserver". \
"App.java" er filen som skal kjøres, den finner du i fxui/src/main/java/valutaKalk/fxui 

**README-filen inni "core" mappa inneholder beskrivelse av appen, brukerhistorier og et snapshot av hvordan appen ser ut.**


Trestruktur av mappene:

|-- gr1921
    |-- .DS_Store
    |-- .gitignore
    |-- .gitlab-ci.yml
    |-- README.md
    |-- build.gradle
    |-- gradlew
    |-- gradlew.bat
    |-- settings.gradle
    |-- .idea
    |   |-- ...
    |-- **core**
    |   |-- .DS_Store
    |   |-- README.md
    |   |-- build.gradle
    |   |-- gradlew
    |   |-- gradlew.bat
    |   |-- settings.gradle
    |   |-- valuta.txt
    |   |-- ...
    |   |-- ...
    |   |-- src
    |       |-- main
    |           |-- java
    |               |-- Valuta.java
    |               |-- valutaKalk
    |                   |-- core
    |                       |-- AppIO.java
    |                       |-- AppIOInterface.java
    |                       |-- JSON.java
    |                       |-- Valuta.java
    |                       |-- ValutaObjectLoader.java
    |-- **fxui**
    |   |-- build.gradle
    |   |-- gradlew
    |   |-- gradlew.bat
    |   |-- settings.gradle
    |   |-- valuta.json
    |   |-- valuta.txt
    |   |-- src
    |       |-- main
    |       |   |-- java
    |       |   |   |-- valutaKalk
    |       |   |       |-- fxui
    |       |   |           |-- App.java
    |       |   |           |-- ValutakalkulatorController.java
    |       |   |-- resources
    |       |       |-- valutaKalk
    |       |           |-- fxui
    |       |               |-- App.fxml
    |       |-- test
    |           |-- java
    |               |-- valutaKalk
    |                   |-- AppTest.java
    |-- ...
    |-- **restapi**
    |   |-- build.gradle
    |   |-- gradlew
    |   |-- gradlew.bat
    |   |-- settings.gradle
    |   |-- ...
    |   |-- src
    |       |-- main
    |           |-- java
    |               |-- valutaKalk
    |                   |-- restapi
    |                       |-- ValutaService.java
    |-- **restserver**
        |-- build.gradle
        |-- settings.gradle
        |-- src
            |-- main
                |-- java
                    |-- valutaKalk
                        |-- restserver
                            |-- ValutaConfig.java
                            |-- ValutaGrizzlyApp.java
