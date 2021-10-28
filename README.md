### Text Search Command Line Utility

The purpose of this project is to write a command line driven text search engine.

This utility reads the all files from provided directory path.
Building an ​in-memory​ representation of the files
and their contents, and then give a command prompt at which interactive searches can be performed. 

The search should take the words given on the prompt and return a list of the top 10 (maximum)
matching filenames in rank order, giving the rank score against each match.

### Ranking
- The rank score must be 100% if a file contains all the words
- It must be 0% if it contains none of the words
- It should be between 0 and 100 if it contains only some of the words.

### Requirements
Download and install `JDK` and `Maven`
* [Java 8](https://www.oracle.com/in/java/technologies/javase/javase-jdk8-downloads.html)
* [Apache maven](https://maven.apache.org/download.cgi)


#### How to build
Go to the application source code directory 
```
mvn clean package
```
It will create a JAR file in `target` folder.

#### How to run
```
java -jar target/text-search-engine-0.0.1-SNAPSHOT.jar ./src/main/resources/resource-dir 
```
Type `:quit` to stop the application


Run all in once
```
$mvn clean package && java -jar target/text-search-engine-0.0.1-SNAPSHOT.jar ./src/main/resources/resource-dir

{12} files read in directory {./src/main/resources/resource-dir}

search> This is the first resource file
resourceFile1.txt : 100%
resourceFile10.txt : 83%
resourceFile7.txt : 83%
resourceFile2.txt : 83%
search> Non existing word
no matches found 
search> :quit
```
