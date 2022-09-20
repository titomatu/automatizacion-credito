pipeline {
    agent any
    tools {
        maven 'maven-3.8.6'
        docker 'docker-latest'
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
            steps {
                script{
                    sh 'cd solicitud-service/'
                    sh 'docker build -t tamatu/solicitud-service .'
                }
                script{
                    sh 'cd motor-reglas-service/'
                    sh 'docker build -t tamatu/motor-reglas-service .'
                }
            }
       }
       stage('Push Contenedores de la Aplicación'){
            //when{branch 'development'}
            steps {
                sh 'Push Contenedores'
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
