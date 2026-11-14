def call() {

    echo "Deploying application to AWS EC2..."

    sshagent(['ubuntu-key']) {

        sh '''
            ssh -o "path/to/private-key.pem" \
            ubuntu@ec2-13-234-231-59.ap-south-1.compute.amazonaws.com '
                cd /home/ubuntu/three-tier-app &&
                export IMAGE_TAG=$BUILD_NUMBER &&
                docker compose pull django_app &&
                docker compose up -d
            '
        '''
    }

    echo "Application deployment completed!"
}
