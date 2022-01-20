pipeline {
    agent any

    tools {
        maven "MAVEN_HOME"
    }

    stages {
        stage('Build') {
            steps {
                
                 bat "mvn clean install"
            }

          
        }
         stage('Test') {
            steps {
                
                 bat "mvn test"
            }

          
        }
        stage('Sonar') {
            steps {
                
                 bat "mvn sonar:sonar"
            }

          
        }
        
    }
}
