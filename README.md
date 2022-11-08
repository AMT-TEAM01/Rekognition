#AMT : Labo Rekognition

Collaborators : Chiffelle Janis, Tomic Mario

# Getting ready to work on the project

This project uses Maven, java, and is developped using Intellij Ultimate Edition. Please, ensure that you have them installed.

## Opening the project

Simply clone the repo, and open then open the project folder containing the pom.xml in Intellij. A prompt will appear to propose you to import the Maven project. Accept it.

## Resolving the dependencies

Execute the following command, where the pom.xml is located

`mvn dependency:resolve`

Maven will then proceed to download all the dependencies listed in the pom.xml file. 

## Settings

The profile used is the default profile. Please configure your default profile with the credentials for it to work.

## Compiling and running, local

To compile the project and generate an executable file, run `mvn package` 

To run the project:

### Windows

Execute the `run_win.bat` script, at the root of the project (where pom.xml is located)

### Linux

1. Give execution right to the script with `chmod +x run_linux.sh`, at the root of the project (where pom.xml is located)
2. Execute the script with `./run_linux` 

## Running the deployed version

Simply run the run.sh file with `./run.sh` in the `Rekognition` folder on the deployed machine. 

## Testing

Tests are implemented using jUnit 5. To run all the tests, you can simple run `mvn test`.
To run a specific test without triggering all the other ones, run `mvn test -Dtest="[TestClassName]#[FunctionName]"`
