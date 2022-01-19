pipeline {
    agent any
	tools {
    maven 'maven-3.8.4' 
  	}
    stages {
        stage ('Compile Stage') {

            steps {
                withMaven{
                    sh 'mvn clean compile'
                }
            }
        }

        stage ('Testing Stage') {

            steps {
                 withMaven {
                    sh 'mvn test'
                }
            }
        }


        stage ('Sonar Stage') {
            steps {
                 withMaven{
                    sh 'mvn sonar:sonar'
                }
            }
        }
    }
}