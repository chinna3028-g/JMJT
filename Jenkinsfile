pipeline {
    agent any

    tools {
        maven "MAVEN_HOME"
    }

    stages {
        stage('Build') {
            steps {
                // Get some code from a GitHub repository
                

                

                // To run Maven on a Windows agent, use
                 bat "mvn clean package"
            }

          
        }
    }
}
