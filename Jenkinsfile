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
                 {
                    bat 'mvn clean compile'
                }
            }
        }

        stage ('Testing Stage') {

            steps {
                 {
                    bat 'mvn test'
                }
            }
        }
        stage ('Deployment Stage') {
            steps {
                 {
                    bat 'mvn deploy'
                }
            }
        }
    }
}