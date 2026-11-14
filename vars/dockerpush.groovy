def call(String credentialsId, String imageName, String tag) {

    echo "Logging into Docker Hub..."

    withCredentials([
        usernamePassword(
            credentialsId: credentialsId,
            usernameVariable: 'DOCKER_USER',
            passwordVariable: 'DOCKER_PASS'
        )
    ]) {

        sh """
            echo "\$DOCKER_PASS" | docker login \
            -u "\$DOCKER_USER" --password-stdin

            docker push ${imageName}:${tag}

            docker logout
        """
    }

    echo "Docker image pushed successfully!"
}
