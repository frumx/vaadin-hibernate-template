vaadin-hibernate-demo
==============

Template for a simple Vaadin application (saving bookmarks to a MySQL db)
requires a servlet container to run.


Workflow
========

Setup for the MySQL db:
create schema bookmarks_db; 
then run import.sql to create table and insert some values.

To compile the entire project, run "mvn install".

To run the application, run "mvn jetty:run" and open http://localhost:8080/ .

To produce a deployable production mode WAR:
- change productionMode to true in the servlet class configuration (nested in the UI class)
- run "mvn clean package"
- test the war file with "mvn jetty:run-war"

