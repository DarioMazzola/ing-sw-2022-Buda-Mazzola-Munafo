# Final test Software Engineering 2022

## Group PSP21

- 10675063    [**Alessio Buda**](https://github.com/alessio-buda) <br>alessio.buda@mail.polimi.it
- 10650009    [**Dario Mazzola**](https://github.com/DarioMazzola) <br>dario.mazzola@mail.polimi.it
- 10654531    [**Gabriele Munafo'**](https://github.com/GabrieleMunafo) <br>gabriele.munafo@mail.polimi.it

**Eriantys** Board Game is the final test of the **"Software Engineering"** course of **"Computer Science Engineering"**, held at Politecnico di Milano (2021/2022).

**Teacher**: Pierluigi San Pietro

## Project specification 

The project consists in the implementation of the board game **"Eriantys"** by **Cranio Creations**. The game is implemented in Java, following the MVC pattern, as a distributed system composed of a single server and multiple clients (one for each player). The network connection is handled by using sockets.

The game can be played both by command line interface (CLI) and graphic user interface (GUI).

## Documentation

### UML

The following UML class diagrams represent the initial model of the architecture as developed during the design phase and the final implementation.

- [UML initial design](https://github.com/DarioMazzola/ing-sw-2022-Buda-Mazzola-Munafo/blob/main/deliverables/UML_initial_design.png)
- [UML final implementation](https://github.com/DarioMazzola/ing-sw-2022-Buda-Mazzola-Munafo/blob/main/deliverables/UML_final_implementation.png)

### Coverage report

A coverage report generated by JUnit can be found at the following link: [Report](https://github.com/DarioMazzola/ing-sw-2022-Buda-Mazzola-Munafo/tree/main/deliverables/report)

To correctly visualize the coverage report, clone the repository and open the "index.html" file in deliverables/report with a browser.

### Libraries and plugins

| Library |                                                                                                  Descriprion                                                                                                   |
|:--------|:--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
| Maven   | Software project management and comprehension tool. Based on the concept of a project object model (POM), Maven can manage a project's build, reporting and documentation from a central piece of information. |
| JUnit   |                                                                                           Framework for unit testing                                                                                           |
| JavaFX  |                                                                               Graphic library used to realize the user interface                                                                               |
| Gson    |                   Java library that can be used to convert Java Objects into their JSON representation. It can also be used to convert a JSON string to an equivalent Java object.                   |


## Functionality

| Functionality                |     |
|:-----------------------------|:---:|
| Basic rules                  | 🟢  |
| Complete rules               | 🟢  |
| Socket                       | 🟢  |
| GUI                          | 🟢  |
| CLI                          | 🟢  |
| All Character Cards          | 🟢  |
| Four players match           | 🟢  |
| Multiple matches             | 🔴  |
| Persistence                  | 🟢  |
| Resilience to disconnections | 🔴  |

**Legend**: 🟢 Implemented   🔴 Not implemented

## Compiling 

After installing and downloading required software, clone this repo by either downloading the .zip and extract it, or using git clone.

Open a terminal, navigate to the folder and compile sources of the package by typing:

```
cd /path/to/project/home/directory
mvn clean package
```

A new folder called "target" will be created in the project home directory, inside it you will find the following jar files:

- `Eriantys-client.jar`: launches the client (CLI or GUI can be chosen)
- `Eriantys-server.jar`: launches the server

### Jar

Precompiled Jars can be found at the following link: [Jar](https://github.com/DarioMazzola/ing-sw-2022-Buda-Mazzola-Munafo/tree/main/deliverables/jar)

## Execution

This project requires Java 11 or higher to be correctly executed.

### Eriantys client

For a better user experience, we suggest using a macOS/Linux terminal. If you run the project on a Windows system, we recommend using Windows Terminal instead of Command Prompt to correctly visualize all characters.

To launch Eriantys client enter the following command by opening a terminal in the "target" directory:

```
java -jar Eriantys-client.jar
```

Then chose whether to play with CLI or GUI by entering 1 or 2

### Eriantys server

To launch Eriantys server enter the following command by opening a terminal in the "target" directory:

```
java -jar Eriantys-server.jar
```
