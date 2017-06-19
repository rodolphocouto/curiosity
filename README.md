# Curiosity

[![Build Status](https://travis-ci.org/rodolphocouto/curiosity.svg?branch=master)](https://travis-ci.org/rodolphocouto/curiosity)
[![Coverage Status](https://coveralls.io/repos/github/rodolphocouto/curiosity/badge.svg?branch=master)](https://coveralls.io/github/rodolphocouto/curiosity)

REST API to move NASA's rover on Mars

### Prerequisites

Java >= 1.8

### Building

This project has Maven Wrapper, so it is not necessary to have Maven installed.

Building with tests:

```
./mvnw clean verify
```

Building ignoring tests:

```
./mvnw clean verify -DskipTests
```

### Running

After building, the Spring Boot Plugin will generate a fat JAR file in the `target` directory. To run it:

```
java -jar target/curiosity.jar
```

The HTTP server will start on port 8080.

### Commands

To send commands to the rover, it is necessary to make a `POST` request to `http://localhost:8080/rest/mars/{input}`, where input can be something like:

* `L` to move 90° to the left;
* `R` to move 90° to the right;
* `M` to move 1 position forward.

After executing the movements, the rover will return the last position in this format: `(x,y,d)`, where:

* `x` represents the position X;
* `y` represents the position Y;
* `d` represents the initial of cardinal direction (`N`orth, `E`ast, `S`outh and `W`est).

### Examples

```
curl -s --request POST http://localhost:8080/rest/mars/MMRMMRMM
200 OK: (2,0,S)

curl -s --request POST http://localhost:8080/rest/mars/MML
200 OK: (0,2,W)

curl -s --request POST http://localhost:8080/rest/mars/AAA
400 Bad Request: 'A' is not a recognized movement. Consider just these: (L,R,M)

curl -s --request POST http://localhost:8080/rest/mars/MMMMMMMMMMMMMMMMMMMMMMMM
400 Bad Request: The (0,6,N) position is out of the bounds (0 to 5, 0 to 5)
```