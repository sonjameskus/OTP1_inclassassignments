pipeline {
    agent any
    tools {
        maven 'Maven3'
    }

    environment {
        PATH = "C:\\Program Files\\Docker\\Docker\\resources\\bin;${env.PATH}"
        DOCKERHUB_CREDENTIALS_ID = 'sinyalohis'
        DOCKERHUB_REPO = 'sinyalohis/otp1_inclass'
        DOCKER_IMAGE_TAG = 'v1'
    }
    stages {
        stage ('check'){
            steps{
                git 'https://github.com/sonjameskus/OTP1_inclassassignments.git'
            }
        }
        stage ('build'){
            steps{
                bat 'mvn clean install'
            }
        }

        stage('test') {
            steps{
                bat 'mvn test'
            }
        }
        stage('jacoco'){
            steps{
                jacoco()
            }
        }

        stage('Build Docker Image') {
            steps {
                bat '''
                    echo ===== WHO AM I =====
                    whoami

                    echo ===== PATH =====
                    echo %PATH%

                    echo ===== FIND DOCKER =====
                    where.exe docker

                    echo ===== DOCKER VERSION =====
                    docker --version

                    echo ===== DOCKER INFO =====
                    docker info

                    echo ===== BUILD =====
                    docker build -t "sinyalohis/otp1_inclass:v1" .
                '''
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}")
                }
            }
        }


        stage('Push Docker Image to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', DOCKERHUB_CREDENTIALS_ID) {
                        docker.image("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}").push()
                    }
                }
            }
        }

    }
}