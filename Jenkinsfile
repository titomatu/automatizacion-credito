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
                sh 'mvn clean install -pl centrales-service'
                sh 'mvn test -Dtest=CentralesServiceTest -pl centrales-service'
            }
       }
       stage('Pruebas Integración'){
            steps {
                sh 'mvn test -Dtest=ReglasNegocioControllerTest -pl motor-reglas-service'
            }
       }
       stage('Reporte SonarQube'){
            steps {
                sh 'mvn sonar:sonar -Dsonar.projectKey=preoferta_credito_automatizado -Dsonar.host.url=http://localhost:9000 -Dsonar.login=af14b5e072531448ab9a3d64145a1b0e76ec06f9'
            }
       }
       stage('Build Contenedores de la Aplicación'){
            when{branch 'development'}
            ///Applications/Docker.app/Contents/Resources/bin/
            steps {
                dir("solicitud-service") {
                    sh '/Applications/Docker.app/Contents/Resources/bin/docker build -t tamatu/solicitud-service .'
                }

                dir("aportes-linea-service") {
                    sh '/Applications/Docker.app/Contents/Resources/bin/docker build -t tamatu/aportes-linea-service .'
                }

                dir("auth-service") {
                    sh '/Applications/Docker.app/Contents/Resources/bin/docker build -t tamatu/auth-service .'
                }

                dir("centrales-service") {
                    sh '/Applications/Docker.app/Contents/Resources/bin/docker build -t tamatu/centrales-service .'
                }

                dir("email-service") {
                    sh '/Applications/Docker.app/Contents/Resources/bin/docker build -t tamatu/email-service .'
                }

                dir("estudio-solicitud-service") {
                    sh '/Applications/Docker.app/Contents/Resources/bin/docker build -t tamatu/estudio-solicitud-service .'
                }

                dir("motor-reglas-service") {
                    sh '/Applications/Docker.app/Contents/Resources/bin/docker build -t tamatu/motor-reglas-service .'
                }

                dir("registraduria-service") {
                    sh '/Applications/Docker.app/Contents/Resources/bin/docker build -t tamatu/registraduria-service .'
                }

                dir("solicitud-credito") {
                    sh '/Applications/Docker.app/Contents/Resources/bin/docker build -t tamatu/solicitud-credito .'
                }
            }
       }
       stage('Push Contenedores de la Aplicación'){
            when{branch 'development'}
            steps {
                sh '/Applications/Docker.app/Contents/Resources/bin/docker push tamatu/solicitud-service:latest'
                sh '/Applications/Docker.app/Contents/Resources/bin/docker push tamatu/aportes-linea-service:latest'
                sh '/Applications/Docker.app/Contents/Resources/bin/docker push tamatu/auth-service:latest'
                sh '/Applications/Docker.app/Contents/Resources/bin/docker push tamatu/centrales-service:latest'
                sh '/Applications/Docker.app/Contents/Resources/bin/docker push tamatu/email-service:latest'
                sh '/Applications/Docker.app/Contents/Resources/bin/docker push tamatu/estudio-solicitud-service:latest'
                sh '/Applications/Docker.app/Contents/Resources/bin/docker push tamatu/motor-reglas-service:latest'
                sh '/Applications/Docker.app/Contents/Resources/bin/docker push tamatu/registraduria-service:latest'
                sh '/Applications/Docker.app/Contents/Resources/bin/docker push tamatu/solicitud-credito:latest'
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