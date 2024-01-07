
pipeline {
   tools {
     maven 'maven'
   }

  environment {
    dockerimagename = "ayga/gateway-app"
    dockerImage = ""
  }

  agent any

  stages {

    stage('Checkout Source') {
      steps {
				sh 'git config --global core.compression 0'
        git url: 'https://github.com/FSTT-Disaster-Aid-App/Gateway-Microservice.git', branch: 'main'
				sh 'mvn clean package'
      }
    }

    stage('Build image') {
      steps{
        script {
          dockerImage = docker.build dockerimagename
        }
      }
    }

    stage('Pushing Image') {
      environment {
               registryCredential = 'dockerhublogin'
           }
      steps{
        script {
          docker.withRegistry( 'https://registry.hub.docker.com', registryCredential ) {
            dockerImage.push("latest")
          }
        }
      }
    }

    stage('Deploying App to Kubernetes') {
      steps {
        script {
        kubernetesDeploy(configs: "gateway-service.yaml", kubeconfigId: "kubernetes")
        }
      }
    }

  }

}
