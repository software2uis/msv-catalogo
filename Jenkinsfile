pipeline {
  agent any
 parameters {
        string(name: 'CONTAINER_NAME', defaultValue: 'msv-catalago', description: 'nombre del docker')
        string(name: 'IMAGE_NAME', defaultValue: 'msv-catalogo', description: 'nombre de la imagen')
        string(name: 'IMAGE_TAG', defaultValue: 'latest', description: 'etiqueta de la imagen')
        string(name: 'IMAGE_PORT', defaultValue: '8081', description: 'puerto a publicar')
    }
    environment {
        FINAL_NAME = "${CONTAINER_NAME}${IMAGE_TAG}${IMAGE_PORT}"
        DOCKERHUB_CREDENTIALS = 'docker'
        w="majority"
        appName="Software2"
        imageName="${USERNAME}/${IMAGE_NAME}:${IMAGE_TAG}"
        MONGODBURI = "mongodb+srv://${appName}:*M36VX7q-6hTbRY@software2.i7mel.mongodb.net/Catalogo?retryWrites=true&w=${w}&appName=${appName}"
        USERNAME = 'sebstiian'
    }
    stages {
          stage('stop/rm') {

            when {
                expression { 
                    DOCKER_EXIST = sh(returnStdout: true, script: 'echo "$(docker ps -q --filter name=${FINAL_NAME})"').trim()
                    return  DOCKER_EXIST != '' 
                }
            }
            steps {
                script{
                    sh ''' 
                         docker stop ${FINAL_NAME}
                    '''
                    }
                    
                }                    
                                  
            }
           
        stage('build') {
            steps {
                script {
                    // Load Docker Hub credentials from Jenkins credentials store
                    withCredentials([usernamePassword(credentialsId: DOCKERHUB_CREDENTIALS, usernameVariable: 'DOCKERHUB_USERNAME', 
passwordVariable: 'DOCKERHUB_PASSWORD')]) {
                        // Login to Docker Hub
                        sh "docker login -u ${DOCKERHUB_USERNAME} -p ${DOCKERHUB_PASSWORD}"
                        // Build Docker image
                        sh "docker build -t ${DOCKERHUB_USERNAME}/${IMAGE_NAME}:${IMAGE_TAG} ."
                        // Tag the Docker image
                        sh "docker tag ${DOCKERHUB_USERNAME}/${IMAGE_NAME}:${IMAGE_TAG} index.docker.io/${DOCKERHUB_USERNAME}/${IMAGE_NAME}:${IMAGE_TAG}"
                        // Push Docker image to Docker Hub
                        sh "docker push index.docker.io/${DOCKERHUB_USERNAME}/${IMAGE_NAME}:${IMAGE_TAG}"
                    }
                }
            }
        }

                   
                                  
            
        stage('run') {
            steps {
                script{
                    sh "docker images"
                    sh "docker run -dp ${IMAGE_PORT}:8081 \
                     -e MONGODBURI=${MONGODBURI} \
                        ${imageName} "

                    }
                    
                }                    
                                  
            
            
          
        }

    }
}
