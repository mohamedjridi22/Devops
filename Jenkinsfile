pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                // Compile your Maven project
                sh 'mvn clean install'
            }
        }
    }
}
