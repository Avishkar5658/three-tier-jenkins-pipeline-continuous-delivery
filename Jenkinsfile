
pipeline {

    agent {
        label 'dev-server'
    }

    options {
        timestamps()
    }

    stages {

        // Stage 1: Checkout source code
        stage('Git Checkout') {
            steps {
                echo 'Downloading application from GitHub'

                checkout scm
            }
        }

        // Stage 2: Validate configuration
        stage('Validate Application') {
            steps {
                echo 'Checking application files'

                sh '''
                    test -f Dockerfile
                    test -f docker-compose.yml
                    test -f .env

                    docker --version
                    docker compose version

                    docker compose config -q
                '''
            }
        }

        // Stage 3: Build Docker images
        stage('Docker Build') {
            steps {
                echo 'Building application images'

                sh '''
                    docker compose build
                '''
            }
        }

        // Stage 4: Deploy application
        stage('Deploy Application') {
            steps {
                echo 'Deploying three-tier application'

                sh '''
                    docker compose up -d --build --remove-orphans
                '''
            }
        }

        // Stage 5: Verify deployment
        stage('Verify Deployment') {
            steps {
                echo 'Checking running containers'

                sh '''
                    docker compose ps
                    docker ps
                '''
            }
        }
    }

    post {

        success {
            echo 'Three-tier application deployed successfully!'
        }

        failure {
            echo 'Deployment failed. Checking application logs.'

            sh '''
                docker compose logs --tail=100 || true
            '''
        }

        always {
            echo 'Jenkins pipeline execution completed.'
        }
    }
}
