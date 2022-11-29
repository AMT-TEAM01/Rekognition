# AMT : Labo Rekognition

Collaborators : Chiffelle Janis, Tomic Mario

# Getting ready to work on the project

This project uses Maven, java, and is developped using Intellij Ultimate Edition. Please, ensure that you have them installed.

## Opening the project and editing the files.

We use IntelliJ Ultimate Edition. It has a built in support for maven, making it easier to iterate over changes. To use our project, simply clone the repo, and open then open the project folder containing the pom.xml in Intellij. A prompt will appear to propose you to import the Maven project. Accept it.

### **Note: The steps that follow this one don't require IntelliJ. They can all be executed through the console/terminal, at the root of the repo.**

## Resolving the dependencies

Execute the following command :

`mvn dependency:resolve`

Maven will then proceed to download all the dependencies listed in the pom.xml file. 

## Settings

To use the service, you first need to install the AWS console : https://aws.amazon.com/cli/
You can then proceed to enter the credentials for a profile. We recommend using the default profile. If it's not possible, you can specify the profile name as an argument for when you launch the profile. 

## Compiling and running, local

To compile the project and generate an executable file, run `mvn package`. If needed, you can add the flag `-DskipTests` to allow the project to be compiled without having the tests that pass. 

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

## Misc

