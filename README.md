# AMT : Labo Rekognition

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
> TODO c'est insuffisant comme explications, il n'y a même pas de lien et vous ne mentionnez pas de quel profile vous parlez. Ces explications sont a destination de quelqu'un d'externe au projet.

> TODO (pas pénalisé) ajoutez des explications sur ce que votre programme demande comme configuration. Ici je pense que le profile AWS que j'utilise n'a pas les droits sur votre bucket. Vous devez expliquer dans votre readme au minimum ce qui est nécessaire de configrurer et si possible comment le configurer. 


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

## Misc

> TODO un bon conseil si vous voulez que vous assurer que votre doc permet a un quelqu'un d'externe au projet de faire tourner votre programme c'est de donner votre projet à quelqu'un d'externe et de lui demander de le run. Si il vous pose des questions c'est que votre doc est incomplète.

> TODO vos conventions de nommage ne sont pas respectées : `Files and packages are names in UpperCamelCase`, vos packages ne sont pas en UpperCamelCase, vos fichiers non plus (eg: test/java/ch/heig/vd/images/aws.jpg). Ne vous mettez pas des batons dans les roues en définissant des rêgles qui sont difficiles à suivre.

> TODO Votre argumentation des technologies utilisées ne me parait pas très construite et vous ne mentionnez jamais d'alternative a votre choix.

> Pas pénalisé mais généralement les images et les text sont stockés dans un dossier resources dans test/java/resources/...
