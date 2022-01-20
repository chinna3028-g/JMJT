pipeline {
    agent any

	tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "MAVEN_HOME"
    }
  stages {
  
      stage ('Git') {
         steps {
        git 'https://github.com/chinna3028/JMJT.git'
      }
    }
        stage ('Compile Stage') {

            steps {
                 withMaven{
                    bat 'mvn clean compile'
                }
            }
        }

        stage ('Testing Stage') {

            steps {
                 withMaven{
                    bat 'mvn test'
                }
            }
        }
        stage ('Deployment Stage') {
            steps {
                 withMaven{
                    bat 'mvn deploy'
                }
            }
        }
    }
}