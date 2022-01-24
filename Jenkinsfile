pipeline {
    agent any

    tools {
		maven "MAVEN_HOME"
        jdk "JAVA_HOME"
    }
	
  stages {
  
        stage ('Code Build Stage') {

            steps {
                git branch: 'main', credentialsId: 'bf1f2b8e-926b-4d3f-9b2b-543247e82ebf', url: 'https://github.com/chinna3028/JMJT.git'
            }
        }
        stage ('Clean, Installand Compile Stage') {

            steps {
                bat 'mvn clean install compile'
            }
        }

        stage ('Testing Stage') {

            steps {
                bat 'mvn test'
            }
        }
        stage ('Code Coverage Stage') {
            steps {
                jacoco()
            }
        }
        stage('Sonar Report Stage') {
            steps {
				bat 'mvn sonar:sonar'
          }
        }
    }
}