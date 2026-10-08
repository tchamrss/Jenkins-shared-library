#!/user/bin/env/ groovy

def call(){
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'DOCKER_HUB_PASSWORD', usernameVariable: 'DOCKER_HUB_USERNAME')]) {
        sh 'docker build -t tchamrss/demo-app:jma-2.0 .'
        sh 'echo $DOCKER_HUB_PASSWORD | docker login -u $DOCKER_HUB_USERNAME --password-stdin'
        sh 'docker push tchamrss/demo-app:jma-2.0'
    }
}
