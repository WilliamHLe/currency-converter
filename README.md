# **gr1921 project**

This is a group project for the course IT1901 Informatikk Prosjektarbeid I. We have decided to make a simple currency converter.
The goal of this course is to develop crucial skills in cooperation, project planning and source-code management. This includes learning
to use Git and using Git-repository manager GitLab.

## Requirements

You need to have a recent version of Java installed.

## Installation

1. Use any IDE thats supports Java and clone: 

```bash
https://gitlab.stud.idi.ntnu.no/it1901/gr1921/gr1921.git
```

Or use Git to clone:

```bash
 $ git clone https://gitlab.stud.idi.ntnu.no/it1901/gr1921/gr1921.git
```

2. Create module from existing sources and select build.gradle file in the root folder.

3. Build and run proejct.

## Usage


Mappene som utgjør største delen av kodingsprosjektet er modulene "core", "fxui", "restapi" og "restserver". \
"App.java" er filen som skal kjøres, den finner du i fxui/src/main/java/valutaKalk/fxui 

## Tests

## Technology/framework
* Java
* Gradle



## Team Members
*  Malin Holte
*  Sivert Hognes
*  William H. Le
*  Stefan Fongen





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
