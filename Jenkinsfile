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
                git branch :'Sleh'  ,
                url : 'https://github.com/mohamedjridi22/Devops.git'
            }
        }

        stage ('MVN clean'){
            steps{
                sh 'mvn clean';
           }    
        }
       
        stage('MVN compile'){
            steps{
                sh'mvn compile';
            }
        }
        stage('MVN SONARQUBE') {
            steps {
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=123123Abc? -Dmaven.test.skip=true';
            }
        }
        
    }
}
