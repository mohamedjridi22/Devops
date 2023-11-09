pipeline {
    agent any
    tools{
         maven 'M2_HOME'
     }
    stages {
         stage('Checkout') {
               steps {
                // Check out your source code from your version control system (e.g., Git)
                checkout scm
            }
         }
    }
}
