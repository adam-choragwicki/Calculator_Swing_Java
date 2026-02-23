# Calculator

Java implementation of a simple calculator using:
* OpenJDK 18
* Java Swing library
* Maven 3.8.1.

![](screenshots.jpg)

**Executable JAR is provided.**

### Features:
* Supports all basic mathematical operations and parentheses.

Internally, it performs infix-to-postfix expression conversion followed by postfix expression evaluation. It also provides visual feedback in case the input expression is ill-formed.

# Tests
Unit tested using JUnit 5.9.0

# How to run
#### Prerequisites:
* Java 16 or higher

1. Download the latest release zip from:\
   https://github.com/adam-choragwicki/Calculator_Swing_Java/releases/latest/download/Calculator_Swing_Java.jar

2. Run `java -jar Calculator_Swing_Java.jar`