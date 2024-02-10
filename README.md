# :mortar_board: SpringSchoolApiRest :mortar_board:

Welcome to my Api Rest with Spring Framework. :fire::rainbow: A small work simulating a quite fake school structure. 


## Table of contents

- [Installation](#installation)
- [Usage](#usage)
- [It's not a bug, it's a feature](#features)
- [Maintainers](#maintainers)
- [Contributing](#contributing)
- [License](#license)


## Installation

Just cloning or downloading the project. Other option is to use the online version

## Usage

- Check and try the usage of [SpringSchoolApiRest](https://springschoolapirest.onrender.com/swagger-ui/index.html#/) using Open API and Swagger UI hosted in [Render](https://render.com/). But be quick, free stuff doesn't last long :wink:

If not, ensure you add the path /api/ to your requests in Postman, Httpiness or similar program

```
** Get a List of all teachers **
https://springschoolapirest.onrender.com/api/teachers

**  Get a teacher according to an Id **
https://springschoolapirest.onrender.com/api/teachers/1
```

- Maybe you prefer to open it from you favorite IDE

- Or you could use the jar file hosted in /target. For this, run this command from the folder and send your HTTP request to localhost:8080

```
java -jar school-api-rest-1.0.jar
```

Example of URL for and HTTP GET request in local environment

```
**  Get a student according to an Id **
localhost:8080/api/students/2
```

- Also, you can use [Docker](https://docs.docker.com/engine/install/). Whether it is your flavor, first, from the inside of the main project folder, build the image using the settings from Dockerfile

```
docker build -t spring-school-api:latest .
```

Then create and run a container with, for instance, these settings

```
docker run --name spring-school-api-container -p 8080:8080 -d spring-school-api
```


## Features

### :zap: Spring Security full extra deluxe

### :zap: Authentic Oauth2 with authentic GitHub authentication 

### :zap: JUnit test everywhere


## Maintainers

Just me, [Iván](https://github.com/Ivan-Montes) :sweat_smile:


## Contributing

Contributions are always welcome! 


## License

[![Java](https://badgen.net/static/JavaSE/17/orange)](https://www.java.com/es/)
[![Maven](https://badgen.net/badge/icon/maven?icon=maven&label&color=red)](https://https://maven.apache.org/)
[![Spring](https://img.shields.io/badge/spring-blue?logo=Spring&logoColor=white)](https://spring.io)
[![GitHub](https://badgen.net/badge/icon/github?icon=github&label)](https://github.com)
[![Eclipse](https://badgen.net/badge/icon/eclipse?icon=eclipse&label)](https://https://eclipse.org/)
[![SonarQube](https://badgen.net/badge/icon/sonarqube?icon=sonarqube&label&color=purple)](https://www.sonarsource.com/products/sonarqube/downloads/)
[![GPLv3 license](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://choosealicense.com/licenses/gpl-3.0/)