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
                git branch :'Yosra'  ,
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
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar -Dmaven.test.skip=true';
            }
        }
        stage('MVN NEXUS') {
            steps {
                sh 'mvn deploy -Dmaven.test.skip=true';
            }
        }
        stage('Building Docker image') {
	   steps {
                // Étape du build de l'image docker de l'application spring boot
		 script {
			// Generating image from Dockerfile
			  sh 'docker build -t yosrasmida/gestion-station-ski-1.0.jar .'
			}
		 }
	    }
        stage('Docker compose') {
            steps {
                sh 'docker-compose -f docker-compose.yml up -d --build'
            }
        }
    }
}
