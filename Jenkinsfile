pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Check out your source code from your version control system (e.g., Git)
                checkout scm
            }
        }
        stage('Git') {
            
            steps {
                echo 'Getting project from Git'
                  git "https://github.com/mohamedjridi22/Devops.git"
            }
        }
        
        
    }
}
