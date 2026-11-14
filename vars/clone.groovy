def call(String repoUrl, String branch) {

    echo "Cloning application repository..."

    git branch: branch,
        url: repoUrl

    echo "Repository cloned successfully!"
}
