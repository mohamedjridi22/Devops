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
        SONAR_PASSWORD = "123"
        NEXUS_REPOSITORY = "Devops_Project"
        DOCKER_IMAGE_NAME = "jridimohamed/springboot_devops:latest"
        DOCKER_FRONT_IMAGE_NAME = "jridimohamed/devops_angular:latest"
	
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
    }
	
}
