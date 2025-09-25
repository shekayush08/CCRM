Campus Course & Records Manager (CCRM)
Project Overview
The Campus Course & Records Manager (CCRM) is a console-based Java application designed to manage student, course, and enrollment data for an academic institution. Built as a Java SE project, it showcases a wide range of Java programming concepts, including Object-Oriented Programming (OOP) principles, modern APIs like NIO.2 and the Date/Time API, and several key design patterns.

How to Run the Program
To run this application, you will need the Java Development Kit (JDK) installed on your machine. This project was developed using JDK 17.

Clone the Repository:

git clone [Your Repository URL]

Navigate to the Project Directory:

cd CCRM

Compile the Source Code:

javac -d bin -sourcepath src src/edu/ccrm/cli/CliMenu.java

Run the Application:

java -cp bin edu.ccrm.cli.CliMenu

Evolution of Java
Java's journey from its inception to its current state reflects its adaptability and enduring relevance.

1995: Initial release by Sun Microsystems. Designed to be "write once, run anywhere."

1998: Introduction of the Java Platform, Standard Edition (J2SE).

2004: Java 5 introduces major features like generics, annotations, autoboxing, and enums.

2014: Java 8 marks a significant milestone with the introduction of lambda expressions and the Stream API, enabling functional-style programming.

2017 & Onwards: A new six-month release cadence begins, leading to rapid feature releases and more predictable updates.

Java SE vs. ME vs. EE
Feature

Java SE (Standard Edition)

Java ME (Micro Edition)

Java EE (Enterprise Edition)

Purpose

Desktop & general-purpose applications

Embedded systems & mobile devices

Enterprise-level applications (e.g., web services)

Typical Use

Console apps, desktop GUI apps

IoT devices, feature phones

Server-side applications, web apps

Key APIs

Core libraries, Swing, JavaFX

A subset of SE, specific APIs for small devices

Includes SE and APIs for enterprise tasks like JDBC, Servlets, EJBs

JDK vs. JRE vs. JVM
JDK (Java Development Kit):  This is a software development environment used for developing Java applications and applets. It includes the JRE, along with compilers and debuggers.

JRE (Java Runtime Environment): This is the environment required to run a Java application. It includes the JVM, core classes, and supporting files.

JVM (Java Virtual Machine):  The core of the Java platform, the JVM is an abstract machine that provides the runtime environment in which Java bytecode is executed. It interprets the bytecode and translates it into machine code for the specific hardware.

Mapping of Syllabus Topics
Syllabus Topic

File/Class/Method

Inheritance & Polymorphism

Student & Instructor classes (extending a base Person class, not implemented yet)

Abstraction

Builder interface (Builder.java)

Encapsulation

Student and Course classes (private fields with public getters/setters)

Exception Handling

Custom exceptions like DuplicateEnrollmentException (not implemented yet)

NIO.2

RecursiveDirectoryInfo.java (using Files.walkFileTree)

Streams & Lambdas

CourseService.java (searchAndFilterCourses method)

Enums

StudentStatus.java (not implemented yet)

Recursion

RecursiveDirectoryInfo.java (listDirectoryRecursive method)

Singleton Design Pattern

AppConfig.java

Builder Design Pattern

StudentBuilder.java

Notes on Assertions
Assertions are a tool for debugging and testing your program's assumptions. To enable them, run the program with the -ea flag.

java -cp bin -ea edu.ccrm.cli.CliMenu

Acknowledgements
This project was completed as part of the Programming in Java course. Special thanks to [Professor/Instructor Name] for their guidance.