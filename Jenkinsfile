pipeline {
    agent any

    stages {
        stage ('Compile Stage') {

            steps {
                withMaven(maven : 'apache-maven-3.8.4') {
                    sh 'mvn clean compile'
                }
            }
        }

        stage ('Testing Stage') {

            steps {
                 withMaven(maven : 'apache-maven-3.8.4') {
                    sh 'mvn test'
                }
            }
        }


        stage ('Deployment Stage') {
            steps {
                 withMaven(maven : 'apache-maven-3.8.4'){
                    sh 'mvn deploy'
                }
            }
        }
    }
}