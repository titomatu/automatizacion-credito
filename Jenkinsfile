pipeline {
    agent any
    tools {
        maven 'maven-3.8.6'
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
                dir("aportes-linea-service") {
                    sh '/Applications/Docker.app/Contents/Resources/bin/docker build -t tamatu/aportes-linea-service .'
                }
            }
       }
       stage('Push Contenedores de la Aplicación'){
            //when{branch 'development'}
            steps {
                sh '/Applications/Docker.app/Contents/Resources/bin/docker push tamatu/solicitud-service:latest'
            }
            steps {
                sh '/Applications/Docker.app/Contents/Resources/bin/docker push tamatu/aportes-linea-service:latest'
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