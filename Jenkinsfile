pipeline {
    agent any
    


    environment {
        NEXUS_VERSION = "nexus3"
        NEXUS_PROTOCOL = "http"
        NEXUS_URL = "192.168.33.10:8081"
        SONAR_SERVER_URL = "http://192.168.33.10:9000/"
        PROJECT_NAME = "devopsBackend"
        PROJECT_KEY = "devopsBackend"
        SONAR_USERNAME = "admin"
        SONAR_PASSWORD = "fatma10828708"
        NEXUS_REPOSITORY = "Devops_Project"
        DOCKER_IMAGE_NAME = "fatmabenabdelaziz/springboot_devops:latest"
        DOCKER_FRONT_IMAGE_NAME = "fatmabenabdelaziz/devops_angular:latest"
	
    }

    stages {
        stage('Checkout Backend code') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/Fatma']], userRemoteConfigs: [[url: 'https://github.com/mohamedjridi22/Devops.git']]])
            }
        }
	 stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                echo 'hello'
            }
        }
    
		stage("Create SonarQube Project") {
            steps {
                script {
                    def sonarServerUrl = "http://192.168.33.10:9000/"
                    def projectName = "devopsBackend"
                    def projectKey = "devopsBackend"

                    sh """
                        curl -X POST "${sonarServerUrl}/api/projects/create?name=${projectName}&project=${projectKey}"
                    """
                }
            }
        }
	         stage("Run SonarQube Analysis") {
            steps {
                script {
                    withSonarQubeEnv('sonrserver') {
                        def sonarUsername = "admin"
                        def sonarPassword = "fatma10828708"

                        withSonarQubeEnv(credentialsId: 'sonartoken') {
                            sh """
                                set +x
                                mvn sonar:sonar -Dsonar.projectKey=devopsBackend
                                set -x
                            """
                        }
                        echo 'Static Analysis Completed'
                    }
                }
            }
        }
	    
	
}
}
