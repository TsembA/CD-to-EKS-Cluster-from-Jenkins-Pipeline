def buildJar() {
    echo 'building the application...'
    sh 'mvn package'
}

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t tsemb/demo-app:jma-2.0.0 .'
        sh 'echo $PASS | docker login -u $USER --password-stdin'
        sh 'docker push tsembn/demo-app:jma-2.0.0'
    }
}

def deployApp() {
    echo 'deploying the application...'
}

return this
