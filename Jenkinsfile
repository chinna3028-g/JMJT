pipeline {
    agent any

    tools {
        maven "MAVEN_HOME"
    }

    stages {
        stage('Build') {
            steps {
                // Get some code from a GitHub repository
                git 'https://github.com/chinna3028/JMJT.git'

                

                // To run Maven on a Windows agent, use
                 bat "mvn clean package"
            }

          
        }
    }
}
