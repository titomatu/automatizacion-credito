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
       stage('Despliegue en QA'){
            //when{branch 'development'}
            steps {
                sh 'java -jar /Users/titoandres-maturana/.jenkins/workspace/solicitud-credito/webapp-automatizacion-credito/solicitud-service/target/solicitud-service-0.0.1-SNAPSHOT.jar'
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
