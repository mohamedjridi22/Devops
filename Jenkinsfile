pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Check out your source code from your version control system (e.g., Git)
                checkout scm
            }
        }
        stage('Build') {
            steps {
                // Compile your Maven project
                sh 'mvn clean install'
            }
        }
    }
}
