pipeline{
    agent any
    environment{
        DOCKER IMAGE ="pa1cherry/mmv2-currency-exchange-service:latest"
    }
    stages{
        stage('Checkout Code'){
            steps{
                checkout scm
            }
        }
        stage('Build with maven'){
            steps{
               script{
                  sh "mvn clean package -DskipTests"
               }
             }
        }
        stage('Build Docker Image'){
            steps{
                script{
                    sh "docker build -t pa1cherry/mmv2-currency-exchange-service:0.0.2 ."
                }
            }
        }
        stage('Push to Docker Hub'){
            steps{
                script{
                    sh 'docker push pa1cherry/mmv2-currency-exchange-service:0.0.2 '
                }
            }
        }
        stage('Cleanup'){
            steps{
                script{
                    sh 'docker rmi $DOCKER_IMAGE'
                }
            }
        }
    }
}