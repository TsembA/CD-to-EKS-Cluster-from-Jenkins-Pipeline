#!/usr/bin/env groovy

pipeline {
    agent any
    stages {
        stage('build app') {
            steps {
                script {
                    echo "building the application..."
                }
            }
        }
        stage('build image') {
            steps {
                script {
                    echo "building the docker image..."
                }
            }
        }
        stage('deploy') {
            environment {
                AWS_ACCESS_KEY_ID = credentials('aws_access_key_id')
                AWS_SECRET_ACCESS_KEY = credentials('aws_secret_access_key')
            }
            steps {
                script {
                    echo 'deploying docker image...'
                    // For deploy to LKE cluster use "withKubeConfig([credentialsId: 'lke-credentials', serverUrl 'https://<server-url>'])"
                    sh '''
                        kubectl create deployment nginx-deployment --image=nginx
                        kubectl expose deployment nginx-deployment \
                          --type=LoadBalancer \
                          --name=nginx-service \
                          --port=80 \
                          --target-port=80
                    '''
                }
            }
        }
    }
}
