# Demo payment module with Spring
This is a project that simulates a payment within a universe of microservices. 
The goal is not really to make a payment in the real world but only to practice 
the use of tools learned in the trainings.

The module is built using spring boot framework and maven as dependency manager. 
Communication with other modules is based on the publish/subscribe concept used
in Kafka and docker as a containerizer.

Data persistence will be carried out in the main API layer that is responsible
to communicate the data with the web application(fake application).

# Requirements
* Java 11
* Maven 3.10.1
* Docker
* Apache Kafka

# Installation
You need run the docker-compose that will generate 3 zookeeper and 3 kafka brokers.
After that, only run the application using mvn spring-boot:run.

The main idea to maintain 3 brokers is to use kafka best practice where we create 3
partitions in different brokers and with that we create a safer environment
in case of any server shutdown.

# How to use
This module can be tested using kafka-console-producer to generate the messages or 
using another application that publish the payment order.
