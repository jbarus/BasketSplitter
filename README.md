# Basket Splitter</h1>

## Table of contents
* [General Info](#general-info)
* [Technical Info](#technical-info)
* [Technologies](#technologies)
* [Setup](#setup)

## General Info
This project is my attempt to create an efficient program that splits products into groups based on 
delivery method. Then it can be used in an external solution to calculate the most efficient way to 
split orders.


## Technical Info
* Pre-first use:<br>
Before first use you need to prepare config.json file. is responsible for providing data regarding 
products and their transportation methods. This file has a precise structure and should look something like this:<br>
```json
{
    "product1": ["transportMethod1", "transportMethod2"],
    "product2": ["transportMethod1", "transportMethod2", "transportMethod3"],
    "product3": ["transportMethod1", "transportMethod2"],
    "product4": ["transportMethod1"]
}
```

* Way it works:<br>
Underlying algorithm is pretty straightforward. Firstly, it fetches products from config file and then 
puts all methods of transport in HashMap in such a way:

| Key                    | Value                            |
|------------------------|----------------------------------|
| "transportMethod1"     | "product1","product2","product3" |
| "transportMethod2"     | "product1"                       |
| "transportMethod3"     | "product1","product2"            |

Then it gets a transport method with most products and puts it in similar result HashMap. After removal of 
the longest list items from it are removed from other positions (so they won't duplicate in the result). 
The process repeats itself until there are no items to distribute.

* Output:<br>
split() function outputs```HashMap<String, List<String>>``` with items grouped into categories in descending 
order:

| Key                    | Value                            |
|------------------------|----------------------------------|
| "transportMethod1"     | "product1","product2","product3" |
| "transportMethod2"     | "product1","product2"            |
| "transportMethod3"     | "product1"                       |

## Technologies
Project is created with:
* Java version: 21
* [Exec Maven Plugin](https://www.mojohaus.org/exec-maven-plugin/) version: 3.2.0
* [JUnit](https://junit.org/junit5/) version: 5.9.2
* [Jackson](https://github.com/FasterXML/jackson) version: 2.17.0
* [Maven Shade Plugin](https://maven.apache.org/plugins/maven-shade-plugin/) version: 3.2.4

## Setup
Setup of this project is pretty straight forward. There are two ways of installing this library:
* You can use it as a standalone project:
1. Download the project using ```git clone```
2. Add main class and method in com.ocado.basket directory
3. Compile and use as a standalone project

* Or you can use it as a library in some project:
1. You can head to releases tab and download compiled library (it is a fat jar, so all dependencies are included).
2. Add following dependency tou you pom.xml file:
```xml
<dependency>
    <groupId>com.ocado.basket</groupId>
    <artifactId>basket</artifactId>
    <version>1.0</version>
    <scope>system</scope>
    <systemPath>${path_to_library}</systemPath>
</dependency>
```
3. Alternatively you can download source code and compile it yourself using ```mvn clean package```. 
Output library should be in the target directory under basket-1.0 name.
4. Add mentioned earlier dependency.