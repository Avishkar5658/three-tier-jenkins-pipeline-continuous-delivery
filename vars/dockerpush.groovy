def call(String credentialsId, String imageName, String tag) {

    echo "Logging into Docker Hub..."

    withCredentials([
        usernamePassword(
            credentialsId: credentialsId,
            usernameVariable: 'avishkarrthorave',
            passwordVariable: 'Avishkar@hub'
        )
    ]) {

        sh """
            echo "\$DOCKER_PASS" | docker login \
            -u "\$DOCKER_USER" --password-stdin

            docker push ${avishkarrthorave/three-tier-notes-app}:${tag}

            docker logout
        """
    }

    echo "Docker image pushed successfully!"
}
