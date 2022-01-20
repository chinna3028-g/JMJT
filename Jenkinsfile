pipeline {
    agent any
	
    stages {
      stage ('Git') {
         steps {
        git 'https://github.com/chinna3028/JMJT.git'
      }
    }
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