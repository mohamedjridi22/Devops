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
        stage('Git') {
            
            steps {
                echo 'Getting project from Git'
                git branch :'jridi'  ,
                url : 'https://github.com/mohamedjridi22/Devops.git'
            }
        }
          stage ('MVN clean'){
            steps{
                sh 'mvn clean';
           }    
        }
     
            stage('MVN SONARQUBE') {
            steps {
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=24930942mM -Dmaven.test.skip=true';
            }
        }
         stage('MVN NEXUS') {
            steps {
                sh 'mvn deploy -Dmaven.test.skip=true';
            }
        }

    }
}
