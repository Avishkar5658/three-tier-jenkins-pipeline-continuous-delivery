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
            echo "\$avishkarrthorave" | docker login \
            -u "\$Avishkar@hub" --password-stdin

            docker push ${'avishkarrthorave/three-tier-notes-app'}:${tag}

            docker logout
        """
    }

    echo "Docker image pushed successfully!"
}
