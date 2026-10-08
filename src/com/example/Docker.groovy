package com.example

class Docker implements Serializable {
    def script
    Docker(script){
        this.script = script
    }

def buildDockerImage(String imageName){
    script.echo "building the docker image..."
    script.withCredentials([script.usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'DOCKER_HUB_PASSWORD', usernameVariable: 'DOCKER_HUB_USERNAME')]) {
        script.sh "docker build -t ${imageName} ."
        script.sh "echo '\${DOCKER_HUB_PASSWORD}' | docker login -u '\${DOCKER_HUB_USERNAME}' --password-stdin"
        script.sh "docker push ${imageName}"
    }
}
}