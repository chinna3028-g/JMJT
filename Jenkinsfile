pipeline {
    agent any

	tools {
       
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