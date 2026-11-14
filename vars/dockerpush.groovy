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
            echo "\$Avishkar@hub" | docker login \
            -u "\$avishkarrthorave" --password-stdin

            docker push ${'avishkarrthorave/three-tier-notes-app'}:${tag}

            docker logout
        """
    }

    echo "Docker image pushed successfully!"
}
