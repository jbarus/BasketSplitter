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
Underlying algorithm is pretty straightforward. Firstly, it fetches products from config file and then counts 
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

## Setup
