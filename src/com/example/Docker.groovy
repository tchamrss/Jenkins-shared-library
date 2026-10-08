
package com.example

class Docker implements Serializable {

    def script

    Docker(script) {
        this.script = script
    }

    def buildDockerImage(String imageName) {
        script.echo "building the docker image..."
        script.sh "docker build -t ${imageName} ."
        }

    def dockerLogin() {

        script.withCredentials([
                script.usernamePassword(
                        credentialsId: 'docker-hub-repo',
                        passwordVariable: 'DOCKER_HUB_PASSWORD',
                        usernameVariable: 'DOCKER_HUB_USERNAME'
                )
        ]) {

            script.sh """
                echo "\${DOCKER_HUB_PASSWORD}" | docker login \
                -u "\${DOCKER_HUB_USERNAME}" \
                --password-stdin
            """
        }
    }
    def dockerPush(String imageName) {
        script.sh "docker push ${imageName}"
    }
}
