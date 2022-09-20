pipeline {
    agent any
    tools {
        maven 'maven-3.8.6'
    }

    environment {
        DOCKERHUB_CREDENTIALS=credentials('0d3afa65-4dbb-4bd5-bba4-a41c814120d5')
    }

    stages {
       stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
       }
       stage('Pruebas Unitarias'){
            steps {
                sh 'mvn test -Dtest=CentralesServiceTest -pl centrales-service'
            }
       }
       stage('Pruebas Integración'){
            steps {
                sh 'mvn test -Dtest=ReglasNegocioControllerTest -pl motor-reglas-service'
            }
       }
       stage('Build Contenedores de la Aplicación'){
            //when{branch 'development'}
            ///Applications/Docker.app/Contents/Resources/bin/
            steps {
                dir("solicitud-service") {
                    sh '/Applications/Docker.app/Contents/Resources/bin/docker build -t tamatu/solicitud-service .'
                }
            }
       }
       stage('Push Contenedores de la Aplicación'){
            //when{branch 'development'}
            steps {
                sh '/Applications/Docker.app/Contents/Resources/bin/docker push tamatu/solicitud-service:latest'
            }
       }
    }

    post{
        success{
            setBuildStatus("Build succeeded", "SUCCESS");
        }

        failure {
            setBuildStatus("Build failed", "FAILURE");
        }

		always {
			sh 'docker logout'
		}
    }
}

void setBuildStatus(String message, String state) {
    step([
        $class: "GitHubCommitStatusSetter",
        reposSource: [$class: "ManuallyEnteredRepositorySource", url: "https://https://github.com/titomatu/automatizacion-credito"],
        contextSource: [$class: "ManuallyEnteredCommitContextSource", context: "ci/jenkins/build-status"],
        errorHandlers: [[$class: "ChangingBuildStatusErrorHandler", result: "UNSTABLE"]],
        statusResultSource: [$class: "ConditionalStatusResultSource", results: [[$class: "AnyBuildResult", message: message, state: state]]]
    ]);
}