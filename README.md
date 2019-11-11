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

3. Build and run project.

## Usage


The project consists of four modules; "core", "fxui", "restapi" and "restserver". \
"App.java" is the file to be runned and is located in fxui/src/main/java/valutaKalk/fxui \

You can find another README file inside the core module that describes the user history and a screenshot of the application.

## Tests
We use Jacoco to check the test coverage.


1. Run jacocoTestReport under the gradle tab. 
<img src="/uploads/4e26ced24e5ced2961c846bceea435b9/Skjermbilde_2019-11-07_kl._14.34.50.png"  width="250">


2. The test report is located under every module under
```bash
 /reports/tests/test/index.html.
```
<img src="/uploads/9ceb93349052727f3b962af0f3d08926/Skjermbilde_2019-11-07_kl._14.41.47.png"  width="250">




3. Open the .html file in your browser.


To run CheckStyle do the same with the folder CheckStyle.




## Technology/framework
* Java
* Gradle

### Tests and code quality
*  CheckStyle
*  Jacoco


## Team Members
*  Malin Holte
*  Sivert Hognes
*  William H. Le
*  Stefan Fongen


## Diagrams



### Sequence diagram



User saves the converted values

<img src="/uploads/ac5cac0a16dbbe3a5d7ad2fd86d16038/Skjermbilde_2019-11-11_kl._12.25.02.png"  width="800">



### Package diagram



<img src="/uploads/82397ce7df2b3945c3d634fa1b110054/75573795_3384639871578563_5147716985148145664_n.png"  width="800">



### Class diagram



<img src="/uploads/792c9ed06f040c386133d7400c141f03/Skjermbilde_2019-11-11_kl._14.22.34.png"  width="800">


